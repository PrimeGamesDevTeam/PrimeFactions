package net.primegames.primefactions.settings;

import lombok.NonNull;
import net.primegames.plugin.PrimePlugin;
import net.primegames.server.settings.ServerSettings;

public abstract class FactionsSettings extends ServerSettings {

    public FactionsSettings(@NonNull PrimePlugin plugin) {
        super(plugin);
    }


}
