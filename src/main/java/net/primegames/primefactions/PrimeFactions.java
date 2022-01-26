package net.primegames.primefactions;

import lombok.Getter;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.primegames.plugin.PrimePlugin;
import net.primegames.primefactions.commands.LobbyCommand;
import net.primegames.primefactions.listener.FactionsGroupListener;
import net.primegames.primefactions.settings.FactionsSettings;
import net.primegames.primefactions.settings.presets.ClassicFactionsSettings;
import org.bukkit.plugin.PluginManager;

public final class PrimeFactions extends PrimePlugin {

    @Getter
    private static PrimeFactions instance;
    @Getter
    private LuckPerms luckPerms;


    @Override
    protected void onInternalLoad() {
        instance = this;

        setServerSettings(new ClassicFactionsSettings(this));
    }

    @Override
    protected void onInternalEnable() {
        luckPerms = LuckPermsProvider.get();
        getServer().getCommandMap().register("f", new LobbyCommand());
    }

    @Override
    protected void onInternalDisable() {
        super.onInternalDisable();
    }

    @Override
    protected void registerListeners(PluginManager pluginManager) {
        pluginManager.registerEvents(new FactionsGroupListener(), this);
    }

    public FactionsSettings getFactionsSettings(){
        return (FactionsSettings) super.getServerSettings();
    }

}
