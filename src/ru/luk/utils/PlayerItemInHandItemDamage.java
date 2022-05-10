package ru.luk.utils;

import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;


public class PlayerItemInHandItemDamage {
    private static final List<List<Integer>> chanceList = Arrays.asList(
            Arrays.asList(0, 0, 0, 1),
            Arrays.asList(0, 0, 1, 1),
            Arrays.asList(0, 1, 1, 1)
    );

    public static void playerItemInHandItemDamage(Player player) {
        if (player.getInventory().getItemInMainHand().containsEnchantment(Enchantment.DURABILITY)) {
            int level = player.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.DURABILITY);

            if (chanceList.get(level - 1).get((int) (Math.random() * 3)) == 1) {
                return;
            }
        }

        org.bukkit.inventory.meta.Damageable damageableMeta = (org.bukkit.inventory.meta.Damageable) player.getInventory().getItemInMainHand().getItemMeta();
        int damage = damageableMeta.getDamage();
        int maxDurability = player.getInventory().getItemInMainHand().getType().getMaxDurability();
        damageableMeta.setDamage(damage + 1);

        if ((damage + 1) < maxDurability) {
            player.getInventory().getItemInMainHand().setItemMeta(damageableMeta);

        } else {
            player.playSound(player.getLocation().add(0, -1, 0), Sound.ENTITY_ITEM_BREAK, 1, 1);
            player.getInventory().getItemInMainHand().setAmount(0);
        }

        player.updateInventory();
    }
}
