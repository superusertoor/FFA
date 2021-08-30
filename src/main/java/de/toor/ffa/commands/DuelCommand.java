package de.toor.ffa.commands;

import de.scar.cloud.main.Cloud;
import de.scar.cloud.player.CloudPlayer;
import de.toor.ffa.objects.StatsPlayer;
import de.toor.ffa.objects.StatsPlayerPool;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DuelCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length == 1) {
                if (args[0].equalsIgnoreCase(player.getName())) {
                    player.sendMessage("§7[§cFFA§7] §cDu kannst dir selber keine Anfrage senden");
                    return true;
                }
                if (Bukkit.getPlayerExact(args[0]) == null) {
                    player.sendMessage("§7[§cFFA§7] §cDer angegebene Spieler ist offline");
                    return true;
                } else {
                    final Player target = Bukkit.getPlayerExact(args[0]);
                    StatsPlayer targetStatsPlayer = StatsPlayerPool.getStatsPlayer(Bukkit.getPlayerExact(args[0]).getUniqueId());
                    StatsPlayer selfStatsPlayer = StatsPlayerPool.getStatsPlayer(player.getUniqueId());
                    CloudPlayer selfCloudPlayer = Cloud.INSTANCE.getCloudPlayerManager().getCachedCloudPlayer(player.getUniqueId());
                    if (!targetStatsPlayer.getRequests().contains(selfCloudPlayer.getUniqueID())) {
                        player.sendMessage("§7[§cFFA§7] §aDeine Anfrage wurde an " + targetStatsPlayer.getColor() + targetStatsPlayer.getName() + " §ageschickt");
                        Bukkit.getPlayer(targetStatsPlayer.getName()).sendMessage("§7[§cFFA§7] §aDu hast eine Duellanfrage von " + selfCloudPlayer.getRank().getColor() + selfCloudPlayer.getName() + " §aerhalten");
                        targetStatsPlayer.addRequest(selfCloudPlayer.getUniqueID());
                    } else {
                        player.sendMessage("§7[§cFFA§7] §cDu hast diesem Spieler bereits eine Duelanfrage geschickt");
                    }
                    if (selfStatsPlayer.getRequests().contains(targetStatsPlayer.getUniqueId())) {
                        selfStatsPlayer.getCloudPlayer().getBukkitPlayer().sendMessage("§7[§cFFA§7] §aDas Duell mit " + targetStatsPlayer.getColor() + targetStatsPlayer.getName() + " §abeginnt");
                        targetStatsPlayer.getCloudPlayer().getBukkitPlayer().sendMessage("§7[§cFFA§7] §aDas Duell mit " + selfStatsPlayer.getColor() + selfStatsPlayer.getName() + " §abeginnt");
                        selfStatsPlayer.getRequests().remove(targetStatsPlayer.getUniqueId());
                        targetStatsPlayer.getRequests().remove(selfStatsPlayer.getRequests());
                        target.teleport(new Location(Bukkit.getWorld("world"), -755.5, 17, 394.5, -90 ,0));
                        player.teleport(new Location(Bukkit.getWorld("world"), -739.5, 17 ,394.5, 90, 0));
                        selfStatsPlayer.setInDuell(true);
                        targetStatsPlayer.setInDuell(true);
                        selfStatsPlayer.setOpponent(targetStatsPlayer);
                        targetStatsPlayer.setOpponent(selfStatsPlayer);
                    }
                    return true;
                }
            }else {
                player.sendMessage("§7[§cFFA§7] §cBenutze: /Duell [Name]");
            }
        }
        return false;
    }
}