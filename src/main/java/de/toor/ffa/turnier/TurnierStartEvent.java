package de.toor.ffa.turnier;

import de.scar.cloud.main.Cloud;
import de.scar.cloud.player.CloudPlayer;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import java.util.HashMap;

public class TournamentStartCommand implements CommandExecutor {

    private HashMap<CloudPlayer, Tournament> tournaments;

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(sender instanceof Player) {
            final Player player = (Player) sender;
            final CloudPlayer cloudPlayer = Cloud.INSTANCE.getCloudPlayerManager().getCachedCloudPlayer(player.getUniqueId());
            if (player.hasPermission("Thuglife.FFA.Turnier")) {
                if(args.length > 0) {
                    Inventory inventory = Bukkit.createInventory(null, 27, "§bFFA Turnier");
                    tournaments.put(cloudPlayer, new Tournament(cloudPlayer));
                }
            }else {
                //send no perm
            }
        }
        return false;
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        if(event.getClickedInventory() == null) return;
        if(event.getCurrentItem() == null) return;
        if(event.getCurrentItem().getItemMeta() == null) return;
        if(event.getClickedInventory().getName().equalsIgnoreCase("§bFFA Turnier")) {
            final CloudPlayer cloudPlayer = Cloud.INSTANCE.getCloudPlayerManager().getCachedCloudPlayer(event.getWhoClicked().getUniqueId());
            //SELECT MODE
            //CHEck mit ScreenManager ob screen existiert
            final Tournament tournament = tournaments.get(cloudPlayer);
            tournament.setMode(TournamentMode.KO);
            tournament.start();
        }
    }
}