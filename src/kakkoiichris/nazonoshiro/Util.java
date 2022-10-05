// Christian Alexander, 9/4/2022
package kakkoiichris.nazonoshiro;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Util {
    public static final Random random = new Random();
    
    public static final Scanner input = new Scanner(System.in);
    
    public static <T> T getRandom(List<T> list) {
        return list.get(random.nextInt(list.size()));
    }
    
    public static <T> T removeRandom(List<T> list) {
        return list.remove(random.nextInt(list.size()));
    }
    
    public static <T> void shuffle(List<T> list) {
        var shuffled = new ArrayList<>(list);
        
        list.clear();
        
        while (!shuffled.isEmpty()) {
            list.add(removeRandom(shuffled));
        }
    }
    
    public static void pause(int seconds) {
        try {
            Thread.sleep(seconds * 2L); //TODO: Re-enable pausing
        }
        catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
