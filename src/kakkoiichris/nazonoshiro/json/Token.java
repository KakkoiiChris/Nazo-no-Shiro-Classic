// Christian Alexander, 12/7/2022
package kakkoiichris.nazonoshiro.json;

import java.util.StringJoiner;

public record Token(Location location, Type type, Object value) {
    public Token(Location location, Type type) {
        this(location, type, null);
    }
    
    @Override
    public String toString() {
        var joiner = new StringJoiner(", ", Token.class.getSimpleName() + "[", "]")
            .add("location=" + location)
            .add("type=" + type);
        
        if (value != null) {
            joiner.add("type=" + type);
        }
        
        return joiner.toString();
    }
    
    public enum Type {
        LITERAL("ABC"),
        NUMBER("123"),
        STRING("\"S\""),
        LEFT_BRACE("{"),
        RIGHT_BRACE("}"),
        LEFT_SQUARE("["),
        RIGHT_SQUARE("]"),
        COMMA(","),
        COLON(":"),
        END_OF_FILE("0");
        
        private final String rep;
        
        Type(String rep) {
            this.rep = rep;
        }
        
        @Override
        public java.lang.String toString() {
            return rep;
        }
    }
}
