// Christian Alexander, 12/7/2022
package kakkoiichris.nazonoshiro.json.lexer;

import kakkoiichris.nazonoshiro.json.JsonNull;

import java.util.Iterator;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;

public class Lexer implements Iterator<Token> {
    private static final char NUL = '\0';
    
    private static final Map<String, Object> LITERALS = Map.of(
        "true", true,
        "false", false,
        "null", JsonNull.get()
    );
    
    private final String source;
    
    private int position = 0, row = 1, column = 1;
    
    public Lexer(String source) {
        this.source = source;
    }
    
    @Override
    public boolean hasNext() {
        return position <= source.length();
    }
    
    @Override
    public Token next() {
        while (!match(NUL)) {
            if (match(Character::isWhitespace)) {
                skipWhitespace();
                
                continue;
            }
            
            if (match(Character::isLetter)) {
                return literal();
            }
            
            if (match(Character::isDigit)) {
                return number();
            }
            
            if (match(c -> "'\"".indexOf(c) >= 0)) {
                return string();
            }
            
            return symbol();
        }
        
        return new Token(here(), Token.Type.END_OF_FILE);
    }
    
    private Location here() {
        return new Location(row, column);
    }
    
    private char peek() {
        if (position < source.length()) {
            return source.charAt(position);
        }
        
        return NUL;
    }
    
    private boolean match(char c) {
        return peek() == c;
    }
    
    private boolean match(Predicate<Character> p) {
        return p.test(peek());
    }
    
    private void step() {
        if (match('\n')) {
            column = 1;
            row++;
        }
        else {
            column++;
        }
        
        position++;
    }
    
    private boolean skip(char c) {
        if (match(c)) {
            step();
            
            return true;
        }
        
        return false;
    }
    
    private void skipWhitespace() {
        do {
            step();
        }
        while (match(Character::isWhitespace));
    }
    
    private void take(StringBuilder builder) {
        builder.append(peek());
        
        step();
    }
    
    private Token literal() {
        var location = here();
        
        var builder = new StringBuilder();
        
        do {
            take(builder);
        }
        while (match(Character::isLetter));
        
        var value = Optional.ofNullable(LITERALS.get(builder.toString())).orElseThrow();
        
        return new Token(location, Token.Type.LITERAL, value);
    }
    
    private Token number() {
        var location = here();
        
        var builder = new StringBuilder();
        
        do {
            take(builder);
        }
        while (match(Character::isDigit));
        
        if (match('.')) {
            do {
                take(builder);
            }
            while (match(Character::isDigit));
        }
        
        if (match(c -> "Ee".indexOf(c) >= 0)) {
            take(builder);
            
            do {
                take(builder);
            }
            while (match(Character::isLetter));
        }
        
        var value = Double.parseDouble(builder.toString());
        
        return new Token(location, Token.Type.NUMBER,value);
    }
    
    private Token string() {
        var location = here();
        
        var builder = new StringBuilder();
        
        skip('"');
        
        do {
            take(builder);
        }
        while (!skip('"'));
        
        return new Token(location, Token.Type.STRING, builder.toString());
    }
    
    private Token symbol() {
        var location = here();
        
        Token.Type symbol;
        
        if (skip('{')) {
            symbol = Token.Type.LEFT_BRACE;
        }
        else if (skip('}')) {
            symbol = Token.Type.RIGHT_BRACE;
        }
        else if (skip('[')) {
            symbol = Token.Type.LEFT_SQUARE;
        }
        else if (skip(']')) {
            symbol = Token.Type.RIGHT_SQUARE;
        }
        else if (skip(',')) {
            symbol = Token.Type.COMMA;
        }
        else if (skip(':')) {
            symbol = Token.Type.COLON;
        }
        else {
            throw new RuntimeException();
        }
        
        return new Token(location, symbol);
    }
}
