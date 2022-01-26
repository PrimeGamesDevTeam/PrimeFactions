package net.primegames.primefactions.settings.presets;

import lombok.NonNull;
import net.primegames.plugin.PrimePlugin;
import net.primegames.primefactions.settings.FactionsSettings;
import net.primegames.server.GameMode;
import net.primegames.server.GameServerSettings;
import net.primegames.server.GameServerStatus;
import net.primegames.utils.LoggerUtils;
import org.bukkit.util.Vector;

public class ClassicFactionsSettings extends FactionsSettings {

    public ClassicFactionsSettings(@NonNull PrimePlugin plugin) {
        super(plugin);
    }

    @Override
    public @NonNull GameServerSettings getServerSettings() {
        return new GameServerSettings("Class Factions", GameMode.FACTIONS, GameServerStatus.PRODUCTION, "primegames.net", 19242, null);
    }

    @Override
    public @NonNull String getLobbyWorldName() {
        return "Factions";
    }

    @Override
    public @NonNull Vector getLobbySpawn() {
        return new Vector(0, 64, 0);
    }
}
