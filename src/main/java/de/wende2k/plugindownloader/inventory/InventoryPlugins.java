package de.wende2k.plugindownloader.inventory;

import de.wende2k.plugindownloader.PluginDownloader;
import de.wende2k.plugindownloader.setting.Settings;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class InventoryPlugins implements Listener {

    private Inventory inventory;

    public InventoryPlugins() {
        this.inventory = Bukkit.createInventory(null, 6 * 9, Settings.INVENTORY_NAME);

        for (int i = 0; i < Settings.PLUGINS.size(); i++) {
            this.inventory.setItem(i, Settings.PLUGINS.get(i).toItemStack());
        }
    }

    public void open(Player player) {
        player.openInventory(this.inventory);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getInventory().equals(this.inventory)) {
            event.setCancelled(true);

            ItemStack itemStack = event.getCurrentItem();
            Player player = (Player) event.getWhoClicked();

            if (itemStack != null && itemStack.getType() != Material.AIR) {
                Settings.PLUGINS
                        .stream()
                        .filter(bukkitPlugin -> bukkitPlugin.getDisplayItem() == itemStack.getType())
                        .forEach(bukkitPlugin -> {
                            player.closeInventory();
                            player.sendMessage("§7Downloading §6" + bukkitPlugin.getName() + "§7...");

                            bukkitPlugin.download(success -> {
                                if (success) {
                                    player.sendMessage("§7The plugin has been §asuccessfully downloaded§7.");

                                    String usage = "/reload";
                                    if (PluginDownloader.getInstance().getServer()
                                            .getPluginManager().getPlugin("PlugMan") != null) {
                                        usage = "/plugman load " + bukkitPlugin.getName();
                                    }

                                    player.sendMessage("§7Use §6" + usage + " §7to load the plugin.");
                                } else {
                                    player.sendMessage("§cDownload of " + bukkitPlugin.getName() + " failed!");
                                    player.sendMessage("§cCheck your logs for more information.");
                                }
                            });
                        });
            }
        }
    }

}
