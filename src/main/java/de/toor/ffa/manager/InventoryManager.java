package de.toor.ffa.manager;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class InventoryManager {

	public static void clear(Player p) {
		ItemStack air = new ItemManager(Material.AIR).buildItem();
		p.getInventory().clear();
		p.getInventory().setHelmet(air);
		p.getInventory().setChestplate(air);
		p.getInventory().setLeggings(air);
		p.getInventory().setBoots(air);
	}
	
	public static void addItem(Player p, ItemStack itemStack) {
		p.getInventory().addItem(itemStack);
	}
}
