package de.toor.ffa.manager;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemManager {

	  private ItemStack itemStack;
	  private ItemMeta itemMeta;

	  public ItemManager(Material material, short subID) {
	    this.itemStack = new ItemStack(material, 1, subID);
	    this.itemMeta = this.itemStack.getItemMeta();
	  }

	  public ItemManager(Material material) {
	    this(material, (short)0);
	  }

	  public ItemManager setDisplayName(String name) {
	    this.itemMeta.setDisplayName(name);
	    return this;
	  }

	  public ItemManager setAmount(int amount) {
	    this.itemStack.setAmount(amount);
	    return this;
	  }
	  
	  public ItemManager addEnchant(Enchantment enchantment, int level, boolean Boolean) {
	    this.itemMeta.addEnchant(enchantment, level, Boolean);
	    return this;
	  }
	  
	  public ItemManager setUnbreakable(boolean Boolean) {
	    this.itemMeta.spigot().setUnbreakable(Boolean);
	    return this;
	  }
	  
	  public ItemManager setColor(short color) {
	    this.itemStack.setDurability(color);
	    return this;
	  }
	  
	  public ItemManager hideEnchantment() {
	    this.itemMeta.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS });
	    return this;
	  }
	  
	  public ItemStack buildItem() {
	    this.itemStack.setItemMeta(this.itemMeta);
	    return this.itemStack;
	  }
	  
	  public static void fillEmptySlots(Inventory inv, ItemStack item) {
		  for (int i = 0; i < inv.getSize(); i++) {
			  if (inv.getItem(i) == null || inv.getItem(i).getType().equals(Material.AIR))
				  inv.setItem(i, item);
		  }
	  }
}
