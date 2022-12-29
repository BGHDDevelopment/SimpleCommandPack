package com.bgddevelopment.simplecommandpack.factories;

import org.bukkit.GameMode;

import java.util.Optional;

/**
 * Author:  Thinkverse <work@hallberg.kim>
 * Created: 2020-03-31 16:31
 */
public final class GameModeFactory {

    private final String SURVIVAL_NUMBER = "s";
    private final String CREATIVE_NUMBER = "c";
    private final String ADVENTURE_NUMBER = "a";
    private final String SPECTATOR_NUMBER = "sp";

    private final String SURVIVAL_STRING = "survival";
    private final String CREATIVE_STRING = "creative";
    private final String ADVENTURE_STRING = "adventure";
    private final String SPECTATOR_STRING = "spectator";

    public Optional<GameMode> getGameMode(final String gamemode) {
        switch (gamemode) {
            case SURVIVAL_NUMBER:
            case SURVIVAL_STRING:
                return Optional.of(GameMode.SURVIVAL);
            case CREATIVE_NUMBER:
            case CREATIVE_STRING:
                return Optional.of(GameMode.CREATIVE);
            case ADVENTURE_NUMBER:
            case ADVENTURE_STRING:
                return Optional.of(GameMode.ADVENTURE);
            case SPECTATOR_NUMBER:
            case SPECTATOR_STRING:
                return Optional.of(GameMode.SPECTATOR);
            default:
                return Optional.empty();
        }
    }

}
