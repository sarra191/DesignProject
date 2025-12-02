package model.entities;
public class Enemy extends Entity {
    private float speed;
    public Enemy(float x, float y, float speed) {
        super(x, y, 40, 40);
        this.speed = speed;
    }
    public void update() {
        x -= speed;
        if (x + width < 0) active = false;
    }
    public void render() {}
}
