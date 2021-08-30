package de.toor.ffa.objects;

import java.util.HashMap;
import java.util.UUID;

public class StatsPlayerPool {

    public static HashMap<UUID, StatsPlayer> pool = new HashMap<>();

    public static void addPlayer(UUID uuid, StatsPlayer ffaPlayer) {
        pool.put(uuid, ffaPlayer);
    }

    public static StatsPlayer getStatsPlayer(UUID uuid) {
        return pool.get(uuid);
    }

}