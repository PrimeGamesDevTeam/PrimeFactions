package net.primegames.primefactions;

import lombok.Getter;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.primegames.bedrockforms.ShopForm;
import net.primegames.bedrockforms.WarpsForm;
import net.primegames.commands.BedrockPlayerCommandHandler;
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
import net.primegames.utils.BedrockPlayerCallback;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.geysermc.floodgate.util.DeviceOs;

import java.io.IOException;

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
        BedrockPlayerCommandHandler.handle("/shop", new BedrockPlayerCallback() {
            @Override
            public void call(Player player) {
                ShopForm.init(player);
                ignore(DeviceOs.UWP);
            }
        });
        BedrockPlayerCommandHandler.handle("/warps", new BedrockPlayerCallback() {
            @Override
            public void call(Player player) {
                WarpsForm.init(player);
            }
        });
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
        componentManager.register(new ForceSpawnComponent(this, getFactionsSettings().getLobbySpawnLocation()));
    }

}
