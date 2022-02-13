package net.primegames.primefactions.settings.presets;

import lombok.NonNull;
import net.primegames.components.vote.data.VoteSite;
import net.primegames.plugin.PrimePlugin;
import net.primegames.primefactions.settings.FactionsSettings;
import net.primegames.server.GameMode;
import net.primegames.server.GameServerSettings;
import net.primegames.server.GameServerStatus;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class ClassicFactionsSettings extends FactionsSettings {

    public ClassicFactionsSettings(@NonNull PrimePlugin plugin) {
        super(plugin);
    }

    @Override
    public Location getFactionsPowerLbLocation() {
        return new Location(getPlugin().getServer().getWorld("warzone"), 2081.5, 67, 2552.5);
    }

    @Override
    public @NonNull GameServerSettings getServerSettings() {
        return new GameServerSettings("Fac_advanced", GameMode.FACTIONS, GameServerStatus.BETA, "primegames.net", 19332, "https://primegames.net/servericons/server/Factions.png");
    }

    @Override
    public @NonNull String getLobbyWorldName() {
        return "warzone";
    }

    @Override
    public @NonNull Location getLobbySpawnLocation() {
        return new Location(getPlugin().getServer().getWorld(getLobbyWorldName()), 2081.5, 67, 2593.5, 180, -10.5f);
    }

    @Override
    public boolean onVoteClaim(Player player, VoteSite site) {
        player.sendMessage("§aYou have claimed §e" + site.getVote() + "§a!");
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "ac key give " + player.getName() + " Vote 1");
        return true;
    }
}
