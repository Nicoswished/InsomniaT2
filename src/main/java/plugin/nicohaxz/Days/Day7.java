package plugin.nicohaxz.Days;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import plugin.nicohaxz.Utils.Utils;

public class Day7 implements Listener {
    @EventHandler
    public void onGhastFireball(EntityShootBowEvent event) {
        Utils.onDay(7, null, () -> {
            if (event.getEntity() instanceof Ghast) {
                Fireball fireball = (Fireball) event.getProjectile();
                fireball.setYield(3);
            }
        });
    }
    @EventHandler
    public void onWitherSkeletonSpawn(EntitySpawnEvent event) {
        Utils.onDay(7, null, () -> {

            if (event.getEntity() instanceof WitherSkeleton) {
                WitherSkeleton skeleton = (WitherSkeleton) event.getEntity();

                // Equipar al Wither Skeleton con full Netherite
                ItemStack helmet = new ItemStack(Material.NETHERITE_HELMET);
                ItemStack chestplate = new ItemStack(Material.NETHERITE_CHESTPLATE);
                ItemStack leggings = new ItemStack(Material.NETHERITE_LEGGINGS);
                ItemStack boots = new ItemStack(Material.NETHERITE_BOOTS);
                ItemStack sword = new ItemStack(Material.NETHERITE_SWORD);

                skeleton.getEquipment().setHelmet(helmet);
                skeleton.getEquipment().setChestplate(chestplate);
                skeleton.getEquipment().setLeggings(leggings);
                skeleton.getEquipment().setBoots(boots);
                skeleton.getEquipment().setItemInMainHand(sword);
            }
        });
    }
    @EventHandler
    public void onPlayerHit(EntityDamageEvent event) {
        Utils.onDay(7, null, () -> {

            if (event.getEntity() instanceof Player) {
                Player player = (Player) event.getEntity();
                if (event.getCause() == EntityDamageEvent.DamageCause.WITHER) {
                    player.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 2400, 1));  // 2 minutos
                } else if (event.getCause() == EntityDamageEvent.DamageCause.POISON) {
                    player.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 2400, 1));  // 2 minutos
                }
            }
        });
    }
    @EventHandler
    public void onCaveSpiderHit(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof CaveSpider) {
            CaveSpider spider = (CaveSpider) event.getDamager();
            if (event.getEntity() instanceof Player) {
                Player player = (Player) event.getEntity();
                spider.setVelocity(player.getLocation().toVector().subtract(spider.getLocation().toVector()).normalize().multiply(2));
                event.setDamage(10);
            }
        }
    }
    @EventHandler
    public void onStrayArrowHit(EntityDamageByEntityEvent event) {
        Utils.onDay(7, null, () -> {
            if (event.getDamager() instanceof Arrow) {
                Arrow arrow = (Arrow) event.getDamager();
                if (arrow.getShooter() instanceof Stray) {
                    Location hitLocation = arrow.getLocation();
                    hitLocation.getWorld().spawnEntity(hitLocation, EntityType.PRIMED_TNT);
                }
            }
        });
    }
    @EventHandler
    public void onEndermanSpawn(EntitySpawnEvent event) {
        Utils.onDay(7, null, () -> {
            if (event.getEntity() instanceof Enderman) {
                Enderman enderman = (Enderman) event.getEntity();
                enderman.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, Integer.MAX_VALUE, 4));  // Fuerza V
                enderman.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, Integer.MAX_VALUE, 1));  // Resistencia II
            }
        });
    }
}
