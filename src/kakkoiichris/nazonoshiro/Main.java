// Christian Alexander, 5/12/11, Pd. 6
package kakkoiichris.nazonoshiro;

public class Main {
    private static Game game;
    
    public static void main(String[] args) {
        mainMenu();
    }
    
    private static void mainMenu() {
        var playing = true;
        
        while (playing) {
            var fileName = (Math.random() > 0.001) ? "title" : "tlite";
            
            var title = Resources.getString(fileName);
            var splash = Util.getRandom(Resources.getLines("splashText"));
            
            System.out.printf(title, splash);
            
            var inMenu = true;
            
            while (inMenu) {
                System.out.println("""
                    1) New Game
                    2) Load Game
                    3) Options
                    4) Credits
                    5) Quit""".stripIndent());
                
                System.out.print("> ");
                
                var action = Util.input.nextLine().trim().toLowerCase();
                
                if (action.matches("1|(new(\s+game)?)")) {
                    setUpNew();
                    
                    inMenu = false;
                }
                else if (action.matches("2|(load(\s+game)?)")) {
                    setUpLoad();
                    
                    inMenu = false;
                }
                else if (action.matches("3|options|settings")) {
                    options();
                }
                else if (action.matches("4|credits")) {
                    credits();
                }
                else if (action.matches("5|quit")) {
                    playing = inMenu = false;
                }
                else {
                    System.out.printf("I don't know how to '%s'...%n%n", action);
                }
            }
        }
    }
    
    private static void setUpNew() {
        game = new Game();
        
        game.playNewGame();
    }
    
    
    private static void setUpLoad() {
    }
    
    private static void options() {
    }
    
    private static void credits() {
        var lines = Resources.getLines("credits");
        
        lines.forEach(System.out::println);
        
        System.out.println();
    }
}
