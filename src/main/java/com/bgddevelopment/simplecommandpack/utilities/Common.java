package com.bgddevelopment.simplecommandpack.utilities;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import java.util.Arrays;

/**
 * Author:  Thinkverse <work@hallberg.kim>
 * Created: 2020-03-31 15:40
 */
public final class Common {

    private Common() { }

    public static CommandSender CONSOLE = Bukkit.getConsoleSender();

    private final static String INFO_PREFIX = "&8[&9&li&r&8] &7";
    private final static String SUCCESS_PREFIX = "&8[&2\u2714&8] &a";
    private final static String WARN_PREFIX = "&8[&6&l!&r&8] &6";
    private final static String ERROR_PREFIX = "&8[&4\u2715&8] &c";
    private final static String QUESTION_PREFIX = "&8[&a?&8] &7";

    /**
     * Send a message to sender with info prefix
     * @param sender Sender to target
     * @param message Message to send
     */
    public static void info(final CommandSender sender, final String message) {
        tell(sender, INFO_PREFIX + message);
    }

    /**
     * Send a message to sender with success prefix
     * @param sender Sender to target
     * @param message Message to send
     */
    public static void success(final CommandSender sender, final String message) {
        tell(sender, SUCCESS_PREFIX + message);
    }

    /**
     * Send a message to sender with warn prefix
     * @param sender Sender to target
     * @param message Message to send
     */
    public static void warn(final CommandSender sender, final String message) {
        tell(sender, WARN_PREFIX + message);
    }

    /**
     * Send a message to sender with error prefix
     * @param sender Sender to target
     * @param message Message to send
     */
    public static void error(final CommandSender sender, final String message) {
        tell(sender, ERROR_PREFIX + message);
    }

    /**
     * Send a message to sender with question-mark prefix
     * @param sender Sender to target
     * @param message Message to send
     */
    public static void question(final CommandSender sender, final String message) {
        tell(sender, QUESTION_PREFIX + message);
    }

    /**
     * Send messages to sender
     * @param sender Sender to target
     * @param messages Messages to send
     */
    public static void tell(final CommandSender sender, final String... messages) {
        Arrays.stream(messages).map(Common::translate).forEach(sender::sendMessage);
    }

    /**
     * Broadcast messages to all online players,
     * that has a specific permission.
     * @param permission Permission node to target
     * @param messages Messages to broadcast
     */
    public static void notify(final String permission, final String... messages) {
        Arrays.stream(messages).map(Common::translate).forEach(message -> Bukkit.broadcast(message, permission));
    }

    /**
     * Broadcast messages to all online players.
     * @param messages Messages to broadcast
     */
    public static void broadcast(final String... messages) {
        Arrays.stream(messages).map(Common::translate).forEach(Bukkit::broadcastMessage);
    }

    /**
     * Capitalize a message.
     * <p>
     * Thank you Stack Overflow.
     *
     * @param message Message to capitalize
     * @return Capitalized message
     */
    public static String capitalize(final String message) {
        if (message == null || message.length() == 0) {
            return message;
        }

        return message.substring(0, 1).toUpperCase() + message.substring(1).toLowerCase();
    }

    /**
     * String translated {@link ChatColor}
     * @param message Message to strip
     * @return Stripped message
     */
    public static String strip(final String message) {
        return ChatColor.stripColor(message);
    }

    /**
     * Translate messages chat color.. {@link ChatColor}
     * @param message Message to translate
     * @return Translated message
     */
    public static String translate(final String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

}
