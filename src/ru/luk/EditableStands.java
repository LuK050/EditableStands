package ru.luk;
import ru.luk.handlers.ArmorStand;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;


public class EditableStands extends JavaPlugin {
    public static EditableStands plugin;
    public static ProtocolManager protocolManager;

    public void onEnable() {
        plugin = this;
        protocolManager = ProtocolLibrary.getProtocolManager();

        File config = new File(getDataFolder() + File.separator + "config.yml");
        if (!config.exists()) {
            getConfig().options().copyDefaults(true);
            saveDefaultConfig();
        }

        ArmorStand.playSounds = plugin.getConfig().getBoolean("playSounds");
        ArmorStand.removeItems = plugin.getConfig().getBoolean("removeItems");
        ArmorStand.removeItemsInCreative = plugin.getConfig().getBoolean("removeItemsInCreative");
        ArmorStand.damageTools = plugin.getConfig().getBoolean("damageTools");
        ArmorStand.damageToolsInCreative = plugin.getConfig().getBoolean("damageToolsInCreative");
        ArmorStand.dropItems = plugin.getConfig().getBoolean("dropItems");
        ArmorStand.armsAdd = plugin.getConfig().getBoolean("armsAdd");
        ArmorStand.armsRemove = plugin.getConfig().getBoolean("armsRemove");
        ArmorStand.plateAdd = plugin.getConfig().getBoolean("plateAdd");
        ArmorStand.plateRemove = plugin.getConfig().getBoolean("plateRemove");
        ArmorStand.doSmall = plugin.getConfig().getBoolean("doSmall");
        ArmorStand.doBig = plugin.getConfig().getBoolean("doBig");

        Bukkit.getPluginManager().registerEvents(new ArmorStand(), this);

        getCommand("editablestands").setExecutor(new ru.luk.commands.EditableStands());
    }
}
