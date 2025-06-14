//Christian Alexander, 6/21/11, Pd. 6
package kakkoiichris.nazonoshiro.castle.storage;

import kakkoiichris.kotoba.Console;
import kakkoiichris.nazonoshiro.ResetList;
import kakkoiichris.nazonoshiro.Resettable;
import kakkoiichris.nazonoshiro.fighter.Self;
import kakkoiichris.nazonoshiro.item.Coin;
import kakkoiichris.nazonoshiro.item.Item;
import kakkoiichris.nazonoshiro.item.kusuri.Kusuri;

import java.util.regex.Pattern;
import java.util.stream.Collectors;

public abstract class Storage implements Resettable {
    private final String name;

    protected ResetList<Item> items = new ResetList<>();
    protected ResetList<Kusuri> kusuris = new ResetList<>();

    public Storage(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    @Override
    public void storeState() {
        items.storeState();
        kusuris.storeState();
    }

    @Override
    public void resetState() {
        items.resetState();
        kusuris.resetState();
    }

    public void add(Item item) {
        items.add(item);
    }

    public void rummage(Console console, Self self) {
        console.writeLine("The %s opened.%n", name);

        console.pushPrompt("%s > ".formatted(name));

        while (true) {
            if (items.isEmpty()) {
                console.writeLine("It is empty...");

                break;
            }

            var coins = items
                    .stream()
                    .filter(item -> item instanceof Coin)
                    .map(Coin.class::cast)
                    .toList();

            if (!coins.isEmpty()) {
                console.writeLine("- Coins (%s)", Coin.getTotalString(coins));
            }

            var items = this.items
                    .stream()
                    .filter(item -> !(item instanceof Coin))
                    .collect(Collectors.groupingBy(item -> item.getName().toLowerCase()));

            for (var key : items.keySet()) {
                var list = items.get(key);

                var name = list.get(0).getName();
                var count = list.size();

                console.writeLine("- %s (%d)", name, count);
            }

            console.newLine();

            var pick = console.readLine().orElseThrow().toLowerCase();

            console.newLine();

            if (pick.matches("exit|leave")) {
                console.writeLine("You closed the %s and stepped away.%n", name);

                break;
            }

            if (pick.matches("take all|empty")) {
                console.writeLine("You stuffed everything into your backpack.\n");

                self.getInventory().addAll(this.items);

                this.items.clear();

                break;
            }

            var matcher = Pattern.compile("take (\\w+)(s?)").matcher(pick);

            if (matcher.find()) {
                var choice = matcher.group(1);
                var plural = !matcher.group(2).isEmpty();

                if (choice.equals("coin")) {
                    console.writeLine("You scooped up all the coins at the bottom.\n");

                    this.items.removeAll(coins);

                    self.getInventory().addAll(coins);
                }
                else {

                }
            }
        }

        console.popPrompt();
    }

    public abstract void open(Console console, Self self);
}