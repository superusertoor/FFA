package de.toor.ffa.manager;

import de.scar.cloud.manager.CloudServerManager;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class KitManager {

    private static File publicFile = new File("plugins/Hardcore/Kit.yml");
    private static FileConfiguration publicConfig = YamlConfiguration.loadConfiguration(publicFile);
    private static ItemStack[] armor = ((List<ItemStack>) publicConfig.get("Inventory.Armor")).toArray(new ItemStack[0]);

    public static void create() {
        File folder = new File("plugins/Hardcore/Inventories");
        if(!folder.exists()) {
            folder.mkdirs();
        }
        File kitFile = new File("plugins/Hardcore/Kit.yml");
        if(!kitFile.exists()) {
            try {
                kitFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void saveKit(Player player, Inventory inventory) {
        CloudServerManager.getCloudServers().forEach(server -> {
            if(server.getName().contains("FFA")) {
                try {
                    File file = new File("../" + server.getName() + "/plugins/Hardcore/Inventories/" + player.getUniqueId());
                    if (!file.exists()) {
                        file.createNewFile();
                    }
                    FileConfiguration config = YamlConfiguration.loadConfiguration(file);
                    config.set("Inventory.Armor", armor);
                    config.set("Inventory.Content", inventory.getContents());
                    config.save(file);
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
        });
    }

    public static void loadKit(Player player) {
        File customFile = new File("plugins/Hardcore/Inventories/" + player.getUniqueId());

        player.getInventory().setArmorContents(armor);

        if(!customFile.exists()) {
            player.getInventory().setItem(0, new ItemManager(Material.DIAMOND_SWORD).addEnchant(Enchantment.DAMAGE_ALL, 1, true).buildItem());
            player.getInventory().setItem(8, new ItemManager(Material.GOLDEN_APPLE).setAmount(10).buildItem());
        }else {
            FileConfiguration customConfig = YamlConfiguration.loadConfiguration(customFile);
            ItemStack[] contents = ((List<ItemStack>) customConfig.get("Inventory.Content")).toArray(new ItemStack[0]);
            player.getInventory().setContents(contents);
        }
    }
}