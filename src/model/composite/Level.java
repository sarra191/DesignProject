package model.composite;
import java.util.*;
public class Level implements GameComponent {
    private List<GameComponent> ent = new ArrayList<>();
    public void update() { for (GameComponent c : ent) c.update(); }
    public void render() { for (GameComponent c : ent) c.render(); }
    public void add(GameComponent c) { ent.add(c); }
    public void remove(GameComponent c) { ent.remove(c); }
}
