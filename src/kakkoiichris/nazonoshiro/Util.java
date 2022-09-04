// Christian Alexander, 9/4/2022
package kakkoiichris.nazonoshiro;

import java.util.List;
import java.util.Random;

public class Util {
    private static final Random random = new Random();
    
    public static <T> T getRandom(List<T> list) {
        return list.get(random.nextInt(list.size()));
    }
}
