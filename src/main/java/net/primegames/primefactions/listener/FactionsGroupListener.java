package net.primegames.primefactions.listener;

import net.primegames.PrimeGames;
import net.primegames.event.player.CorePlayerLoadedEvent;
import net.primegames.primefactions.groups.FactionsGroups;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class FactionsGroupListener implements Listener {

    @EventHandler
    public void onGroupLoad(CorePlayerLoadedEvent event) {
        FactionsGroups.addGroupsFromTiers(event.getPlayer(), event.getPlayerData().getGroupTiers());
        FactionsGroups groups = FactionsGroups.getGroupFor(event.getPlayer());
        event.getPlayer().setDisplayName(groups.getDisplayNameFor(event.getPlayer()));
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        if (!PrimeGames.getInstance().getCorePlayerManager().isFloodGatePlayer(event.getPlayer())) {
            FactionsGroups groups = FactionsGroups.getGroupFor(event.getPlayer());
            event.getPlayer().setDisplayName(groups.getDisplayNameFor(event.getPlayer()));
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onChat(AsyncPlayerChatEvent event) {
        FactionsGroups group = FactionsGroups.getGroupFor(event.getPlayer());
        event.setCancelled(true);
        Bukkit.broadcastMessage(group.getChatFormatFor(event.getPlayer(), event.getMessage()));
    }

}
