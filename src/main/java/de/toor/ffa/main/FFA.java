package de.toor.ffa.main;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import de.scar.cloud.manager.WorldManager;
import de.toor.ffa.commands.*;
import de.toor.ffa.listener.*;
import de.toor.ffa.manager.EntityManager;
import de.toor.ffa.manager.KitManager;
import de.toor.ffa.manager.LocationManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class FFA extends JavaPlugin implements Listener {

	public static ArrayList<UUID> vanish = new ArrayList<>();
	public static FFA instance;
	public static List<Player> moderationTool = new ArrayList<>();

	@Override
	public void onEnable() {
		WorldManager.setDefaults(Bukkit.getWorld("world"));
		instance = this;
		Bukkit.getPluginManager().registerEvents(new ListenerJoin(), this);
		Bukkit.getPluginManager().registerEvents(new ListenerCombat(), this);
		Bukkit.getPluginManager().registerEvents(new ListenerCancels(), this);
		Bukkit.getPluginManager().registerEvents(new ListenerStats(), this);
		Bukkit.getPluginManager().registerEvents(new StartEvent(), this);
		Bukkit.getPluginManager().registerEvents(new HealthListener(), this);
		Bukkit.getPluginManager().registerEvents(new InventoryCommand(), this);
		Bukkit.getPluginCommand("Spectate").setExecutor(new CommandSpectate());
		Bukkit.getPluginCommand("Vanish").setExecutor(new CommandVanish());
		Bukkit.getPluginCommand("SetSpawn").setExecutor(new CommandSetSpawn());
		Bukkit.getPluginCommand("Inventory").setExecutor(new InventoryCommand());
		Bukkit.getPluginCommand("Stats").setExecutor(new CommandStats());
		Bukkit.getPluginCommand("duell").setExecutor(new DuelCommand());
		Bukkit.getPluginCommand("ChatClear").setExecutor(new CommandChatClear());
		LocationManager.createDefaultLocations();
		EntityManager.removeEntites();
		KitManager.create();
	}

	public static FFA getInstance() {
		return instance;
	}
}