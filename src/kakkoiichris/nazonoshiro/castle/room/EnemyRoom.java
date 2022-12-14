// Christian Alexander, 9/6/2022
package kakkoiichris.nazonoshiro.castle.room;

import kakkoiichris.nazonoshiro.ResetValue;
import kakkoiichris.nazonoshiro.fighter.Enemy;

import java.util.List;

public class EnemyRoom extends Room {
    private final Enemy enemy;
    
    private final ResetValue<Boolean> defeated = new ResetValue<>(false);
    
    public EnemyRoom(String name, int key, int lock, boolean locked, Enemy enemy) {
        super(name, key, lock, locked);
        
        this.enemy = enemy;
        
        resetGroup.addAll(List.of(this.enemy, defeated));
    }
    
    public Enemy getEnemy() {
        return enemy;
    }
}