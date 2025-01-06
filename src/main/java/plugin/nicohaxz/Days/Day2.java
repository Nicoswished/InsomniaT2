package plugin.nicohaxz.Days;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.Enderman;
import org.bukkit.entity.Player;
import org.bukkit.entity.Skeleton;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.entity.EntityTargetLivingEntityEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import plugin.nicohaxz.Utils.Utils;

public class Day2 implements Listener {
    @EventHandler
    public void onCreeperSpawn(EntitySpawnEvent event) {
        Utils.onDay(2, null, () -> {
            if (event.getEntity() instanceof Creeper) {
                Creeper creeper = (Creeper) event.getEntity();
                if (Math.random() < 0.5) {
                    creeper.setPowered(true);
                }
            }
        });
    }
    @EventHandler
    public void onSkeletonSpawn(EntitySpawnEvent event) {
        Utils.onDay(0, null, () -> {
            if (event.getEntity() instanceof Skeleton) {
                Skeleton skeleton = (Skeleton) event.getEntity();
                ItemStack bow = new ItemStack(Material.BOW);
                ItemMeta meta = bow.getItemMeta();
                if (meta != null) {
                    meta.addEnchant(Enchantment.ARROW_FIRE, 1, true);
                    meta.addEnchant(Enchantment.ARROW_DAMAGE, 5, true);
                    bow.setItemMeta(meta);
                }
                skeleton.getEquipment().setItemInMainHand(bow);
            }
        });
    }
    @EventHandler
    public void onInventoryOpen(InventoryOpenEvent event) {
        Utils.onDay(2, null, () -> {
            if (event.getInventory().getType() == InventoryType.CHEST || event.getInventory().getType() == InventoryType.BARREL) {
                if (Math.random() < 0.05) {
                    event.setCancelled(true);
                    event.getPlayer().sendMessage(Utils.chatcolor("&l&5Ups, la cafeina esta dura."));
                }
            }
        });
    }
    @EventHandler
    public void onEndermanLook(EntityTargetLivingEntityEvent event) {
        Utils.onDay(2, null, () -> {
            if (event.getEntity() instanceof Enderman && event.getTarget() instanceof Player) {
                ((Enderman) event.getEntity()).setTarget(event.getTarget());
            }
        });
    }
}
