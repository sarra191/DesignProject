package model.state.game;

import javafx.scene.canvas.GraphicsContext;
import model.Game;
import model.entities.Entity;
import model.entities.Player;

import java.util.Iterator;
import java.util.List;

public class PlayState implements GameState {
    @Override
    public void enter(Game game) {
        // ...existing code...
        // initialisation simple : créer joueur si absent
        if (game.getPlayer() == null) {
            Player p = new Player(100, 300);
            game.setPlayer(p);
            game.getEntities().add(p);
        }
    }

    @Override
    public void exit(Game game) {
        // ...existing code...
    }

    @Override
    public void update(Game game, double dt) {
        List<Entity> entities = game.getEntities();
        // mettre à jour chaque entité
        for (Entity e : entities) {
            e.update(dt);
        }

        // simple suppression des inactifs
        Iterator<Entity> it = entities.iterator();
        while (it.hasNext()) {
            Entity e = it.next();
            if (!e.isActive()) {
                it.remove();
            }
        }

        // collisions basiques (O(n^2) ; remplacer par spatial hashing si besoin)
        for (int i = 0; i < entities.size(); i++) {
            for (int j = i + 1; j < entities.size(); j++) {
                Entity a = entities.get(i);
                Entity b = entities.get(j);
                if (a.getBounds().intersects(b.getBounds())) {
                    a.onCollision(b);
                    b.onCollision(a);
                }
            }
        }

        // si joueur mort -> game over
        Player player = game.getPlayer();
        if (player != null && player.isDead()) {
            game.changeState(new GameOverState());
        }
    }

    @Override
    public void render(Game game, GraphicsContext gc) {
        // ...existing code...
        // TODO: dessiner entités via leurs méthodes si nécessaire
    }

    @Override
    public void handleInput(Game game, String input) {
        Player p = game.getPlayer();
        if (p != null) {
            // p.handleInput(input); // Removed as Player doesn't need string input handling
            if ("FLAP".equals(input) || "SPACE".equals(input)) {
                p.flap();
            }
        }
        if ("PAUSE".equals(input)) {
            // game.changeState(new PauseState());
        }
    }
}
