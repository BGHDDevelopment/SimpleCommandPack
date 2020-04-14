package me.noodles.scp.commands.admin;

import me.noodles.scp.factories.GameModeFactory;
import me.noodles.scp.utilities.Common;
import me.noodles.scp.utilities.Messages;
import org.bukkit.util.StringUtil;
import org.bukkit.command.*;
import me.noodles.scp.SCP;
import org.bukkit.entity.*;
import org.bukkit.*;
import java.util.*;

public final class GameModeCommand implements TabExecutor {
    private final String GAMEMODE_CHANGE = "Gamemode changed to %s!";

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

                            Common.success(player, String.format(GAMEMODE_CHANGE, Common.capitalize(gamemode.get().name())));

                            return true;
                        }
                    }

                    else if (args.length == 2) {
                        final Player target = Bukkit.getPlayer(args[1]);

                        if (target != null) {
                            final Optional<GameMode> gamemode = factory.getGameMode(args[0]);

                            if (gamemode.isPresent()) {
                                target.setGameMode(gamemode.get());

                                Common.info(target, String.format(GAMEMODE_CHANGE, Common.capitalize(gamemode.get().name())));
                                Common.success(player, String.format("&f%s's &agamemode was changed to %s!", target.getName(), Common.capitalize(gamemode.get().name())));

                                return true;
                            }
                        }

                        Common.info(player, Messages.PLAYER_OFFLINE);

                        return true;
                    }

                    Common.warn(player, "Invalid usage &f/<command> <gamemode> (player)".replace("<command>", label));

                    return true;
                }

                Common.error(player, Messages.NO_PERMISSION);

                return true;
            }

            Common.tell(Common.CONSOLE, Messages.ONLY_PLAYERS);

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

    public SCP getPlugin() {
        return SCP.getInstance();
    }

}
