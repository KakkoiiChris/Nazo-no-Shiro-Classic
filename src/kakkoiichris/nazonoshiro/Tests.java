// Christian Alexander, 12/13/2022
package kakkoiichris.nazonoshiro;

public class Tests {
    public static void main(String[] args) {
        var max = 20;
        
        var now = 20;
        
        var blocks = " ▘▚▜█";
        
        while (now >= 0) {
            Console.write("%03d / %03d ║", now, max);
            
            var i = now;
            
            if (i > 0) {
                while (i - 4 > 0) {
                    Console.write(blocks.charAt(4));
                    
                    i -= 4;
                }
                
                Console.write(blocks.charAt(i));
            }
            
            i = max - now;
            
            while (i - 4 >= 0) {
                Console.write(' ');
                
                i -= 4;
            }
            
            Console.writeLine('║');
            
            now--;
        }
    }
}
