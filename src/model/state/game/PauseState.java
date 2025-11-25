package model.state.game;

import javafx.scene.canvas.GraphicsContext;
import model.Game;

public class PauseState implements GameState {
    @Override
    public void enter(Game game) {
        // ...existing code...
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
    }

    @Override
    public void handleInput(Game game, String input) {
        if ("RESUME".equals(input)) {
            game.changeState(new PlayState());
        }
    }
}

