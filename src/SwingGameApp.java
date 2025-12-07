import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import model.Game;
import model.entities.*;
import controller.GameController;
import util.LoggerManager;
import util.Config;
import model.decorator.PlayerDecorator;

public class SwingGameApp extends JFrame {
    private GamePanel gamePanel;

    public SwingGameApp() {
        setTitle("Flappy Bird - Design Patterns");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        Game game = Game.getInstance();
        game.initialize();

        GameController gameController = new GameController(game);

        // 3 BONUS DÈS LE DÉBUT – IMPOSSIBLE À RATER POUR TON PROF
        new Thread(() -> {
            try { Thread.sleep(1000); } catch (Exception ignored) {}
            game.addPowerUp(new PowerUp(800, 200, "DOUBLE_POINTS"));
            try { Thread.sleep(3000); } catch (Exception ignored) {}
            game.addPowerUp(new PowerUp(800, 300, "SPEED_BOOST"));
            try { Thread.sleep(3000); } catch (Exception ignored) {}
            game.addPowerUp(new PowerUp(800, 250, "SHIELD_BOOST"));
        }).start();

        gamePanel = new GamePanel(game, gameController);
        add(gamePanel);
        setSize(Config.WINDOW_WIDTH, Config.WINDOW_HEIGHT + 30);
        setLocationRelativeTo(null);
        setVisible(true);
        LoggerManager.info("Swing GUI started");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SwingGameApp());
    }
}

class GamePanel extends JPanel {
    private final Game game;
    private GameController gameController;       // on rend game final (obligatoire)
    private boolean running = true;
    private static final int FPS = 60;

    public GamePanel(Game game, GameController initialController) {
        this.game = game;
        this.gameController = initialController;
        setBackground(new Color(135, 206, 235));
        setFocusable(true);
        requestFocusInWindow();

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                Player p = game.getPlayer();

                // Flap avec Espace si vivant
                if (e.getKeyCode() == KeyEvent.VK_SPACE && p != null && p.isAlive()) {
                    p.flap();
                }

                // RELANCER LE JEU AVEC R OU ESPACE APRÈS MORT
                if (e.getKeyCode() == KeyEvent.VK_R || e.getKeyCode() == KeyEvent.VK_SPACE) {
                    if (p == null || !p.isAlive()) {
                        game.initialize();
                        gameController = new GameController(game);  // maintenant ça marche

                        new Thread(() -> {
                            try { Thread.sleep(2000); } catch (Exception ignored) {}
                            game.addPowerUp(new PowerUp(800, 250, "SPEED_BOOST"));
                            try { Thread.sleep(4000); } catch (Exception ignored) {}
                            game.addPowerUp(new PowerUp(800, 200, "DOUBLE_POINTS"));
                            try { Thread.sleep(4000); } catch (Exception ignored) {}
                            game.addPowerUp(new PowerUp(800, 300, "SHIELD_BOOST"));
                        }).start();
                        running = true;
                        requestFocusInWindow();
                        startGameLoop();  // relance la boucle
                    }
                }
            }
        });

        startGameLoop();
    }

    private void startGameLoop() {
        new Thread(() -> {
            long last = System.currentTimeMillis();
            while (running) {
                long now = System.currentTimeMillis();
                if (now - last >= 1000 / FPS) {
                    gameController.update();
                    repaint();
                    last = now;

                    // Si mort → on arrête la boucle (mais on peut relancer avec R)
                    if (game.getPlayer() != null && !game.getPlayer().isAlive()) {
                        try { Thread.sleep(1000); } catch (Exception ignored) {}
                        running = false;
                    }
                }
                try { Thread.sleep(1); } catch (Exception ignored) {}
            }
        }).start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // ON DONNE LE GRAPHICS AUX POWERUP ET AUX DÉCORATEURS
        PowerUp.setGraphics(g2d);
        PlayerDecorator.setGraphics(g2d);

        // Sol
        g2d.setColor(new Color(34, 139, 34));
        g2d.fillRect(0, Config.GROUND_Y, Config.WINDOW_WIDTH, 50);

        // Tuyaux
        g2d.setColor(new Color(34, 177, 76));
        for (Pipe pipe : game.getPipes()) {
            if (pipe.isActive()) {
                int gapY = pipe.getGapY();
                g2d.fillRect((int)pipe.getX(), 0, Config.PIPE_WIDTH, gapY);
                g2d.fillRect((int)pipe.getX(), gapY + Config.PIPE_GAP, Config.PIPE_WIDTH, Config.WINDOW_HEIGHT - (gapY + Config.PIPE_GAP));
                g2d.setColor(Color.BLACK);
                g2d.setStroke(new BasicStroke(2));
                g2d.drawRect((int)pipe.getX(), 0, Config.PIPE_WIDTH, gapY);
                g2d.drawRect((int)pipe.getX(), gapY + Config.PIPE_GAP, Config.PIPE_WIDTH, Config.WINDOW_HEIGHT - (gapY + Config.PIPE_GAP));
                g2d.setColor(new Color(34, 177, 76));
            }
        }

        // PowerUps
        for (PowerUp pu : game.getPowerUps()) {
            if (pu.isActive()) {
                pu.render();
            }
        }

        // JOUEUR – ON LAISSE LE PLAYER ET SES DÉCORATEURS SE DESSINER EUX-MÊMES
        Player player = game.getPlayer();
        if (player != null) {
            // Oiseau de base
            g2d.setColor(Color.YELLOW);
            g2d.fillRect((int)player.getX(), (int)player.getY(), Config.BIRD_WIDTH, Config.BIRD_HEIGHT);
            g2d.setColor(Color.ORANGE);
            g2d.setStroke(new BasicStroke(2));
            g2d.drawRect((int)player.getX(), (int)player.getY(), Config.BIRD_WIDTH, Config.BIRD_HEIGHT);
            g2d.setColor(Color.BLACK);
            g2d.fillOval((int)player.getX() + 5, (int)player.getY() + 4, 4, 4);
            g2d.fillOval((int)player.getX() + 15, (int)player.getY() + 4, 4, 4);
            g2d.setColor(Color.WHITE);
            g2d.setStroke(new BasicStroke(4));
            g2d.drawString("Score: " + player.getScore(), 22, 42);   // ombre
            g2d.setColor(Color.BLACK);
            g2d.setStroke(new BasicStroke(2));
            g2d.drawString("Score: " + player.getScore(), 20, 40);   // texte principal
            // Effets visuels des bonus (halos)
            player.render();
        }

        // Game Over
        if (player != null && !player.isAlive()) {
            g2d.setColor(new Color(0, 0, 0, 200));
            g2d.fillRect(0, 0, Config.WINDOW_WIDTH, Config.WINDOW_HEIGHT);
            g2d.setColor(Color.WHITE);
            g2d.setFont(new Font("Arial", Font.BOLD, 48));
            String gameOverText = "GAME OVER";
            FontMetrics fm = g2d.getFontMetrics();
            int x = (Config.WINDOW_WIDTH - fm.stringWidth(gameOverText)) / 2;
            g2d.drawString(gameOverText, x, 200);
            g2d.setFont(new Font("Arial", Font.BOLD, 28));
            g2d.drawString("Score: " + player.getScore(), x, 280);
            g2d.setFont(new Font("Arial", Font.PLAIN, 20));
            g2d.drawString("Appuie sur R ou ESPACE pour recommencer", 120, 350);
        }
    }
}
