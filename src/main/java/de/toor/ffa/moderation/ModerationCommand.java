package de.toor.ffa.moderation;

import de.scar.cloud.main.Cloud;
import de.scar.cloud.player.Language;
import de.toor.ffa.main.FFA;
import de.toor.ffa.manager.ItemManager;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class ModerationCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            Language language = Cloud.INSTANCE.getCloudPlayerManager().getCachedCloudPlayer(player.getUniqueId()).getLanguage();
            if (player.hasPermission("FFA.ModerationTool")) {
                if (FFA.moderationTool.contains(player)) {
                    FFA.moderationTool.remove(player);
                }
                FFA.moderationTool.add(player);
                loadModerationTool(player);
            }else {
                player.sendMessage(language.getMessage("no-perm"));
            }
        }
        return false;
    }

    private static void loadModerationTool(Player player) {
        ItemStack onlineStaff = new ItemManager(Material.REDSTONE).setDisplayName("§cTeammitglieder §7(§eRechtsklick§7)").buildItem();
        ItemStack cpsTest = new ItemManager(Material.WATCH).setDisplayName("§3CPS Test §7(§eRechtsklick§7)").buildItem();
        ItemStack quit = new ItemManager(Material.INK_SACK).setColor((short)14).setDisplayName("§cVerlassen §7(§eRechtsklick§7)").buildItem();
        ItemStack freeze = new ItemManager(Material.PACKED_ICE).setDisplayName("§bFreeze §7(§eRechtsklick§7)").buildItem();
        ItemStack air = new ItemManager(Material.AIR).buildItem();
        player.getInventory().clear();
        player.getInventory().setHelmet(air);
        player.getInventory().setBoots(air);
        player.getInventory().setChestplate(air);
        player.getInventory().setLeggings(air);
        player.getInventory().setItem(0, cpsTest);
        player.getInventory().setItem(1, onlineStaff);
        player.getInventory().setItem(2, freeze);
        player.getInventory().setItem(8, quit);
    }

    @EventHandler
    public void onModerationToolClick(InventoryClickEvent event) {
        if(event.getClickedInventory() == null) return;
        if(event.getCurrentItem().getItemMeta() == null) return;
        if(FFA.moderationTool.contains(event.getWhoClicked())) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onModerationToolUse(PlayerInteractEvent event) {
        if (FFA.moderationTool.contains(event.getPlayer())) {
            if(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
                String name = event.getItem().getItemMeta().getDisplayName();
                if (name.equalsIgnoreCase("§cTeammitglieder §7(§eRechtsklick§7)")) {

                } else if (name.equalsIgnoreCase("§3CPS Test §7(§eRechtsklick§7)")) {

                } else if (name.equalsIgnoreCase("§cVerlassen §7(§eRechtsklick§7)")) {

                } else if (name.equalsIgnoreCase("§bFreeze §7(§eRechtsklick§7)")) {

                }
            }
        }
    }
}
