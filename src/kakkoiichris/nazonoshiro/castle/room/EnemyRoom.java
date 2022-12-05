// Christian Alexander, 9/6/2022
package kakkoiichris.nazonoshiro.castle.room;

import kakkoiichris.nazonoshiro.fighter.Enemy;

public class EnemyRoom extends Room {
    private final Enemy enemy;
    
    public EnemyRoom(String name, int key, int lock, boolean locked, Enemy enemy) {
        super(name, key, lock, locked);
        
        this.enemy = enemy;
        
        resetGroup.add(enemy);
    }
    
    public Enemy getEnemy() {
        return enemy;
    }
}