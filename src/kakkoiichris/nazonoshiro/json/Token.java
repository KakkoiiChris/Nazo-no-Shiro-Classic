// Christian Alexander, 12/7/2022
package kakkoiichris.nazonoshiro.json;

import java.util.StringJoiner;

public record Token(Location location, Type type) {
    @Override
    public String toString() {
        return new StringJoiner(", ", Token.class.getSimpleName() + "[", "]")
            .add("location=" + location)
            .add("type=" + type)
            .toString();
    }
    
    public interface Type {
        enum Symbol implements Type {
            LEFT_BRACE('{'),
            RIGHT_BRACE('}'),
            LEFT_SQUARE('['),
            RIGHT_SQUARE(']'),
            COMMA(','),
            COLON(':'),
            END_OF_FILE('0');
            
            private final char rep;
            
            Symbol(char rep) {
                this.rep = rep;
            }
            
            @Override
            public java.lang.String toString() {
                return java.lang.String.valueOf(rep);
            }
        }
        
        record Literal(Object value) implements Type {
            @Override
            public java.lang.String toString(){
                return "Boolean: %s".formatted(value);
            }
        }
        
        record Number(double value) implements Type {
            @Override
            public java.lang.String toString(){
                return "Number: %s".formatted(value);
            }
        }
        
        record String(java.lang.String value) implements Type {
            @Override
            public java.lang.String toString(){
                return "String: %s".formatted(value);
            }
        }
    }
}
