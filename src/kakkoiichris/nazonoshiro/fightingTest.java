package kakkoiichris.nazonoshiro;

public class fightingTest {
    public static void main(String[] args) {
        int amax = 17, amin = 14, dmax = 10, dmin = 4, smax = 8, smin = 2;
        for (int i = 0; i < 5; i++) {
            int a = (int) (Math.random() * (amax - amin)) + amin,
                d = (int) (Math.random() * (dmax - dmin)) + dmin,
                s = (int) (Math.random() * (smax - smin)) + smin;
            System.out.println(a + ", " + d + ", " + s + ", " + (a - d) + ", " + ((a - d) + ((int) (a - d) / s)));
        }
    }
}