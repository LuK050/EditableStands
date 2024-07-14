package ru.luk
import ru.luk.handlers.ArmorStandInteractListener

import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin

import java.io.File


class EditableStands : JavaPlugin() {
    companion object {
        lateinit var plugin: EditableStands
    }

    override fun onEnable() {
        plugin = this

        File(dataFolder.toString() + File.separator + "config.yml").apply {
            if (!exists()) {
                config.options().copyDefaults(true)
                saveDefaultConfig()
            }
        }

        ArmorStandInteractListener.playSounds = this.config.getBoolean("playSounds")
        ArmorStandInteractListener.removeItems = this.config.getBoolean("removeItems")
        ArmorStandInteractListener.removeItemsInCreative = this.config.getBoolean("removeItemsInCreative")
        ArmorStandInteractListener.damageTools = this.config.getBoolean("damageTools")
        ArmorStandInteractListener.damageToolsInCreative = this.config.getBoolean("damageToolsInCreative")
        ArmorStandInteractListener.dropItems = this.config.getBoolean("dropItems")
        ArmorStandInteractListener.armsAdd = this.config.getBoolean("armsAdd")
        ArmorStandInteractListener.armsRemove = this.config.getBoolean("armsRemove")
        ArmorStandInteractListener.plateAdd = this.config.getBoolean("plateAdd")
        ArmorStandInteractListener.plateRemove = this.config.getBoolean("plateRemove")
        ArmorStandInteractListener.doSmall = this.config.getBoolean("doSmall")
        ArmorStandInteractListener.doBig = this.config.getBoolean("doBig")

        Bukkit.getPluginManager().registerEvents(ArmorStandInteractListener(), this)

        getCommand("editablestands")?.setExecutor(ru.luk.commands.EditableStands())
    }
}