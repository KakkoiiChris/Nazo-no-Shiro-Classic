// Christian Alexander, 5/12/11, Pd. 6
package kakkoiichris.nazonoshiro;

import kakkoiichris.nazonoshiro.castle.Castle;
import kakkoiichris.nazonoshiro.castle.Direction;
import kakkoiichris.nazonoshiro.castle.room.EnemyRoom;
import kakkoiichris.nazonoshiro.castle.room.PuzzleRoom;
import kakkoiichris.nazonoshiro.fighter.*;
import kakkoiichris.nazonoshiro.item.Coin;
import kakkoiichris.nazonoshiro.item.HealthPack;
import kakkoiichris.nazonoshiro.item.Item;
import kakkoiichris.nazonoshiro.item.Weapon;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Main {
    private static int row = 1; //the row you are located in
    private static int column = 2; //the column you are located in
    private static int floor = 0; //the floor you are located in
    private static int rowLast = 1; //stores previous row for restart checkpoint
    private static int columnLast = 2; //stores previous column for restart checkpoint
    private static int floorLast = 0; //stores previous floor for restart checkpoint
    
    private static Direction lastDirection = Direction.NONE;
    
    private static final SaveFileCreator saver = new SaveFileCreator();
    
    private static boolean ran = false, ranLast = false; //keeps track of if you run from a fight
    
    private static final Enemy[] guards = { //collection of all enemies
        new Ninja(),
        new Samurai(),
        new Daimyo(),
        new Shogun(),
        new Imperial()
    };
    
    private static final List<Weapon> weapons = new ArrayList<>(); //stores all possible weapons
    
    private static Castle castle;
    private static Self self = new Self(); //this fighter represents you
    
    public static void main(String[] argv) {
        mainMenu();
        
        storeState();
        
        explore();
    }
    
    private static void mainMenu() {
        var fileName = (Math.random() > 0.001) ? "title" : "tlite";
        
        var title = Resources.getString(fileName);
        var splash = Util.getRandom(Resources.getLines("splashText"));
        
        System.out.printf(title, splash);
        
        var inMenu = true;
        
        while (inMenu) {
            System.out.print("""
                1) New Game
                2) Load Game
                3) Options
                4) Credits
                5) Quit
                
                >\040""".stripIndent());
            
            var first = Util.input.nextLine().trim().toLowerCase();
            
            if (first.matches("1|(new(\s+game)?)")) {
                setUpNew();
                
                inMenu = false;
            }
            else if (first.matches("2|(load(\s+game)?)")) {
                setUpLoad();
                
                inMenu = false;
            }
            else if (first.matches("3|options")) {
                options();
            }
            else if (first.matches("4|credits")) {
                credits();
            }
            else if (first.matches("5|quit")) {
                System.out.println("Thanks for playing!");
                
                System.exit(0);
            }
            else {
                System.out.println("I don't know how to do that...\n");
            }
        }
    }
    
    private static void setUpNew() {
        System.out.print("Map Name > ");
        
        var mapName = Util.input.nextLine();
        
        System.out.println();
        
        castle = switch (mapName) {
            case "Original Castle", "" -> new Castle("original");
            
            default -> throw new RuntimeException("NO CASTLE");
        };
        
        var items = new ArrayList<Item>();
        
        for (var i = 0; i < Util.random.nextInt(1_000); i++) {
            items.add(Coin.random());
        }
        
        for (var j = 0; j < Util.random.nextInt(250); j++) {
            items.add(HealthPack.HERB);
        }
        
        for (var k = 0; k < Util.random.nextInt(100); k++) {
            items.add(HealthPack.BUSHEL);
        }
        
        Util.shuffle(items);
        
        distributeItems(items);
        
        self.getInventory().add(Weapon.KATANA);
        self.getInventory().add(Weapon.TANTO);
        
        weapons.addAll(List.of(Weapon.values()));
        
        for (var guard : guards) {
            guard.setDrop(self, weapons);
        }
        
        introduction();
    }
    
    private static void distributeItems(List<Item> items) {
        while (!items.isEmpty()) {
            castle.distributeItems(items);
        }
    }
    
    private static void setUpLoad() {
        System.out.print("File Name > ");
        
        var fileName = Util.input.nextLine();
        
        var lines = Resources.getLines(fileName);
    }
    
    private static void options() {
    }
    
    private static void credits() {
        var lines = Resources.getLines("credits");
        
        lines.forEach(System.out::println);
        
        System.out.println();
    }
    
    private static void introduction() {
        var source = Resources.getLines("intro");
        
        var script = new Script(source);
        
        var vars = script.run();
        
        self = new Self();
    }
    
    private static void explore() {
        while (true) {
            var room = castle.get(floor, row, column);
            
            System.out.printf("%s%n%n", room);
            
            if (room instanceof EnemyRoom e && !e.getEnemy().isDead()) {
                if (fight(e.getEnemy())) {
                    var drop = e.getEnemy().getDrop();
                    
                    if (drop == null) {
                        drop = Coin.random();
                    }
                    
                    System.out.println("""
                        The %s dropped something...
                        
                        It's a %s!
                        
                        They also dropped a key. Now to
                        find which door it unlocks...
                        """.formatted(e.getEnemy(), drop).stripIndent());
                    
                    self.setKey(room.getKey());
                }
                else if (ran) {
                    System.out.println("That was a close one...");
                }
                else {
                    System.out.println("You died...");
                    
                    resetState();
                }
                
                continue;
            }
            
            var timing = room.isVisited() ? "then" : "first";
            
            room.setVisited();
            
            var fileName = "%s%d-%d-%d".formatted(timing, row, column, floor);
            
            var description = Resources.tryGetLines(fileName).orElse(List.of("Default room text."));
            
            description.forEach(System.out::println);
            
            for (var wall : room.getWalls().values()) {
                System.out.printf("There is a %s to the %s.%n", wall.getStorage().getName(), wall.getDirection());
            }
            
            System.out.print(" > ");
            
            var choice = Util.input.nextLine().toLowerCase();
            
            System.out.println();
            
            var matcher = Pattern.compile("play( puzzle)?|solve( puzzle)?").matcher(choice);
            
            if (matcher.find() && room instanceof PuzzleRoom p && !p.getPuzzle().isWon()) {
                if (p.getPuzzle().play()) {
                    System.out.println("""
                        You won!
                        
                        You have earned a key.
                        
                        Now to figure out which door it unlocks...
                        """.stripIndent());
                    
                    self.setKey(p.getKey());
                }
                else {
                    System.out.println("""
                        The puzzle clicks and whirs away,
                        and just like that, the puzzle is
                        reset. Better luck next time...""");
                }
                
                continue;
            }
            
            matcher = Pattern.compile("((play |show )inventory)|e").matcher(choice);
            
            if (matcher.find()) {
                showInventory();
                
                continue;
            }
            
            matcher = Pattern.compile("search (\\w+)").matcher(choice);
            
            if (matcher.find()) {
                var storageName = matcher.group(1);
                var direction = Direction.NONE;
                
                for (var wall : room.getWalls().values()) {
                    if (wall.getStorage().getName().toLowerCase().equals(storageName)) {
                        direction = wall.getDirection();
                    }
                }
                
                room.look(direction, self);
                
                continue;
            }
            
            matcher = Pattern.compile("go (\\w+)").matcher(choice);
            
            if (matcher.find()) {
                storeState();
                
                var direction = matcher.group(1);
                
                if (Direction.isValid(direction)) {
                    move(Direction.valueOf(direction.toUpperCase()));
                }
                else {
                    System.out.printf("'%s' is not a valid direction.", direction);
                }
                
                continue;
            }
            
            if (choice.equals("skip")) {
                self.setKey(room.getKey());
                
                continue;
            }
            
            if (choice.equals("w")) {
                storeState();
                
                move(Direction.NORTH);
                
                continue;
            }
            
            if (choice.equals("a")) {
                storeState();
                
                move(Direction.WEST);
                
                continue;
            }
            
            if (choice.equals("s")) {
                storeState();
                
                move(Direction.SOUTH);
                
                continue;
            }
            
            if (choice.equals("d")) {
                storeState();
                
                move(Direction.EAST);
                
                continue;
            }
            
            if (choice.equals("r")) {
                storeState();
                
                move(Direction.UP);
                
                continue;
            }
            
            if (choice.equals("f")) {
                storeState();
                
                move(Direction.DOWN);
                
                continue;
            }
            
            if (choice.equals("save")) {
                saver.openFile();
                
                System.out.print("Saving");
                
                //saver.addData(floors, guards, self, row, column, floor, yourTurn);
                
                saver.closeFile();
                
                for (var i = 0; i < (int) (Math.random() * 5) + 3; i++) {
                    System.out.print(".");
                    
                    Util.pause(1);
                }
                
                System.out.println("\nSaved.\n");
                
                continue;
            }
            
            if (choice.equals("snq")) {
                saver.openFile();
                
                System.out.print("Saving");
                
                //saver.addData(floors, guards, self, row, column, floor, yourTurn);
                
                saver.closeFile();
                
                for (var i = 0; i < (int) (Math.random() * 5) + 3; i++) {
                    System.out.print(".");
                    
                    Util.pause(1);
                }
                
                System.out.println("\nSaved.\nThanks for playing.");
                
                break;
            }
            
            if (choice.equals("quit")) {
                break;
            }
            
            System.out.printf("\nI don't recognize the phrase '%s'.%n%n > ", choice);
        }
    }
    
    private static boolean fight(Enemy enemy) {
        var directHit = Resources.getLines("directHit");
        var indirectHit = Resources.getLines("indirectHit");
        var missHit = Resources.getLines("missHit");
        var directBlock = Resources.getLines("directBlock");
        var indirectBlock = Resources.getLines("indirectBlock");
        var missBlock = Resources.getLines("missBlock");
        
        System.out.printf("The %s stands before you.%n%n", enemy);
        
        //determines who's attacking and who's defending in battle
        boolean yourTurn = Math.random() >= 0.5;
        
        ran = false;
        
        while (!self.isDead() && !enemy.isDead() && !ran) {
            self.showHP();
            enemy.showHP();
            
            if (yourTurn) {
                self.allAffect();
                self.filter();
                
                System.out.print("(Attack/Use/Run)\n\n> ");
                
                var action = Util.input.nextLine().toLowerCase();
                
                System.out.println();
                
                if (action.matches("a(ttack)?")) {
                    self.attack(enemy, directHit, indirectHit, missHit);
                }
                else if (action.matches("u(se)?")) {
                    self.use(enemy);
                }
                else if (action.matches("r(un)?")) {
                    ran = run(enemy);
                }
                else {
                    System.out.println("Can't do that...");
                    
                    continue;
                }
            }
            else {
                enemy.use(self);
                enemy.allAffect();
                enemy.filter();
                
                enemy.attack(self, directBlock, indirectBlock, missBlock);
            }
            
            yourTurn = !yourTurn;
            
            if (ran || self.isDead()) {
                return false;
            }
            
            Util.pause(2);
        }
        
        return true;
    }
    
    private static boolean run(Fighter enemy) {
        var speedDiff = ((double) self.getSpeed() / enemy.getSpeed()) * 50;
        
        var runChance = Math.random() * 100;
        
        var lastRow = row;
        var lastColumn = column;
        
        move(lastDirection.getInverse());
        
        if (row == lastRow && column == lastColumn) {
            System.out.println("You've been cornered.\n");
            
            return false;
        }
        
        if (speedDiff > runChance) {
            System.out.println("You made a clean getaway.\n");
            
            return true;
        }
        
        System.out.println("You've been cut off.\n");
        
        row = lastRow;
        column = lastColumn;
        
        return false;
    }
    
    private static void move(Direction direction) {
        lastDirection = direction;
        
        var floorLast = floor;
        var rowLast = row;
        var columnLast = column;
        
        switch (direction) {
            case UP -> floor = goUp();
            
            case DOWN -> floor = goDown();
            
            case NORTH -> row = goNorth();
            
            case SOUTH -> row = goSouth();
            
            case EAST -> column = goEast();
            
            case WEST -> column = goWest();
        }
        
        if (floor == floorLast && row == rowLast && column == columnLast) {
            lastDirection = Direction.NONE;
        }
    }
    
    private static boolean northernMost() {
        return row == 0;
    }
    
    private static boolean southernMost() {
        return row == castle.getRows() - 1;
    }
    
    private static boolean easternMost() {
        return column == castle.getColumns() - 1;
    }
    
    private static boolean westernMost() {
        return column == 0;
    }
    
    private static boolean upperMost() {
        return floor == castle.getFloors() - 1;
    }
    
    private static boolean lowerMost() {
        return floor == 0;
    }
    
    private static boolean hasVisited(int floor, int row, int column) {
        return castle.get(floor, row, column).isVisited();
    }
    
    private static boolean hasWall(Direction direction) {
        return castle.get(floor, row, column).hasWall(direction);
    }
    
    private static int goNorth() {
        if (hasWall(Direction.NORTH) || northernMost()) {
            if (column == 4 && hasVisited(0, 2, 4)) {
                endGame();
            }
            
            System.out.println("A wall blocks your path.\n");
            
            return row;
        }
        
        if (self.getKey() >= castle.get(floor, row - 1, column).getLock()) {
            if (!hasVisited(floor, row - 1, column)) {
                System.out.println("The door is unlocked.\n");
            }
            
            return row - 1;
        }
        
        System.out.println("None of the keys you have fit that lock.\n");
        
        return row;
    }
    
    private static int goSouth() {
        if (hasWall(Direction.SOUTH) || southernMost()) {
            System.out.println("A wall blocks your path.\n");
            
            return row;
        }
        
        if (self.getKey() >= castle.get(floor, row + 1, column).getLock()) {
            if (!hasVisited(floor, row + 1, column)) {
                System.out.println("The door is unlocked.\n");
            }
            
            return row + 1;
        }
        
        System.out.println("None of the keys you have fit that lock.\n");
        
        return row;
    }
    
    private static int goEast() {
        if (hasWall(Direction.EAST) || easternMost()) {
            System.out.println("A wall blocks your path.\n");
            
            return column;
        }
        
        if (self.getKey() >= castle.get(floor, row, column + 1).getLock()) {
            if (!hasVisited(floor, row, column + 1)) {
                System.out.println("The door is unlocked.\n");
            }
            
            return column + 1;
        }
        
        System.out.println("None of the keys you have fit that lock.\n");
        
        return column;
    }
    
    private static int goWest() {
        if (hasWall(Direction.WEST) || westernMost()) {
            System.out.println("A wall blocks your path.\n");
            
            return column;
        }
        
        if (self.getKey() >= castle.get(floor, row, column - 1).getLock()) {
            if (!hasVisited(floor, row, column - 1)) {
                System.out.println("The door is unlocked.\n");
            }
            
            return column - 1;
        }
        
        System.out.println("None of the keys you have fit that lock.\n");
        
        return column;
    }
    
    private static int goUp() {
        if (hasWall(Direction.UP) || upperMost()) {
            System.out.println("The ceiling blocks your path.\n");
            
            return floor;
        }
        
        if (self.getKey() >= castle.get(floor + 1, row, column).getLock()) {
            if (!hasVisited(floor + 1, row, column)) {
                System.out.println("The hatch is unlocked.\n");
            }
            
            return floor + 1;
        }
        
        System.out.println("None of the keys you have fit that lock.\n");
        
        return floor;
    }
    
    private static int goDown() {
        if (hasWall(Direction.DOWN) || lowerMost()) {
            System.out.println("The floor blocks your path.\n");
            
            return floor;
        }
        
        if (self.getKey() >= castle.get(floor - 1, row, column).getLock()) {
            if (!hasVisited(floor - 1, row, column)) {
                System.out.println("The hatch is unlocked.\n");
            }
            
            return floor - 1;
        }
        
        System.out.println("None of the keys you have fit that lock.\n");
        
        return floor;
    }
    
    private static void showInventory() {
        int w = 0, h = 0, b = 0, c = 0, n = 0;
        
        for (var i = 0; i < self.getInventory().size(); i++) {
            switch (self.getInventory().get(i).getName()) {
                case "herb" -> h++;
                
                case "herb bushel" -> b++;
                
                case "coin" -> c++;
                
                case "note" -> n++;
                
                default -> w++;
            }
        }
        
        var selection = "";
        
        while (!selection.equals("close") && !selection.equals("e")) {
            System.out.println("""
                [Inventory]
                
                Independent Items
                -----------------
                """.stripIndent());
            
            if (w > 0) {
                System.out.printf("Weapons: [%d]%n%n", w);
            }
            
            if (c > 0) {
                System.out.printf("Yen: [%d]%n%n", c);
            }
            
            System.out.println("""
                Usable Items
                -------------
                """.stripIndent());
            
            if (n > 0) {
                System.out.printf("Notes: [%d]%n%n", n);
            }
            
            if (h > 0) {
                System.out.printf("Herbs: [%d]%n%n", h);
            }
            
            if (b > 0) {
                System.out.printf("(Herb) Bushels: [%d]%n%n", b);
            }
            
            System.out.print(" > ");
            
            selection = Util.input.nextLine().toLowerCase();
            
            if (selection.startsWith("use")) {
                selection = selection.substring(selection.indexOf(" ") + 1);
            }
            
            System.out.println();
            
            while (!selection.equals("note") && !selection.equals("herb") && !selection.equals("bushel") && !selection.equals("close") && !selection.equals("e")) {
                if (selection.equals("weapon")) {
                    System.out.println("You cannot use this now.");
                }
                else if (selection.equals("coin")) {
                    if (c == 0) {
                        System.out.println("Even if you had one, you still couldn't use it.");
                    }
                    else {
                        System.out.println("You can't use this.");
                    }
                }
                else {
                    System.out.println("You don't have any " + selection + "s to use.");
                }
                
                System.out.print("\n > ");
                
                selection = Util.input.nextLine().toLowerCase();
                
                System.out.println();
            }
            
            for (var i = 0; i < self.getInventory().size(); i++) {
                if ((self.getInventory().get(i).getName()).equals(selection)) {
                    self.getInventory().get(i).use(self);
                    
                    self.getInventory().remove(i);
                    
                    if (selection.startsWith("h")) {
                        h--;
                    }
                    else if (selection.startsWith("b")) {
                        b--;
                    }
                    
                    break;
                }
            }
        }
    }
    
    private static void storeState() {
        rowLast = row;
        columnLast = column;
        floorLast = floor;
        ranLast = ran;
        
        self.storeState();
        
        for (var guard : guards) {
            guard.storeState();
        }
        
        castle.storeState();
    }
    
    private static void resetState() {
        row = rowLast;
        column = columnLast;
        floor = floorLast;
        ran = ranLast;
        
        self.resetState();
        
        for (var guard : guards) {
            guard.resetState();
        }
        
        castle.resetState();
    }
    
    private static void endGame() {
        var text = Resources.getLines("endGame");
        
        text.forEach(System.out::println);
        
        System.out.println();
        
        System.exit(0);
    }
}