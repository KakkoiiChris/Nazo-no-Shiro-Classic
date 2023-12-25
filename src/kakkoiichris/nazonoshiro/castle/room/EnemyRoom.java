// Christian Alexander, 9/6/2022
package kakkoiichris.nazonoshiro.castle.room;

import kakkoiichris.nazonoshiro.ResetValue;
import kakkoiichris.nazonoshiro.fighter.Enemy;

import java.util.List;

public class EnemyRoom extends Room {
    private final Enemy enemy;

    public EnemyRoom(String name, int floor, int row, int column, int lock, boolean locked, Enemy enemy) {
        super(name, floor, row, column, lock, locked);
        
        this.enemy = enemy;
        
        resetGroup.add(this.enemy);
    }
    
    public Enemy getEnemy() {
        return enemy;
    }
    
    public boolean isDefeated() {
        return enemy.isDead();
    }
}