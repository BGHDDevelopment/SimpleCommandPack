package me.noodles.scp.commands.admin;

import me.noodles.scp.factories.GameModeFactory;
import me.noodles.scp.utilities.Common;
import org.bukkit.util.StringUtil;
import org.bukkit.command.*;
import me.noodles.scp.Main;
import org.bukkit.entity.*;
import org.bukkit.*;
import java.util.*;

public final class GameModeCommand implements TabExecutor {

    private Main plugin;

    public GameModeCommand(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(final CommandSender sender, final Command command, final String label, final String[] args) {
        final GameModeFactory factory = new GameModeFactory();

        if (getPlugin().getConfig().getBoolean("Gamemode.Enabled")) {

            if (sender instanceof Player) {
                final Player player = (Player) sender;

                if (player.hasPermission("scp.gamemode")) {

                    if (args.length == 1) {
                        final Optional<GameMode> gamemode = factory.getGameMode(args[0]);

                        if (gamemode.isPresent()) {
                            player.setGameMode(gamemode.get());

                            Common.success(player, String.format("Gamemode changed to %s!", Common.capitalize(gamemode.get().name())));

                            return true;
                        }
                    }

                    else if (args.length == 2) {
                        final Player target = Bukkit.getPlayer(args[1]);

                        if (target != null) {
                            final Optional<GameMode> gamemode = factory.getGameMode(args[0]);

                            if (gamemode.isPresent()) {
                                target.setGameMode(gamemode.get());

                                Common.info(target, String.format("Gamemode changed to %s!", Common.capitalize(gamemode.get().name())));
                                Common.success(player, String.format("&f%s's &agamemode was changed to %s!", target.getName(), Common.capitalize(gamemode.get().name())));

                                return true;
                            }
                        }

                        Common.info(player, "Player is not online!");

                        return true;
                    }

                    Common.warn(player, "Invalid usage &f/<command> <gamemode> (player)".replace("<command>", label));

                    return true;
                }

                Common.error(player, "You do not have permission to use this command!");

                return true;
            }

            Common.tell(Common.CONSOLE, "Only players can use that command!");

            return true;
        }

        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length == 1) {
            return StringUtil.copyPartialMatches(args[0], Collections.unmodifiableList(Arrays.asList("s", "c", "a", "sp")), new ArrayList<>());
        } else if (args.length == 2) {
            return null;
        } else {
            return Collections.emptyList();
        }
    }

    public Main getPlugin() {
        return plugin;
    }

}
