package ru.luk.commands;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.command.*;
import ru.luk.handlers.ArmorStand;

import java.util.ArrayList;
import java.util.List;


public class EditableStands implements CommandExecutor, TabExecutor {

    @Override public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (args.length == 0) {
            return false;
        }

        if (args[0].equals("reload")) {
            ru.luk.EditableStands.plugin.reloadConfig();

            ArmorStand.playSounds = ru.luk.EditableStands.plugin.getConfig().getBoolean("playSounds");
            ArmorStand.removeItems = ru.luk.EditableStands.plugin.getConfig().getBoolean("withdrawItems");
            ArmorStand.removeItemsInCreative = ru.luk.EditableStands.plugin.getConfig().getBoolean("withdrawItemsInCreative");
            ArmorStand.damageTools = ru.luk.EditableStands.plugin.getConfig().getBoolean("damageTools");
            ArmorStand.damageToolsInCreative = ru.luk.EditableStands.plugin.getConfig().getBoolean("damageToolsInCreative");
            ArmorStand.dropItems = ru.luk.EditableStands.plugin.getConfig().getBoolean("dropItems");
            ArmorStand.armsAdd = ru.luk.EditableStands.plugin.getConfig().getBoolean("armsAdd");
            ArmorStand.armsRemove = ru.luk.EditableStands.plugin.getConfig().getBoolean("armsRemove");
            ArmorStand.plateAdd = ru.luk.EditableStands.plugin.getConfig().getBoolean("plateAdd");
            ArmorStand.plateRemove = ru.luk.EditableStands.plugin.getConfig().getBoolean("plateRemove");
            ArmorStand.doSmall = ru.luk.EditableStands.plugin.getConfig().getBoolean("doSmall");
            ArmorStand.doBig = ru.luk.EditableStands.plugin.getConfig().getBoolean("doBig");

            sender.sendMessage(ChatColor.GREEN + "Plugin successfully reloaded!");

        } else if (args[0].equals("info")) {
            sender.sendMessage(String.format("%sAuthor %s> %s_LuK__\n%sVersion %s> %s%s", ChatColor.GRAY, ChatColor.DARK_GRAY, ChatColor.WHITE,
                                                                                            ChatColor.GRAY, ChatColor.DARK_GRAY, ChatColor.WHITE, "1.0"));
        }

        return true;
    }

    @Override public List<String> onTabComplete(CommandSender sender, Command command, String s, String[] args) {
        if (sender instanceof Player) {
            List<String> arguments = new ArrayList<>();

            if (args.length == 1) {
                arguments.add("info");
                arguments.add("reload");
            }

            return arguments;
        }
        return null;
    }
}
