#!/bin/bash

cd "$(dirname "$0")"

echo "🐦 Flappy Bird - Final Build"
echo "============================"

# Remove debug version
rm -f src/Main.java

# Recreate clean Player without debug logs
cat > src/model/entities/Player.java << 'EOFPLAYER'
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
EOFPLAYER

# Compile
rm -rf target/classes
mkdir -p target/classes

echo "[1/2] Compiling..."
javac -d target/classes -sourcepath src src/SwingGameApp.java 2>&1 | grep -v "warning"

echo "[2/2] Running Game..."
echo ""
echo "🎮 Controls:"
echo "  SPACE - Flap/Jump"
echo "  R - Restart"
echo ""

java -cp target/classes SwingGameApp
