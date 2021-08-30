package de.toor.ffa.commands;

import de.toor.ffa.objects.TopPlayers;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TopCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        TopPlayers top = new TopPlayers();
        Player player = (Player) sender;
        top.getTop().forEach(topPlayer -> {
            player.sendMessage(topPlayer.getColor() + topPlayer.getName() + "§7: §a" + topPlayer.getKills() + " §eKills");
        });
        return false;
    }
}
