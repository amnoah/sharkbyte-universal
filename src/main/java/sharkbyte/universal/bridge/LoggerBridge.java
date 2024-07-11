package sharkbyte.universal.bridge;

/**
 * This is a simple interface for SharkByte projects to be able to log to whatever message system is being used. While
 * most projects will use a console logger, I'm not going to assume everything will - especially since some SharkByte
 * projects using this system may not be working with a console whatsoever.
 *
 * @Authors: am noah
 * @Since: 1.0.0
 * @Updated: 1.0.0
 */
public interface LoggerBridge {

    /**
     * Logs an info-level message as output.
     */
    void logInfo(String message);

    /**
     * Logs a warning-level message as output.
     */
    void logWarning(String message);
}
