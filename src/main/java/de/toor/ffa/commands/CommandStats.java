package de.toor.ffa.commands;

import de.scar.cloud.database.Database;
import de.scar.cloud.main.Cloud;
import de.scar.cloud.player.CloudPlayer;
import de.scar.cloud.player.Language;
import de.toor.ffa.objects.OfflineStatsPlayer;
import de.toor.ffa.objects.StatsPlayer;
import de.toor.ffa.objects.StatsPlayerPool;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import java.text.DecimalFormat;

public class CommandStats implements CommandExecutor {
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			StatsPlayer statsPlayer = StatsPlayerPool.getStatsPlayer(player.getUniqueId());
			CloudPlayer cloudPlayer = Cloud.INSTANCE.getCloudPlayerManager().getCachedCloudPlayer(player.getUniqueId());
			Language language = cloudPlayer.getLanguage();
			DecimalFormat format = new DecimalFormat("#.##");
			if (args.length == 0) {
				int kills = statsPlayer.getKills();
				int deaths = statsPlayer.getDeaths();
				int killstreak = statsPlayer.getKillstreak();
				double kd = (double) kills / deaths;
				int ranking = StatsPlayerPool.getStatsPlayer(player.getUniqueId()).getRanking();
				player.sendMessage(language.getMessage("own-stats-header"));
				player.sendMessage(language.getMessage("ranking").replace("%ranking%", String.valueOf(ranking)));
				player.sendMessage(language.getMessage("kills").replace("%kills%", String.valueOf(kills)));
				player.sendMessage(language.getMessage("deaths").replace("%deaths%", String.valueOf(deaths)));
				if (kills != 0) {
					player.sendMessage(language.getMessage("kd").replace("%kd%", format.format(kd)));
				} else {
					player.sendMessage(language.getMessage("kdNA"));
				}
				player.sendMessage(language.getMessage("killstreak").replace("%killstreak%", String.valueOf(killstreak)));
				player.sendMessage(language.getMessage("own-stats-header"));
			} else if (args.length == 1) {
				@SuppressWarnings("deprecation")
				OfflinePlayer target = Bukkit.getOfflinePlayer(args[0]);
				if (!(Database.playerExists(target.getUniqueId()))) {
					player.sendMessage(language.getMessage("target-not-found"));
					return true;
				}
				int kills;
				int deaths;
				int ranking;
				int killstreak;
				String color = "";
				String targetName;
				if (Bukkit.getPlayer(args[0]) == null) {
					OfflineStatsPlayer targetStatsPlayer = new OfflineStatsPlayer(args[0]);
					ranking = targetStatsPlayer.getRanking();
					kills = targetStatsPlayer.getKills();
					deaths = targetStatsPlayer.getDeaths();
					killstreak = 0;
					targetName = targetStatsPlayer.getName();
					color = targetStatsPlayer.getColor();
				} else {
					StatsPlayer targetStatsPlayer = StatsPlayerPool.getStatsPlayer(Bukkit.getPlayerExact(args[0]).getUniqueId());
					ranking = targetStatsPlayer.getRanking();
					kills = targetStatsPlayer.getKills();
					deaths = targetStatsPlayer.getDeaths();
					killstreak = targetStatsPlayer.getKillstreak();
					targetName = targetStatsPlayer.getName();
					color = targetStatsPlayer.getColor();
				}
				double kd = (double) kills / deaths;
				player.sendMessage(language.getMessage("target-stats-header-alltime").replace("%targetname%", targetName).replace("%color%", color));
				player.sendMessage(language.getMessage("ranking").replace("%ranking%", String.valueOf(ranking)));
				player.sendMessage(language.getMessage("kills").replace("%kills%", String.valueOf(kills)));
				player.sendMessage(language.getMessage("deaths").replace("%deaths%", String.valueOf(deaths)));
				if (kills != 0) {
					player.sendMessage(language.getMessage("kd").replace("%kd%", format.format(kd)));
				} else {
					player.sendMessage(language.getMessage("kdNA"));
				}
				player.sendMessage(language.getMessage("killstreak").replace("%killstreak%", String.valueOf(killstreak)));
				player.sendMessage(language.getMessage("target-stats-header-alltime").replace("%targetname%", targetName).replace("%color%", color));
			} else if (args.length == 2) {
				String arg = args[1];
				if (arg.equalsIgnoreCase("alltime")) {
					@SuppressWarnings("deprecation")
					OfflinePlayer target = Bukkit.getOfflinePlayer(args[0]);
					if (!(Database.playerExists(target.getUniqueId()))) {
						player.sendMessage(language.getMessage("target-not-found"));
						return true;
					}
					int kills;
					int deaths;
					int ranking;
					int killstreak;
					String color = "";
					String targetName = "§4NOT FOUND";
					if (Bukkit.getPlayer(args[0]) == null) {
						OfflineStatsPlayer targetStatsPlayer = new OfflineStatsPlayer(args[0]);
						ranking = targetStatsPlayer.getRanking();
						kills = targetStatsPlayer.getKills();
						deaths = targetStatsPlayer.getDeaths();
						killstreak = 0;
						targetName = targetStatsPlayer.getName();
						color = targetStatsPlayer.getColor();
					} else {
						StatsPlayer targetStatsPlayer = StatsPlayerPool.getStatsPlayer(Bukkit.getPlayerExact(args[0]).getUniqueId());
						ranking = targetStatsPlayer.getRanking();
						kills = targetStatsPlayer.getKills();
						deaths = targetStatsPlayer.getDeaths();
						killstreak = targetStatsPlayer.getKillstreak();
						targetName = targetStatsPlayer.getName();
						color = targetStatsPlayer.getColor();
					}
					double kd = (double) kills / deaths;
					player.sendMessage(language.getMessage("target-stats-header-alltime").replace("%targetname%", targetName).replace("%color%", color));
					player.sendMessage(language.getMessage("ranking").replace("%ranking%", String.valueOf(ranking)));
					player.sendMessage(language.getMessage("kills").replace("%kills%", String.valueOf(kills)));
					player.sendMessage(language.getMessage("deaths").replace("%deaths%", String.valueOf(deaths)));
					if (kills != 0) {
						player.sendMessage(language.getMessage("kd").replace("%kd%", format.format(kd)));
					} else {
						player.sendMessage(language.getMessage("kdNA"));
					}
					player.sendMessage(language.getMessage("killstreak").replace("%killstreak%", String.valueOf(killstreak)));
					player.sendMessage(language.getMessage("target-stats-header-alltime").replace("%targetname%", targetName).replace("%color%", color));
				} else if (arg.equalsIgnoreCase("30d")) {
					@SuppressWarnings("deprecation")
					OfflinePlayer target = Bukkit.getOfflinePlayer(args[0]);
					if (!(Database.playerExists(target.getUniqueId()))) {
						player.sendMessage(language.getMessage("target-not-found"));
						return true;
					}
					int kills;
					int deaths;
					int ranking;
					int killstreak;
					String color = "";
					String targetName = "§4NOT FOUND";
					if (Bukkit.getPlayer(args[0]) == null) {
						OfflineStatsPlayer targetStatsPlayer = new OfflineStatsPlayer(args[0]);
						ranking = targetStatsPlayer.getRanking();
						kills = targetStatsPlayer.getKills();
						deaths = targetStatsPlayer.getDeaths();
						killstreak = 0;
						targetName = targetStatsPlayer.getName();
						color = targetStatsPlayer.getColor();
					} else {
						StatsPlayer targetStatsPlayer = StatsPlayerPool.getStatsPlayer(Bukkit.getPlayerExact(args[0]).getUniqueId());
						ranking = targetStatsPlayer.getRanking();
						kills = targetStatsPlayer.getKills();
						deaths = targetStatsPlayer.getDeaths();
						killstreak = targetStatsPlayer.getKillstreak();
						targetName = targetStatsPlayer.getName();
						color = targetStatsPlayer.getColor();
					}
					double kd = (double) kills / deaths;
					player.sendMessage(language.getMessage("target-stats-header-30d").replace("%targetname%", targetName).replace("%color%", color));
					player.sendMessage(language.getMessage("ranking").replace("%ranking%", String.valueOf(ranking)));
					player.sendMessage(language.getMessage("kills").replace("%kills%", String.valueOf(kills)));
					player.sendMessage(language.getMessage("deaths").replace("%deaths%", String.valueOf(deaths)));
					if (kills != 0) {
						player.sendMessage(language.getMessage("kd").replace("%kd%", format.format(kd)));
					} else {
						player.sendMessage(language.getMessage("kdNA"));
					}
					player.sendMessage(language.getMessage("killstreak").replace("%killstreak%", String.valueOf(killstreak)));
					player.sendMessage(language.getMessage("target-stats-header-30d").replace("%targetname%", targetName).replace("%color%", color));
				}else {
					player.sendMessage("§7[§cFFA§7] §cBenutze: /Stats [Name] | 30d");
					player.sendMessage("§7[§cFFA§7] §cBenutze: /Stats #[Ranking]");
				}
			}else {
				player.sendMessage("§7[§cFFA§7] §cBenutze: /Stats [Name] | 30d");
				player.sendMessage("§7[§cFFA§7] §cBenutze: /Stats #[Ranking]");
			}
		} else {
			sender.sendMessage("Du musst ein Spieler sein");
		}
		return false;
	}
}