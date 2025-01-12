package plugin.nicohaxz.Days;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityMountEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import plugin.nicohaxz.Utils.Utils;
import plugin.nicohaxz.main;

public class Day14 implements Listener {
    @EventHandler
    public void onGhastHit(EntityDamageByEntityEvent event) {
        Utils.onDay(14, null, () -> {
            if (event.getEntity() instanceof Ghast) {
                Bukkit.getScheduler().runTaskLater(main.getInstance(), () -> {
                    Ghast ghast = (Ghast) event.getEntity();
                    ghast.getWorld().spawnEntity(ghast.getLocation(), EntityType.PRIMED_TNT);
                    if (event.getEntity() instanceof Ghast) {
                        event.setCancelled(true);
                    }
                }, 60L);
            }
        });
    }
    @EventHandler
    public void onWitherSkeletonDeath(EntityDeathEvent event) {
        Utils.onDay(14, null, () -> {
            if (event.getEntityType() == EntityType.WITHER_SKELETON) {
                if (Math.random() < 0.5) {
                    ItemStack bow = new ItemStack(Material.BOW);
                    ItemMeta bowMeta = bow.getItemMeta();
                    if (bowMeta != null) {
                        bowMeta.addEnchant(Enchantment.ARROW_DAMAGE, 30, true); // Power XXX
                        bowMeta.addEnchant(Enchantment.FIRE_ASPECT, 1, true); // Flame I
                        bow.setItemMeta(bowMeta);
                    }
                    event.getDrops().add(bow);
                }
            }
        });
    }

    @EventHandler
    public void onShieldBreak(EntityDamageByEntityEvent event) {
        Utils.onDay(14, null, () -> {
            if (event.getEntity() instanceof Player) {
                Player player = (Player) event.getEntity();
                ItemStack itemInHand = player.getInventory().getItemInMainHand();
                if (itemInHand.getType() == Material.SHIELD && event.getCause() == EntityDamageEvent.DamageCause.ENTITY_EXPLOSION) {
                    itemInHand.setDurability((short) (itemInHand.getDurability() + 1));
                }
            }
        });
    }


    @EventHandler
    public void onGoldenCarrotEat(PlayerInteractEvent event) {
        Utils.onDay(14, null, () -> {
            if (event.getItem() != null && event.getItem().getType() == Material.GOLDEN_CARROT) {
                event.setCancelled(true);
            }
        });
    }

    @EventHandler
    public void onPhantomDeath(EntityDeathEvent event) {
        Utils.onDay(14, null, () -> {
            if (event.getEntityType() == EntityType.PHANTOM) {
                Phantom phantom = (Phantom) event.getEntity();
                Mob newMob = (Mob) phantom.getWorld().spawnEntity(phantom.getLocation(), EntityType.ZOMBIE);
                newMob.teleport(phantom.getLocation());
            }
        });
    }
    @EventHandler
    public void onPhantomNearby(EntityMountEvent event) {
        Utils.onDay(14, null, () -> {
            if (event.getEntityType() == EntityType.PHANTOM) {
                Phantom phantom = (Phantom) event.getEntity();
                for (Player player : phantom.getWorld().getPlayers()) {
                    if (phantom.getLocation().distance(player.getLocation()) < 5) {
                        Bukkit.getScheduler().runTaskLater(main.getInstance(), () -> {
                            if (Math.random() < 0.05) {
                                player.damage(1);
                            }
                        }, 100L);
                    }
                }
            }
        });
    }
}
