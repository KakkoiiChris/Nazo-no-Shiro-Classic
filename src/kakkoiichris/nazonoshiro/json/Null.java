// Christian Alexander, 12/7/2022
package kakkoiichris.nazonoshiro.json;

public class Null {
    private static Null instance;
    
    public static Null get() {
        if (instance == null) {
            instance = new Null();
        }
        
        return instance;
    }
    
    private Null() {
    }
    
    @Override
    public String toString() {
        return "null";
    }
}
