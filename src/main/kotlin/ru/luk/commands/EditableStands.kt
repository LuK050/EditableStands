package ru.luk.commands
import ru.luk.EditableStands.Companion.plugin
import ru.luk.handlers.ArmorStandInteractListener;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.command.*;


class EditableStands : CommandExecutor, TabExecutor {
    override fun onCommand(sender: CommandSender, command: Command, s: String, args: Array<String>): Boolean {
        if (args.isEmpty())
            return false

        when(args[0]) {
            "reload" -> {
                plugin.reloadConfig()
                ArmorStandInteractListener.playSounds = plugin.config.getBoolean("playSounds")
                ArmorStandInteractListener.removeItems = plugin.config.getBoolean("removeItems")
                ArmorStandInteractListener.removeItemsInCreative = plugin.config.getBoolean("removeItemsInCreative")
                ArmorStandInteractListener.damageTools = plugin.config.getBoolean("damageTools")
                ArmorStandInteractListener.damageToolsInCreative = plugin.config.getBoolean("damageToolsInCreative")
                ArmorStandInteractListener.dropItems = plugin.config.getBoolean("dropItems")
                ArmorStandInteractListener.armsAdd = plugin.config.getBoolean("armsAdd")
                ArmorStandInteractListener.armsRemove = plugin.config.getBoolean("armsRemove")
                ArmorStandInteractListener.plateAdd = plugin.config.getBoolean("plateAdd")
                ArmorStandInteractListener.plateRemove = plugin.config.getBoolean("plateRemove")
                ArmorStandInteractListener.doSmall = plugin.config.getBoolean("doSmall")
                ArmorStandInteractListener.doBig = plugin.config.getBoolean("doBig")

                sender.sendMessage(ChatColor.GREEN.toString() + "Plugin successfully reloaded!")
            }

            "info" -> {
                sender.sendMessage("${ChatColor.GRAY}Author ${ChatColor.DARK_GRAY}> ${ChatColor.WHITE}_LuK__\n${ChatColor.GRAY}Version ${ChatColor.DARK_GRAY}> ${ChatColor.WHITE}1.3.1")
            }
        }
        return true
    }

    override fun onTabComplete(sender: CommandSender, command: Command, s: String, args: Array<String>): MutableList<String>? {
        if (sender is Player)
            return if (args.size == 1) mutableListOf("info", "reload") else mutableListOf()
        return null
    }
}