// Christian Alexander, 5/12/11, Pd. 6
package kakkoiichris.nazonoshiro;

public class Main {
    private static Game game;
    
    public static void main(String[] args) {
        mainMenu();
        
        Console.close();
    }
    
    private static void mainMenu() {
        var playing = true;
        
        while (playing) {
            var fileName = (Math.random() > 0.001) ? "title.txt" : "tlite.txt";
            
            var title = Resources.getString(fileName);
            var splash = Util.getRandom(Resources.getLines("splashText.txt"));
            
            Console.writeLine(title, splash);
            
            var inMenu = true;
            
            while (inMenu) {
                Console.writeLine("""
                    1) New Game
                    2) Load Game
                    3) Continue
                    4) Options
                    5) Credits
                    6) Quit
                    """.stripIndent());
                
                Console.setPrompt("> ");
                
                var action = Console.readLine().trim().toLowerCase();
                
                Console.newLine();
                
                if (action.matches("1|(new(\\s+game)?)")) {
                    newGame();
                    
                    inMenu = false;
                }
                else if (action.matches("2|(load(\\s+game)?)")) {
                    loadGame();
                    
                    inMenu = false;
                }
                else if (action.matches("3|(continue(\\s+game)?)")) {
                    continueGame();
    
                    inMenu = false;
                }
                else if (action.matches("4|options|settings")) {
                    options();
                }
                else if (action.matches("5|credits")) {
                    credits();
                }
                else if (action.matches("6|quit(\\s+game)")) {
                    playing = inMenu = false;
                }
                else {
                    Console.writeLine("You don't know how to '%s'...%n", action);
                }
            }
        }
    }
    
    private static void newGame() {
        game = new Game();
        
        game.newGame();
        
        game.play();
    }
    
    
    private static void loadGame() {
        game = new Game();
        
        game.loadGame();
        
        game.play();
    }
    
    private static void continueGame() {
        if (game == null){
            Console.writeLine("There is no game to continue.\n");
            
            return;
        }
        
        game.play();
    }
    
    private static void options() {
    }
    
    private static void credits() {
        var lines = Resources.getLines("credits.txt");
        
        lines.forEach(Console::writeLine);
        
        Console.newLine();
    }
}
