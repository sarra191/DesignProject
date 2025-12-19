package model.decorator;

import model.Game;
import model.entities.Player;
import java.awt.*;

public class ShieldBoost extends PlayerDecorator {

    private int timer = 480;

    public ShieldBoost(Player p) {
        super(p);
    }

    @Override
    public void setAlive(boolean alive) {
        if (timer > 0) {
            return;
        }
        decorated.setAlive(alive);
    }

    @Override
    public boolean isAlive() {
        if (timer > 0) {
            return true;
        }
        return decorated.isAlive();
    }

    @Override
    public void update() {
        super.update();

        if (--timer <= 0) {

            Game.getInstance().setPlayer(decorated);
        }
    }

    @Override
    public void render() {
        decorated.render();

        if (getG() != null && timer > 0) {
            Graphics2D g = getG();

            g.setColor(new Color(100, 180, 255, 140));
            g.fillOval((int) getX() - 65, (int) getY() - 65, 170, 170);

            g.setColor(new Color(50, 150, 255));
            g.setStroke(new BasicStroke(10));
            g.drawOval((int) getX() - 65, (int) getY() - 65, 170, 170);

            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 22));
            FontMetrics fm = g.getFontMetrics();
            g.drawString("SHIELD", (int) getX() - fm.stringWidth("SHIELD") / 2, (int) getY() + 8);
        }
    }
}