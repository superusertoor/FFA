package de.toor.ffa.commands;

import de.scar.cloud.main.Cloud;
import de.scar.cloud.player.Language;
import de.toor.ffa.manager.LocationManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandSetSpawn implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player p = (Player) sender;
			Language language = Cloud.INSTANCE.getCloudPlayerManager().getCachedCloudPlayer(p.getUniqueId()).getLanguage();
			if (p.hasPermission("FFA.SetSpawn")) {
				if (args.length >= 0) {
					LocationManager.setSpawn(p.getLocation());
					p.sendMessage(language.getMessage("spawnlocation-set"));
				}
			} else {
				p.sendMessage(language.getMessage("no-perm"));
			}
		} else {
			sender.sendMessage("Du musst ein Spieler sein");
		}
		return false;
	}
}
