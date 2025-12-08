package model.decorator;

import model.Game;
import model.entities.Player;
import java.awt.*;

public class DoublePoints extends PlayerDecorator {

    private int timer = 480; // 8 secondes

    public DoublePoints(Player p) {
        super(p);
    }

    @Override
    public void update() {
        super.update();

        if (--timer <= 0) {
            Game.getInstance().setPlayer(decorated);
        }
    }

    @Override
    public void addScore(int points) {
        decorated.addScore(points * 2);  // DOUBLE LES POINTS
    }

    @Override
    public void render() {
        decorated.render();

        if (getG() != null) {
            getG().setColor(new Color(255, 215, 0, 100));
            getG().fillOval((int)getX() - 45, (int)getY() - 45, 130, 130);
            getG().setColor(new Color(255, 180, 0));
            getG().setStroke(new BasicStroke(6));
            getG().drawOval((int)getX() - 45, (int)getY() - 45, 130, 130);
        }
    }
}