package kakkoiichris.nazonoshiro.fighter;

import kakkoiichris.kotoba.Console;

public class Fight {
    public enum Result {
        WIN,
        RUN,
        LOSE
    }

    public static Result fight(Console console, Self self, Enemy enemy) {
        console.write("A %s stands before you.%n%n", enemy);

        //determines who's attacking and who's defending in battle
        var yourTurn = self.getSpeed() > enemy.getSpeed() || self.getLuck() / 3.0 > Math.random() * 100;

        while (!(self.isDead() || enemy.isDead())) {
            self.showHP(console, yourTurn);
            console.newLine();

            enemy.showHP(console, !yourTurn);
            console.newLine();

            if (yourTurn) {
                self.allAffect(console);
                self.filter();

                console.setPrompt("Attack / Use / Run > ");

                var action = console.readLine().orElseThrow().toLowerCase();

                console.newLine();

                if (action.matches("a(ttack)?")) {
                    self.attack(console, enemy);
                }
                else if (action.matches("u(se)?")) {
                    self.use(console, enemy);
                }
                else if (action.matches("r(un)?")) {
                    var speedDiff = (enemy.getSpeed() - self.getSpeed()) / self.getSpeed();

                    if (speedDiff >= 1) {
                        console.writeLine("You've been cornered.\n");
                    }
                    else if (speedDiff >= 0) {
                        if (Math.random() > speedDiff) {
                            console.writeLine("You barely scraped by.\n");

                            return Result.RUN;
                        }
                        else {
                            console.writeLine("You've been cut off.\n");
                        }
                    }
                    else {
                        console.writeLine("You made a clean getaway.\n");

                        return Result.RUN;
                    }
                }
                else {
                    console.writeLine("Can't do that...\n");

                    continue;
                }
            }
            else {
                enemy.allAffect(console);

                enemy.filter();

                enemy.use(console, self);

                enemy.attack(console, self);
            }

            yourTurn = !yourTurn;

            console.pause(2);
        }

        if (self.isDead()) {
            return Result.LOSE;
        }

        return Result.WIN;
    }
}
