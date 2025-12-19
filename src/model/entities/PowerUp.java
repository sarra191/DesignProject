package model.entities;

import java.awt.*;

public class PowerUp extends Entity {

    private final String type;
    private static Graphics2D staticG2D = null;

    public PowerUp(float x, float y, String type) {
        super(x, y, 60, 60);
        this.type = type;
        this.active = true;
    }

    @Override
    public void update() {
        x -= 2f;
        if (x < -100)
            active = false;
    }

    public static void setGraphics(Graphics2D g) {
        staticG2D = g;
    }

    @Override
    public void render() {
        if (!active || staticG2D == null)
            return;

        switch (type) {
            case "DOUBLE_POINTS" -> staticG2D.setColor(new Color(255, 215, 0));
            case "SPEED_BOOST" -> staticG2D.setColor(new Color(0, 255, 255));
            case "SHIELD_BOOST" -> staticG2D.setColor(new Color(100, 150, 255));
            default -> staticG2D.setColor(Color.MAGENTA);
        }

        staticG2D.fillOval((int) x - 30, (int) y - 30, 60, 60);
        staticG2D.setColor(Color.WHITE);
        staticG2D.drawOval((int) x - 30, (int) y - 30, 60, 60);

        staticG2D.setFont(new Font("Arial", Font.BOLD, 16));
        String label = switch (type) {
            case "DOUBLE_POINTS" -> "2x";
            case "SPEED_BOOST" -> "SPEED";
            case "SHIELD_BOOST" -> "SHIELD";
            default -> "?";
        };
        FontMetrics fm = staticG2D.getFontMetrics();
        int tx = (int) x - fm.stringWidth(label) / 2;
        int ty = (int) y + 6;
        staticG2D.drawString(label, tx, ty);
    }

    public String getType() {
        return type;
    }

    public Rectangle getBounds() {
        return new Rectangle((int) x - 30, (int) y - 30, 60, 60);
    }
}