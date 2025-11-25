package model.state.game;

import javafx.scene.canvas.GraphicsContext;
import model.Game;

public class MenuState implements GameState {
    @Override
    public void enter(Game game) {
        // ...existing code...
        // TODO: initialiser menu (musique, options)
    }

    @Override
    public void exit(Game game) {
        // ...existing code...
    }

    @Override
    public void update(Game game, double dt) {
        // ...existing code...
    }

    @Override
    public void render(Game game, GraphicsContext gc) {
        // ...existing code...
        // TODO: dessiner le menu
    }

    @Override
    public void handleInput(Game game, String input) {
        if ("START".equals(input)) {
            game.changeState(new PlayState());
        }
    }
}

