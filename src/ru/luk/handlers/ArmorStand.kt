package ru.luk.handlers
import ru.luk.utils.playerItemInHandItemDamage
import ru.luk.utils.getAxeList
import ru.luk.utils.getPickaxeList
import ru.luk.EditableStands

import com.comphenix.protocol.PacketType
import com.comphenix.protocol.events.PacketContainer
import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.*
import org.bukkit.event.player.PlayerInteractAtEntityEvent
import org.bukkit.inventory.ItemStack
import org.bukkit.GameMode
import org.bukkit.Sound
import org.bukkit.entity.*
import org.bukkit.entity.ArmorStand
import org.bukkit.event.entity.EntityDeathEvent

import kotlin.properties.Delegates


class ArmorStand : Listener {
    companion object {
        var playSounds by Delegates.notNull<Boolean>()
        var removeItems by Delegates.notNull<Boolean>()
        var removeItemsInCreative by Delegates.notNull<Boolean>()
        var damageTools by Delegates.notNull<Boolean>()
        var damageToolsInCreative by Delegates.notNull<Boolean>()
        var dropItems by Delegates.notNull<Boolean>()
        var armsAdd by Delegates.notNull<Boolean>()
        var armsRemove by Delegates.notNull<Boolean>()
        var plateAdd by Delegates.notNull<Boolean>()
        var plateRemove by Delegates.notNull<Boolean>()
        var doSmall by Delegates.notNull<Boolean>()
        var doBig by Delegates.notNull<Boolean>()
    }

    @EventHandler fun onArmorStandInteraction(event: PlayerInteractAtEntityEvent) {
        if (event.rightClicked !is org.bukkit.entity.ArmorStand || !event.player.isSneaking) return

        val player: Player = event.player
        val armorStand: org.bukkit.entity.ArmorStand = event.rightClicked as org.bukkit.entity.ArmorStand
        event.isCancelled = !armorStand.isVisible

        val animation: PacketContainer = EditableStands.protocolManager.createPacket(PacketType.Play.Server.ANIMATION, false).apply {
            getEntityModifier(player.world).write(0, player)
            integers.write(1, 0)
        }

        when {
            player.hasPermission("editablestands.arms.add") && armsAdd && player.inventory.itemInMainHand.type == Material.STICK && player.inventory.itemInMainHand.amount >= 2 && !armorStand.hasArms() -> {
                EditableStands.protocolManager.sendServerPacket(player, animation)
                if (playSounds) {
                    player.world.playSound(armorStand.location, Sound.ENTITY_ITEM_FRAME_ADD_ITEM, 1F, 1F)
                }

                event.isCancelled = true
                armorStand.setArms(true)

                if (removeItems && (removeItemsInCreative || player.gameMode != GameMode.CREATIVE)) {
                    player.inventory.itemInMainHand.amount -= 2
                }
            }

            player.hasPermission("editablestands.arms.remove") && armsRemove && player.inventory.itemInMainHand.type == Material.SHEARS && armorStand.hasArms() -> {
                EditableStands.protocolManager.sendServerPacket(player, animation)
                if (playSounds) {
                    player.world.playSound(armorStand.location, Sound.ITEM_AXE_STRIP, 1F, 1F)
                }

                event.isCancelled = true
                armorStand.setArms(false)

                if (dropItems) {
                    if (armorStand.equipment?.itemInMainHand?.type != Material.AIR) {
                        armorStand.equipment?.itemInMainHand?.let {
                            player.world.dropItem(armorStand.location.add(.0, .25, .0), it)
                        }
                        armorStand.equipment?.setItemInMainHand(ItemStack(Material.AIR, 1))
                    }

                    if (armorStand.equipment?.itemInOffHand?.type != Material.AIR) {
                        armorStand.equipment?.itemInOffHand?.let {
                            player.world.dropItem(armorStand.location.add(.0, .25, .0), it)
                        }
                        armorStand.equipment?.setItemInOffHand(ItemStack(Material.AIR, 1))
                    }
                    player.world.dropItem(armorStand.location.add(.0, .25, .0), ItemStack(Material.STICK, 2))
                }

                if (damageTools && (damageToolsInCreative || player.gameMode != GameMode.CREATIVE)) {
                    playerItemInHandItemDamage(player)
                }
            }

            player.hasPermission("editablestands.size.small") && doSmall && player.inventory.itemInMainHand.type in getAxeList() && !armorStand.isSmall -> {
                EditableStands.protocolManager.sendServerPacket(player, animation)
                if (playSounds) {
                    player.world.playSound(armorStand.location, Sound.ITEM_AXE_STRIP, 1F, 1F)
                }

                event.isCancelled = true
                armorStand.isSmall = true

                if (dropItems) {
                    player.world.dropItem(armorStand.location.add(.0, .25, .0), ItemStack(Material.STICK, 2))
                }

                if (damageTools && (damageToolsInCreative || player.gameMode != GameMode.CREATIVE)) {
                    playerItemInHandItemDamage(player)
                }
            }

            player.hasPermission("editablestands.size.big") && doBig && player.inventory.itemInMainHand.type == Material.STICK && player.inventory.itemInMainHand.amount >= 2 && armorStand.isSmall -> {
                EditableStands.protocolManager.sendServerPacket(player, animation)
                if (playSounds) {
                    player.world.playSound(armorStand.location, Sound.ITEM_AXE_STRIP, 1F, 1F)
                }

                event.isCancelled = true
                armorStand.isSmall = false

                if (removeItems && (removeItemsInCreative || player.gameMode != GameMode.CREATIVE)) {
                    player.inventory.itemInMainHand.amount -= 2
                }
            }

            player.hasPermission("editablestands.plate.remove") && plateRemove && player.inventory.itemInMainHand.type in getPickaxeList() && armorStand.hasBasePlate() -> {
                EditableStands.protocolManager.sendServerPacket(player, animation)
                if (playSounds) {
                    player.world.playSound(armorStand.location, Sound.BLOCK_STONE_BREAK, 1F, 1F)
                }

                event.isCancelled = true
                armorStand.setBasePlate(false)

                if (dropItems) {
                    player.world.dropItem(armorStand.location.add(.0, .25, .0), ItemStack(Material.SMOOTH_STONE_SLAB, 1))
                }

                if (damageTools && (damageToolsInCreative || player.gameMode != GameMode.CREATIVE)) {
                    playerItemInHandItemDamage(player)
                }
            }

            player.hasPermission("editablestands.plate.add") && plateAdd && player.inventory.itemInMainHand.type == Material.SMOOTH_STONE_SLAB && !armorStand.hasBasePlate() -> {
                EditableStands.protocolManager.sendServerPacket(player, animation)
                if (playSounds) {
                    player.world.playSound(armorStand.location, Sound.BLOCK_STONE_PLACE, 1F, 1F)
                }

                event.isCancelled = true
                armorStand.setBasePlate(true)

                if (removeItems && (removeItemsInCreative || player.gameMode != GameMode.CREATIVE)) {
                    player.inventory.itemInMainHand.amount -= 1
                }
            }
        }
    }

    @EventHandler fun onArmorStandDeath(event: EntityDeathEvent) {
        if (event.entity !is org.bukkit.entity.ArmorStand || !dropItems || (!(event.entity as ArmorStand).hasArms() && !(event.entity as ArmorStand).isSmall && (event.entity as ArmorStand).hasBasePlate())) return

        val armorStand: org.bukkit.entity.ArmorStand = event.entity as org.bukkit.entity.ArmorStand
        event.drops.remove(ItemStack(Material.ARMOR_STAND, 1))

        when {
            armorStand.hasArms() && armorStand.hasBasePlate() && !armorStand.isSmall -> event.drops.addAll(arrayListOf(ItemStack(Material.ARMOR_STAND, 1), ItemStack(Material.STICK, 2)))
            !armorStand.hasArms() && armorStand.hasBasePlate() && armorStand.isSmall -> event.drops.addAll(arrayListOf(ItemStack(Material.SMOOTH_STONE_SLAB, 1), ItemStack(Material.STICK, 4)))
            armorStand.hasArms() && armorStand.hasBasePlate() && armorStand.isSmall -> event.drops.addAll(arrayListOf(ItemStack(Material.ARMOR_STAND, 1), ItemStack(Material.STICK, 6)))
            !armorStand.hasArms() && !armorStand.hasBasePlate() && armorStand.isSmall -> event.drops.add(ItemStack(Material.STICK, 4))
            armorStand.hasArms() && !armorStand.hasBasePlate() && armorStand.isSmall -> event.drops.add(ItemStack(Material.STICK, 6))
            armorStand.hasArms() && !armorStand.hasBasePlate() && !armorStand.isSmall -> event.drops.add(ItemStack(Material.STICK, 8))
            !armorStand.hasArms() && !armorStand.hasBasePlate() && !armorStand.isSmall -> event.drops.add(ItemStack(Material.STICK, 6))
        }
    }
}