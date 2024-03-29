package com.bgddevelopment.simplecommandpack;

import co.aikar.commands.BukkitCommandIssuer;
import co.aikar.commands.BukkitCommandManager;
import co.aikar.commands.ConditionFailedException;
import com.bgddevelopment.simplecommandpack.commands.FeedCommand;
import com.bgddevelopment.simplecommandpack.commands.HealCommand;
import com.bgddevelopment.simplecommandpack.commands.admin.DayCommand;
import com.bgddevelopment.simplecommandpack.commands.admin.FlyCommand;
import com.bgddevelopment.simplecommandpack.commands.admin.GameModeCommand;
import com.bgddevelopment.simplecommandpack.commands.admin.NightCommand;
import com.bgddevelopment.simplecommandpack.events.JoinLeaveEvents;
import com.bgddevelopment.simplecommandpack.utilities.Color;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public final class SCP extends JavaPlugin implements Listener {

    private static SCP instance;

    public static ArrayList<String> youtuber;
    public static ArrayList<String> onlineStaff;
    public static ArrayList<String> Donor;

    @Override
    public void onEnable() {
        instance = this;

        final String version = this.getDescription().getVersion();

        this.getLogger().info(String.format("SimpleCommandPack v%s starting ...", version));

        this.saveDefaultConfig();
        this.reloadConfig();

        this.getLogger().info(String.format("SimpleCommandPack v%s loading commands ...", version));

        this.loadCommands();

        this.getLogger().info(String.format("SimpleCommandPack v%s loading events ...", version));

        Bukkit.getPluginManager().registerEvents(new JoinLeaveEvents(), this);

        this.getLogger().info(String.format("SimpleCommandPack v%s started ...", version));
        updateCheck(Bukkit.getConsoleSender(), true);

    }

    static {
        SCP.youtuber = new ArrayList<>();
        SCP.onlineStaff = new ArrayList<>();
        SCP.Donor = new ArrayList<>();
    }

    public synchronized static SCP getInstance() {
        return instance;
    }

    private void loadCommands() {
        BukkitCommandManager manager = new BukkitCommandManager(this);
        manager.getCommandConditions().addCondition("noconsole", (context) -> {
            BukkitCommandIssuer issuer = context.getIssuer();
            if (!issuer.isPlayer()) {
                throw new ConditionFailedException("Console cannot use this command.");
            }
        });
        manager.registerCommand(new DayCommand());
        manager.registerCommand(new FlyCommand());
        manager.registerCommand(new GameModeCommand());
        manager.registerCommand(new NightCommand());
        manager.registerCommand(new NightCommand());
        manager.registerCommand(new FeedCommand());
        manager.registerCommand(new HealCommand());
    }

    public void updateCheck(CommandSender sender, boolean console) {
        try {
            String urlString = "https://updatecheck.bghddevelopment.com";
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("User-Agent", "Mozilla/5.0");
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String input;
            StringBuffer response = new StringBuffer();
            while ((input = reader.readLine()) != null) {
                response.append(input);
            }
            reader.close();
            JsonObject object = new JsonParser().parse(response.toString()).getAsJsonObject();

            if (object.has("plugins")) {
                JsonObject plugins = object.get("plugins").getAsJsonObject();
                JsonObject info = plugins.get("SimpleCommandPack").getAsJsonObject();
                String version = info.get("version").getAsString();
                Boolean archived = info.get("archived").getAsBoolean();
                if (archived) {
                    sender.sendMessage(Color.translate(""));
                    sender.sendMessage(Color.translate(""));
                    sender.sendMessage(Color.translate("&cThis plugin has been marked as 'Archived' by BGHDDevelopment LLC."));
                    sender.sendMessage(Color.translate("&cThis version will continue to work but will not receive updates or support."));
                    sender.sendMessage(Color.translate(""));
                    sender.sendMessage(Color.translate(""));
                    return;
                }
                if (version.equals(getDescription().getVersion())) {
                    if (console) {
                        sender.sendMessage(Color.translate("&aSimpleCommandPack is on the latest version."));
                    }
                } else {
                    sender.sendMessage(Color.translate(""));
                    sender.sendMessage(Color.translate(""));
                    sender.sendMessage(Color.translate("&cYour SimpleCommandPack version is out of date!"));
                    sender.sendMessage(Color.translate("&cWe recommend updating ASAP!"));
                    sender.sendMessage(Color.translate(""));
                    sender.sendMessage(Color.translate("&cYour Version: &e" + getDescription().getVersion()));
                    sender.sendMessage(Color.translate("&aNewest Version: &e" + version));
                    sender.sendMessage(Color.translate(""));
                    sender.sendMessage(Color.translate(""));
                }
            } else {
                sender.sendMessage(Color.translate("&cWrong response from update API, contact plugin developer!"));
            }
        } catch (
                Exception ex) {
            sender.sendMessage(Color.translate("&cFailed to get updater check. (" + ex.getMessage() + ")"));
        }
    }

}