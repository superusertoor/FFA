package de.toor.ffa.listener;

import de.toor.ffa.listener.custom.KillEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class ListenerStats implements Listener {

	@EventHandler
	public void onDeath(PlayerDeathEvent e) {
		if (e.getEntity() instanceof Player) {
			if (e.getEntity().getKiller() instanceof Player) {
				Bukkit.getPluginManager().callEvent(new KillEvent(e.getEntity().getPlayer(), e.getEntity().getKiller()));
				e.setDeathMessage("");
				e.setKeepLevel(true);
				e.setDroppedExp(0);
				e.setKeepInventory(true);
			}
		}
	}
}