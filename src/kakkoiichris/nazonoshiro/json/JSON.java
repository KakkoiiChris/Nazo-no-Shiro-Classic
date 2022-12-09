// Christian Alexander, 12/9/2022
package kakkoiichris.nazonoshiro.json;

public class JSON<X> {
    private Node.Object root;
    
    public JSON(String source) {
        var lexer = new Lexer(source);
        
        var parser = new Parser(lexer);
        
        root = parser.parse();
    }
    
    public JSON() {
        this("{}");
    }
    
    public JSONConvertible<X> load() {
        return
    }
    
    public void save(JSONConvertible<X> convertible) {
        root = convertible.save();
    }
}
