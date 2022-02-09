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
import org.bukkit.util.Vector;

public class ClassicFactionsSettings extends FactionsSettings {

    public ClassicFactionsSettings(@NonNull PrimePlugin plugin) {
        super(plugin);
    }

    @Override
    public Location getFactionsPowerLbLocation() {
        return new Location(getPlugin().getServer().getWorld("Factions"), 431.5, 68.7, 1595.5);
    }

    @Override
    public @NonNull GameServerSettings getServerSettings() {
        return new GameServerSettings("Fac_advanced", GameMode.FACTIONS, GameServerStatus.ALPHA, "primegames.net", 19332, "https://primegames.net/servericons/server/Factions.png");
    }

    @Override
    public @NonNull String getLobbyWorldName() {
        return "Factions";
    }

    @Override
    public @NonNull Vector getLobbySpawn() {
        return new Vector(0, 64, 0);
    }

    @Override
    public boolean onVoteClaim(Player player, VoteSite site) {
        player.sendMessage("§aYou have claimed §e" + site.getVote() + "§a!");
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "ac key give " + player.getName() + " Vote 1");
        return true;
    }
}
