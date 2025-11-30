package model.state.player;

import model.entities.Player;

public class PlayerJumpingState implements PlayerState {
    private static final double JUMP_VELOCITY = -420; // px/s (ajuster)

    @Override
    public void enter(Player player) {
        player.setVelocityY(JUMP_VELOCITY);
        // conserve l'inertie horizontale (ne remet pas velocityX à zéro)
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
        // une fois que la vélocité verticale devient positive, on est en chute
        if (player.getVelocityY() > 0) {
            player.changeState(new PlayerFallingState());
        }
    }

    @Override
    public String name() { return "JUMPING"; }
}

