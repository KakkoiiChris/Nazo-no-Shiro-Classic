// Christian Alexander, 12/9/2022
package kakkoiichris.nazonoshiro.json;

import kakkoiichris.nazonoshiro.json.parser.Node;
import kakkoiichris.nazonoshiro.json.parser.Object;

public class Json<X> {
    private final JsonConverter<X> converter;
    
    private Object root;
    
    public Json(JsonConverter<X> converter) {
        this.converter = converter;
    }
    
    public Node get(String name) {
        return root.members().get(name);
    }
    
    public X load() {
        return converter.load(root);
    }
    
    public void save(X x) {
        root = converter.save(x);
    }
}
