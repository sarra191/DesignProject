
package model.decorator;

import model.Game;
import model.entities.Player;
import java.awt.*;

public class SpeedBoost extends PlayerDecorator {

    private int timer = 480;
    private static final float SUPER_JUMP = -16f;

    public SpeedBoost(Player p) {
        super(p);
    }

    @Override
    public void flap() {

        try {
            java.lang.reflect.Field field = Player.class.getDeclaredField("velocityY");
            field.setAccessible(true);
            field.setFloat(decorated, SUPER_JUMP);
        } catch (Exception e) {

            decorated.flap();
        }
    }

    @Override
    public void update() {
        super.update();

        if (--timer <= 0) {
            Game.getInstance().setPlayer(decorated);
        }
    }

    @Override
    public void render() {
        decorated.render();

        if (getG() != null) {

            getG().setColor(new Color(0, 255, 255, 100));
            getG().fillOval((int) getX() - 60, (int) getY() - 60, 160, 160);
            getG().setColor(new Color(0, 255, 255));
            getG().setStroke(new BasicStroke(6));
            getG().drawOval((int) getX() - 60, (int) getY() - 60, 160, 160);
            getG().setColor(Color.WHITE);
            getG().setFont(new Font("Arial", Font.BOLD, 24));
            getG().drawString("SPEED", (int) getX() - 42, (int) getY() + 10);
        }
    }
}
