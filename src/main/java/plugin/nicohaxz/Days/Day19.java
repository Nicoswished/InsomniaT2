package plugin.nicohaxz.Days;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.projectiles.ProjectileSource;
import plugin.nicohaxz.Utils.Utils;

public class Day19 implements Listener {
    @EventHandler
    public void onVexSpawn(EntitySpawnEvent event) {
        Utils.onDay(19, null, () -> {
            if (event.getEntity() instanceof Vex vex) {
                ItemStack sword = createEnchantedItem(Material.WOODEN_SWORD, Enchantment.DAMAGE_ALL, 100);
                vex.getEquipment().setItemInMainHand(sword);
            }
        });
    }

    @EventHandler
    public void onEndermanSpawn(EntitySpawnEvent event) {
        Utils.onDay(19, null, () -> {

            if (event.getEntity() instanceof Enderman enderman) {
                ItemStack tnt = new ItemStack(Material.TNT);
                enderman.getEquipment().setItemInMainHand(tnt);
            }
        });
    }

    @EventHandler
    public void onEndermanDamage(EntityDamageByEntityEvent event){
            Utils.onDay(19, null, () -> {
                if (event.getEntity() instanceof Player player && event.getDamager() instanceof Enderman) {
                    TNTPrimed tnt = (TNTPrimed) player.getWorld().spawnEntity(player.getLocation(), EntityType.PRIMED_TNT);
                    tnt.setFuseTicks(0);
                }
            });
        }

    @EventHandler
    public void onPlayerFish(PlayerFishEvent event) {
        Utils.onDay(19, null, () -> {
            if (event.getState() == PlayerFishEvent.State.CAUGHT_FISH) {
                Player player = event.getPlayer();
                player.getWorld().spawnEntity(player.getLocation(), EntityType.WARDEN);
            }
        });
    }

    @EventHandler
    public void onPlayerTouchBlock(EntityDamageEvent event) {
        Utils.onDay(19, null, () -> {


            if (event.getEntity() instanceof Player player && event.getCause() == EntityDamageEvent.DamageCause.FALLING_BLOCK) {
                Block blockBelow = player.getLocation().subtract(0, 1, 0).getBlock();
                if (blockBelow.getType() == Material.SAND || blockBelow.getType() == Material.GLASS) {
                    player.setFireTicks(200);
                }
            }
        });
    }

    @EventHandler
    public void onArrowHit(EntityDamageByEntityEvent event) {
        Utils.onDay(19, null, () -> {


            if (event.getDamager() instanceof Arrow arrow) {
                ProjectileSource shooter = arrow.getShooter();
                if (arrow.hasCustomEffects() && shooter instanceof Player) {
                    event.setCancelled(true);
                }
            }
        });
    }

    private ItemStack createEnchantedItem(Material material, Enchantment enchantment, int level) {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            meta.addEnchant(enchantment, level, true);
            item.setItemMeta(meta);
        }
        return item;
    }
}

