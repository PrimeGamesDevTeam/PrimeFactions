package net.primegames.primefactions.commands;

import net.primegames.primefactions.PrimeFactions;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class LobbyCommand extends Command {

    public LobbyCommand() {
        super("spawn", "Teleport to the lobby", "lobby", List.of(new String[]{"lobby"}));
    }

    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {
        if (sender instanceof Player player) {
            player.teleport(PrimeFactions.getInstance().getFactionsSettings().getLobbySpawnLocation());
            return true;
        }
        return false;
    }
}
