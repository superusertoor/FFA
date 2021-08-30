package de.toor.ffa.listener.custom;

import de.scar.cloud.main.Cloud;
import de.scar.cloud.main.ThuglifeCloudAPI;
import de.scar.cloud.player.Language;
import de.toor.ffa.objects.StatsPlayer;
import de.toor.ffa.objects.StatsPlayerPool;
import de.toor.ffa.main.FFA;
import de.toor.ffa.manager.*;
import de.toor.ffa.scoreboards.ScoreboardManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class KillEvent extends Event {

    private static HandlerList handlerList = new HandlerList();
    private Player player;
    private Player killer;
    private StatsPlayer statsPlayer;
    private StatsPlayer statsKiller;
    private static ItemStack apple = new ItemManager(Material.GOLDEN_APPLE).setAmount(2).buildItem();
    private static ThuglifeCloudAPI instance = Cloud.INSTANCE.getPlugin();

    public KillEvent(Player player, Player killer) {
        this.player = player;
        this.killer = killer;
        this.statsPlayer = StatsPlayerPool.getStatsPlayer(player.getUniqueId());
        this.statsKiller = StatsPlayerPool.getStatsPlayer(killer.getUniqueId());

        player.setVelocity(new Vector(0, 0, 0));

        statsPlayer.addDeath();
        statsPlayer.resetKillstreak();
        statsKiller.addDeath();
        statsKiller.addKillstreak();
        statsKiller.setInDuell(false);
        statsPlayer.setInDuell(false);

        Language killerLanguage = Cloud.INSTANCE.getCloudPlayerManager().getCachedCloudPlayer(killer.getUniqueId()).getLanguage();
        killer.playSound(killer.getLocation(), Sound.LEVEL_UP, 20, 5);
        killer.sendMessage(killerLanguage.getMessage("you-killed").replaceAll("%color%", statsPlayer.getColor()).replace("%targetname%", player.getName()));
        killer.setHealth(20);
        InventoryManager.addItem(killer, apple);
        ScoreboardManager.updateKills(killer);
        ScoreboardManager.updateKillstreak(killer);

        for(Player online : Bukkit.getOnlinePlayers()) {
            if(!FFA.vanish.contains(player.getUniqueId())) {
                killer.showPlayer(online);
            }
        }

        if(!(Bukkit.getPlayerExact(player.getName()) == null)) {
            ScoreboardManager.updateDeaths(player);
            ScoreboardManager.updateKillstreak(player);
            Language playerLanguage = Cloud.INSTANCE.getCloudPlayerManager().getCachedCloudPlayer(player.getUniqueId()).getLanguage();
            KitManager.loadKit(player);
            player.sendMessage(playerLanguage.getMessage("you-were-killed").replace("%color%", statsKiller.getColor()).replace("%targetname%", killer.getName()));
            player.getActivePotionEffects().clear();
            player.getActivePotionEffects().forEach(potioneffect->{ player.getPlayer().removePotionEffect(potioneffect.getType()); });
            for (Player online : Bukkit.getOnlinePlayers()) {
                if (!FFA.vanish.contains(player.getUniqueId())) {
                    player.showPlayer(online);
                }
            }
            Bukkit.getScheduler().scheduleSyncDelayedTask(instance, new BukkitRunnable() {
                @Override
                public void run() {
                    player.spigot().respawn();
                }
            }, 6L);
            Bukkit.getScheduler().scheduleSyncDelayedTask(instance, new BukkitRunnable() {
                @Override
                public void run() {
                    player.teleport(LocationManager.getSpawnLocation());
                }
            }, 7);
        }
        CombatManager.remove(player);
    }

    public static HandlerList getHandlerList() {
        return handlerList;
    }

    public Player getKiller(){
        return killer;
    }

    public Player getPlayer() {
        return player;
    }

    public StatsPlayer getStatsPlayer() {
        return statsPlayer;
    }

    public StatsPlayer getStatsKiller() {
        return statsKiller;
    }

    public HandlerList getHandlers() {
        return handlerList;
    }
}