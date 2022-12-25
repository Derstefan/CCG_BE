package com.ccg.ccgbe.cardgame.draw;


import com.ccg.ccgbe.cardgame.player.Player;

public abstract class Draw {

    private Player player;

    public Draw(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }
}
