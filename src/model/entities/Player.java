package model.entities;
import util.Config;

public class Player extends Entity {
    private float velocity;
    private int score;
    private boolean alive;
    
    public Player(float x, float y) {
        super(x, y, Config.BIRD_WIDTH, Config.BIRD_HEIGHT);
        this.velocity = 0;
        this.score = 0;
        this.alive = true;
    }
    
    public void update() {
        velocity += Config.GRAVITY;
        if (velocity > Config.MAX_VELOCITY) velocity = Config.MAX_VELOCITY;
        y += velocity;
        
        if (y + height >= Config.GROUND_Y) { 
            alive = false;
        }
        if (y <= 0) { 
            alive = false;
        }
    }
    
    public void render() {}
    
    public void flap() { 
        velocity = Config.FLAP_STRENGTH;
    }
    
    public int getScore() { return score; }
    public void addScore(int p) { score += p; }
    public float getVelocity() { return velocity; }
    public boolean isAlive() { return alive; }
    public void setAlive(boolean a) { this.alive = a; }
}
