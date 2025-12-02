package model.entities;
import util.Config;
public class Pipe extends Entity {
    private int gapY;
    private boolean scored;
    public Pipe(float x, int gapY) {
        super(x, 0, Config.PIPE_WIDTH, Config.WINDOW_HEIGHT);
        this.gapY = gapY;
        this.scored = false;
    }
    public void update() {
        x -= Config.PIPE_SPEED;
        if (x + width < 0) active = false;
    }
    public void render() {}
    public int getGapY() { return gapY; }
    public boolean hasScored() { return scored; }
    public void setScored(boolean s) { scored = s; }
    public boolean collidesWith(Player p) {
        if (x > p.getX() + p.getWidth() || x + width < p.getX()) return false;
        if (p.getY() < gapY || p.getY() + p.getHeight() > gapY + Config.PIPE_GAP) return true;
        return false;
    }
}
