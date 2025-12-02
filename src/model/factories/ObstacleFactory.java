package model.factories;
import model.entities.Obstacle;
public class ObstacleFactory implements AbstractFactory {
    private static ObstacleFactory inst;
    private ObstacleFactory() {}
    public static ObstacleFactory getInstance() {
        if (inst == null) inst = new ObstacleFactory();
        return inst;
    }
    public Object create(float x, float y) {
        return new Obstacle(x, y, (float)(Math.random() * 2 + 1.5));
    }
}
