package com.thejourneyofone.game;

/*
Not sure if necessary.
If I play to implement more complex enemy movement(such as forming groups and coordinating), then this
class will be integral. For now, it will hold player information and relay it to Enemy classes acting as if
it was commanding the Enemy in a way.
 */

import com.badlogic.gdx.math.Vector2;
import com.thejourneyofone.game.characters.Player;

public class EnemyManager {
    private static Player player;

    public EnemyManager(Player player) {
        this.player = player;
    }

    //*note: this will return the where the player is drawing, this needs to be fixed.
    public static Vector2 getPlayerPos() {
        return new Vector2(player.getPosX(), player.getPosY());
    }
}
