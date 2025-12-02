package model.composite;
public interface GameComponent {
    void update();
    void render();
    void add(GameComponent c);
    void remove(GameComponent c);
}
