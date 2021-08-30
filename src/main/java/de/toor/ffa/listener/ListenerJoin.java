package de.toor.ffa.listener;

import de.scar.cloud.events.custom.CloudPlayerJoinEvent;
import de.scar.cloud.manager.ActionBar;
import de.scar.cloud.player.CloudPlayer;
import de.toor.ffa.objects.StatsPlayer;
import de.toor.ffa.main.FFA;
import de.toor.ffa.manager.KitManager;
import de.toor.ffa.manager.LocationManager;
import de.toor.ffa.scoreboards.ScoreboardManager;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;


public class ListenerJoin implements Listener {

	@SuppressWarnings("unlikely-arg-type")
	@EventHandler
	public void onJoin(CloudPlayerJoinEvent e) {
		CloudPlayer cloudPlayer = e.getCloudPlayer();
		StatsPlayer statsPlayer = new StatsPlayer(cloudPlayer);
		ScoreboardManager.createScoreboard(cloudPlayer, statsPlayer);
	}

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		for(Player player : Bukkit.getOnlinePlayers()) {
			if(!FFA.vanish.contains(player.getUniqueId())) {
				event.getPlayer().showPlayer(player);
			}
		}
		Player player = event.getPlayer();
		player.getInventory().clear();
		ActionBar.sendActionBar(player, "§aTeams erlaubt", true);
		event.setJoinMessage("");
		event.getPlayer().setGameMode(GameMode.ADVENTURE);
		KitManager.loadKit(player);
		player.teleport(LocationManager.getSpawnLocation());
		player.setHealth(20);
		player.setFoodLevel(20);
		player.getActivePotionEffects().clear();
		player.setLevel(0);
		player.setExp(0);
	}
}