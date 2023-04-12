// Christian Alexander, 12/4/2022
package kakkoiichris.nazonoshiro;

import kakkoiichris.kotoba.Console;
import kakkoiichris.nazonoshiro.castle.Castle;
import kakkoiichris.nazonoshiro.castle.Direction;
import kakkoiichris.nazonoshiro.castle.room.EnemyRoom;
import kakkoiichris.nazonoshiro.castle.room.PuzzleRoom;
import kakkoiichris.nazonoshiro.fighter.Fighter;
import kakkoiichris.nazonoshiro.fighter.Ninja;
import kakkoiichris.nazonoshiro.fighter.Self;
import kakkoiichris.nazonoshiro.item.Coin;
import kakkoiichris.nazonoshiro.item.HealthPack;
import kakkoiichris.nazonoshiro.item.Item;
import kakkoiichris.nazonoshiro.item.Weapon;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Game {
    private final Console console;
    
    private final ResetValue<Integer> floor = new ResetValue<>(0); //the floor you are located in
    private final ResetValue<Integer> row = new ResetValue<>(1); //the row you are located in
    private final ResetValue<Integer> column = new ResetValue<>(2); //the column you are located in
    
    private final ResetValue<Boolean> ran = new ResetValue<>(false); //keeps track of if you run from a fight
    
    private Castle castle;
    private Self self = new Self(); //this fighter represents you
    
    private ResetGroup resetGroup;
    
    private boolean exploring = true;
    
    private boolean visiting = true;
    
    public Game(Console console) {
        this.console = console;
    }
    
    public void newGame() {
        console.setPrompt("Map Name > ");
        
        var mapName = console.readLine().orElseThrow();
        
        console.newLine();
        
        if (mapName.isEmpty()) mapName = "original";
        
        castle = Castle.load("%s.json".formatted(mapName));
        
        resetGroup = ResetGroup.of(floor, row, column, ran, castle);
        
        var items = new ArrayList<Item>();
        
        for (var count = 0; count < Util.random.nextInt(500, 1_000); count++) {
            items.add(Coin.random());
        }
        
        for (var count = 0; count < Util.random.nextInt(100, 250); count++) {
            items.add(HealthPack.HERB);
        }
        
        for (var count = 0; count < Util.random.nextInt(50, 100); count++) {
            items.add(HealthPack.BUSHEL);
        }
        
        Util.shuffle(items);
        
        distributeItems(items);
        
        introduction();
        
        self.add(Weapon.KATANA);
        self.add(Weapon.TANTO);
    }
    
    private void distributeItems(List<Item> items) {
        while (!items.isEmpty()) {
            castle.distributeItems(items);
        }
    }
    
    private void introduction() {
        var source = Resources.getLines("intro.txt");
        
        var script = new Script(source);
        
        var vars = script.run(console);
        
        var name = vars.get("name");
        
        if (name.isEmpty()) name = "Self";
        
        var birthday = vars.get("birthday");
        
        if (birthday.isEmpty()) birthday = "01/01/2000";
        
        var gender = vars.get("gender");
        
        if (gender.isEmpty()) gender = "o";
        
        self = new Self(name, birthday, gender);
        
        resetGroup.add(self);
    }
    
    public void loadGame() {
    }
    
    public void play() {
        exploring = true;
        
        while (exploring) {
            var room = castle.get(floor.get(), row.get(), column.get());
            
            console.writeLine("[ %s ]%n", room);
            
            visiting = true;
            
            while (visiting) {
                if (room instanceof EnemyRoom e && !e.isDefeated()) {
                    var result = Fighter.fight(console, self, e.getEnemy());
                    
                    switch (result) {
                        case WIN -> {
                            e.setDefeated();
                            
                            var drop = e.getEnemy().getDrop();
                            
                            if (drop == null) {
                                drop = Coin.random();
                            }
                            
                            console.writeLine("""
                                The %s dropped something...
                                
                                It's a(n) %s!
                                
                                They also dropped a key. Now to
                                find which door it unlocks...
                                """.stripIndent(), e.getEnemy(), drop);
                            
                            self.setKey(room.getKey());
                        }
                        
                        case RUN -> {
                            ran.set(true);
                            
                            console.writeLine("That was a close one...");
                        }
                        
                        case LOSE -> {
                            console.writeLine("You died...\n\nContinue?\n");
                            
                            resetState();
                            
                            console.setPrompt("Y / N > ");
                            
                            var choice = "";
                            
                            do {
                                choice = console.readLine().orElseThrow();
                            }
                            while (!choice.matches("[YyNn]"));
                            
                            if (choice.equalsIgnoreCase("n")) {
                                exploring = visiting = false;
                            }
                        }
                    }
                    
                    continue;
                }
                
                console.writeLine("%s%n", room.getDescription());
                
                room.setVisited();
                
                console.setPrompt("%s > ".formatted(room.getName()));
                
                var choice = console.readLine().orElseThrow().toLowerCase();
                
                console.newLine();
                
                var matcher = Pattern.compile("(play|solve)( puzzle)?").matcher(choice);
                
                if (matcher.find() && room instanceof PuzzleRoom p && !p.getPuzzle().isWon()) {
                    if (p.getPuzzle().play(console)) {
                        console.writeLine("""
                            You won!
                            
                            You have earned a key.
                            
                            Now to figure out which door it unlocks...
                            """.stripIndent());
                        
                        self.setKey(p.getKey());
                    }
                    else {
                        console.writeLine("""
                            The puzzle clicks and whirs away, and
                            in a matter of seconds, it resets itself.
                            Better luck next time...""");
                    }
                    
                    continue;
                }
                
                matcher = Pattern.compile("^((show\\s+inventory)|e)$").matcher(choice);
                
                if (matcher.find()) {
                    showInventory();
                    
                    continue;
                }
                
                matcher = Pattern.compile("search (.+)").matcher(choice);
                
                if (matcher.find()) {
                    var option = matcher.group(1);
                    var direction = Direction.NONE;
                    
                    if (Direction.isValid(option.toUpperCase())) {
                        direction = Direction.valueOf(option.toUpperCase());
                    }
                    else {
                        for (var wall : room.getWalls().values()) {
                            if (wall.getStorage().getName().toLowerCase().equals(option)) {
                                direction = wall.getDirection();
                            }
                        }
                    }
                    
                    room.search(console, direction, self);
                    
                    continue;
                }
                
                matcher = Pattern.compile("look (.+)").matcher(choice);
                
                if (matcher.find()) {
                    var directionName = matcher.group(1);
                    
                    var direction = Direction.valueOf(directionName.toUpperCase());
                    
                    room.look(console, direction);
                    
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
                        console.writeLine("'%s' is not a valid direction.", direction);
                    }
                    
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
                    console.write("Saving");
                    
                    continue;
                }
                
                if (choice.equals("snq")) {
                    console.write("Saving");
                    
                    exploring = visiting = false;
                    
                    break;
                }
                
                if (choice.equals("quit")) {
                    exploring = visiting = false;
                    
                    break;
                }
                
                // TODO: HACKS FOR TESTING! DO NOT SHIP!
                
                if (choice.equals("skip")) {
                    var key = room.getKey();
                    
                    console.writeLine("Set player key to '%d'.%n", key);
                    
                    self.setKey(key);
                    
                    continue;
                }
                
                if (choice.equals("cheat")) {
                    console.writeLine("Set player key to '99'.\n");
                    
                    self.setKey(99);
                    
                    continue;
                }
                
                if (choice.equals("fight")) {
                    self.storeState();
                    
                    console.writeLine("%s%n", Fighter.fight(console, self, new Ninja()));
                    
                    self.resetState();
                    
                    continue;
                }
                
                console.writeLine("You don't know how to '%s'.%n", choice);
            }
        }
    }
    
    private void move(Direction direction) {
        floor.storeState();
        row.storeState();
        column.storeState();
        
        go(direction);
    }
    
    private boolean notVisited(int floor, int row, int column) {
        return !castle.get(floor, row, column).isVisited();
    }
    
    private boolean hasWall(Direction direction) {
        return castle.get(floor.get(), row.get(), column.get()).hasWall(direction);
    }
    
    private void go(Direction direction) {
        var f = floor.get();
        var r = row.get();
        var c = column.get();
        
        if (castle.get(f, r, c).getExit() == direction) {
            endGame();
            
            return;
        }
        
        if (hasWall(direction)) {
            console.writeLine("A wall blocks your path.\n");
            
            return;
        }
        
        f += direction.getDeltaFloor();
        r += direction.getDeltaRow();
        c += direction.getDeltaColumn();
        
        if (self.getKey() >= castle.get(f, r, c).getLock()) {
            if (notVisited(f, r, c)) {
                console.writeLine("The door is unlocked.\n");
            }
            
            floor.set(f);
            row.set(r);
            column.set(c);
            
            visiting = false;
            
            return;
        }
        
        console.writeLine("None of the keys you have fit this lock.\n");
    }
    
    private void showInventory() {
        console.pushPrompt("Inventory > ");
        
        var action = "";
        
        while (!action.matches("(clos)?e")) {
            var coins = self.getInventory()
                .stream()
                .filter(item -> item instanceof Coin)
                .map(Coin.class::cast)
                .toList();
            
            if (!coins.isEmpty()) {
                console.writeLine("- Coins (%s)", Coin.getTotalString(coins));
            }
            
            var items = self.getInventory()
                .stream()
                .filter(item -> !(item instanceof Coin))
                .collect(Collectors.groupingBy(Item::getName));
            
            for (var key : items.keySet()) {
                var count = items.get(key).size();
                
                console.writeLine("- %s (%d)", key, count);
            }
            
            console.newLine();
            
            action = console.readLine().orElseThrow().toLowerCase();
            
            console.newLine();
        }
        
        console.popPrompt();
    }
    
    private void storeState() {
        resetGroup.storeState();
    }
    
    private void resetState() {
        resetGroup.resetState();
    }
    
    private void endGame() {
        var text = Resources.getLines("endGame.txt");
        
        text.forEach(console::writeLine);
        
        console.newLine();
        
        visiting = exploring = false;
    }
}
