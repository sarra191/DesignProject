package model.state.game;

import javafx.scene.canvas.GraphicsContext;
import model.Game;

public interface GameState {
    // appelé quand on entre dans l'état
    void enter(Game game);
    // appelé quand on quitte l'état
    void exit(Game game);
    // mise à jour logique (dt en secondes)
    void update(Game game, double dt);
    // rendu (peut être noop pour états non-visuels)
    void render(Game game, GraphicsContext gc);
    // gestion des entrées (ex: key presses)
    void handleInput(Game game, String input);
}

