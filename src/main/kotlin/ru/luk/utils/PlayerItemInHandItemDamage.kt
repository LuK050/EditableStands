package ru.luk.utils

import org.bukkit.Sound
import org.bukkit.entity.Player
import org.bukkit.enchantments.Enchantment
import org.bukkit.inventory.meta.Damageable
import org.bukkit.inventory.meta.ItemMeta

import kotlin.random.Random


private val levelChancesMap: Map<Int, Int> = mapOf(
    1 to 25,
    2 to 36,
    3 to 43
)

fun playerItemInHandItemDamage(player: Player) {
    if (Enchantment.DURABILITY in player.inventory.itemInMainHand.enchantments) {
        if (Random.nextInt(
                0,
                100
            ) in 0..levelChancesMap[player.inventory.itemInMainHand.getEnchantmentLevel(Enchantment.DURABILITY)]!!
        ) {
            return
        }
    }

    val damageableMeta: Damageable = player.inventory.itemInMainHand.itemMeta as Damageable
    damageableMeta.damage += 1
    val maxDurability: Short = player.inventory.itemInMainHand.type.maxDurability

    if (damageableMeta.damage < maxDurability) {
        player.inventory.itemInMainHand.itemMeta = damageableMeta as ItemMeta
    } else {
        player.playSound(player.location.add(.0, -1.0, .0), Sound.ENTITY_ITEM_BREAK, 1F, 1F)
        player.inventory.itemInMainHand.amount = 0
    }
    player.updateInventory()
}