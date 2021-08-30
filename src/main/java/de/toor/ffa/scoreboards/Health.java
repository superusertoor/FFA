package de.toor.ffa.scoreboards;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;

public class Health {

    public static void updateHealth(final Player player) {
        final int health = (int) ((player.getHealth() / 2));
        for(Player online : Bukkit.getOnlinePlayers()) {
            online.getScoreboard().getObjective(DisplaySlot.BELOW_NAME).getScore(player.getName()).setScore(health);
        }
    }
}