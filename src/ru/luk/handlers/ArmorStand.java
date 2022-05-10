package ru.luk.handlers;
import ru.luk.utils.PlayerItemInHandItemDamage;
import ru.luk.EditableStands;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketContainer;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.*;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.entity.*;
import org.bukkit.event.entity.EntityDeathEvent;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class ArmorStand implements Listener {
    public static boolean playSounds;
    public static boolean removeItems;
    public static boolean removeItemsInCreative;
    public static boolean damageTools;
    public static boolean damageToolsInCreative;
    public static boolean dropItems;
    public static boolean armsAdd;
    public static boolean armsRemove;
    public static boolean plateAdd;
    public static boolean plateRemove;
    public static boolean doSmall;
    public static boolean doBig;

    public static List<Material> getPickaxeList() {
        List<Material> materialList = new ArrayList<>();
        materialList.add(Material.WOODEN_PICKAXE);
        materialList.add(Material.STONE_PICKAXE);
        materialList.add(Material.IRON_PICKAXE);
        materialList.add(Material.GOLDEN_PICKAXE);
        materialList.add(Material.DIAMOND_PICKAXE);
        materialList.add(Material.NETHERITE_PICKAXE);
        return materialList;
    }

    public static List<Material> getAxeList() {
        List<Material> materialList = new ArrayList<>();
        materialList.add(Material.WOODEN_AXE);
        materialList.add(Material.STONE_AXE);
        materialList.add(Material.IRON_AXE);
        materialList.add(Material.GOLDEN_AXE);
        materialList.add(Material.DIAMOND_AXE);
        materialList.add(Material.NETHERITE_AXE);
        return materialList;
    }

    @EventHandler public static void onArmorStandInteraction(PlayerInteractAtEntityEvent event) throws InvocationTargetException {
        if (event.getRightClicked() instanceof org.bukkit.entity.ArmorStand armorStand && event.getPlayer().isSneaking()) {
            if (!armorStand.isVisible()) event.setCancelled(true);

            Player player = event.getPlayer();
            final PacketContainer animation = EditableStands.protocolManager.createPacket(PacketType.Play.Server.ANIMATION, false);

            animation.getEntityModifier(player.getWorld()).write(0, player);
            animation.getIntegers().write(1, 0);

            if (player.hasPermission("editablestands.arms.add") && armsAdd && player.getInventory().getItemInMainHand().getType() == Material.STICK
            && player.getInventory().getItemInMainHand().getAmount() >= 2
            && !armorStand.hasArms()) {
                EditableStands.protocolManager.sendServerPacket(player, animation);

                if (playSounds) {
                    event.getPlayer().getWorld().playSound(armorStand.getLocation(), Sound.ENTITY_ITEM_FRAME_ADD_ITEM, 1, 1);
                }

                event.setCancelled(true);
                armorStand.setArms(true);

                if (removeItems) {
                    if (removeItemsInCreative || player.getGameMode() != GameMode.CREATIVE) {
                        player.getInventory().getItemInMainHand().setAmount(player.getInventory().getItemInMainHand().getAmount() - 2);
                    }
                }

            } else if (player.hasPermission("editablestands.arms.remove") && armsRemove && player.getInventory().getItemInMainHand().getType() == Material.SHEARS
                    && armorStand.hasArms()) {
                EditableStands.protocolManager.sendServerPacket(player, animation);

                if (playSounds) {
                    event.getPlayer().getWorld().playSound(armorStand.getLocation(), Sound.ITEM_AXE_STRIP, 1, 1);
                }

                event.setCancelled(true);
                armorStand.setArms(false);

                if (dropItems) {
                    if (Objects.requireNonNull(armorStand.getEquipment()).getItemInMainHand().getType() != Material.AIR) {
                        player.getWorld().dropItem(armorStand.getLocation().add(0, 0.25, 0), armorStand.getEquipment().getItemInMainHand());
                        armorStand.getEquipment().setItemInMainHand(new ItemStack(Material.AIR, 1));
                    }

                    player.getWorld().dropItem(armorStand.getLocation().add(0, 0.25, 0), new ItemStack(Material.STICK, 2));
                }

                if (damageTools) {
                    if (damageToolsInCreative || player.getGameMode() != GameMode.CREATIVE) {
                        PlayerItemInHandItemDamage.playerItemInHandItemDamage(player);
                    }
                }

            } else if (player.hasPermission("editablestands.size.small") && doSmall && getAxeList().contains(player.getInventory().getItemInMainHand().getType())
                    && !armorStand.isSmall()) {
                EditableStands.protocolManager.sendServerPacket(player, animation);

                if (playSounds) {
                    event.getPlayer().getWorld().playSound(armorStand.getLocation(), Sound.ITEM_AXE_STRIP, 1, 1);
                }

                event.setCancelled(true);
                armorStand.setSmall(true);

                if (dropItems) {
                    player.getWorld().dropItem(armorStand.getLocation().add(0, 0.25, 0), new ItemStack(Material.STICK, 2));
                }

                if (damageTools) {
                    if (damageToolsInCreative || player.getGameMode() != GameMode.CREATIVE) {
                        PlayerItemInHandItemDamage.playerItemInHandItemDamage(player);
                    }
                }

            } else if (player.hasPermission("editablestands.size.big") && doBig && player.getInventory().getItemInMainHand().getType() == Material.STICK
                    && player.getInventory().getItemInMainHand().getAmount() >= 2
                    && armorStand.isSmall()) {
                EditableStands.protocolManager.sendServerPacket(player, animation);

                if (playSounds) {
                    event.getPlayer().getWorld().playSound(armorStand.getLocation(), Sound.ITEM_AXE_STRIP, 1, 1);
                }

                event.setCancelled(true);
                armorStand.setSmall(false);

                if (removeItems) {
                    if (removeItemsInCreative || player.getGameMode() != GameMode.CREATIVE) {
                        player.getInventory().getItemInMainHand().setAmount(player.getInventory().getItemInMainHand().getAmount() - 2);
                    }
                }

            } else if (player.hasPermission("editablestands.plate.remove") && plateRemove && getPickaxeList().contains(player.getInventory().getItemInMainHand().getType())
                && armorStand.hasBasePlate()) {
                EditableStands.protocolManager.sendServerPacket(player, animation);

                if (playSounds) {
                    event.getPlayer().getWorld().playSound(armorStand.getLocation(), Sound.BLOCK_STONE_BREAK, 1, 1);
                }

                event.setCancelled(true);
                armorStand.setBasePlate(false);

                if (dropItems) {
                    player.getWorld().dropItem(armorStand.getLocation().add(0, 0.25, 0), new ItemStack(Material.SMOOTH_STONE_SLAB, 1));
                }

                if (damageTools) {
                    if (damageToolsInCreative || player.getGameMode() != GameMode.CREATIVE) {
                        PlayerItemInHandItemDamage.playerItemInHandItemDamage(player);
                    }
                }

            } else if (player.hasPermission("editablestands.plate.add") && plateAdd && player.getInventory().getItemInMainHand().getType() == Material.SMOOTH_STONE_SLAB
                    && !armorStand.hasBasePlate()) {
                EditableStands.protocolManager.sendServerPacket(player, animation);

                if (playSounds) {
                    event.getPlayer().getWorld().playSound(armorStand.getLocation(), Sound.BLOCK_STONE_PLACE, 1, 1);
                }

                event.setCancelled(true);
                armorStand.setBasePlate(true);

                if (removeItems) {
                    if (removeItemsInCreative || player.getGameMode() != GameMode.CREATIVE) {
                        player.getInventory().getItemInMainHand().setAmount(player.getInventory().getItemInMainHand().getAmount() - 1);
                    }
                }
            }
        }
    }

    @EventHandler public static void onArmorStandDeath(EntityDeathEvent event) {
        if (event.getEntity() instanceof org.bukkit.entity.ArmorStand armorStand) {
            if (armorStand.hasArms() || armorStand.isSmall() || !armorStand.hasBasePlate()) {
                if (!dropItems) {
                    return;
                }

                event.getDrops().remove(new ItemStack(Material.ARMOR_STAND, 1));

                if (armorStand.hasArms() && armorStand.hasBasePlate() && !armorStand.isSmall()) {
                    event.getDrops().add(new ItemStack(Material.ARMOR_STAND, 1));
                    event.getDrops().add(new ItemStack(Material.STICK, 2));

                } else if (!armorStand.hasArms() && armorStand.hasBasePlate() && armorStand.isSmall()) {
                    event.getDrops().add(new ItemStack(Material.STICK, 4));
                    event.getDrops().add(new ItemStack(Material.SMOOTH_STONE_SLAB, 1));

                } else if (armorStand.hasArms() && armorStand.hasBasePlate() && armorStand.isSmall()) {
                    event.getDrops().add(new ItemStack(Material.STICK, 6));
                    event.getDrops().add(new ItemStack(Material.SMOOTH_STONE_SLAB, 1));

                } else if (!armorStand.hasArms() && !armorStand.hasBasePlate() && armorStand.isSmall()) {
                    event.getDrops().add(new ItemStack(Material.STICK, 4));

                } else if (armorStand.hasArms() && !armorStand.hasBasePlate() && armorStand.isSmall()) {
                    event.getDrops().add(new ItemStack(Material.STICK, 6));

                } else if (armorStand.hasArms() && !armorStand.hasBasePlate() && !armorStand.isSmall()) {
                    event.getDrops().add(new ItemStack(Material.STICK, 8));

                } else if (!armorStand.hasArms() && !armorStand.hasBasePlate() && !armorStand.isSmall()) {
                    event.getDrops().add(new ItemStack(Material.STICK, 6));
                }
            }
        }
    }
}
