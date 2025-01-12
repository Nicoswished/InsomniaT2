package plugin.nicohaxz.Days;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import plugin.nicohaxz.Utils.Utils;

import java.util.Random;


public class Day7 implements Listener {
    private final Random random = new Random();

    @EventHandler
    public void onGhastFireball(EntityShootBowEvent event) {
        Utils.onDay(7, null, () -> {
            if (event.getEntity() instanceof Ghast) {
                Fireball fireball = (Fireball) event.getProjectile();
                fireball.setYield(6);
            }
        });
    }
    @EventHandler
    public void onWitherSkeletonSpawn(EntitySpawnEvent event) {
        Utils.onDay(7, null, () -> {
            if (event.getEntity() instanceof WitherSkeleton) {
                WitherSkeleton skeleton = (WitherSkeleton) event.getEntity();

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

                skeleton.getEquipment().setHelmetDropChance(0f);
                skeleton.getEquipment().setChestplateDropChance(0f);
                skeleton.getEquipment().setLeggingsDropChance(0f);
                skeleton.getEquipment().setBootsDropChance(0f);
                skeleton.getEquipment().setItemInMainHandDropChance(0f);
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
    public void onCaveSpiderTarget(EntityTargetEvent event) {
        Utils.onDay(7, null, () -> {
            if (event.getEntity().getType() == EntityType.CAVE_SPIDER) {
                CaveSpider caveSpider = (CaveSpider) event.getEntity();
                if (random.nextInt(2) == 0) {
                    caveSpider.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 40, 4)); // 40 ticks = 2 segundos
                }
            }
        });
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
