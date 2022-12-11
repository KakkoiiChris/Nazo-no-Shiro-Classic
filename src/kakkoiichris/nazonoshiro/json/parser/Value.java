// Christian Alexander, 12/11/2022
package kakkoiichris.nazonoshiro.json.parser;

import kakkoiichris.nazonoshiro.json.lexer.Location;

public record Value(Location location, java.lang.Object value) implements Node {
}

