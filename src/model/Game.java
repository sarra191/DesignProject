package model;

import model.entities.Entity;
import model.entities.Pipe;
import model.entities.Player;
import model.entities.PowerUp;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

public class Game {
    private static Game instance;
    private Player player;
    private List<Entity> entities;
    private List<Pipe> pipes;
    private List<PowerUp> powerUps;

    private Game() {
        entities = new ArrayList<>();
        pipes = new ArrayList<>();
        powerUps = new ArrayList<>();
    }

    public static Game getInstance() {
        if (instance == null) {
            instance = new Game();
        }
        return instance;
    }

    public void initialize() {
        entities.clear();
        pipes.clear();
        powerUps.clear();

        player = new Player(100, 300);
        entities.add(player);
    }

    public void update() {
        if (player != null)
            player.update();

        Iterator<Pipe> pipeIt = pipes.iterator();
        while (pipeIt.hasNext()) {
            Pipe p = pipeIt.next();
            p.update();
            if (!p.isActive()) {
                pipeIt.remove();
                entities.remove(p);
            }
        }

        Iterator<PowerUp> powerIt = powerUps.iterator();
        while (powerIt.hasNext()) {
            PowerUp p = powerIt.next();
            p.update();
            if (!p.isActive()) {
                powerIt.remove();
                entities.remove(p);
            }
        }
    }

    public void render() {

    }

    public void setPlayer(Player p) {
        this.player = p;
    }

    public Player getPlayer() {
        return player;
    }

    public List<Pipe> getPipes() {
        return pipes;
    }

    public void addPipe(Pipe p) {
        pipes.add(p);
        entities.add(p);
    }

    public List<PowerUp> getPowerUps() {
        return powerUps;
    }

    public void addPowerUp(PowerUp p) {
        powerUps.add(p);
        entities.add(p);
    }

    public List<Entity> getEntities() {
        return entities;
    }
}
