// Christian Alexander, 12/7/2022
package kakkoiichris.nazonoshiro.json;

public class JsonNull {
    private static JsonNull instance;

    public static JsonNull get() {
        if (instance == null) {
            instance = new JsonNull();
        }

        return instance;
    }

    private JsonNull() {
    }

    @Override
    public String toString() {
        return "null";
    }
}
