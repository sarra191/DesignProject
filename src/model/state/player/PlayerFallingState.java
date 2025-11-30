package model.state.player;

import model.entities.Player;

public class PlayerFallingState implements PlayerState {
    @Override
    public void enter(Player player) {
        // ...existing code...
    }

    @Override
    public void exit(Player player) {
        // ...existing code...
    }

    @Override
    public void handleInput(Player player, String input) {
        if ("MOVE_LEFT".equals(input)) {
            player.setVelocityX(-player.getSpeed());
        } else if ("MOVE_RIGHT".equals(input)) {
            player.setVelocityX(player.getSpeed());
        } else if ("STOP".equals(input)) {
            player.setVelocityX(0);
        }
    }

    @Override
    public void update(Player player, double dt) {
        // si on touche le sol -> idle
        if (player.isOnGround()) {
            player.changeState(new PlayerIdleState());
        }
    }

    @Override
    public String name() { return "FALLING"; }
}

