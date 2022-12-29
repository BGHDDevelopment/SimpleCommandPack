package com.bgddevelopment.simplecommandpack.commands.admin;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import com.bgddevelopment.simplecommandpack.SCP;
import com.bgddevelopment.simplecommandpack.factories.GameModeFactory;
import com.bgddevelopment.simplecommandpack.utilities.Common;
import com.bgddevelopment.simplecommandpack.utilities.Messages;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;

import java.util.Optional;
import java.util.*;

@CommandAlias("gamemode|gm")
@Description("Allows you change your own or someone else's gamemode.")
@CommandPermission("scp.gamemode")
@Conditions("noconsole")
public final class GameModeCommand extends BaseCommand implements TabCompleter {

    @Dependency
    private SCP plugin;

    private final String GAMEMODE_CHANGE = "Gamemode changed to %s!";

    @Default
    public void onDefault(CommandSender sender, String[] args) {
        final GameModeFactory factory = new GameModeFactory();

        if (plugin.getConfig().getBoolean("Gamemode.Enabled", true)) {

            final Player player = (Player) sender;

            if (args.length == 1) {
                final Optional<GameMode> gamemode = factory.getGameMode(args[0]);

                if (gamemode.isPresent()) {
                    player.setGameMode(gamemode.get());

                    Common.success(player, String.format(GAMEMODE_CHANGE, Common.capitalize(gamemode.get().name())));

                    return;
                }
            } else if (args.length == 2) {
                final Player target = Bukkit.getPlayer(args[1]);

                if (target != null) {
                    final Optional<GameMode> gamemode = factory.getGameMode(args[0]);

                    if (gamemode.isPresent()) {
                        target.setGameMode(gamemode.get());

                        Common.info(target, String.format(GAMEMODE_CHANGE, Common.capitalize(gamemode.get().name())));
                        Common.success(player, String.format("&f%s's &agamemode was changed to %s!", target.getName(), Common.capitalize(gamemode.get().name())));

                        return;
                    }
                }

                Common.info(player, Messages.PLAYER_OFFLINE);

                return;
            }

            Common.warn(player, "Invalid usage &f/<command> <gamemode> (player)".replace("<command>", "gamemode"));

            return;
        }

        return;
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

}
