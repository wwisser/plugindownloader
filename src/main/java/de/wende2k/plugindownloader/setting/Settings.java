package de.wende2k.plugindownloader.setting;

import com.google.common.collect.ImmutableList;
import de.wende2k.plugindownloader.mapping.BukkitPlugin;
import org.bukkit.Material;

import java.util.List;

public final class Settings {

    public static final String INVENTORY_NAME = "§0§lPluginDownloader";

    public final static List<BukkitPlugin> PLUGINS = ImmutableList.of(
            new BukkitPlugin("WorldEdit", Material.WOOD_AXE),
            new BukkitPlugin("WorldGuard", Material.LEATHER),
            new BukkitPlugin("VoxelSniper", Material.ARROW),
            new BukkitPlugin("PlugMan", Material.REDSTONE_COMPARATOR),
            new BukkitPlugin("PermissionsEx", Material.BOOK),
            new BukkitPlugin("Essentials", Material.DIRT),
            new BukkitPlugin("EssentialsX", Material.GRASS),
            new BukkitPlugin("Vault", Material.CHEST),
            new BukkitPlugin("Multiverse-Core", Material.COMPASS),
            new BukkitPlugin("Holographic-Displays", Material.ARMOR_STAND),
            new BukkitPlugin("Citizens", Material.MONSTER_EGGS),
            new BukkitPlugin("ClearLagg", Material.LAVA_BUCKET),
            new BukkitPlugin("Shopkeepers", Material.EMERALD),
            new BukkitPlugin("ProtocolLib", Material.PAPER),
            new BukkitPlugin("Tablist", Material.BOOK_AND_QUILL),
            new BukkitPlugin("NametagEdit", Material.ENCHANTED_BOOK),
            new BukkitPlugin("ScoreboardStats", Material.DOUBLE_PLANT),
            new BukkitPlugin("NoCheatPlus", Material.SKULL_ITEM),
            new BukkitPlugin("ChatEx", Material.HOPPER),
            new BukkitPlugin("PlotMe", Material.DIAMOND_PICKAXE),
            new BukkitPlugin("Votifier", Material.LEVER),
            new BukkitPlugin("MotdManager", Material.PAINTING),
            new BukkitPlugin("AuthMe", Material.STORAGE_MINECART),
            new BukkitPlugin("Health Bar", "health-bar", Material.REDSTONE),
            new BukkitPlugin("A Skyblock", "skyblock", Material.SAPLING),
            new BukkitPlugin("AutoMessage", Material.REDSTONE_TORCH_ON),
            new BukkitPlugin("Buycraft", Material.TRAPPED_CHEST),
            new BukkitPlugin("CleanroomGenerator", Material.BEDROCK),
            new BukkitPlugin("Orebfuscator", Material.DIAMOND_ORE),
            new BukkitPlugin("Chat Color", "chat-color", Material.WATER_BUCKET),
            new BukkitPlugin("Colored Signs", "colored-signs", Material.SIGN)
    );

}
