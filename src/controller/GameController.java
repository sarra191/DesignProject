package controller;
import model.*;
import model.entities.*;
import util.Config;
public class GameController {
    private Game game;
    private int pipeSpawnCounter = 0;
    private int pipeSpawnInterval = 120;
    
    // ... existing code ...
    public GameController(Game g) { game = g; }
    public void update() {
        pipeSpawnCounter++;
        if (pipeSpawnCounter >= pipeSpawnInterval) {
            spawnPipe();
            pipeSpawnCounter = 0;
        }
        game.update();
        checkPipeCollisions();
        checkPipeScoring();
    }
    public void render() { game.render(); }
    private void spawnPipe() {
        int minGapY = 80;
        int maxGapY = Config.GROUND_Y - Config.PIPE_GAP - 80;
        int randomGapY = minGapY + (int)(Math.random() * (maxGapY - minGapY));
        game.addPipe(new Pipe(Config.WINDOW_WIDTH, randomGapY));
    }
    private void checkPipeCollisions() {
        Player p = game.getPlayer();
        if (p == null || !p.isAlive()) return;
        for (Pipe pipe : game.getPipes()) {
            if (pipe.isActive() && pipe.collidesWith(p)) p.setAlive(false);
        }
    }
    private void checkPipeScoring() {
        Player p = game.getPlayer();
        if (p == null || !p.isAlive()) return;
        for (Pipe pipe : game.getPipes()) {
            if (pipe.isActive() && !pipe.hasScored()) {
                if (p.getX() > pipe.getX() + Config.PIPE_WIDTH) {
                    p.addScore(1);
                    pipe.setScored(true);
                }
            }
        }
    }
}
