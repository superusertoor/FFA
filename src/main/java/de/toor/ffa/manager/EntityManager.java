package de.toor.ffa.manager;

import org.bukkit.Bukkit;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;

public class EntityManager {

    public static void removeEntites() {
        for(Entity entity : Bukkit.getWorld("world").getEntities()) {
            if(!(entity instanceof ArmorStand)) {
                entity.remove();
            }
        }
    }
}
