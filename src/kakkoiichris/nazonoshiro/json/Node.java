// Christian Alexander, 12/7/2022
package kakkoiichris.nazonoshiro.json;

import java.util.List;
import java.util.Map;

public interface Node {
    Location location();
    
    record Value(Location location, java.lang.Object value) implements Node {
    }
    
    record Array(Location location, List<Node> elements) implements Node {
    }
    
    record Object(Location location, Map<String, Node> members) implements Node {
    }
}
