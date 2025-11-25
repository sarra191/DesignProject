package model.entities;

import javafx.geometry.Rectangle2D;

/**
 * Classe de base pour toutes les entités du jeu.
 * Fournit position, taille, vélocités, état actif/inactif et utilitaires de collision.
 *
 * TODO: Créer Player, Enemy, Obstacle, PowerUp dans leurs propres fichiers en étendant cette classe.
 */
public abstract class Entity {
    protected double x;
    protected double y;
    protected double width = 32;
    protected double height = 32;

    protected double velocityX = 0;
    protected double velocityY = 0;

    protected boolean active = true;

    public Entity(double x, double y) {
        this.x = x;
        this.y = y;
    }

    // Méthode que les sous-classes doivent implémenter pour mettre à jour l'état (dt en secondes)
    public abstract void update(double dt);

    // Comportement par défaut lors d'une collision (peut être surchargé)
    public void onCollision(Entity other) {
        // comportement par défaut : inactiver si collision avec un objet hostile
        if (other instanceof Enemy) {
            this.active = false;
        }
    }

    // Rectangle de collision utilisé par les GameState pour détecter les collisions
    public Rectangle2D getBounds() {
        return new Rectangle2D(x, y, width, height);
    }

    // Test d'intersection simple
    public boolean intersects(Entity other) {
        return this.getBounds().intersects(other.getBounds());
    }

    // getters / setters utiles
    public double getX() { return x; }
    public double getY() { return y; }
    public void setX(double x) { this.x = x; }
    public void setY(double y) { this.y = y; }

    public double getWidth() { return width; }
    public double getHeight() { return height; }
    public void setSize(double w, double h) { this.width = w; this.height = h; }

    public double getVelocityX() { return velocityX; }
    public double getVelocityY() { return velocityY; }
    public void setVelocityX(double vx) { this.velocityX = vx; }
    public void setVelocityY(double vy) { this.velocityY = vy; }

    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }

    // Position update helper (utile pour sous-classes)
    protected void applyMovement(double dt) {
        x += velocityX * dt;
        y += velocityY * dt;
    }

    @Override
    public String toString() {
        return String.format("%s[x=%.1f,y=%.1f,w=%.1f,h=%.1f,ax=%.1f,ay=%.1f,active=%b]",
                this.getClass().getSimpleName(), x, y, width, height, velocityX, velocityY, active);
    }
}
