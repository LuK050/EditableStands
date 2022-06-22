package ru.luk.utils

import org.bukkit.Sound
import org.bukkit.enchantments.Enchantment
import org.bukkit.inventory.meta.Damageable
import org.bukkit.entity.Player


private val chanceList: List<List<Int>> = listOf(
    listOf(0, 0, 0, 1),
    listOf(0, 0, 1, 1),
    listOf(0, 1, 1, 1)
)

fun playerItemInHandItemDamage(player: Player) {
    if (Enchantment.DURABILITY in player.inventory.itemInMainHand.enchantments) {
        val level = player.inventory.itemInMainHand.getEnchantmentLevel(Enchantment.DURABILITY)
        if (chanceList[level - 1][(Math.random() * 3).toInt()] == 1) return
    }

    val damageableMeta: Damageable = player.inventory.itemInMainHand.itemMeta as Damageable
    damageableMeta.damage += 1

    val damage: Int = damageableMeta.damage
    val maxDurability: Short = player.inventory.itemInMainHand.type.maxDurability

    if (damage < maxDurability) {
        player.inventory.itemInMainHand.itemMeta = damageableMeta
    } else {
        player.playSound(player.location.add(.0, -1.0, .0), Sound.ENTITY_ITEM_BREAK, 1F, 1F)
        player.inventory.itemInMainHand.amount = 0
    }

    player.updateInventory()
}