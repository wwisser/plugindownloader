package de.wende2k.plugindownloader;

import de.wende2k.plugindownloader.inventory.InventoryPlugins;
import lombok.Getter;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionDefault;
import org.bukkit.plugin.PluginLogger;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.java.annotation.command.Command;
import org.bukkit.plugin.java.annotation.permission.Permission;
import org.bukkit.plugin.java.annotation.plugin.Description;
import org.bukkit.plugin.java.annotation.plugin.Plugin;
import org.bukkit.plugin.java.annotation.plugin.author.Author;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

@Plugin(name = "PluginDownloader", version = "1.0-SNAPSHOT")
@Author(name = "Wende2k")
@Description(desc = "Download common plugins via GUI.")
@Command(name = "plugindownloader", aliases = {"pd"}, permission = "plugindownloader.use")
@Permission(name = "plugindownloader.use", desc = "Allows the usage of PluginDownloader", defaultValue = PermissionDefault.OP)
@Getter
public class PluginDownloader extends JavaPlugin {

    @Getter private static PluginDownloader instance;

    private ExecutorService executorService;
    private Logger pluginLogger;
    private InventoryPlugins inventory;

    @Override
    public void onEnable() {
        PluginDownloader.instance = this;
        this.executorService = Executors.newCachedThreadPool();
        this.pluginLogger = new PluginLogger(this);
        this.inventory = new InventoryPlugins();

        super.getServer().getPluginManager().registerEvents(this.inventory, this);
        super.getCommand("plugindownloader").setExecutor((commandSender, command, label, args) -> {
            if (commandSender instanceof Player) {
                this.inventory.open((Player) commandSender);
            } else {
                commandSender.sendMessage("You must be a player to be able to use this command.");
            }

            return true;
        });
    }

}
