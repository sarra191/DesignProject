package model.state.player;

import model.entities.Player;

public interface PlayerState {
    void enter(Player player);
    void exit(Player player);
    void handleInput(Player player, String input);
    void update(Player player, double dt);
    String name();
}

