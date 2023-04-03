// Christian Alexander, 12/6/2022
package kakkoiichris.nazonoshiro;

import java.util.Scanner;
import java.util.Stack;

public class Console {
    private static final Scanner input = new Scanner(System.in);
    
    private static String prompt;
    
    private static final Stack<String> promptStack = new Stack<>();
    
    static {
        setPrompt("> ");
    }
    
    public static String getPrompt() {
        return prompt;
    }
    
    public static void pushPrompt(String prompt) {
        promptStack.push(prompt);
        
        Console.prompt = String.join("", promptStack);
    }
    
    public static void popPrompt() {
        if (!promptStack.isEmpty()) {
            promptStack.pop();
        }
        
        Console.prompt = String.join("", promptStack);
    }
    
    public static void setPrompt(String prompt) {
        if (!promptStack.isEmpty()) {
            promptStack.pop();
        }
        
        promptStack.push(prompt);
        
        Console.prompt = String.join("", promptStack);
    }
    
    public static String read() {
        System.out.print(prompt);
        
        return input.next();
    }
    
    public static String readLine() {
        System.out.print(prompt);
        
        return input.nextLine();
    }
    
    public static int readInt() {
        System.out.print(prompt);
        
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
