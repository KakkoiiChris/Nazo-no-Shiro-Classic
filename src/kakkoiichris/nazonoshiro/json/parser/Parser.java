// Christian Alexander, 12/7/2022
package kakkoiichris.nazonoshiro.json.parser;

import kakkoiichris.nazonoshiro.json.lexer.Lexer;
import kakkoiichris.nazonoshiro.json.lexer.Location;
import kakkoiichris.nazonoshiro.json.lexer.Token;

import java.util.ArrayList;
import java.util.HashMap;

public class Parser {
    private final Lexer lexer;
    
    private Token token;
    
    public Parser(Lexer lexer) {
        this.lexer = lexer;
        
        token = lexer.next();
    }
    
    private Location here() {
        return token.location();
    }
    
    private boolean match(Token.Type type) {
        return token.type() == type;
    }
    
    private void step() {
        if (lexer.hasNext()) {
            token = lexer.next();
        }
    }
    
    private boolean skip(Token.Type type) {
        if (match(type)) {
            step();
            
            return true;
        }
        
        return false;
    }
    
    private void mustSkip(Token.Type type) {
        if (!skip(type)) {
            throw new RuntimeException();
        }
    }
    
    public Object parse() {
        return object();
    }
    
    private Node node() {
        if (match(Token.Type.LEFT_BRACE)) {
            return object();
        }
        
        if (match(Token.Type.LEFT_SQUARE)) {
            return array();
        }
        
        if (match(Token.Type.LITERAL)) {
            return literal();
        }
        
        if (match(Token.Type.NUMBER)) {
            return number();
        }
        
        if (match(Token.Type.STRING)) {
            return string();
        }
        
        throw new RuntimeException();
    }
    
    private Object object() {
        var location = here();
        
        var members = new HashMap<String, Node>();
        
        mustSkip(Token.Type.LEFT_BRACE);
        
        if (!skip(Token.Type.RIGHT_BRACE)) {
            do {
                var key = token;
                
                mustSkip(Token.Type.STRING);
                
                mustSkip(Token.Type.COLON);
                
                var value = node();
                
                members.put((String) key.value(), value);
            }
            while (skip(Token.Type.COMMA));
            
            mustSkip(Token.Type.RIGHT_BRACE);
        }
        
        return new Object(location, members);
    }
    
    private Array array() {
        var location = here();
        
        var elements = new ArrayList<Node>();
        
        mustSkip(Token.Type.LEFT_SQUARE);
        
        if (!skip(Token.Type.RIGHT_SQUARE)) {
            do {
                var element = node();
                
                elements.add(element);
            }
            while (skip(Token.Type.COMMA));
            
            mustSkip(Token.Type.RIGHT_SQUARE);
        }
        
        return new Array(location, elements);
    }
    
    private Value literal() {
        var location = here();
        
        var value = token;
        
        mustSkip(Token.Type.LITERAL);
        
        return new Value(location, value.value());
    }
    
    private Value number() {
        var location = here();
        
        var value = token;
        
        mustSkip(Token.Type.NUMBER);
        
        return new Value(location, value.value());
    }
    
    private Value string() {
        var location = here();
        
        var value = token;
        
        mustSkip(Token.Type.STRING);
        
        return new Value(location, value.value());
    }
}
