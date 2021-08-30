package de.toor.ffa.listener;

import de.scar.cloud.main.Cloud;
import de.toor.ffa.objects.StatsPlayer;
import de.toor.ffa.objects.StatsPlayerPool;
import de.toor.ffa.listener.custom.KillEvent;
import de.toor.ffa.main.FFA;
import de.toor.ffa.manager.CombatManager;
import de.toor.ffa.scoreboards.Health;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class ListenerCombat extends Health implements Listener {

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onDamage(EntityDamageByEntityEvent event) {
		if (event.isCancelled()) {
			return;
		}
		StatsPlayer target = StatsPlayerPool.getStatsPlayer(event.getEntity().getUniqueId());
		if(target.isInDuel()) {
			StatsPlayer hitter = StatsPlayerPool.getStatsPlayer(event.getDamager().getUniqueId());
			if(!target.getOpponent().getName().equalsIgnoreCase(hitter.getName())) {
				event.getDamager().sendMessage("§7[§dFFA§7] §cDu kannst diesen Spieler nicht schlagen, da er in einem Duell ist");
				event.setCancelled(true);
			}
			return;
		}
		final Player player = (Player) event.getEntity();
		final Player hitter = (Player) event.getDamager();
		if (CombatManager.isInCombat(player)) {
			return;
		}
		if (!CombatManager.isInCombat(player, hitter)) {
			CombatManager.addPlayers(player, hitter);
			Bukkit.getScheduler().runTaskLater(Cloud.INSTANCE.getPlugin(), new BukkitRunnable() {
				@Override
				public void run() {
					if (CombatManager.isInCombat(player, hitter)) {
						CombatManager.remove(player, hitter);
					}
				}
			}, 15 * 20);
		} else if (!CombatManager.isInCombat(player) && CombatManager.isInCombat(hitter)) {
			CombatManager.remove(player);
			CombatManager.addPlayers(player, hitter);
			Bukkit.getScheduler().runTaskLater(Cloud.INSTANCE.getPlugin(), new BukkitRunnable() {
				@Override
				public void run() {
					if (CombatManager.isInCombat(player, hitter)) {
						CombatManager.remove(player, hitter);
					}
				}
			}, 15 * 20);
		}
	}

	@EventHandler(priority = EventPriority.LOWEST)
	public void onCombatLog(PlayerQuitEvent event) {
		final Player player = event.getPlayer();
		FFA.vanish.remove(player);
		if(CombatManager.isInCombat(player)) {
			Bukkit.getPluginManager().callEvent(new KillEvent(player, CombatManager.getKiller(player)));
		}
		StatsPlayer statsPlayer = StatsPlayerPool.getStatsPlayer(player.getUniqueId());
		statsPlayer.saveStats();
		statsPlayer.unregister();
	}

	@EventHandler(priority = EventPriority.LOWEST)
	public void onCombatKick(PlayerKickEvent event) {
		event.setLeaveMessage("");
		final Player player = event.getPlayer();
		FFA.vanish.remove(player);
		if(CombatManager.isInCombat(player)) {
			Bukkit.getPluginManager().callEvent(new KillEvent(player, CombatManager.getKiller(player)));
		}
		StatsPlayer statsPlayer = StatsPlayerPool.getStatsPlayer(player.getUniqueId());
		statsPlayer.saveStats();
		statsPlayer.unregister();
	}
}
