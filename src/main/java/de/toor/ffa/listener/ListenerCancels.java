package de.toor.ffa.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerAchievementAwardedEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.weather.WeatherChangeEvent;

public class ListenerCancels implements Listener {

	@EventHandler
	public void onHunger(FoodLevelChangeEvent e) {
		e.setCancelled(true);
	}


	@EventHandler
	public void onAchievement(PlayerAchievementAwardedEvent e) {
		e.setCancelled(true);
	}

	@EventHandler
	public void onDropItem(PlayerDropItemEvent e) {
		e.setCancelled(true);
	}

	@EventHandler
	public void onKeepDay(WeatherChangeEvent e) {
		e.setCancelled(true);
	}

	@EventHandler
	public void onCreeperExplode(EntityExplodeEvent e) {
		e.setCancelled(true);
	}
}
