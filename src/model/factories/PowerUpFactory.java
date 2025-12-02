package model.factories;
import model.entities.PowerUp;
public class PowerUpFactory implements AbstractFactory {
    private static PowerUpFactory inst;
    private PowerUpFactory() {}
    public static PowerUpFactory getInstance() {
        if (inst == null) inst = new PowerUpFactory();
        return inst;
    }
    public Object create(float x, float y) {
        String[] t = {"speed", "shield", "points"};
        return new PowerUp(x, y, t[(int)(Math.random() * 3)]);
    }
}
