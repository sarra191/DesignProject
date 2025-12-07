package util;
public class Config {
    // Window
    public static final int WINDOW_WIDTH = 800;
    public static final int WINDOW_HEIGHT = 600;
    public static final int GROUND_Y = 550;
    
    // Bird
    public static final int BIRD_WIDTH = 34;
    public static final int BIRD_HEIGHT = 24;
    public static final float GRAVITY = 0.3f;
    public static final float FLAP_STRENGTH = -8f;
    public static final float MAX_VELOCITY = 7f;
    
    // Pipes
    public static final int PIPE_WIDTH = 52;
    public static final int PIPE_GAP = 140;
    public static final int PIPE_SPACING = 200;
    public static final float PIPE_SPEED = 3f;
    
    // Game
    public static final int FPS = 60;
    public static final int INITIAL_SCORE = 0;
    
    private Config() {}
}
