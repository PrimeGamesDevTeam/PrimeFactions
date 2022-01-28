package net.primegames.primefactions;

import lombok.Getter;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.primegames.leaderboard.LeaderboardManager;
import net.primegames.plugin.PrimePlugin;
import net.primegames.primefactions.commands.LobbyCommand;
import net.primegames.primefactions.leaderboard.FactionsPowerLeaderboard;
import net.primegames.primefactions.listener.FactionsGroupListener;
import net.primegames.primefactions.listener.NoRainListener;
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
        registerCommand(new LobbyCommand(), true);
        LeaderboardManager.getInstance().registerLeaderboard(new FactionsPowerLeaderboard(getFactionsSettings().getFactionsPowerLbLocation()));
    }

    @Override
    protected void onInternalDisable() {
        super.onInternalDisable();
    }

    @Override
    protected void registerListeners(PluginManager pluginManager) {
        pluginManager.registerEvents(new FactionsGroupListener(), this);
        pluginManager.registerEvents(new NoRainListener(), this);
    }

    public FactionsSettings getFactionsSettings(){
        return (FactionsSettings) super.getServerSettings();
    }

}
