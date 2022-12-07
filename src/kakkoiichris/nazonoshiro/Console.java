// Christian Alexander, 12/6/2022
package kakkoiichris.nazonoshiro;

import java.util.Scanner;

public class Console {
    private static final Scanner input = new Scanner(System.in);
    
    public static String read() {
        return input.next();
    }
    
    public static String readLine() {
        return input.nextLine();
    }
    
    public static int readInt() {
        return input.nextInt();
    }
    
    public static void write(Object x) {
        System.out.print(x);
    }
    
    public static void write(String format, Object... args) {
        System.out.printf(format, args);
    }
    
    public static void writeLine(Object x) {
        System.out.println(x);
    }
    
    public static void writeLine(String format, Object... args) {
        System.out.printf(format + '\n', args);
    }
    
    public static void newLine() {
        System.out.println();
    }
    
    public static void pause(int seconds) {
        try {
            Thread.sleep(seconds * 2L); //TODO: Re-enable pausing
        }
        catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    
    static void close() {
        input.close();
    }
}
