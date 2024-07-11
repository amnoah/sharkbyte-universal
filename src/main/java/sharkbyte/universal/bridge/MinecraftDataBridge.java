package sharkbyte.universal.bridge;

import java.util.UUID;

/**
 * While not all SharkByte projects are built for Minecraft, the initial purpose of the project was Minecraft
 * development. Thus, utilities like this one exist to help connect projects to a Minecraft server.
 *
 * @Authors: am noah
 * @Since: 1.0.0
 * @Updated: 1.0.0
 */
public interface MinecraftDataBridge {

    /**
     * Return whether the player with the associated UUID has any of the inputted permission nodes.
     * If they have none, return false.
     * If they have at least one but are missing others, return true.
     * If they have all, return true.
     */
    boolean hasPermission(UUID uuid, String... permission);

    /**
     * Run the given command as console.
     */
    boolean sendCommand(String command);
}
