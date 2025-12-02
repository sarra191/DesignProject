package model.composite;
import java.util.*;
public class EntityGroup implements GameComponent {
    private List<GameComponent> comp = new ArrayList<>();
    public void update() { for (GameComponent c : comp) c.update(); }
    public void render() { for (GameComponent c : comp) c.render(); }
    public void add(GameComponent c) { comp.add(c); }
    public void remove(GameComponent c) { comp.remove(c); }
}
