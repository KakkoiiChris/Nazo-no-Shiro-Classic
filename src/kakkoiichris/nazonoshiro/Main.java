// Christian Alexander, 5/12/11, Pd. 6
package kakkoiichris.nazonoshiro;

import kakkoiichris.nazonoshiro.castle.*;
import kakkoiichris.nazonoshiro.fighter.*;
import kakkoiichris.nazonoshiro.item.Coin;
import kakkoiichris.nazonoshiro.item.HealthPack;
import kakkoiichris.nazonoshiro.item.weapon.*;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final OptionsMenu aaa = new OptionsMenu();
    
    private static int row = 1; //the row you are located in
    private static int column = 2; //the column you are located in
    private static int floor = 0; //the floor you are located in
    private static int rowLast = 1; //stores previous row for restart checkpoint
    private static int columnLast = 2; //stores previous column for restart checkpoint
    private static int floorLast = 0; //stores previous floor for restart checkpoint
    
    private static boolean yourTurn = true; //determines who's attacking and who's defending in battle
    
    private static Direction lastDirection;
    
    private static final SaveFileCreator saver = new SaveFileCreator();
    
    private static boolean ran = false, ranLast = false; //keeps track of if you run from a fight
    
    private static String choice = ""; //used when navigating between rooms
    private static String action = ""; //used when fighting
    private static String selection = ""; //used in inventory
    
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
    
    public static void main(String[] argv) throws IOException {
        mainMenu();
        
        storeState();
        
        choice = "none";
        
        while (!choice.equals("quit")) {
            explore(floors.get(floor).getRoom(row, column));
            
            while (choice.equals("inventory")) {
                explore(floors.get(floor).getRoom(row, column));
            }
        }
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
            
            for (var r = 0; r < castleFloor.getColumns(); r++) {
                for (var c = 0; c < castleFloor.getRows(); c++) {
                    castleFloor.getRoom(r, c).setRoom();
                }
            }
        }
        
        distributeAllItems();
        
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
            
            var name = lines.get(data).substring(index + 4);
            
            data++;
            
            var p = Integer.parseInt(lines.get(data).substring(0, lines.get(data).indexOf(",")));
            var k = Integer.parseInt(lines.get(data).substring(lines.get(data).indexOf(",") + 1, lines.get(data).indexOf("'")));
            var l = Integer.parseInt(lines.get(data).substring(lines.get(data).indexOf("'") + 1));
            
            floors.get(f).getFloorPlan()[r][c] = new Room(name, p, k, l, false);
            
            data++;
            
            while (!lines.get(data).startsWith("#") && !lines.get(data).startsWith("S")) {
                var d = lines.get(data).charAt(lines.get(data).indexOf('|') + 1);
                var name2 = lines.get(data).substring(lines.get(data).indexOf(':') + 1);
                
                floors.get(f).getFloorPlan()[r][c].getWalls().add(new Wall(r, c, d, name2));
                
                data++;
                
                for (var u = 0; u < Integer.parseInt(lines.get(data).substring(lines.get(data).indexOf('!') + 1, lines.get(data).indexOf('a'))); u++) {
                    floors.get(f).getFloorPlan()[r][c].getWalls().get(0).getStorage().add(new Tanto());
                }
                
                for (var v = 0; v < Integer.parseInt(lines.get(data).substring(lines.get(data).indexOf('a') + 1, lines.get(data).indexOf('b'))); v++) {
                    floors.get(f).getFloorPlan()[r][c].getWalls().get(0).getStorage().add(new Wakizashi());
                }
                
                for (var w = 0; w < Integer.parseInt(lines.get(data).substring(lines.get(data).indexOf('b') + 1, lines.get(data).indexOf('c'))); w++) {
                    floors.get(f).getFloorPlan()[r][c].getWalls().get(0).getStorage().add(new Katana());
                }
                
                for (var x = 0; x < Integer.parseInt(lines.get(data).substring(lines.get(data).indexOf('c') + 1, lines.get(data).indexOf('d'))); x++) {
                    floors.get(f).getFloorPlan()[r][c].getWalls().get(0).getStorage().add(new Bo());
                }
                
                for (var y = 0; y < Integer.parseInt(lines.get(data).substring(lines.get(data).indexOf('d') + 1, lines.get(data).indexOf('e'))); y++) {
                    floors.get(f).getFloorPlan()[r][c].getWalls().get(0).getStorage().add(new Shuriken());
                }
                
                for (var z = 0; z < Integer.parseInt(lines.get(data).substring(lines.get(data).indexOf('e') + 1)); z++) {
                    floors.get(f).getFloorPlan()[r][c].getWalls().get(0).getStorage().add(new Nunchaku());
                }
                
                data++;
                
                for (var x = 0; x < Integer.parseInt(lines.get(data).substring(lines.get(data).indexOf('*') + 1, lines.get(data).indexOf('a'))); x++) {
                    floors.get(f).getFloorPlan()[r][c].getWalls().get(0).getStorage().add(new HealthPack("Herb", 3));
                }
                
                for (var y = 0; y < Integer.parseInt(lines.get(data).substring(lines.get(data).indexOf('a') + 1, lines.get(data).indexOf('b'))); y++) {
                    floors.get(f).getFloorPlan()[r][c].getWalls().get(0).getStorage().add(new HealthPack("Bushel", 5));
                }
                
                for (var z = 0; z < Integer.parseInt(lines.get(data).substring(lines.get(data).indexOf('b') + 1)); z++) {
                    floors.get(f).getFloorPlan()[r][c].getWalls().get(0).getStorage().add(new Coin());
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
        System.out.println("                        [Nazo No Shiro]");
        System.out.println("                    [XXXXX{================>");
        System.out.println();
        
        var lines = Resources.getLines("introA");
        
        var i = 0;
        
        for (; i < 5; i++) {
            System.out.println(lines.get(i));
        }
        
        pause(12);
        
        for (; i < 11; i++) {
            System.out.println(lines.get(i));
        }
        
        pause(15);
        
        for (; i < 14; i++) {
            System.out.println(lines.get(i));
        }
        
        pause(6);
        
        for (; i < 16; i++) {
            System.out.println(lines.get(i));
        }
        
        pause(2);
        
        for (; i < 22; i++) {
            System.out.println(lines.get(i));
        }
        
        pause(7);
        
        for (; i < 24; i++) {
            System.out.println(lines.get(i));
        }
        
        pause(4);
        
        for (; i < 27; i++) {
            System.out.println(lines.get(i));
        }
        
        pause(6);
        
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
        
        pause(6);
        
        for (; i < 4; i++) {
            System.out.println(lines.get(i));
        }
        
        pause(2);
        
        for (; i < 8; i++) {
            System.out.println(lines.get(i));
        }
        
        pause(3);
        
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
        
        pause(8);
        
        for (i = 26; i < 28; i++) {
            System.out.println(lines.get(i));
        }
        
        pause(2);
        
        for (; i < 33; i++) {
            System.out.println(lines.get(i));
        }
        
        pause(11);
        
        for (; i < 35; i++) {
            System.out.println(lines.get(i));
        }
        
        pause(3);
        
        System.out.println();
    }
    
    private static void explore(Room room) throws IOException {
        System.out.printf("%s%n%n", room);
        
        var timing = room.isVisited() ? "then" : "first";
        
        room.setVisited();
        
        var fileName = "%s%d-%d-%d".formatted(timing, row, column, floor);
        
        var description = Resources.getLines(fileName);
        
        description.forEach(System.out::println);
        
        for (var i = 0; i < room.getSize(); i++) {
            var direction = switch (room.getWall(i).getSide()) {
                case 'N' -> "north";
                
                case 'S' -> "south";
                
                case 'E' -> "east";
                
                default -> "west";
            };
            
            System.out.printf("There is a %s to the %s.%n", room.getWall(i).getStorage().getName(), direction);
        }
        
        var foeIndex = room.getFoe();
        
        if (room.getPuzzle() == 4 && guards[foeIndex].getHealth() > 0) {
            fight(guards[foeIndex]);
            
            if (guards[foeIndex].getHealth() <= 0) {
                System.out.println("You were victorious!");
                System.out.println();
                
                guards[foeIndex].dropItem(self);
                
                self.getKeys().add(room.getKey());
                
                room.setKey(99);
                
                storeState();
            }
            else {
                System.out.println("You lost.");
                
                resetState();
            }
        }
        else {
            System.out.print(" > ");
            
            choice = input.nextLine().toLowerCase();
            
            while (!isExploreOption(choice)) {
                System.out.printf("\nI don't recognize the phrase '%s'.%n%n > ", choice);
                
                choice = input.nextLine().toLowerCase();
                
                System.out.println();
            }
            
            System.out.println();
            
            if (choice.equals("play puzzle") || choice.equals("play") || choice.equals("solve puzzle") || choice.equals("solve") && !floors.get(floor).getPuzzles()[row][column].isWon()) {
                floors.get(floor).playPuzzle(row, column);
            }
            else if (choice.equals("show inventory") || choice.equals("inventory") || choice.equals("view inventory") || choice.equals("e")) {
                showInventory();
            }
            else if (choice.startsWith("search")) {
                var temp = choice.substring(choice.indexOf(" ") + 1);
                var dir = 'a';
                
                for (var i = 0; i < room.getSize(); i++) {
                    if (room.getWall(i).getStorage().getName().toLowerCase().equals(temp)) {
                        dir = room.getWall(i).getSide();
                    }
                }
                
                room.look(self, dir);
            }
            else if (choice.startsWith("go")) {
                storeState();
                
                var direction = choice.substring(choice.indexOf(" ") + 1);
                
                if (Direction.isValid(direction)) {
                    move(Direction.valueOf(direction.toUpperCase()));
                }
                else {
                    System.out.printf("'%s' is not a valid direction.", direction);
                }
            }
            else if (choice.equals("w")) {
                storeState();
                
                move(Direction.NORTH);
            }
            else if (choice.equals("a")) {
                storeState();
                
                move(Direction.WEST);
            }
            else if (choice.equals("s")) {
                storeState();
                
                move(Direction.SOUTH);
            }
            else if (choice.equals("d")) {
                storeState();
                
                move(Direction.EAST);
            }
            else if (choice.equals("r")) {
                storeState();
                
                move(Direction.UP);
            }
            else if (choice.equals("f")) {
                storeState();
                
                move(Direction.DOWN);
            }
            else if (choice.equals("save")) {
                saver.openFile();
                
                System.out.print("Saving");
                
                saver.addData(floors, guards, self, row, column, floor, yourTurn);
                
                saver.closeFile();
                
                for (var i = 0; i < (int) (Math.random() * 5) + 3; i++) {
                    System.out.print(".");
                    
                    pause(1);
                }
                
                System.out.println("\nSaved.\n");
            }
            else if (choice.equals("snq")) {
                saver.openFile();
                
                System.out.print("Saving");
                
                saver.addData(floors, guards, self, row, column, floor, yourTurn);
                
                saver.closeFile();
                
                for (var i = 0; i < (int) (Math.random() * 5) + 3; i++) {
                    System.out.print(".");
                    
                    pause(1);
                }
                
                System.out.println("\nSaved.\nThanks for playing.");
                
                System.exit(0);
            }
            
            if (choice.equals("play puzzle") || choice.equals("play") || choice.equals("solve puzzle") || choice.equals("solve") || choice.equals("skip") && floors.get(floor).getRoom(row, column).getKey() != 99) {
                self.getKeys().add(room.getKey());
                
                room.setKey(99);
                
                storeState();
            }
        }
    }
    
    private static void fight(Fighter enemy) throws IOException {
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
                    
                    action = input.next().toLowerCase();
                    
                    System.out.println();
                    
                    while (!action.equals("attack") && !action.equals("a") && !action.equals("run") && !action.equals("r") && !action.equals("use") && !action.equals("e")) {
                        System.out.print("What?\n(Attack/Use/Run)\n\n> ");
                        
                        action = input.next().toLowerCase();
                        
                        System.out.println();
                    }
                    
                    if (action.equals("attack") || action.equals("a")) {
                        self.attack(enemy, directHit, indirectHit, missHit);
                    }
                    else if (action.equals("use") || action.equals("e")) {
                        self.use(enemy);
                    }
                    else {
                        ran = true;
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
                    var temp = action.substring(action.indexOf(" ") + 1);
                    
                    run(enemy);
                }
                
                if (self.isDead()) {
                    System.out.println("You died.\n");
                }
                
                pause(2);
            }
        }
    }
    
    private static void run(Fighter enemy) throws IOException {
        //used in run method
        double speedDiff = ((double) self.getSpeed() / enemy.getSpeed()) * 50;
        
        var runChance = Math.random() * 100;
        
        var tempRow = row;
        var tempColumn = column;
        
        move(lastDirection.getInverse());
        
        if (row == tempRow && column == tempColumn) {
            System.out.println("You've been cornered.\n");
            
            ran = false;
        }
        else if (speedDiff > runChance) {
            System.out.println("You made a clean getaway.\n");
            
            explore(floors.get(floor).getRoom(row, column));
            
            ran = true;
        }
        else {
            System.out.println("You've been cut off.\n");
            
            row = tempRow;
            column = tempColumn;
            
            ran = false;
        }
    }
    
    private static void pause(int seconds) {
        try {
            Thread.sleep(seconds * 1000L * 0); //TODO: Reenable pausing
        }
        catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
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
    
    private static void distributeAllItems() {
        for (var castleFloor : floors) {
            for (var r = 0; r < castleFloor.getColumns(); r++) {
                for (var c = 0; c < castleFloor.getRows(); c++) {
                    for (var i = 0; i < (int) (Math.random() * 5) + 500; i++) {
                        castleFloor.getRoom(r, c).addToRoom(new Coin());
                    }
                    
                    for (var j = 0; j < (int) (Math.random() * 40); j++) {
                        castleFloor.getRoom(r, c).addToRoom(new HealthPack("herb", 3));
                    }
                    
                    for (var k = 0; k < (int) (Math.random() * 40); k++) {
                        castleFloor.getRoom(r, c).addToRoom(new HealthPack("bushel", 5));
                    }
                    
                    castleFloor.getRoom(r, c).redistribute();
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
        
        selection = "none";
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
                    castleFloor.getPuzzles()[i][j].storeState();
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
                    castleFloor.getPuzzles()[i][j].resetState();
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
    
    private static boolean hasWall(char side) {
        for (var i = 0; i < floors.get(floor).getRoom(row, column).getSize(); i++) {
            if (floors.get(floor).getRoom(row, column).getWalls().get(i).getSide() == side) {
                return true;
            }
        }
        
        return false;
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
    
    private static boolean isExploreOption(String choice) {
        return choice.equals("play puzzle") || choice.equals("play") ||
            choice.equals("solve puzzle") || choice.equals("solve") ||
            choice.equals("show inventory") || choice.equals("inventory") ||
            choice.equals("view inventory") || choice.startsWith("search") ||
            choice.equals("close") || choice.startsWith("go") ||
            choice.startsWith("skip") || choice.equals("w") ||
            choice.equals("a") || choice.equals("s") ||
            choice.equals("d") || choice.equals("quit") ||
            choice.endsWith("?") || choice.equals("snq") ||
            choice.equals("save") || choice.equals("e") ||
            choice.equals("up") || choice.equals("down") ||
            choice.equals("r") || choice.equals("f");
    }
    
    private static boolean hasVisited(int row, int column, int floor) {
        return floors.get(floor).getRoom(row, column).isVisited();
    }
    
    private static int goNorth() {
        if (hasWall('N') || northernMost()) {
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
        if (hasWall('S') || southernMost()) {
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
        if (hasWall('E') || easternMost()) {
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
        if (hasWall('W') || westernMost()) {
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
}