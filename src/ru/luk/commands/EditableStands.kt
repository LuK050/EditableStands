package ru.luk.commands
import ru.luk.EditableStands.Companion.plugin
import ru.luk.handlers.ArmorStand;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.command.*;


class EditableStands : CommandExecutor, TabExecutor {
    override fun onCommand(sender: CommandSender, command: Command, s: String, args: Array<String>): Boolean {
        if (args.isEmpty()) return false

        when(args[0]) {
            "reload" -> {
                plugin.reloadConfig()
                ArmorStand.playSounds = plugin.config.getBoolean("playSounds")
                ArmorStand.removeItems = plugin.config.getBoolean("removeItems")
                ArmorStand.removeItemsInCreative = plugin.config.getBoolean("removeItemsInCreative")
                ArmorStand.damageTools = plugin.config.getBoolean("damageTools")
                ArmorStand.damageToolsInCreative = plugin.config.getBoolean("damageToolsInCreative")
                ArmorStand.dropItems = plugin.config.getBoolean("dropItems")
                ArmorStand.armsAdd = plugin.config.getBoolean("armsAdd")
                ArmorStand.armsRemove = plugin.config.getBoolean("armsRemove")
                ArmorStand.plateAdd = plugin.config.getBoolean("plateAdd")
                ArmorStand.plateRemove = plugin.config.getBoolean("plateRemove")
                ArmorStand.doSmall = plugin.config.getBoolean("doSmall")
                ArmorStand.doBig = plugin.config.getBoolean("doBig")

                sender.sendMessage(ChatColor.GREEN.toString() + "Plugin successfully reloaded!")
            }

            "info" -> {
                sender.sendMessage("${ChatColor.GRAY}Author ${ChatColor.DARK_GRAY}> ${ChatColor.WHITE}_LuK__\n${ChatColor.GRAY}Version ${ChatColor.DARK_GRAY}> ${ChatColor.WHITE}1.0")
            }
        }
        return true
    }

    override fun onTabComplete(sender: CommandSender, command: Command, s: String, args: Array<String>): MutableList<String>? {
        if (sender is Player) {
            return if (args.size == 1) mutableListOf("info", "reload") else mutableListOf()
        }
        return null
    }
}