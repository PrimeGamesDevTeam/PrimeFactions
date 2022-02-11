package net.primegames.primefactions.listener;

import net.primegames.PrimeGames;
import net.primegames.event.player.CorePlayerLoadedEvent;
import net.primegames.primefactions.groups.FactionsGroup;
import net.primegames.utils.LoggerUtils;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class FactionsGroupListener implements Listener {

    @EventHandler
    public void onGroupLoad(CorePlayerLoadedEvent event) {
        FactionsGroup.addGroupsFromTiers(event.getPlayer(), event.getPlayerData().getGroupTiers());
        FactionsGroup groups = FactionsGroup.getGroupFor(event.getPlayer());
        event.getPlayer().setDisplayName(groups.getDisplayNameFor(event.getPlayer()));
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        if (!PrimeGames.getInstance().getCorePlayerManager().isFloodGatePlayer(event.getPlayer())) {
            FactionsGroup groups = FactionsGroup.getGroupFor(event.getPlayer());
            event.getPlayer().setDisplayName(groups.getDisplayNameFor(event.getPlayer()));
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onChat(AsyncPlayerChatEvent event) {
        FactionsGroup group;
        try {
              group = FactionsGroup.getGroupFor(event.getPlayer());
        } catch (Exception e) {
            LoggerUtils.error("Failed to get group for player " + event.getPlayer().getName() + "!");
            LoggerUtils.error(e.getMessage());
            return;
        }
        event.setCancelled(true);
        String message = group.getChatFormatFor(event.getPlayer(), event.getMessage());
        Bukkit.broadcastMessage(message);
    }

}
