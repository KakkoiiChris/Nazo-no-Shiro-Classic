package kakkoiichris.nazonoshiro;

public class FightingTest {
    public static void main(String[] args) {
        int attackMax = 17, amin = 14, defenseMax = 10, defenseMin = 4, speedMax = 8, speedMin = 2;

        for (int i = 0; i < 5; i++) {
            int attack = (int) (Math.random() * (attackMax - amin)) + amin,
                defense = (int) (Math.random() * (defenseMax - defenseMin)) + defenseMin,
                speed = (int) (Math.random() * (speedMax - speedMin)) + speedMin;

            System.out.printf("%d, %d, %d, %d, %d%n",
                    attack,
                    defense,
                    speed,
                    attack - defense,
                    (attack - defense) + ((attack - defense) / speed));
        }
    }
}