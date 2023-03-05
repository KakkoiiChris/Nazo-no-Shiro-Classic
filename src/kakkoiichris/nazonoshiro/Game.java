// Christian Alexander, 12/4/2022
package kakkoiichris.nazonoshiro;

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
    private final ResetValue<Integer> floor = new ResetValue<>(0); //the floor you are located in
    private final ResetValue<Integer> row = new ResetValue<>(1); //the row you are located in
    private final ResetValue<Integer> column = new ResetValue<>(2); //the column you are located in
    
    private final ResetValue<Boolean> ran = new ResetValue<>(false); //keeps track of if you run from a fight
    
    private Castle castle;
    private Self self = new Self(); //this fighter represents you
    
    private ResetGroup resetGroup;
    
    private boolean exploring = true;
    
    public void newGame() {
        Console.setPrompt("Map Name > ");
        
        var mapName = Console.readLine();
        
        Console.newLine();
        
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
        
        var vars = script.run();
        
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
            
            Console.writeLine("[ %s ]%n", room);
            
            if (room instanceof EnemyRoom e && !e.isDefeated()) {
                var result = Fighter.fight(self, e.getEnemy());
                
                switch (result) {
                    case WIN -> {
                        e.setDefeated();
                        
                        var drop = e.getEnemy().getDrop();
                        
                        if (drop == null) {
                            drop = Coin.random();
                        }
                        
                        Console.writeLine("""
                            The %s dropped something...
                            
                            It's a(n) %s!
                            
                            They also dropped a key. Now to
                            find which door it unlocks...
                            """.stripIndent(), e.getEnemy(), drop);
                        
                        self.setKey(room.getKey());
                    }
                    
                    case RUN -> {
                        ran.set(true);
                        
                        Console.writeLine("That was a close one...");
                    }
                    
                    case LOSE -> {
                        Console.writeLine("You died...\n\nContinue?\n");
                        
                        resetState();
                        
                        Console.setPrompt("Y / N > ");
                        
                        var choice = "";
                        
                        do {
                            choice = Console.readLine();
                        }
                        while (!choice.matches("[YyNn]"));
                        
                        if (choice.equalsIgnoreCase("n")) {
                            exploring = false;
                        }
                    }
                }
                
                continue;
            }
            
            Console.writeLine("%s%n", room.getDescription());
            
            room.setVisited();
            
            for (var wall : room.getWalls().values()) {
                Console.writeLine("There is a %s to the %s.", wall.getStorage().getName(), wall.getDirection());
            }
            
            Console.setPrompt("> ");
            
            var choice = Console.readLine().toLowerCase();
            
            Console.newLine();
            
            var matcher = Pattern.compile("(play|solve)( puzzle)?").matcher(choice);
            
            if (matcher.find() && room instanceof PuzzleRoom p && !p.getPuzzle().isWon()) {
                if (p.getPuzzle().play()) {
                    Console.writeLine("""
                        You won!
                        
                        You have earned a key.
                        
                        Now to figure out which door it unlocks...
                        """.stripIndent());
                    
                    self.setKey(p.getKey());
                }
                else {
                    Console.writeLine("""
                        The puzzle clicks and whirs away,
                        and just like that, the puzzle is
                        reset. Better luck next time...""");
                }
                
                continue;
            }
            
            matcher = Pattern.compile("^(((play|show)\\s+inventory)|e)$").matcher(choice);
            
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
                    Console.write("'%s' is not a valid direction.", direction);
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
                Console.write("Saving");
                
                continue;
            }
            
            if (choice.equals("snq")) {
                Console.write("Saving");
                
                break;
            }
            
            if (choice.equals("quit")) {
                break;
            }
            
            // TODO: HACKS FOR TESTING! DO NOT SHIP!
            
            if (choice.equals("skip")) {
                var key = room.getKey();
                
                Console.writeLine("Set player key to '%d'.%n", key);
                
                self.setKey(key);
                
                continue;
            }
            
            if (choice.equals("cheat")) {
                Console.writeLine("Set player key to '99'.\n");
                
                self.setKey(99);
                
                continue;
            }
            
            if (choice.equals("fight")) {
                self.storeState();
                
                Console.writeLine(Fighter.fight(self, new Ninja()));
                
                self.resetState();
                
                continue;
            }
            
            Console.write("You don't know how to '%s'.%n%n", choice);
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
            Console.writeLine("A wall blocks your path.\n");
            
            return;
        }
        
        f += direction.getDeltaFloor();
        r += direction.getDeltaRow();
        c += direction.getDeltaColumn();
        
        if (self.getKey() >= castle.get(f, r, c).getLock()) {
            if (notVisited(f, r, c)) {
                Console.writeLine("The door is unlocked.\n");
            }
            
            floor.set(f);
            row.set(r);
            column.set(c);
            
            return;
        }
        
        Console.writeLine("None of the keys you have fit this lock.\n");
    }
    
    private void showInventory() {
        var action = "";
        
        while (!action.matches("(clos)?e")) {
            Console.writeLine("[ Inventory ]");
            
            var coins = self.getInventory()
                .stream()
                .filter(item -> item instanceof Coin)
                .map(Coin.class::cast)
                .toList();
            
            if (!coins.isEmpty()) {
                Console.writeLine("- Coins (%s)", Coin.getTotalString(coins));
            }
            
            var items = self.getInventory()
                .stream()
                .filter(item -> !(item instanceof Coin))
                .collect(Collectors.groupingBy(Item::getName));
            
            for (var key : items.keySet()) {
                var count = items.get(key).size();
                
                Console.writeLine("- %s (%d)", key, count);
            }
            
            Console.setPrompt("> ");
            
            action = Console.readLine().toLowerCase();
            
            Console.newLine();
        }
    }
    
    private void storeState() {
        resetGroup.storeState();
    }
    
    private void resetState() {
        resetGroup.resetState();
    }
    
    private void endGame() {
        var text = Resources.getLines("endGame.txt");
        
        text.forEach(Console::writeLine);
        
        Console.newLine();
        
        exploring = false;
    }
}
