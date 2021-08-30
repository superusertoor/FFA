package de.toor.ffa.commands;

import de.scar.cloud.main.Cloud;
import de.scar.cloud.player.Language;
import de.toor.ffa.main.FFA;
import de.toor.ffa.manager.InventoryManager;
import de.toor.ffa.manager.KitManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandVanish implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player) {
			Player p = (Player)sender;
			Language language = Cloud.INSTANCE.getCloudPlayerManager().getCachedCloudPlayer(p.getUniqueId()).getLanguage();
				if(p.hasPermission("FFA.Vanish")) {
					if(args.length == 0) {
						if(!FFA.vanish.contains(p.getUniqueId())) {
							p.sendMessage(language.getMessage("vanish-enabled"));
							FFA.vanish.add(p.getUniqueId());
							for(Player all : Bukkit.getOnlinePlayers()) {
								all.hidePlayer(p);
								if(FFA.vanish.contains(all.getUniqueId())) {
									p.showPlayer(all);
									all.showPlayer(p);
								}
							}
							p.setAllowFlight(true);
							p.setFlying(true);
							InventoryManager.clear(p);
						}else if(FFA.vanish.contains(p.getUniqueId())) {
							p.sendMessage(language.getMessage("vanish-disabled"));
							FFA.vanish.remove(p.getUniqueId());
							for(Player all : Bukkit.getOnlinePlayers()) {
								all.showPlayer(p);
								if(FFA.vanish.contains(all.getUniqueId())) {
									p.hidePlayer(all);
									all.showPlayer(p);
								}
							}
							KitManager.loadKit(p);
							p.setAllowFlight(false);
							p.setFlying(false);
							p.setHealth(20);
						}
					}else {
						p.sendMessage(language.getMessage("vanish-usage"));
					}
				}else {
					p.sendMessage(language.getMessage("no-perm"));
				}
		}else {
			sender.sendMessage("Du musst ein Spieler sein");
		}
		return false;
	}
}
