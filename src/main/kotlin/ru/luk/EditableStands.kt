package ru.luk
import ru.luk.handlers.ArmorStand

import com.comphenix.protocol.ProtocolLibrary
import com.comphenix.protocol.ProtocolManager
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin

import java.io.File


class EditableStands : JavaPlugin() {
    companion object {
        lateinit var plugin: EditableStands
        lateinit var protocolManager: ProtocolManager
    }

    override fun onEnable() {
        plugin = this
        protocolManager = ProtocolLibrary.getProtocolManager()

        File(dataFolder.toString() + File.separator + "config.yml").apply {
            if (!exists()) {
                config.options().copyDefaults(true)
                saveDefaultConfig()
            }
        }

        ArmorStand.playSounds = this.config.getBoolean("playSounds")
        ArmorStand.removeItems = this.config.getBoolean("removeItems")
        ArmorStand.removeItemsInCreative = this.config.getBoolean("removeItemsInCreative")
        ArmorStand.damageTools = this.config.getBoolean("damageTools")
        ArmorStand.damageToolsInCreative = this.config.getBoolean("damageToolsInCreative")
        ArmorStand.dropItems = this.config.getBoolean("dropItems")
        ArmorStand.armsAdd = this.config.getBoolean("armsAdd")
        ArmorStand.armsRemove = this.config.getBoolean("armsRemove")
        ArmorStand.plateAdd = this.config.getBoolean("plateAdd")
        ArmorStand.plateRemove = this.config.getBoolean("plateRemove")
        ArmorStand.doSmall = this.config.getBoolean("doSmall")
        ArmorStand.doBig = this.config.getBoolean("doBig")

        Bukkit.getPluginManager().registerEvents(ArmorStand(), this)

        getCommand("editablestands")?.setExecutor(ru.luk.commands.EditableStands())
    }
}