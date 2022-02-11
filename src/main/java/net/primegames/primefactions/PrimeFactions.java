package net.primegames.primefactions;

import lombok.Getter;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.primegames.components.ComponentManager;
import net.primegames.components.forcespace.ForceSpawnComponent;
import net.primegames.components.vote.VoteComponent;
import net.primegames.leaderboard.LeaderboardManager;
import net.primegames.plugin.PrimePlugin;
import net.primegames.primefactions.commands.LobbyCommand;
import net.primegames.primefactions.groups.FactionsGroup;
import net.primegames.primefactions.leaderboard.FactionsPowerLeaderboard;
import net.primegames.primefactions.listener.FactionsGroupListener;
import net.primegames.primefactions.listener.NoRainListener;
import net.primegames.primefactions.settings.FactionsSettings;
import net.primegames.primefactions.settings.presets.ClassicFactionsSettings;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public final class PrimeFactions extends PrimePlugin {

    @Getter
    private static PrimeFactions instance;
    @Getter
    private LuckPerms luckPerms;
    @Getter
    private FactionsSettings factionsSettings;
    @Getter
    private FactionsGroup[] groups;


    @Override
    protected void onInternalLoad() {
        instance = this;
        setServerSettings(factionsSettings = new ClassicFactionsSettings(this));
    }

    @Override
    protected void onInternalEnable() {
        luckPerms = LuckPermsProvider.get();
        registerCommand(new LobbyCommand(), true);
        LeaderboardManager.getInstance().registerLeaderboard(new FactionsPowerLeaderboard(getFactionsSettings().getFactionsPowerLbLocation()));
        groups = FactionsGroup.values();
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

    @Override
    protected void registerComponents(ComponentManager componentManager) {
        try {
            ComponentManager.getInstance().register(new VoteComponent(this));
        } catch (IOException e) {
            e.printStackTrace();
        }
        componentManager.register(new ForceSpawnComponent(this, Objects.requireNonNull(Bukkit.getWorld(factionsSettings.getLobbyWorldName())).getSpawnLocation()));
    }

}
