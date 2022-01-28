package net.primegames.primefactions.leaderboard;

import com.massivecraft.factions.Faction;
import com.massivecraft.factions.Factions;
import net.primegames.leaderboard.Leaderboard;
import org.bukkit.Location;

import java.util.*;

public class FactionsPowerLeaderboard extends Leaderboard {

    public FactionsPowerLeaderboard(Location location) {
        super("Factions Power", location);
    }

    @Override
    public void scheduleUpdate() {
        ArrayList<Faction> factions = Factions.getInstance().getAllFactions();
        if (factions.size() < 10) {
            return;
        }
        factions.sort((f1, f2) -> Integer.compare(f2.getPowerRounded(), f1.getPowerRounded()));
        Map<Integer, String> values = new HashMap<>(10);
        for (int i = 0; i < 10; i++) {
            values.put(i, factions.get(i).getId() + ": " + factions.get(i).getPowerRounded());
        }
        updateValues(values, "ℙ");
    }
}
