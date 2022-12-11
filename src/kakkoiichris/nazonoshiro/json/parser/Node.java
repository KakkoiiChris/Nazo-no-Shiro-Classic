// Christian Alexander, 12/7/2022
package kakkoiichris.nazonoshiro.json.parser;

import kakkoiichris.nazonoshiro.json.lexer.Location;

import java.util.List;
import java.util.Optional;

public sealed interface Node permits Array, Value, Object {
    Location location();
    
    default Optional<Value> asValue() {
        if (this instanceof Value value) {
            return Optional.of(value);
        }
        
        return Optional.empty();
    }
    
    default Optional<Boolean> asBoolean() {
        var value = asValue();
        
        if (value.isPresent() && value.get().value() instanceof Boolean b) {
            return Optional.of(b);
        }
        
        return Optional.empty();
    }
    
    default Optional<Double> asNumber() {
        var value = asValue();
        
        if (value.isPresent() && value.get().value() instanceof Double d) {
            return Optional.of(d);
        }
        
        return Optional.empty();
    }
    
    default Optional<String> asString() {
        var value = asValue();
        
        if (value.isPresent() && value.get().value() instanceof String s) {
            return Optional.of(s);
        }
        
        return Optional.empty();
    }
    
    default Optional<Array> asArray() {
        if (this instanceof Array array) {
            return Optional.of(array);
        }
        
        return Optional.empty();
    }
    
    default Optional<List<Array>> asArrayArray() {
        var array = asArray();
        
        if (array.isPresent()) {
            return Optional.of(array.flatMap(Node::asArray).stream().toList());
        }
        
        return Optional.empty();
    }
    
    default Optional<List<Object>> asObjectArray() {
        var array = asArray();
        
        if (array.isPresent()) {
            return Optional.of(array.flatMap(Node::asObject).stream().toList());
        }
        
        return Optional.empty();
    }
    
    default Optional<Object> asObject() {
        if (this instanceof Object object) {
            return Optional.of(object);
        }
        
        return Optional.empty();
    }
}
