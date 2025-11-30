package model.state.player;

import model.entities.Player;

public class PlayerIdleState implements PlayerState {
    @Override
    public void enter(Player player) {
        // ...existing code...
        player.setVelocityY(0);
        player.setVelocityX(0);
    }

    @Override
    public void exit(Player player) {
        // ...existing code...
    }

    @Override
    public void handleInput(Player player, String input) {
        if ("JUMP".equals(input)) {
            player.changeState(new PlayerJumpingState());
        } else if ("MOVE_LEFT".equals(input)) {
            player.setVelocityX(-player.getSpeed());
        } else if ("MOVE_RIGHT".equals(input)) {
            player.setVelocityX(player.getSpeed());
        } else if ("STOP".equals(input)) {
            player.setVelocityX(0);
        }
    }

    @Override
    public void update(Player player, double dt) {
        // si le joueur commence à tomber par gravité -> falling
        if (!player.isOnGround() && player.getVelocityY() > 0) {
            player.changeState(new PlayerFallingState());
        }
    }

    @Override
    public String name() { return "IDLE"; }
}

