package de.toor.ffa.manager;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class LocationManager {

	private static File locationFile = new File("plugins/Hardcore/Locations/Locations.yml");
	public static FileConfiguration locationConfig = YamlConfiguration.loadConfiguration(locationFile);

	public static void saveCfg() {
		try {
			locationConfig.save(locationFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void setSpawn(Location location) {
		List<String> locations = new ArrayList<>();
		locations.add("Spawn0");
		locations.add("Spawn1");
		locations.add("Spawn2");
		locations.add("Spawn3");
		for(String string : locations) {
			locationConfig.set(string + ".world", "world");
			locationConfig.set(string + ".x", location.getX());
			locationConfig.set(string + ".y", location.getY());
			locationConfig.set(string + ".z", location.getZ());
		}
		try {
			locationConfig.save(locationFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void createDefaultLocations() {
		if (!locationFile.exists()) {
			setLocation("Spawn0", 0);
			setLocation("Spawn1", 90);
			setLocation("Spawn2", -90);
			setLocation("Spawn3", 180);
		}
	}

	public static void setLocation(String name, double yaw) {
		locationConfig.set(name + ".world", "world");
		locationConfig.set(name + ".x", 0.0);
		locationConfig.set(name + ".y", 0.0);
		locationConfig.set(name + ".z", 0.0);
		locationConfig.set(name + ".yaw", yaw);
		locationConfig.set(name + ".pitch", 0.0);
		saveCfg();
		if (!locationFile.exists())
			try {
				locationFile.createNewFile();
			} catch (Exception exception) {

			}
	}

	public static Location getSpawnLocation() {
		int id = new Random().nextInt(4);
		String spawn = "Spawn" + id;
		double x = locationConfig.getDouble((spawn) + ".x");
		double y = locationConfig.getDouble((spawn) + ".y");
		double z = locationConfig.getDouble((spawn) + ".z");
		Location loc = new Location(Bukkit.getWorld("world"), x, y, z);
		loc.setYaw(locationConfig.getInt(spawn + ".yaw"));
		loc.setPitch(locationConfig.getInt(spawn + ".pitch"));
		return loc;
	}
}