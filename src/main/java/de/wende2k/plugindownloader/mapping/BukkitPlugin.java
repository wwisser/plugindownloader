package de.wende2k.plugindownloader.mapping;

import de.wende2k.plugindownloader.PluginDownloader;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;

@Getter
public class BukkitPlugin {

    private static final String URL_PATTERN = "https://dev.bukkit.org/projects/%project%/files/latest";

    private String name;
    private String projectName;
    private Material displayItem;

    public BukkitPlugin(String name, String projectName, Material displayItem) {
        this.name = name;
        this.projectName = projectName;
        this.displayItem = displayItem;
    }

    public BukkitPlugin(String name, Material displayItem) {
        this(name, name.toLowerCase(), displayItem);
    }

    public void download(Consumer<Boolean> callback) {
        PluginDownloader.getInstance().getExecutorService().submit(() -> {
            Logger logger = PluginDownloader.getInstance().getLogger();
            long started = System.nanoTime();

            logger.log(Level.INFO, "Downloading  " + this.name + "...");

            try {
                Files.copy(new URL(BukkitPlugin.URL_PATTERN.replace("%project%", this.projectName))
                        .openStream(), Paths.get("plugins/" + this.name + ".jar"), StandardCopyOption.REPLACE_EXISTING);
                logger.log(Level.INFO, "Successfully downloaded " + this.name + " [" + ((System.nanoTime() - started) / 1000000) + "ms]");
                callback.accept(true);
            } catch (Exception e) {
                logger.log(Level.WARNING, "Download of " + name + " failed:");
                e.printStackTrace();
                callback.accept(false);
            }
        });
    }

    public ItemStack toItemStack() {
        ItemStack itemStack = new ItemStack(this.displayItem);
        ItemMeta itemMeta = itemStack.getItemMeta();

        itemMeta.setDisplayName("§a" + name);
        itemMeta.setLore(Arrays.asList("", "§7Click §bhere §7to download the plugin."));
        itemStack.setItemMeta(itemMeta);

        return itemStack;
    }

}

