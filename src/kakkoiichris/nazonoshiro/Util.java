// Christian Alexander, 9/4/2022
package kakkoiichris.nazonoshiro;

import java.util.List;
import java.util.Random;

public class Util {
    public static final Random random = new Random();
    
    public static <T> T getRandom(List<T> list) {
        return list.get(random.nextInt(list.size()));
    }
    
    public static void pause(int seconds) {
        try {
            Thread.sleep(seconds * 10L); //TODO: Reenable pausing
        }
        catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
