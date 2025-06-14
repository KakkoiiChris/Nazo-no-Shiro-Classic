// Christian Alexander, 12/10/2022
package kakkoiichris.nazonoshiro.json;

import kakkoiichris.nazonoshiro.json.parser.Object;

public interface JsonConverter<X> {
    X load(Object object);

    Object save(X x);
}
