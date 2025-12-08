
package model.decorator;

import model.entities.Player;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;

public abstract class PlayerDecorator extends Player {

    protected Player decorated;
    private static Graphics2D g2d = null;

    public PlayerDecorator(Player p) {
        super(p.getX(), p.getY());
        this.decorated = p;
    }

    public static void setGraphics(Graphics2D g) {
        g2d = g;
    }

    protected Graphics2D getG() {
        return g2d;
    }

    // On délègue tout au joueur décoré
    @Override public void update() { decorated.update(); x = decorated.getX(); y = decorated.getY(); }
    @Override public void flap()   { decorated.flap(); }
    @Override public float getX()  { return decorated.getX(); }
    @Override public float getY()  { return decorated.getY(); }
    @Override public boolean isAlive() { return decorated.isAlive(); }
    @Override public void setAlive(boolean b) { decorated.setAlive(b); }
    @Override public int getScore() { return decorated.getScore(); }
    @Override public void addScore(int s) { decorated.addScore(s); }
}
