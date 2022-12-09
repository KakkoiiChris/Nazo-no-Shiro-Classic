// Christian Alexander, 12/9/2022
package kakkoiichris.nazonoshiro;

import kakkoiichris.nazonoshiro.json.*;

public class JSONTest {
    public static void main(String[] args) {
        var json = new JSON<Vector>();
    
        {
            var vector = new Vector(6, 9);
    
            System.out.println(vector);
            
            json.save(vector);
        }
    
        var vector = json.load();
    
        System.out.println(vector);
    }
    
    record Vector(double x, double y) implements JSONConvertible<Vector> {
        @Override
        public Vector load(Node.Object object) {
            var xNode = object.members().get("x");
            var yNode = object.members().get("x");
    
            var x = (double)((Node.Value) xNode).value();
            var y = (double)((Node.Value) yNode).value();
            
            return new Vector(x, y);
        }
    
        @Override
        public Node.Object save() {
            var source = """
                { "x" : %f, "y" : %f }""".formatted(x, y);
            
            var lexer = new Lexer(source);
            
            var parser = new Parser(lexer);
            
            return parser.parse();
        }
    }
}
