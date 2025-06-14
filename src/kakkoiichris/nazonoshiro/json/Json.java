// Christian Alexander, 12/9/2022
package kakkoiichris.nazonoshiro.json;

import kakkoiichris.nazonoshiro.json.lexer.Lexer;
import kakkoiichris.nazonoshiro.json.lexer.Location;
import kakkoiichris.nazonoshiro.json.parser.Object;
import kakkoiichris.nazonoshiro.json.parser.Parser;

import java.util.Map;

public class Json<X> {
    private final JsonConverter<X> converter;

    private Object root;

    private Json(Object root, JsonConverter<X> converter) {
        this.root = root;
        this.converter = converter;
    }

    public static <X> Json<X> empty(JsonConverter<X> converter) {
        var root = new Object(new Location(0, 0), Map.of());

        return new Json<>(root, converter);
    }

    public static <X> Json<X> of(String source, JsonConverter<X> converter) {
        var lexer = new Lexer(source);

        var parser = new Parser(lexer);

        var root = parser.parse();

        return new Json<>(root, converter);
    }

    public Object getRoot() {
        return root;
    }

    public X load() {
        return converter.load(root);
    }

    public void save(X x) {
        root = converter.save(x);
    }
}
