//Christian Alexander, 9/13/2011
package kakkoiichris.nazonoshiro.item.kasugi;

import kakkoiichris.nazonoshiro.fighter.Fighter;

public abstract class Kasugi {
    protected String name;
    protected int magnitude, duration, timer;
    protected boolean forYou;
    
    public Kasugi(String name, int magnitude, int duration, boolean forYou) {
        this.name = name;
        this.magnitude = magnitude;
        this.duration = timer = duration;
        this.forYou = forYou;
    }
    
    public String getName() {
        return name;
    }
    
    public int getTimer() {
        return timer;
    }
    
    public void setTimer(int timer) {
        this.timer = timer;
    }
    
    public boolean isForYou() {
        return forYou;
    }
    
    public abstract void affect(Fighter fighter);
    
    public String toString() {
        return name;
    }
}