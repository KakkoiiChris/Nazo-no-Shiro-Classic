package kakkoiichris.nazonoshiro.fighter;//Christian Alexander, 5/12/11, Pd. 6

import kakkoiichris.nazonoshiro.item.kasugi.Kasugi;

import java.util.ArrayList;

public class Imperial extends Enemy {
    public Imperial() {
        super("Imperial Guard", 3, 3, 3, 35);
    }
    
    protected ArrayList<Kasugi> useable = new ArrayList();
}