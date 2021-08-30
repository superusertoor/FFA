package de.toor.ffa.commands;

import de.scar.cloud.main.Cloud;
import de.scar.cloud.player.Language;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandChatClear implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player) {
			Player p = (Player)sender;
			Language language = Cloud.INSTANCE.getCloudPlayerManager().getCachedCloudPlayer(p.getUniqueId()).getLanguage();
			if(args.length >= 0) {
				if(p.hasPermission("FFA.ChatClear")) {
					for(int i=0; i < 1000; i++) {
						for (Player all : Bukkit.getOnlinePlayers()) {
							if (!(all.hasPermission("FFA.ChatClear"))) {
								all.sendMessage("");
							}
						}
					}
				}else {
					p.sendMessage(language.getMessage("no-perm"));
				}
			}
		}else {
			sender.sendMessage("Du musst ein Spieler sein");
		}
		return false;
	}
}
