//Christian Alexander, 6/14/11, Pd. 6
package kakkoiichris.nazonoshiro.item;

import kakkoiichris.kotoba.Console;
import kakkoiichris.nazonoshiro.fighter.Fighter;
import kakkoiichris.nazonoshiro.fighter.Self;

import java.text.NumberFormat;
import java.util.List;

public enum Coin implements Item {
    ONE_YEN(1, "One Yen Coin", """
        A lightweight silver coin displaying a number '1', with a simple design."""),
    
    FIVE_YEN(5, "Five Yen Coin", """
        A golden coin displaying no number, with a hole through it's center."""),
    
    TEN_YEN(10, "Ten Yen Coin", """
        A copper coin displaying the number '10', with a plant motif."""),
    
    FIFTY_YEN(50, "Fifty Yen Coin", """
        A heavy silver coin displaying the number '50', with a hole through it's center."""),
    
    HUNDRED_YEN(100, "One Hundred Yen Coin", """
        A silver coin displaying the number '100', with a simple design."""),
    
    FIVE_HUNDRED_YEN(500, "Five Hundred Yen Coin", """
        A heavy golden coin displaying the number '500', with a flower motif.""");
    
    private final int value;
    private final String name;
    private final String description;
    
    Coin(int value, String name, String description) {
        this.value = value;
        this.name = name;
        this.description = description;
    }
    
    public static Coin random() {
        return values()[(int) (Math.random() * values().length)];
    }
    
    public static int getTotal(List<Coin> coins) {
        return coins.stream().mapToInt(Coin::getValue).sum();
    }
    
    public static String getTotalString(List<Coin> coins) {
        var format = NumberFormat.getInstance();
        
        format.setGroupingUsed(true);
        
        return "¥" + format.format(getTotal(coins));
    }
    
    @Override
    public int getValue() {
        return value;
    }
    
    @Override
    public String getName() {
        return name;
    }
    
    @Override
    public String getDescription() {
        return description;
    }
    
    @Override
    public boolean pickUp(Console console, Self self) {
        console.writeLine("The coin rings out as you scrape it up.");
        
        return true;
    }
    
    @Override
    public boolean use(Console console, Fighter fighter) {
        console.writeLine("You cannot use this.");
        
        return false;
    }
    
    @Override
    public String toString() {
        return getDescription();
    }
}
