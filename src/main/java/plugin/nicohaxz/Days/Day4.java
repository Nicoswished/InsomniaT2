package plugin.nicohaxz.Days;

import org.bukkit.Material;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityTargetLivingEntityEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import plugin.nicohaxz.Utils.Utils;

import java.util.List;

public class Day4 implements Listener {

    @EventHandler
    public void onSpiderHit(EntityDamageByEntityEvent event) {
        Utils.onDay(4, null, () -> {
            if (event.getEntity() instanceof Player && event.getDamager() instanceof Spider) {
                Player player = (Player) event.getEntity();
                player.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 100, 1));
            }
        });
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Utils.onDay(4, null, () -> {
            if (event.getBlock().getType() == Material.IRON_ORE) {
                event.setDropItems(false);
                event.getPlayer().getWorld().dropItemNaturally(event.getBlock().getLocation(), new ItemStack(Material.IRON_NUGGET));  // Dejar pepitas de hierro
            } else if (event.getBlock().getType() == Material.GOLD_ORE) {
                event.setDropItems(false);
                event.getPlayer().getWorld().dropItemNaturally(event.getBlock().getLocation(), new ItemStack(Material.GOLD_NUGGET));  // Dejar pepitas de oro
            }
        });
    }

    @EventHandler
    public void onGoldenAppleConsume(PlayerItemConsumeEvent event) {
        Utils.onDay(4, null, () -> {
            if (event.getItem().getType() == Material.GOLDEN_APPLE) {
                event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 100, 0));  // Duración 5 segundos, nivel 1 de lentitud
            }
        });
    }

    @EventHandler
    public void onCreatureSpawn(CreatureSpawnEvent event) {
        Utils.onDay(4, null, () -> {
            if (event.getEntity() instanceof Spider || event.getEntity() instanceof Bee ||
                    event.getEntity() instanceof Wolf || event.getEntity() instanceof Panda) {

                if (event.getEntity() instanceof Monster) {
                    return;
                }

                List<Entity> nearbyEntities = (List<Entity>) event.getEntity().getWorld().getNearbyEntities(event.getEntity().getLocation(), 10, 10, 10);

                Player nearestPlayer = null;
                double closestDistance = Double.MAX_VALUE;
                for (Entity entity : nearbyEntities) {
                    if (entity instanceof Player) {
                        double distance = event.getEntity().getLocation().distance(entity.getLocation());
                        if (distance < closestDistance) {
                            closestDistance = distance;
                            nearestPlayer = (Player) entity;
                        }
                    }
                }
                if (nearestPlayer != null) {
                    ((Animals) event.getEntity()).setTarget(nearestPlayer);
                }
            }
        });
    }
}
