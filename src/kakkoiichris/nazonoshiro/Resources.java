// Christian Alexander, 9/4/2022
package kakkoiichris.nazonoshiro;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class Resources {
    private static final Path RESOURCES_PATH;
    
    static {
        try {
            RESOURCES_PATH = Paths.get(Objects.requireNonNull(Resources.class.getResource("/resources")).toURI());
        }
        catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
    
    public static String getString(String fileName) {
        var path = RESOURCES_PATH.resolve("%s.txt".formatted(fileName));
        
        try {
            return Files.readString(path);
        }
        catch (IOException e) {
            throw new RuntimeException("Could not find file '%s'!".formatted(fileName));
        }
    }
    
    public static Optional<String> tryGetString(String fileName) {
        var path = RESOURCES_PATH.resolve("%s.txt".formatted(fileName));
        
        try {
            return Optional.of(Files.readString(path));
        }
        catch (IOException e) {
            return Optional.empty();
        }
    }
    
    public static List<String> getLines(String fileName) {
        var path = RESOURCES_PATH.resolve("%s.txt".formatted(fileName));
        
        try {
            return Files.readAllLines(path);
        }
        catch (IOException e) {
            throw new RuntimeException("Could not find file '%s'!".formatted(fileName));
        }
    }
    
    public static Optional<List<String>> tryGetLines(String fileName) {
        var path = RESOURCES_PATH.resolve("%s.txt".formatted(fileName));
        
        try {
            return Optional.of(Files.readAllLines(path));
        }
        catch (IOException e) {
            return Optional.empty();
        }
    }
}