// Christian Alexander, 5/12/11, Pd. 6
package kakkoiichris.nazonoshiro;

import kakkoiichris.nazonoshiro.castle.CastleFloor;
import kakkoiichris.nazonoshiro.castle.Direction;
import kakkoiichris.nazonoshiro.castle.OriginalCastle;
import kakkoiichris.nazonoshiro.castle.Wall;
import kakkoiichris.nazonoshiro.castle.room.EnemyRoom;
import kakkoiichris.nazonoshiro.castle.room.PuzzleRoom;
import kakkoiichris.nazonoshiro.castle.room.Room;
import kakkoiichris.nazonoshiro.castle.storage.*;
import kakkoiichris.nazonoshiro.fighter.*;
import kakkoiichris.nazonoshiro.item.Coin;
import kakkoiichris.nazonoshiro.item.HealthPack;
import kakkoiichris.nazonoshiro.item.Item;
import kakkoiichris.nazonoshiro.item.weapon.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
    private static final OptionsMenu aaa = new OptionsMenu();
    
    private static int row = 1; //the row you are located in
    private static int column = 2; //the column you are located in
    private static int floor = 0; //the floor you are located in
    private static int rowLast = 1; //stores previous row for restart checkpoint
    private static int columnLast = 2; //stores previous column for restart checkpoint
    private static int floorLast = 0; //stores previous floor for restart checkpoint
    
    private static boolean yourTurn = true; //determines who's attacking and who's defending in battle
    
    private static Direction lastDirection = Direction.NONE;
    
    private static final SaveFileCreator saver = new SaveFileCreator();
    
    private static boolean ran = false, ranLast = false; //keeps track of if you run from a fight
    
    private static final Scanner input = new Scanner(System.in);
    
    private static final Enemy[] guards = { //collection of all enemies
        new Ninja(),
        new Samurai(),
        new Daimyo(),
        new Shogun(),
        new Imperial()
    };
    private static final Self self = new Self(); //this fighter represents you
    
    private static final List<Weapon> weapons = new ArrayList<>(); //stores all possible weapons
    private static final List<CastleFloor> floors = new ArrayList<>();
    
    public static void main(String[] argv) {
        mainMenu();
        
        storeState();
        
        explore();
    }
    
    private static void mainMenu() {
        var lines = Resources.getLines("mainMenu1");
        lines.forEach(System.out::println);
        
        System.out.println();
        
        lines = Resources.getLines("splashText");
        System.out.println(Util.getRandom(lines).toUpperCase());
        
        lines = Resources.getLines("mainMenu2");
        lines.forEach(System.out::println);
        
        System.out.println();
        
        var inMenu = true;
        
        while (inMenu) {
            System.out.print(" > ");
            
            var first = input.nextLine();
            
            if (first.equals("1") || first.startsWith("New")) {
                setUpNew();
                
                inMenu = false;
            }
            else if (first.equals("2") || first.startsWith("Load")) {
                setUpLoad();
                
                inMenu = false;
            }
            else if (first.equals("3") || first.equals("Options")) {
                options();
            }
            else if (first.equals("4") || first.equals("Credits")) {
                credits();
            }
            else {
                System.out.println("Thanks for playing!");
                
                System.exit(0);
            }
        }
    }
    
    private static void setUpNew() {
        floors.clear();
        
        System.out.print("Map Name > ");
        
        var mapName = input.nextLine();
        
        System.out.println();
        
        switch (mapName) {
            case "Original Castle", "" -> floors.add(new OriginalCastle());
            
            case "Double Classic" -> {
                floors.add(new OriginalCastle());
                floors.add(new OriginalCastle());
            }
        }
        
        for (var castleFloor : floors) {
            castleFloor.setUpNew();
        }
        
        var items = new ArrayList<Item>();
        
        for (var i = 0; i < Util.random.nextInt(1_000); i++) {
            items.add(new Coin());
        }
        
        for (var j = 0; j < Util.random.nextInt(250); j++) {
            items.add(new HealthPack("herb", 3));
        }
        
        for (var k = 0; k < Util.random.nextInt(100); k++) {
            items.add(new HealthPack("bushel", 5));
        }
        
        distributeItems(items);
        
        self.getKeys().add(0);
        self.getInventory().add(new Katana());
        self.getInventory().add(new Tanto());
        
        weapons.add(new Bo());
        weapons.add(new Nunchaku());
        weapons.add(new Shuriken());
        weapons.add(new Tanto());
        weapons.add(new Wakizashi());
        weapons.add(new Katana());
        
        for (var guard : guards) {
            guard.setDrop(self, weapons);
        }
        
        introduction();
    }
    
    private static void setUpLoad() {
        System.out.print("File Name > ");
        
        var fileName = input.nextLine();
        
        var lines = Resources.getLines(fileName);
        
        var data = 0;
        
        while (!lines.get(data).startsWith("#")) {
            if (lines.get(data).equals("Original Castle")) {
                floors.add(new OriginalCastle());
            }
            
            data++;
        }
        
        while (!lines.get(data).startsWith("S")) {
            var index = lines.get(data).indexOf("#");
            
            var f = (int) (lines.get(data).charAt(index + 1)) - 48;
            var r = (int) (lines.get(data).charAt(index + 2)) - 48;
            var c = (int) (lines.get(data).charAt(index + 3)) - 48;
            
            var room = floors.get(f).getRooms()[r][c];
            
            var name = lines.get(data).substring(index + 4);
            
            data++;
            
            //var p = Integer.parseInt(lines.get(data).substring(0, lines.get(data).indexOf(",")));
            var k = Integer.parseInt(lines.get(data).substring(lines.get(data).indexOf(",") + 1, lines.get(data).indexOf("'")));
            var l = Integer.parseInt(lines.get(data).substring(lines.get(data).indexOf("'") + 1));
            
            floors.get(f).getRooms()[r][c] = new Room(name, k, l, false);
            
            data++;
            
            while (!lines.get(data).startsWith("#") && !lines.get(data).startsWith("S")) {
                var direction = Direction.values()[Integer.parseInt(lines.get(data).substring(lines.get(data).indexOf('|'), lines.get(data).indexOf('|') + 1))];
                
                var storageName = lines.get(data).substring(lines.get(data).indexOf(':') + 1);
                
                var storage = switch (storageName) {
                    case "Armoir" -> new Armoir();
                    
                    case "Crate" -> new Crate();
                    
                    case "Desk" -> new Desk();
                    
                    case "Dresser" -> new Dresser();
                    
                    case "Jewelry Box" -> new JewelryBox();
                    
                    default -> throw new RuntimeException("NO STORAGE");
                };
                
                room.setWall(direction, new Wall(direction, storage));
                
                data++;
                
                var count = Integer.parseInt(lines.get(data).substring(lines.get(data).indexOf('!') + 1, lines.get(data).indexOf('a')));
                
                for (var u = 0; u < count; u++) {
                    room.getWalls().get(0).getStorage().add(new Tanto());
                }
                
                count = Integer.parseInt(lines.get(data).substring(lines.get(data).indexOf('a') + 1, lines.get(data).indexOf('b')));
                
                for (var v = 0; v < count; v++) {
                    room.getWalls().get(0).getStorage().add(new Wakizashi());
                }
                
                count = Integer.parseInt(lines.get(data).substring(lines.get(data).indexOf('b') + 1, lines.get(data).indexOf('c')));
                
                for (var w = 0; w < count; w++) {
                    room.getWalls().get(0).getStorage().add(new Katana());
                }
                
                count = Integer.parseInt(lines.get(data).substring(lines.get(data).indexOf('c') + 1, lines.get(data).indexOf('d')));
                
                for (var x = 0; x < count; x++) {
                    room.getWalls().get(0).getStorage().add(new Bo());
                }
                
                count = Integer.parseInt(lines.get(data).substring(lines.get(data).indexOf('d') + 1, lines.get(data).indexOf('e')));
                
                for (var y = 0; y < count; y++) {
                    room.getWalls().get(0).getStorage().add(new Shuriken());
                }
                
                for (var z = 0; z < Integer.parseInt(lines.get(data).substring(lines.get(data).indexOf('e') + 1)); z++) {
                    room.getWalls().get(0).getStorage().add(new Nunchaku());
                }
                
                data++;
                
                count = Integer.parseInt(lines.get(data).substring(lines.get(data).indexOf('*') + 1, lines.get(data).indexOf('a')));
                
                for (var x = 0; x < count; x++) {
                    room.getWalls().get(0).getStorage().add(new HealthPack("Herb", 3));
                }
                
                count = Integer.parseInt(lines.get(data).substring(lines.get(data).indexOf('a') + 1, lines.get(data).indexOf('b')));
                
                for (var y = 0; y < count; y++) {
                    room.getWalls().get(0).getStorage().add(new HealthPack("Bushel", 5));
                }
                
                count = Integer.parseInt(lines.get(data).substring(lines.get(data).indexOf('b') + 1));
                
                for (var z = 0; z < count; z++) {
                    room.getWalls().get(0).getStorage().add(new Coin());
                }
                
                data += 2;
            }
        }
    }
    
    private static void options() {
        aaa.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        aaa.setSize(300, 200);
        aaa.setVisible(true);
    }
    
    private static void credits() {
        var lines = Resources.getLines("credits");
        
        lines.forEach(System.out::println);
        
        System.out.println();
    }
    
    private static void introduction() {
        var lines = Resources.getLines("introA");
        
        var i = 0;
        
        for (; i < 5; i++) {
            System.out.println(lines.get(i));
        }
        
        Util.pause(12);
        
        for (; i < 11; i++) {
            System.out.println(lines.get(i));
        }
        
        Util.pause(15);
        
        for (; i < 14; i++) {
            System.out.println(lines.get(i));
        }
        
        Util.pause(6);
        
        for (; i < 16; i++) {
            System.out.println(lines.get(i));
        }
        
        Util.pause(2);
        
        for (; i < 22; i++) {
            System.out.println(lines.get(i));
        }
        
        Util.pause(7);
        
        for (; i < 24; i++) {
            System.out.println(lines.get(i));
        }
        
        Util.pause(4);
        
        for (; i < 27; i++) {
            System.out.println(lines.get(i));
        }
        
        Util.pause(6);
        
        System.out.println();
        
        System.out.print("X ");
        var n = input.nextLine();
        System.out.println();
        
        self.setName(n);
        
        System.out.print("DoB(MM/DD/YYYY): ");
        var DoB = input.nextLine();
        System.out.println();
        
        System.out.print("(M/F): ");
        var gen = input.nextLine().toLowerCase();
        System.out.println();
        
        if (gen.startsWith("m")) {
            System.out.print("[Salesperson]: Alright, sir! ");
        }
        else {
            System.out.print("[Salesperson]: Alright, ma'am! ");
        }
        
        lines = Resources.getLines("introB");
        
        for (i = 0; i < 2; i++) {
            System.out.println(lines.get(i));
        }
        
        Util.pause(6);
        
        for (; i < 4; i++) {
            System.out.println(lines.get(i));
        }
        
        Util.pause(2);
        
        for (; i < 8; i++) {
            System.out.println(lines.get(i));
        }
        
        Util.pause(3);
        
        for (; i < 16; i++) {
            System.out.println(lines.get(i));
        }
        
        if (self.getName().length() < 15) {
            var namePadding = (15 - self.getName().length()) - 1;
            
            System.out.printf("%s%s", lines.get(17), self.getName().toUpperCase());
            
            System.out.print(" ".repeat(namePadding));
            
            System.out.println("         |");
        }
        else {
            System.out.printf("%s%s%s%n", lines.get(17), self.getName().substring(0, 14).toUpperCase(), lines.get(18));
        }
        
        System.out.println(lines.get(19));
        System.out.printf("%s%s%s%n", lines.get(20), DoB, lines.get(21));
        System.out.println(lines.get(22));
        System.out.printf("%s%s%s%n", lines.get(23), gen.toUpperCase(), lines.get(24));
        System.out.println(lines.get(25));
        
        Util.pause(8);
        
        for (i = 26; i < 28; i++) {
            System.out.println(lines.get(i));
        }
        
        Util.pause(2);
        
        for (; i < 33; i++) {
            System.out.println(lines.get(i));
        }
        
        Util.pause(11);
        
        for (; i < 35; i++) {
            System.out.println(lines.get(i));
        }
        
        Util.pause(3);
        
        System.out.println();
    }
    
    private static void explore() {
        while (true) {
            var room = floors.get(floor).getRoom(row, column);
            
            System.out.printf("%s%n%n", room);
            
            if (room instanceof EnemyRoom e && !e.getEnemy().isDead()) {
                fight(e.getEnemy());
                
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
            
            var choice = input.nextLine().toLowerCase();
            
            System.out.println();
            
            var matcher = Pattern.compile("play( puzzle)?|solve( puzzle)?").matcher(choice);
            
            if (matcher.find() && floors.get(floor).getRoom(row, column) instanceof PuzzleRoom p && !p.getPuzzle().isWon()) {
                if (p.getPuzzle().play()) {
                    System.out.println("""
                        You won!
                        
                        You have earned a key.
                        
                        Now to figure out which door it unlocks...
                        """.stripIndent());
                    
                    self.addKey(p.getKey());
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
                self.addKey(room.getKey());
                
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
                
                saver.addData(floors, guards, self, row, column, floor, yourTurn);
                
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
                
                saver.addData(floors, guards, self, row, column, floor, yourTurn);
                
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
        
        while (!self.isDead() && !enemy.isDead()) {
            if (enemy.getName().equals("Imperial Guard")) {
                System.out.printf("An %s stands before you.%n", enemy);
            }
            else {
                System.out.printf("A %s stands before you.%n", enemy);
            }
            
            System.out.println();
            
            yourTurn = Math.random() >= 0.5;
            
            ran = false;
            
            while (enemy.getHealth() > 0 && self.getHealth() > 0 && !ran) {
                self.showHP();
                enemy.showHP();
                
                if (yourTurn) {
                    self.allAffect();
                    self.filter();
                    
                    System.out.print("(Attack/Use/Run)\n\n> ");
                    
                    var action = input.next().toLowerCase();
                    
                    System.out.println();
                    
                    if (action.matches("a(ttack)?")) {
                        self.attack(enemy, directHit, indirectHit, missHit);
                    }
                    else if (action.matches("(us)?e")) {
                        self.use(enemy);
                    }
                    else if (action.matches("run|r")) {
                        ran = run(enemy);
                    }
                    else {
                        System.out.println("Can't do that...");
                    }
                }
                else {
                    input.nextLine();
                    
                    enemy.use(self);
                    enemy.allAffect();
                    enemy.filter();
                    
                    enemy.attack(self, directBlock, indirectBlock, missBlock);
                }
                
                yourTurn = !yourTurn;
                
                if (ran) {
                    return false;
                }
                
                if (self.isDead()) {
                    System.out.println("You died.\n");
                    
                    return false;
                }
                
                Util.pause(2);
            }
        }
        
        return true;
    }
    
    private static boolean run(Fighter enemy) {
        //used in run method
        double speedDiff = ((double) self.getSpeed() / enemy.getSpeed()) * 50;
        
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
        return row == floors.get(floor).getRows() - 1;
    }
    
    private static boolean easternMost() {
        return column == floors.get(floor).getColumns() - 1;
    }
    
    private static boolean westernMost() {
        return column == 0;
    }
    
    private static boolean upperMost() {
        return floor == floors.size() - 1;
    }
    
    private static boolean lowerMost() {
        return floor == 0;
    }
    
    private static boolean hasVisited(int row, int column, int floor) {
        return floors.get(floor).getRoom(row, column).isVisited();
    }
    
    private static boolean hasWall(Direction direction) {
        return floors.get(floor).getRoom(row, column).hasWall(direction);
    }
    
    private static int goNorth() {
        if (hasWall(Direction.NORTH) || northernMost()) {
            if (column == 4 && hasVisited(2, 4, 0)) {
                endGame();
            }
            
            System.out.println("A wall blocks your path.\n");
            
            return row;
        }
        
        if (self.hasKey(floors.get(floor).getRoom(row - 1, column).getLock())) {
            if (!hasVisited(row - 1, column, floor)) {
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
        
        if (self.hasKey(floors.get(floor).getRoom(row + 1, column).getLock())) {
            if (!hasVisited(row + 1, column, floor)) {
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
        
        if (self.hasKey(floors.get(floor).getRoom(row, column + 1).getLock())) {
            if (!hasVisited(row, column + 1, floor)) {
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
        
        if (self.hasKey(floors.get(floor).getRoom(row, column - 1).getLock())) {
            if (!hasVisited(row, column - 1, floor)) {
                System.out.println("The door is unlocked.\n");
            }
            
            return column - 1;
        }
        
        System.out.println("None of the keys you have fit that lock.\n");
        
        return column;
    }
    
    private static int goUp() {
        return floor + 1;
    }
    
    private static int goDown() {
        return floor - 1;
    }
    
    private static void distributeItems(List<Item> items) {
        while (!items.isEmpty()) {
            for (var floor : floors) {
                for (var row : floor.getRooms()) {
                    for (var room : row) {
                        room.distributeItems(items);
                    }
                }
            }
        }
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
            
            selection = input.nextLine().toLowerCase();
            
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
                
                selection = input.nextLine().toLowerCase();
                
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
        
        for (var castleFloor : floors) {
            for (var i = 0; i < castleFloor.getColumns(); i++) {
                for (var j = 0; j < castleFloor.getRows(); j++) {
                    castleFloor.getRoom(i, j).storeState();
                }
            }
        }
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
        
        for (var castleFloor : floors) {
            for (var i = 0; i < castleFloor.getColumns(); i++) {
                for (var j = 0; j < castleFloor.getRows(); j++) {
                    castleFloor.getRoom(i, j).resetState();
                }
            }
        }
    }
    
    private static void endGame() {
        var text = Resources.getLines("endGame");
        
        text.forEach(System.out::println);
        
        System.out.println();
        
        System.exit(0);
    }
}