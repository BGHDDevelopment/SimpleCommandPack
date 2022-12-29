package com.bgddevelopment.simplecommandpack;

import com.bgddevelopment.simplecommandpack.commands.FeedCommand;
import com.bgddevelopment.simplecommandpack.commands.HealCommand;
import com.bgddevelopment.simplecommandpack.commands.admin.DayCommand;
import com.bgddevelopment.simplecommandpack.commands.admin.FlyCommand;
import com.bgddevelopment.simplecommandpack.commands.admin.GameModeCommand;
import com.bgddevelopment.simplecommandpack.commands.admin.NightCommand;
import com.bgddevelopment.simplecommandpack.commands.messages.*;
import com.bgddevelopment.simplecommandpack.events.*;
import com.bgddevelopment.simplecommandpack.list.List;
import com.bgddevelopment.simplecommandpack.list.YouTubersList;
import com.bgddevelopment.simplecommandpack.updatechecker.UpdateChecker;
import com.bgddevelopment.simplecommandpack.updatechecker.UpdateJoinEvent;
import com.bgddevelopment.simplecommandpack.utilities.Color;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
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

        this.registerCommand("teamspeak", new Teamspeak());
        this.registerCommand("gm", new GameModeCommand());
        this.registerCommand("discord", new Discord());
        this.registerCommand("twitter", new Twitter());
        this.registerCommand("website", new Website());
        this.registerCommand("fly", new FlyCommand());
        this.registerCommand("apply", new Apply());
        this.registerCommand("rules", new Rules());
        this.registerCommand("store", new Store());

        this.registerCommand("heal", new HealCommand());
        this.registerCommand("feed", new FeedCommand());
        this.registerCommand("night", new NightCommand());
        this.registerCommand("day", new DayCommand());

        this.registerCommand("scphelp", new HelpMessage());
        this.registerCommand("youtubers", new YouTubersList());
        this.registerCommand("list", new List());

        this.getLogger().info(String.format("SimpleCommandPack v%s loading events ...", version));

        registerEvents(this, new YTlogin(), new YTLeave());
        registerEvents(this, new LoginEvent(), new LeaveEvent());
        registerEvents(this, new JoinEventDonors(), new LeaveEventDonor());
        registerEvents(this, new UpdateJoinEvent());

        this.getLogger().info(String.format("SimpleCommandPack v%s started ...", version));

        if (getConfig().getBoolean("CheckForUpdates.Enabled", true)) {
            new UpdateChecker(this, 45204).getLatestVersion(remoteVersion -> {
                getLogger().info("Checking for Updates ...");

                if (getDescription().getVersion().equalsIgnoreCase(remoteVersion)) {
                    getLogger().info("No new version available");
                } else {
                    getLogger().warning(String.format("Newest version: %s is out! You are running version: %s", remoteVersion, getDescription().getVersion()));
                    getLogger().warning("Please Update Here: http://www.spigotmc.org/resources/45204");
                }
            });
        }

    }

    static {
        SCP.youtuber = new ArrayList<>();
        SCP.onlineStaff = new ArrayList<>();
        SCP.Donor = new ArrayList<>();
    }

    public synchronized static SCP getInstance() {
        return instance;
    }

    private void registerCommand(final String command, final CommandExecutor executor) {
        this.getCommand(command).setExecutor(executor);
    }

    private void registerEvents(final Plugin plugin, final Listener... listeners) {
        for (final Listener listener : listeners) {
            Bukkit.getServer().getPluginManager().registerEvents(listener, plugin);
        }
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
                if(archived) {
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
                        sender.sendMessage(Color.translate("&aBuildMode is on the latest version."));
                    }
                } else {
                    sender.sendMessage(Color.translate(""));
                    sender.sendMessage(Color.translate(""));
                    sender.sendMessage(Color.translate("&cYour BuildMode version is out of date!"));
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