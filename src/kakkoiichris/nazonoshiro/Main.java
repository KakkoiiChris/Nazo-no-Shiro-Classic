// Christian Alexander, 5/12/11, Pd. 6
package kakkoiichris.nazonoshiro;

import kakkoiichris.kotoba.Console;

public class Main {
    private static Game game;
    
    public static void main(String[] args) {
        var console = new Console(
            new Console.Config()
                .prompt("> ")
                .title("Nazo no Shiro")
        );
        
        try {
            console.open();
            
            mainMenu(console);
        }
        finally {
            console.close();
        }
    }
    
    private static void mainMenu(Console console) {
        var playing = true;
        
        while (playing) {
            var fileName = (Math.random() > 0.001) ? "title.txt" : "tlite.txt";
            
            var title = Resources.getString(fileName);
            var splash = Util.getRandom(Resources.getLines("splashText.txt"));
            
            console.writeLine(title, splash);
            
            var inMenu = true;
            
            while (inMenu) {
                console.writeLine("""
                    1) New Game
                    2) Load Game
                    3) Continue
                    4) Options
                    5) Credits
                    6) Quit
                    """.stripIndent());
                
                console.setPrompt("> ");
                
                var action = console.readLine().orElseThrow().trim().toLowerCase();
                
                console.newLine();
                
                if (action.matches("1|(new(\\s+game)?)")) {
                    newGame(console);
                    
                    inMenu = false;
                }
                else if (action.matches("2|(load(\\s+game)?)")) {
                    loadGame(console);
                    
                    inMenu = false;
                }
                else if (action.matches("3|(continue(\\s+game)?)")) {
                    continueGame(console);
                    
                    inMenu = false;
                }
                else if (action.matches("4|options|settings")) {
                    options(console);
                }
                else if (action.matches("5|credits")) {
                    credits(console);
                }
                else if (action.matches("6|quit(\\s+game)")) {
                    playing = inMenu = false;
                }
                else {
                    console.writeLine("You don't know how to '%s'...%n", action);
                }
            }
        }
    }
    
    private static void newGame(Console console) {
        game = new Game(console);
        
        game.newGame();
        
        game.play();
    }
    
    
    private static void loadGame(Console console) {
        game = new Game(console);
        
        game.loadGame();
        
        game.play();
    }
    
    private static void continueGame(Console console) {
        if (game == null) {
            console.writeLine("There is no game to continue.\n");
            
            return;
        }
        
        game.play();
    }
    
    private static void options(Console console) {
    }
    
    private static void credits(Console console) {
        var lines = Resources.getLines("credits.txt");
        
        lines.forEach(console::writeLine);
        
        console.newLine();
    }
}
