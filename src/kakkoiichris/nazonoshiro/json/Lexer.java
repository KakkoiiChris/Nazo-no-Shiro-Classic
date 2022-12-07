// Christian Alexander, 12/7/2022
package kakkoiichris.nazonoshiro.json;

import java.util.Iterator;

public class Lexer implements Iterator<Token> {
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
        while (position < source.length()) {
        
        }
        
        return new Token(here(), Token.Type.Symbol.END_OF_FILE);
    }
    
    private Location here() {
        return new Location(row, column);
    }
    
    private boolean match(){
        return true;
    }
}
