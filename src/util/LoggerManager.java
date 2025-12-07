package util;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class LoggerManager {
    private static final DateTimeFormatter fmt = DateTimeFormatter.ofPattern("HH:mm:ss");
    public static void info(String msg) { System.out.println("[INFO] " + msg); }
    public static void warn(String msg) { System.out.println("[WARN] " + msg); }
    public static void error(String msg) { System.err.println("[ERROR] " + msg); }
    public static void error(String msg, Exception e) { error(msg); e.printStackTrace(); }
    public static void debug(String msg) { System.out.println("[DEBUG] " + msg); }
}
