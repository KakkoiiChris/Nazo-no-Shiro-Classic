// Christian Alexander, 12/11/2022
package kakkoiichris.nazonoshiro.json.parser;

import kakkoiichris.nazonoshiro.json.lexer.Location;

import java.util.Map;

public record Object(Location location, Map<String, Node> members) implements Node {
}
