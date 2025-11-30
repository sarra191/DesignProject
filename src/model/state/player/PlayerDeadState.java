package model.state.player;

import model.entities.Player;

public class PlayerDeadState implements PlayerState {
    @Override
    public void enter(Player player) {
        player.setActive(false);
        // si votre Player a un flag 'dead', mettez-le aussi :
        // player.setDead(true);
    }

    @Override
    public void exit(Player player) {
        // ...existing code...
    }

    @Override
    public void handleInput(Player player, String input) {
        // pas d'input quand mort
    }

    @Override
    public void update(Player player, double dt) {
        // éventuellement jouer animation / temporisation avant suppression
    }

    @Override
    public String name() { return "DEAD"; }
}

