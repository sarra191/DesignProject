package model.factories;
import model.entities.Enemy;
public class EnemyFactory implements AbstractFactory {
    private static EnemyFactory inst;
    private EnemyFactory() {}
    public static EnemyFactory getInstance() {
        if (inst == null) inst = new EnemyFactory();
        return inst;
    }
    public Object create(float x, float y) {
        return new Enemy(x, y, (float)(Math.random() * 3 + 2));
    }
}
