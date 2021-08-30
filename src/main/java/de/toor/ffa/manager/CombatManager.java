package de.toor.ffa.manager;

import org.bukkit.entity.Player;
import java.util.*;
import java.util.concurrent.LinkedBlockingDeque;

public class CombatManager {

    private static LinkedHashMap<Player,Player> combat = new LinkedHashMap<>();

    public static boolean isInCombat(Player player, Player hitter) {
        boolean b = false;
        if(combat.containsKey(player)) {
            if(combat.get(player).equals(hitter)){
                b = true;
            }
        }
        return b;
    }

    public static Player getKiller(Player player) {
        return combat.get(player);
    }

    public static boolean isInCombat(Player player) {
        return combat.containsKey(player);
    }

    public static void addPlayers(Player player, Player hitter) {
        combat.put(player, hitter);
    }

    public static void remove(Player player) {
        combat.remove(player);
    }

    public static void remove(Player player, Player hitter) {
        combat.remove(player,hitter);
    }

    public static void removePlayer(Player player) {
        combat.remove(player);
    }

    public static void createList() {
        combat = new LinkedHashMap<>();
    }

}
