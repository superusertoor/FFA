package de.toor.ffa.commands;

import de.scar.cloud.main.Cloud;
import de.scar.cloud.player.Language;
import de.toor.ffa.manager.ItemManager;
import de.toor.ffa.manager.KitManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.inventory.Inventory;

public class InventoryCommand implements CommandExecutor, Listener {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        createGui((Player) sender);
        return false;
    }

    @EventHandler
    public void onSaveInventoryClick(InventoryClickEvent event) {
        if(event.getInventory().getName().equalsIgnoreCase("§8Inventar sortieren")) {
            if(event.getClickedInventory().getName().equalsIgnoreCase("container.inventory")) {
                event.setCancelled(true);
                return;
            }
        }
    }

    @EventHandler
    public void onSaveInventoryMove(InventoryMoveItemEvent event) {
        Bukkit.broadcastMessage(event.getDestination().getName());
        Bukkit.broadcastMessage(event.getInitiator().getName());
        if(event.getDestination().getName().equalsIgnoreCase("§8Inventar sortieren")) {
            event.setCancelled(true);
        }
        if(event.getDestination().getName().equalsIgnoreCase("container.inventory")) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onSaveInventory(InventoryCloseEvent event) {
        if(event.getInventory().getName().equalsIgnoreCase("§8Inventar sortieren")) {
            Language language = Cloud.INSTANCE.getCloudPlayerManager().getCachedCloudPlayer(event.getPlayer().getUniqueId()).getLanguage();
            KitManager.saveKit((Player) event.getPlayer(), event.getInventory());
            event.getPlayer().sendMessage(language.getMessage(language.getMessage("kit-saved")));
        }
    }

    private void createGui(Player player) {
        Inventory inventory = Bukkit.createInventory(null, 9, "§8Inventar sortieren");
        inventory.setItem(0, new ItemManager(Material.DIAMOND_SWORD).setUnbreakable(true).addEnchant(Enchantment.DAMAGE_ALL, 1, true).buildItem());
        inventory.setItem(8, new ItemManager(Material.GOLDEN_APPLE).setAmount(10).buildItem());
        player.openInventory(inventory);
    }
}