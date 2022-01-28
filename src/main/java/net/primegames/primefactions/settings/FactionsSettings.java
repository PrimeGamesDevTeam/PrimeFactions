package net.primegames.primefactions.settings;

import lombok.NonNull;
import net.primegames.plugin.PrimePlugin;
import net.primegames.server.settings.ServerSettings;
import org.bukkit.Location;

public abstract class FactionsSettings extends ServerSettings {

    public FactionsSettings(@NonNull PrimePlugin plugin) {
        super(plugin);
    }

    public abstract Location getFactionsPowerLbLocation();

}
