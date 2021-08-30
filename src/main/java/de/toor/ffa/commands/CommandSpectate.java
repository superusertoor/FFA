package de.toor.ffa.commands;

import de.scar.cloud.main.Cloud;
import de.scar.cloud.player.Language;
import de.toor.ffa.main.FFA;
import de.toor.ffa.manager.InventoryManager;
import de.toor.ffa.manager.KitManager;
import de.toor.ffa.manager.LocationManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandSpectate implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player) {
			Player p = (Player)sender;
			Language language = Cloud.INSTANCE.getCloudPlayerManager().getCachedCloudPlayer(p.getUniqueId()).getLanguage();
				if(p.hasPermission("FFA.Spectate")) {
					if(args.length == 0) {
						p.sendMessage(language.getMessage("spectate-language"));
					}else if(args.length == 1) {
						if(args[0].equalsIgnoreCase("off")) {
							p.sendMessage(language.getMessage("spectate-disabled"));
							FFA.vanish.remove(p.getUniqueId());
							p.setAllowFlight(false);
							p.setFlying(false);
							KitManager.loadKit(p);
							p.teleport(LocationManager.getSpawnLocation());
							for(Player all : Bukkit.getOnlinePlayers()) {
								all.showPlayer(p);
								if(FFA.vanish.contains(all.getUniqueId())) {
									p.hidePlayer(all);
									all.showPlayer(p);
								}
							}
							return true;
						}
						if(FFA.vanish.contains(p.getUniqueId())) {
							p.sendMessage(language.getMessage("already-spectating"));
							return true;
						}
						Player target = Bukkit.getPlayer(args[0]);
						if(target == null) {
							p.sendMessage(language.getMessage("target-not-found"));
							return true;
						}
						if(target == p) {
							p.sendMessage(language.getMessage("spectate-self"));
							return true;
						}
						InventoryManager.clear(p);
						p.setAllowFlight(true);
						p.setFlying(true);
						FFA.vanish.add(p.getUniqueId());
						p.teleport(target.getLocation());
						p.sendMessage(language.getMessage("spectate-enabled"));
						Bukkit.getOnlinePlayers().forEach(online ->{
							if(FFA.vanish.contains(online.getUniqueId())) {
								online.showPlayer(p);
								p.showPlayer(online);
							}else {
								online.hidePlayer(p);
							}
						});
					}else {
						p.sendMessage(language.getMessage("spectate-self"));
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
