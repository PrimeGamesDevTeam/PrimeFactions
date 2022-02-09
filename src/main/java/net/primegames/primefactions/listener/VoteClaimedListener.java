package net.primegames.primefactions.listener;

import net.primegames.components.vote.event.VoteClaimEvent;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class VoteClaimedListener implements Listener {

    @EventHandler
    public void onVoteClaimed(VoteClaimEvent event) {
        event.getPlayer().sendMessage("§aYou have claimed §e" + event.getSite().getVote() + "§a!");
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "ac key give " + event.getPlayer().getName() + " Vote 1");
    }

}
