package net.primegames.primefactions.leaderboard;

import com.massivecraft.factions.Faction;
import com.massivecraft.factions.Factions;
import net.primegames.leaderboard.Leaderboard;
import org.bukkit.ChatColor;
import org.bukkit.Location;

import java.util.*;

public class FactionsPowerLeaderboard extends Leaderboard {

    public FactionsPowerLeaderboard(Location location) {
        super(ChatColor.RED + "[" + ChatColor.DARK_PURPLE + ChatColor.BOLD + "Faction Worth" + ChatColor.RESET + ChatColor.RED + "]", location);
    }

    @Override
    public void scheduleUpdate() {
        ArrayList<Faction> factions = Factions.getInstance().getAllFactions();
        int size = Math.min(factions.size(), 10);
        Map<Integer, String> values = new HashMap<>(10);
        for (int i = 0; i < size; i++) {
            if (factions.get(i).isNormal()) {
                values.put(factions.get(i).getPowerRounded(), factions.get(i).getTag());
            }
        }
        updateValues(values, "$");
    }
}
