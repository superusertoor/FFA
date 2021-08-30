package de.toor.ffa.listener;

import de.toor.ffa.scoreboards.Health;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent;

public class HealthListener extends Health implements Listener {

    @EventHandler
    public void onDamage(final EntityDamageByEntityEvent event) {
        if (event.getEntity() instanceof Player) {
            final Player player = (Player) event.getEntity();
            super.updateHealth(player);
        }
    }

    @EventHandler
    public void onRegain(EntityRegainHealthEvent event) {
        if(event.getEntity() instanceof Player) {
            final Player player = (Player) event.getEntity();
            super.updateHealth(player);
        }
    }

    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        if(event.getEntity() instanceof Player) {
            final Player player = (Player) event.getEntity();
            super.updateHealth(player);
        }
    }
}