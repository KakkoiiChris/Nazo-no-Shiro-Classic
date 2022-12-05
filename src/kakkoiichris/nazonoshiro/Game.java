// Christian Alexander, 12/4/2022
package kakkoiichris.nazonoshiro;

import kakkoiichris.nazonoshiro.castle.Castle;
import kakkoiichris.nazonoshiro.castle.Direction;
import kakkoiichris.nazonoshiro.castle.room.EnemyRoom;
import kakkoiichris.nazonoshiro.castle.room.PuzzleRoom;
import kakkoiichris.nazonoshiro.fighter.Enemy;
import kakkoiichris.nazonoshiro.fighter.Fighter;
import kakkoiichris.nazonoshiro.fighter.Self;
import kakkoiichris.nazonoshiro.item.Coin;
import kakkoiichris.nazonoshiro.item.HealthPack;
import kakkoiichris.nazonoshiro.item.Item;
import kakkoiichris.nazonoshiro.item.Weapon;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Game {
    private final ResetValue<Integer> floor = new ResetValue<>(0); //the floor you are located in
    private final ResetValue<Integer> row = new ResetValue<>(1); //the row you are located in
    private final ResetValue<Integer> column = new ResetValue<>(2); //the column you are located in
    
    private Direction lastDirection = Direction.NONE;
    
    private final SaveFileCreator saver = new SaveFileCreator();
    
    private final ResetValue<Boolean> ran = new ResetValue<>(false); //keeps track of if you run from a fight
    
    private Castle castle;
    private Self self = new Self(); //this fighter represents you
    
    private ResetGroup resetGroup;
    
    public void playNewGame() {
        System.out.print("Map Name > ");
        
        var mapName = Util.input.nextLine();
        
        System.out.println();
        
        castle = switch (mapName) {
            case "Original Castle", "" -> new Castle("original");
            
            default -> throw new RuntimeException("NO CASTLE");
        };
        
        resetGroup = ResetGroup.of(floor, row, column, ran, castle, self);
        
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
        
        introduction();
        
        explore();
    }
    
    private void distributeItems(List<Item> items) {
        while (!items.isEmpty()) {
            castle.distributeItems(items);
        }
    }
    
    private void introduction() {
        var source = Resources.getLines("intro");
        
        var script = new Script(source);
        
        var vars = script.run();
        
        var name = vars.get("name");
        var birthday = vars.get("birthday");
        var gender = vars.get("gender");
        
        self = new Self(name, birthday, gender);
    }
    
    private void explore() {
        while (true) {
            var room = castle.get(floor.get(), row.get(), column.get());
            
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
                else if (ran.get()) {
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
            
            var fileName = "%s%d-%d-%d".formatted(timing, row.get(), column.get(), floor.get());
            
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
            
            matcher = Pattern.compile("((play|show)\\s+inventory)|e").matcher(choice);
            
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
    
    private boolean fight(Enemy enemy) {
        var directHit = Resources.getLines("directHit");
        var indirectHit = Resources.getLines("indirectHit");
        var missHit = Resources.getLines("missHit");
        var directBlock = Resources.getLines("directBlock");
        var indirectBlock = Resources.getLines("indirectBlock");
        var missBlock = Resources.getLines("missBlock");
        
        System.out.printf("The %s stands before you.%n%n", enemy);
        
        //determines who's attacking and who's defending in battle
        boolean yourTurn = Math.random() >= 0.5;
        
        ran.set(false);
        
        while (!self.isDead() && !enemy.isDead() && !ran.get()) {
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
                    ran.set(run(enemy));
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
            
            if (ran.get() || self.isDead()) {
                return false;
            }
            
            Util.pause(2);
        }
        
        return true;
    }
    
    private boolean run(Fighter enemy) {
        var speedDiff = ((double) self.getSpeed() / enemy.getSpeed()) * 50;
        
        var runChance = Math.random() * 100;
        
        row.storeState();
        column.storeState();
        
        move(lastDirection.getInverse());
        
        if (!row.hasChanged() && !column.hasChanged()) {
            System.out.println("You've been cornered.\n");
            
            return false;
        }
        
        if (speedDiff > runChance) {
            System.out.println("You made a clean getaway.\n");
            
            return true;
        }
        
        System.out.println("You've been cut off.\n");
        
        row.resetState();
        column.resetState();
        
        return false;
    }
    
    private void move(Direction direction) {
        lastDirection = direction;
        
        floor.storeState();
        row.storeState();
        column.storeState();
        
        switch (direction) {
            case UP -> goUp();
            case DOWN -> goDown();
            case NORTH -> goNorth();
            case SOUTH -> goSouth();
            case EAST -> goEast();
            case WEST -> goWest();
        }
        
        if (!(floor.hasChanged() || row.hasChanged() || column.hasChanged())) {
            lastDirection = Direction.NONE;
        }
    }
    
    private boolean northernMost() {
        return row.get() == 0;
    }
    
    private boolean southernMost() {
        return row.get() == castle.getRows() - 1;
    }
    
    private boolean easternMost() {
        return column.get() == castle.getColumns() - 1;
    }
    
    private boolean westernMost() {
        return column.get() == 0;
    }
    
    private boolean upperMost() {
        return floor.get() == castle.getFloors() - 1;
    }
    
    private boolean lowerMost() {
        return floor.get() == 0;
    }
    
    private boolean hasVisited(int floor, int row, int column) {
        return castle.get(floor, row, column).isVisited();
    }
    
    private boolean hasWall(Direction direction) {
        return castle.get(floor.get(), row.get(), column.get()).hasWall(direction);
    }
    
    private void goNorth() {
        var f = floor.get();
        var r = row.get();
        var c = column.get();
        
        if (hasWall(Direction.NORTH) || northernMost()) {
            if (c == 4 && hasVisited(0, 2, 4)) {
                endGame();
            }
            
            System.out.println("A wall blocks your path.\n");
            
            return;
        }
        
        if (self.getKey() >= castle.get(f, r - 1, c).getLock()) {
            if (!hasVisited(f, r - 1, c)) {
                System.out.println("The door is unlocked.\n");
            }
            
            row.set(r - 1);
            
            return;
        }
        
        System.out.println("None of the keys you have fit that lock.\n");
    }
    
    private void goSouth() {
        var f = floor.get();
        var r = row.get();
        var c = column.get();
        
        if (hasWall(Direction.SOUTH) || southernMost()) {
            System.out.println("A wall blocks your path.\n");
            
            return;
        }
        
        if (self.getKey() >= castle.get(f, r + 1, c).getLock()) {
            if (!hasVisited(f, r + 1, c)) {
                System.out.println("The door is unlocked.\n");
            }
            
            row.set(r + 1);
            
            return;
        }
        
        System.out.println("None of the keys you have fit that lock.\n");
    }
    
    private void goEast() {
        var f = floor.get();
        var r = row.get();
        var c = column.get();
        
        if (hasWall(Direction.EAST) || easternMost()) {
            System.out.println("A wall blocks your path.\n");
            
            return;
        }
        
        if (self.getKey() >= castle.get(f, r, c + 1).getLock()) {
            if (!hasVisited(f, r, c + 1)) {
                System.out.println("The door is unlocked.\n");
            }
            
            column.set(c + 1);
            
            return;
        }
        
        System.out.println("None of the keys you have fit that lock.\n");
    }
    
    private void goWest() {
        var f = floor.get();
        var r = row.get();
        var c = column.get();
        
        if (hasWall(Direction.WEST) || westernMost()) {
            System.out.println("A wall blocks your path.\n");
            
            return;
        }
        
        if (self.getKey() >= castle.get(f, r, c - 1).getLock()) {
            if (!hasVisited(f, r, c - 1)) {
                System.out.println("The door is unlocked.\n");
            }
            
            column.set(c - 1);
            
            return;
        }
        
        System.out.println("None of the keys you have fit that lock.\n");
    }
    
    private void goUp() {
        var f = floor.get();
        var r = row.get();
        var c = column.get();
        
        if (hasWall(Direction.UP) || upperMost()) {
            System.out.println("The ceiling blocks your path.\n");
            
            return;
        }
        
        if (self.getKey() >= castle.get(f + 1, r, c).getLock()) {
            if (!hasVisited(f + 1, r, c)) {
                System.out.println("The hatch is unlocked.\n");
            }
            
            floor.set(f + 1);
            
            return;
        }
        
        System.out.println("None of the keys you have fit that lock.\n");
    }
    
    private void goDown() {
        var f = floor.get();
        var r = row.get();
        var c = column.get();
        
        if (hasWall(Direction.DOWN) || lowerMost()) {
            System.out.println("The floor blocks your path.\n");
            
            return;
        }
        
        if (self.getKey() >= castle.get(f - 1, r, c).getLock()) {
            if (!hasVisited(f - 1, r, c)) {
                System.out.println("The hatch is unlocked.\n");
            }
            
            floor.set(f - 1);
            
            return;
        }
        
        System.out.println("None of the keys you have fit that lock.\n");
    }
    
    private void showInventory() {
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
    
    private void storeState() {
        resetGroup.storeState();
    }
    
    private void resetState() {
        resetGroup.resetState();
    }
    
    private void endGame() {
        var text = Resources.getLines("endGame");
        
        text.forEach(System.out::println);
        
        System.out.println();
    }
}