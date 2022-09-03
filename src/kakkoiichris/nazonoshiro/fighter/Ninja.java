package kakkoiichris.nazonoshiro.fighter;//Christian Alexander, 5/12/11, Pd. 6

import kakkoiichris.nazonoshiro.item.kasugi.Kasugi;

import java.util.ArrayList;

public class Ninja extends Enemy {
    public Ninja() {
        super("Ninja", 1, 2, 3, 25);
    }
    
    protected ArrayList<Kasugi> useable = new ArrayList();
}