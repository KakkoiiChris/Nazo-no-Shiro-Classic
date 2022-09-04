// Christian Alexander, 5/12/11, Pd. 6
package kakkoiichris.nazonoshiro;

import kakkoiichris.nazonoshiro.castle.CastleFloor;
import kakkoiichris.nazonoshiro.castle.OriginalCastle;
import kakkoiichris.nazonoshiro.castle.Room;
import kakkoiichris.nazonoshiro.castle.Wall;
import kakkoiichris.nazonoshiro.fighter.*;
import kakkoiichris.nazonoshiro.item.Coin;
import kakkoiichris.nazonoshiro.item.HealthPack;
import kakkoiichris.nazonoshiro.item.weapon.*;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static OptionsMenu aaa = new OptionsMenu();
    
    public static int row = 1, //the row you are located in
        column = 2, //the column you are located in
        floor = 0, //the floor you are located in
        rowLast = 1, //stores previous row for restart checkpoint
        columnLast = 2, //stores previous column for restart checkpoint
        floorLast = 0, //stores previous floor for restart checkpoint
        currentKey = 0, //used to count the number of keys you have
        wCount = 0,
        fileSize = 0; //used when reading in '.txt' files
    
    public static boolean yourTurn = true; //determines who's attacking and who's defending in battle
    
    public static SaveFileCreator saver = new SaveFileCreator();
    
    public static double speedDiff; //used in run method
    
    public static boolean ran = false, ranLast = false; //keeps track of if you run from a fight
    
    public static String choice = "", //used when navigating between rooms
        action = "", //used when fighting
        selection = "", //used in inventory
        fileName = "",
        mapName = "null";
    
    public static Scanner input = new Scanner(System.in);
    
    public static Enemy[] guards = { //collection of all enemies
        new Ninja(),
        new Samurai(),
        new Daimyo(),
        new Shogun(),
        new Imperial()
    };
    public static Self self = new Self();     //this fighter represents you
    
    public static List<Weapon> weapons = new ArrayList<>(); //stores all possible weapons
    public static List<CastleFloor> floors = new ArrayList<>();
    
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
    
    public static void setUpNew() throws IOException {
        floors.clear();
        
        switch (mapName) {
            case "Original Castle", "null" -> floors.add(new OriginalCastle());
            
            case "Double Classic" -> {
                floors.add(new OriginalCastle());
                floors.add(new OriginalCastle());
            }
        }
        
        for (var castleFloor : floors) {
            castleFloor.setUpNew();
            
            for (var r = 0; r < castleFloor.getXSize(); r++) {
                for (var c = 0; c < castleFloor.getYSize(); c++) {
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
            guard.setDrop(self, weapons, wCount);
        }
        
        introduction();
    }
    
    public static void setUpLoad() throws IOException {
        System.out.print("File Name > ");
        
        var fileName = input.nextLine() + ".txt";
        fileSize = getFileSize(fileName);
        
        var lines = new String[fileSize];
        readFile(lines, fileName);
        
        var data = 0;
        
        while (!lines[data].startsWith("#")) {
            if (lines[data].equals("Original Castle")) {
                floors.add(new OriginalCastle());
            }
            
            data++;
        }
        
        while (!lines[data].startsWith("S")) {
            var index = lines[data].indexOf("#");
            
            var f = (int) (lines[data].charAt(index + 1)) - 48;
            var r = (int) (lines[data].charAt(index + 2)) - 48;
            var c = (int) (lines[data].charAt(index + 3)) - 48;
            
            var name = lines[data].substring(index + 4);
            
            data++;
            
            var p = Integer.parseInt(lines[data].substring(0, lines[data].indexOf(",")));
            var k = Integer.parseInt(lines[data].substring(lines[data].indexOf(",") + 1, lines[data].indexOf("'")));
            var l = Integer.parseInt(lines[data].substring(lines[data].indexOf("'") + 1));
            
            floors.get(f).getFloorPlan()[r][c] = new Room(name, p, k, l, false);
            
            data++;
            
            while (!lines[data].startsWith("#") && !lines[data].startsWith("S")) {
                var d = lines[data].charAt(lines[data].indexOf('|') + 1);
                var name2 = lines[data].substring(lines[data].indexOf(':') + 1);
                
                floors.get(f).getFloorPlan()[r][c].getWalls().add(new Wall(r, c, d, name2));
                
                data++;
                
                for (var u = 0; u < Integer.parseInt(lines[data].substring(lines[data].indexOf('!') + 1, lines[data].indexOf('a'))); u++) {
                    floors.get(f).getFloorPlan()[r][c].getWalls().get(0).getStorage().add(new Tanto());
                }
                
                for (var v = 0; v < Integer.parseInt(lines[data].substring(lines[data].indexOf('a') + 1, lines[data].indexOf('b'))); v++) {
                    floors.get(f).getFloorPlan()[r][c].getWalls().get(0).getStorage().add(new Wakizashi());
                }
                
                for (var w = 0; w < Integer.parseInt(lines[data].substring(lines[data].indexOf('b') + 1, lines[data].indexOf('c'))); w++) {
                    floors.get(f).getFloorPlan()[r][c].getWalls().get(0).getStorage().add(new Katana());
                }
                
                for (var x = 0; x < Integer.parseInt(lines[data].substring(lines[data].indexOf('c') + 1, lines[data].indexOf('d'))); x++) {
                    floors.get(f).getFloorPlan()[r][c].getWalls().get(0).getStorage().add(new Bo());
                }
                
                for (var y = 0; y < Integer.parseInt(lines[data].substring(lines[data].indexOf('d') + 1, lines[data].indexOf('e'))); y++) {
                    floors.get(f).getFloorPlan()[r][c].getWalls().get(0).getStorage().add(new Shuriken());
                }
                
                for (var z = 0; z < Integer.parseInt(lines[data].substring(lines[data].indexOf('e') + 1)); z++) {
                    floors.get(f).getFloorPlan()[r][c].getWalls().get(0).getStorage().add(new Nunchaku());
                }
                
                data++;
                
                for (var x = 0; x < Integer.parseInt(lines[data].substring(lines[data].indexOf('*') + 1, lines[data].indexOf('a'))); x++) {
                    floors.get(f).getFloorPlan()[r][c].getWalls().get(0).getStorage().add(new HealthPack("Herb", 3));
                }
                
                for (var y = 0; y < Integer.parseInt(lines[data].substring(lines[data].indexOf('a') + 1, lines[data].indexOf('b'))); y++) {
                    floors.get(f).getFloorPlan()[r][c].getWalls().get(0).getStorage().add(new HealthPack("Bushel", 5));
                }
                
                for (var z = 0; z < Integer.parseInt(lines[data].substring(lines[data].indexOf('b') + 1)); z++) {
                    floors.get(f).getFloorPlan()[r][c].getWalls().get(0).getStorage().add(new Coin());
                }
                
                data += 2;
            }
        }
    }
    
    public static void mainMenu() throws IOException {
        fileSize = getFileSize("res/txt/mainMenu1.txt");
        
        var lines = new String[fileSize];
        readFile(lines, "res/txt/mainMenu1.txt");
        
        for (var i = 0; i < fileSize; i++) {
            System.out.println(lines[i]);
        }
        
        System.out.println();
        
        fileSize = getFileSize("res/txt/splashText.txt");
        
        lines = new String[fileSize];
        readFile(lines, "res/txt/splashText.txt");
        
        System.out.println(lines[(int) (Math.random() * lines.length)].toUpperCase());
        
        fileSize = getFileSize("res/txt/mainMenu2.txt");
        
        lines = new String[fileSize];
        readFile(lines, "res/txt/mainMenu2.txt");
        
        for (var i = 0; i < fileSize; i++) {
            System.out.println(lines[i]);
        }
        
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
    
    public static void options() {
        aaa.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        aaa.setSize(300, 200);
        aaa.setVisible(true);
    }
    
    public static void credits() throws IOException {
        fileSize = getFileSize("res/txt/credits.txt");
        
        var a = new String[fileSize];
        readFile(a, "res/txt/credits.txt");
        
        for (var i = 0; i < fileSize; i++) {
            System.out.println(a[i]);
        }
        
        System.out.println();
    }
    
    public static void introduction() throws IOException {
        System.out.println("                        [Nazo No Shiro]");
        System.out.println("                    [XXXXX{================>");
        System.out.println();
        
        fileSize = getFileSize("res/txt/first1-2-A.txt");
        
        var lines1 = new String[fileSize];
        readFile(lines1, "res/txt/first1-2-A.txt");
        
        for (var i = 0; i < 5; i++) {
            System.out.println(lines1[i]);
        }
        
        delay(12);
        
        for (var i = 5; i < 11; i++) {
            System.out.println(lines1[i]);
        }
        
        delay(15);
        
        for (var i = 11; i < 14; i++) {
            System.out.println(lines1[i]);
        }
        
        delay(6);
        
        for (var i = 14; i < 16; i++) {
            System.out.println(lines1[i]);
        }
        
        delay(2);
        
        for (var i = 16; i < 22; i++) {
            System.out.println(lines1[i]);
        }
        
        delay(7);
        
        for (var i = 22; i < 24; i++) {
            System.out.println(lines1[i]);
        }
        
        delay(4);
        
        for (var i = 24; i < 27; i++) {
            System.out.println(lines1[i]);
        }
        
        delay(6);
        
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
        
        fileSize = getFileSize("res/txt/first1-2-B.txt");
        
        var lines2 = new String[fileSize];
        readFile(lines2, "res/txt/first1-2-B.txt");
        
        for (var i = 0; i < 2; i++) {
            System.out.println(lines2[i]);
        }
        
        delay(6);
        
        for (var i = 2; i < 4; i++) {
            System.out.println(lines2[i]);
        }
        
        delay(2);
        
        for (var i = 4; i < 8; i++) {
            System.out.println(lines2[i]);
        }
        
        delay(3);
        
        for (var i = 8; i < 16; i++) {
            System.out.println(lines2[i]);
        }
        
        if (self.getName().length() < 15) {
            var namePadding = 15 - self.getName().length();
            
            System.out.print(lines2[17] + self.getName().toUpperCase());
            
            for (var i = 1; i < namePadding; i++) {
                System.out.print(" ");
            }
            
            System.out.println("         |");
        }
        else {
            System.out.println(lines2[17] + self.getName().substring(0, 14).toUpperCase() + lines2[18]);
        }
        
        System.out.println(lines2[19]);
        System.out.println(lines2[20] + DoB + lines2[21]);
        System.out.println(lines2[22]);
        System.out.println(lines2[23] + gen.toUpperCase() + lines2[24]);
        System.out.println(lines2[25]);
        
        delay(8);
        
        for (var i = 26; i < 28; i++) {
            System.out.println(lines2[i]);
        }
        
        delay(2);
        
        for (var i = 28; i < 33; i++) {
            System.out.println(lines2[i]);
        }
        
        delay(11);
        
        for (var i = 33; i < 35; i++) {
            System.out.println(lines2[i]);
        }
        
        delay(3);
        
        System.out.println();
    }
    
    public static void explore(Room room) throws IOException {
        System.out.println(room);
        System.out.println();
        
        var timing = alreadyVisited(row, column) ? "then" : "first";
        
        fileName = "%s%d-%d.txt".formatted(timing, row, column);
        
        fileSize = getFileSize(fileName);
        
        var description = new String[fileSize];
        readFile(description, fileName);
        
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
        
        if (room.getPuzzle() == 4 && guards[foeIndex].getHP() > 0) {
            fight(guards[foeIndex]);
            
            if (guards[foeIndex].getHP() <= 0) {
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
                
                room.look(self, self.getInventory(), dir);
            }
            else if (choice.startsWith("go")) {
                storeState();
                
                var temp = choice.substring(choice.indexOf(" ") + 1);
                
                move(temp);
            }
            else if (choice.equals("w")) {
                storeState();
                
                move("north");
            }
            else if (choice.equals("a")) {
                storeState();
                
                move("west");
            }
            else if (choice.equals("s")) {
                storeState();
                
                move("south");
            }
            else if (choice.equals("d")) {
                storeState();
                
                move("east");
            }
            else if (choice.equals("room")) {
                storeState();
                
                move("up");
            }
            else if (choice.equals("f")) {
                storeState();
                
                move("down");
            }
            else if (choice.equals("save")) {
                saver.openFile();
                
                
                System.out.print("Saving");
                
                saver.addData(floors, guards, self, row, column, floor, yourTurn);
                
                saver.closeFile();
                
                for (var i = 0; i < (int) (Math.random() * 5) + 3; i++) {
                    System.out.print(".");
                    
                    delay(1);
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
                    
                    delay(1);
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
    
    public static void readFile(String[] words, String fileName) throws IOException {
        var input = new Scanner(new FileReader(fileName));
        
        var i = 0;
        
        String line;
        
        while (input.hasNextLine()) {
            line = input.nextLine();
            
            words[i] = line;
            
            i++;
        }
        
        input.close();
    }
    
    public static void fight(Fighter enemy) throws IOException {
        fileSize = getFileSize("res/txt/directHit.txt");
        var list = new String[fileSize];
        
        readFile(list, "res/txt/directHit.txt");
        
        fileSize = getFileSize("res/txt/indirectHit.txt");
        var list2 = new String[fileSize];
        
        readFile(list2, "res/txt/indirectHit.txt");
        
        fileSize = getFileSize("res/txt/missHit.txt");
        var list3 = new String[fileSize];
        
        readFile(list3, "res/txt/missHit.txt");
        
        fileSize = getFileSize("res/txt/directBlock.txt");
        var list4 = new String[fileSize];
        
        readFile(list4, "res/txt/directBlock.txt");
        
        fileSize = getFileSize("res/txt/indirectBlock.txt");
        var list5 = new String[fileSize];
        
        readFile(list5, "res/txt/indirectBlock.txt");
        
        fileSize = getFileSize("res/txt/missBlock.txt");
        var list6 = new String[fileSize];
        
        readFile(list6, "res/txt/missBlock.txt");
        
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
            
            while (enemy.getHP() > 0 && self.getHP() > 0 && !ran) {
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
                        self.attack(enemy, list, list2, list3);
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
                    
                    enemy.attack(self, list4, list5, list6);
                }
                
                yourTurn = !yourTurn;
                
                if (ran) {
                    var temp = action.substring(action.indexOf(" ") + 1);
                    
                    run(enemy, temp);
                }
                
                if (self.isDead()) {
                    System.out.println("You died.\n");
                }
                
                delay(2);
            }
        }
    }
    
    public static void run(Fighter enemy, String dir) throws IOException {
        speedDiff = ((double) self.getSpd() / enemy.getSpd()) * 50;
        
        var runChance = Math.random() * 100;
        
        var tempRow = row;
        var tempColumn = column;
        
        move(dir);
        
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
    
    public static void delay(int secs) {
        try {
            Thread.sleep(secs * 1000L);
        }
        catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    
    public static void move(String dir) {
        switch (dir) {
            case "north" -> row = north( );
            
            case "south" -> row = south( );
            
            case "east" -> column = east( );
            
            case "west" -> column = west( );
            
            case "up" -> floor = up();
            
            case "down" -> floor = down();
        }
    }
    
    public static void distributeAllItems() {
        for (var castleFloor : floors) {
            for (var r = 0; r < castleFloor.getXSize(); r++) {
                for (var c = 0; c < castleFloor.getYSize(); c++) {
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
    
    public static void showInventory() {
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
                    self.getInventory().get(i).use(self, self.getInventory().get(i).getValue());
                    
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
    
    public static void storeState() {
        self.storeState();
        
        rowLast = row;
        columnLast = column;
        floorLast = floor;
        ranLast = ran;
        
        for (var i = 0; i < self.getKeys1().size(); i++) {
            self.getKeys1().remove(i--);
        }
        
        for (var i = 0; i < self.getKeys().size(); i++) {
            self.getKeys1().add(self.getKeys().get(i));
        }
        
        for (var i = 0; i < self.getInventory1().size(); i++) {
            self.getInventory1().remove(i--);
        }
        
        for (var i = 0; i < self.getInventory().size(); i++) {
            self.getInventory1().add(self.getInventory().get(i));
        }
        
        for (var guard : guards) {
            guard.storeState();
        }
        
        for (var castleFloor : floors) {
            for (var i = 0; i < castleFloor.getXSize(); i++) {
                for (var j = 0; j < castleFloor.getYSize(); j++) {
                    castleFloor.getPuzzles()[i][j].storeState();
                    castleFloor.getRoom(i, j).storeState();
                }
            }
        }
    }
    
    public static void resetState() {
        self.resetState();
        
        row = rowLast;
        column = columnLast;
        floor = floorLast;
        ran = ranLast;
        
        for (var i = 0; i < self.getKeys1().size(); i++) {
            if (self.getKeys().get(i) == null) {
                self.getKeys().add(self.getKeys1().get(i));
            }
            else if (!Objects.equals(self.getKeys().get(i), self.getKeys1().get(i))) {
                self.getKeys().add(i, self.getKeys1().get(i));
            }
        }
        
        for (var i = 0; i < self.getInventory1().size(); i++) {
            if (self.getInventory().get(i) == null) {
                self.getInventory().add(self.getInventory1().get(i));
            }
            else if (self.getInventory().get(i) != self.getInventory1().get(i)) {
                self.getInventory().add(i, self.getInventory1().get(i));
            }
        }
        
        for (var guard : guards) {
            guard.resetState();
        }
        
        for (var castleFloor : floors) {
            for (var i = 0; i < castleFloor.getXSize(); i++) {
                for (var j = 0; j < castleFloor.getYSize(); j++) {
                    castleFloor.getPuzzles()[i][j].resetState();
                    castleFloor.getRoom(i, j).resetState();
                }
            }
        }
    }
    
    public static void endGame() {
        try {
            fileSize = getFileSize("res/txt/endGame.txt");
            
            var text = new String[fileSize];
            readFile(text, "res/txt/endGame.txt");
            
            for (var i = 0; i < fileSize; i++) {
                System.out.println(text[i]);
            }
            
            System.out.println();
        }
        catch (IOException ignored) {
        }
        
        System.exit(0);
    }
    
    public static boolean hasWall(char side) {
        for (var i = 0; i < floors.get(floor).getRoom(row, column).getSize(); i++) {
            if (floors.get(floor).getRoom(row, column).getWalls().get(i).getSide() == side) {
                return true;
            }
        }
        
        return false;
    }
    
    public static boolean northernMost() {
        return row == 0;
    }
    
    public static boolean southernMost() {
        return row == 3;
    }
    
    public static boolean easternMost() {
        return column == 4;
    }
    
    public static boolean westernMost() {
        return column == 0;
    }
    
    public static boolean isExploreOption(String choice) {
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
    
    public static boolean alreadyVisited(int row, int column) {
        return floors.get(floor).getRoom(row, column).getKey() == 99;
    }
    
    public static int north() {
        currentKey = 0;
        
        for (var i = 0; i < self.getKeys().size(); i++) {
            if (hasWall('N') || northernMost()) {
                if (column == 4 && alreadyVisited(2, 4)) {
                    endGame();
                }
        
                System.out.println("A wall blocks your path.\n");
                
                break;
            }
            else if (alreadyVisited(row - 1, column)) {
                row--;
                
                break;
            }
            else if (self.getKeys().get(i).equals(floors.get(floor).getRoom(row - 1, column).getLock())) {
                System.out.println("The door is unlocked.\n");
                
                row--;
                
                break;
            }
            else {
                currentKey++;
                
                if (currentKey == self.getKeys().size()) {
                    System.out.println("None of the keys you have fit that lock.\n");
                    
                    break;
                }
            }
        }
        
        return row;
    }
    
    public static int south() {
        currentKey = 0;
        
        for (var i = 0; i < self.getKeys().size(); i++) {
            if (hasWall('S') || southernMost()) {
                System.out.println("A wall blocks your path.\n");
                
                break;
            }
            else if (alreadyVisited(row + 1, column)) {
                row++;
                
                break;
            }
            else if (self.getKeys().get(i).equals(floors.get(floor).getRoom(row + 1, column).getLock())) {
                System.out.println("The door is unlocked.\n");
                
                row++;
                
                break;
            }
            else {
                currentKey++;
                
                if (currentKey == self.getKeys().size()) {
                    System.out.println("None of the keys you have fit that lock.\n");
                    
                    break;
                }
            }
        }
        
        return row;
    }
    
    public static int east() {
        currentKey = 0;
        
        for (var i = 0; i < self.getKeys().size(); i++) {
            if (hasWall('E') || easternMost()) {
                System.out.println("A wall blocks your path.\n");
                
                break;
            }
            else if (alreadyVisited(row, column + 1)) {
                column++;
                
                break;
            }
            else if (self.getKeys().get(i).equals(floors.get(floor).getRoom(row, column + 1).getLock())) {
                System.out.println("The door is unlocked.\n");
                
                column++;
                
                break;
            }
            else {
                currentKey++;
                
                if (currentKey == self.getKeys().size()) {
                    System.out.println("None of the keys you have fit that lock.\n");
                    
                    break;
                }
            }
        }
        
        return column;
    }
    
    public static int west() {
        currentKey = 0;
        
        for (var i = 0; i < self.getKeys().size(); i++) {
            if (hasWall('W') || westernMost()) {
                System.out.println("A wall blocks your path.\n");
                
                break;
            }
            else if (alreadyVisited(row, column - 1)) {
                column--;
                
                break;
            }
            else if (self.getKeys().get(i).equals(floors.get(floor).getRoom(row, column - 1).getLock())) {
                System.out.println("The door is unlocked.\n");
                
                column--;
                
                break;
            }
            else {
                currentKey++;
                
                if (currentKey == self.getKeys().size()) {
                    System.out.println("None of the keys you have fit that lock.\n");
                    
                    break;
                }
            }
        }
        
        return column;
    }
    
    public static int up() {
        floor++;
        
        return floor;
    }
    
    public static int down() {
        floor--;
        
        return floor;
    }
    
    public static int getFileSize(String fileName) throws IOException {
        var input = new Scanner(new FileReader(fileName));
        
        var size = 0;
        
        while (input.hasNextLine()) {
            size++;
            
            input.nextLine();
        }
        
        input.close();
        
        return size;
    }
    
    public static int input(String[] lines, String fileName) throws Exception {
        var reader = new BufferedReader(new FileReader(fileName));
        
        var index = 0;
        
        var line = reader.readLine();
        
        while (line != null) {
            lines[index] = line;
        
            index++;
        
            line = reader.readLine();
        }
        
        reader.close();
        
        return index;
    }
}