// Christian Alexander, 9/30/2022
package kakkoiichris.nazonoshiro;

import kakkoiichris.kotoba.Console;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Pattern;

public class Script {
    private final List<String> source;

    public Script(List<String> source) {
        this.source = source;
    }

    public Map<String, String> run(Console console) {
        var vars = new HashMap<String, String>();

        var newline = true;

        for (var line : source) {
            if (line.startsWith("@")) {
                var command = List.of(line.substring(1).trim().toLowerCase().split("\\s+"));

                var name = command.get(0);
                var args = command.subList(1, command.size());

                switch (name) {
                    case "newline" -> newline = Boolean.parseBoolean(args.get(0));

                    case "pause" -> {
                        switch (args.size()) {
                            case 0 -> {
                            }

                            case 1 -> console.pause(Integer.parseInt(args.get(0)));
                        }
                    }

                    case "input" -> {
                        var key = args.get(0);
                        var prompt = args.get(1);

                        var promptLast = console.getPrompt();
                        console.setPrompt(prompt + ' ');

                        var value = console.readLine().orElseThrow();

                        vars.put(key, value);

                        console.setPrompt(promptLast);
                    }
                }
            }
            else {
                var output = line
                        .replace("\\", "\n")
                        .replace("\n\n", "\\");

                var pattern = Pattern.compile("\\{(\\w+)}");

                while (true) {
                    var matcher = pattern.matcher(output);

                    if (!matcher.find()) break;

                    var name = matcher.group(1);

                    output = matcher.replaceFirst(Optional.ofNullable(vars.get(name)).orElseThrow(() -> new RuntimeException("NO VAR FOR MATCHER")));
                }

                if (newline) {
                    console.writeLine(output);
                }
                else {
                    console.write(output);
                }
            }
        }

        return vars;
    }
}
