package me.noodles.scp.updatechecker;
import java.net.*;

import me.noodles.scp.Main;
import org.bukkit.Bukkit;

import java.io.*;
import java.util.Scanner;
import java.util.function.Consumer;

public class UpdateChecker {

    private final String URL = "https://api.spigotmc.org/legacy/update.php?resource=";

    private Main plugin;
    private int resourceId;

    public UpdateChecker(Main plugin, int resourceId) {
        this.plugin = plugin;
        this.resourceId = resourceId;
    }

    public void getLatestVersion(final Consumer<String> consumer) {
        Bukkit.getScheduler().runTaskAsynchronously(getPlugin(), () -> {
           try (InputStream inputStream = new URL(URL + getResourceId()).openStream(); Scanner scanner = new Scanner(inputStream)) {
               if (scanner.hasNext()) {
                   consumer.accept(scanner.next());
               }
           } catch (IOException exception) {
               getPlugin().getLogger().info("Cannot look for updates: " + exception.getMessage());
           }
        });
    }

    public Main getPlugin() {
        return plugin;
    }

    public int getResourceId() {
        return resourceId;
    }

}
