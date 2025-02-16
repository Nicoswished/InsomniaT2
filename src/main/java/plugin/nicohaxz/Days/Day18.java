package plugin.nicohaxz.Days;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Barrel;
import org.bukkit.block.Biome;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import java.util.Random;

import plugin.nicohaxz.Utils.Utils;
import plugin.nicohaxz.main;

public class Day18 implements Listener {

    private final main main;

    public Day18(main main) {
        this.main = main;
    }

    @EventHandler
    public void onVexSpawn(EntitySpawnEvent event) {
        Utils.onDay(18, null, () -> {
            if (event.getEntity() instanceof Vex) {
                Vex vex = (Vex) event.getEntity();
                ItemStack sword = new ItemStack(Material.WOODEN_SWORD);
                ItemMeta meta = sword.getItemMeta();
                if (meta != null) {
                    meta.addEnchant(org.bukkit.enchantments.Enchantment.DAMAGE_ALL, 100, true);
                    sword.setItemMeta(meta);
                }
                vex.getEquipment().setItemInMainHand(sword);
            }
        });
    }

    @EventHandler
    public void onEndermanSpawn(EntitySpawnEvent event) {
        Utils.onDay(18, null, () -> {
            if (event.getEntity() instanceof Enderman) {
                Enderman enderman = (Enderman) event.getEntity();
                ItemStack tnt = new ItemStack(Material.TNT);
                enderman.getEquipment().setItemInMainHand(tnt);
            }
        });
    }

    @EventHandler
    public void onEndermanDamage(EntityDamageByEntityEvent event) {
        if (event.getEntity() instanceof Player && event.getDamager() instanceof Enderman) {
            Player player = (Player) event.getEntity();
            TNTPrimed tnt = (TNTPrimed) player.getWorld().spawnEntity(player.getLocation(), EntityType.PRIMED_TNT);
            tnt.setFuseTicks(0);
        }
    }

    @EventHandler
    public void onPlayerFish(PlayerFishEvent event) {
        if (event.getState() == PlayerFishEvent.State.CAUGHT_FISH) {
            Player player = event.getPlayer();
            player.getWorld().spawnEntity(player.getLocation(), EntityType.WARDEN);
        }
    }

    @EventHandler
    public void onPlayerTouchBlock(EntityDamageEvent event) {
        Utils.onDay(18, null, () -> {
            if (event.getEntity() instanceof Player) {
                Player player = (Player) event.getEntity();
                if (event.getCause() == EntityDamageEvent.DamageCause.FALL) {
                    Location location = player.getLocation();
                    Block blockBelow = location.subtract(0, 1, 0).getBlock();
                    if (blockBelow.getType() == Material.SAND || blockBelow.getType() == Material.GLASS) {
                        player.setFireTicks(200);
                    }
                }
            }
        });
    }

    @EventHandler
    public void onArrowHit(EntityDamageByEntityEvent event) {
        Utils.onDay(18, null, () -> {
            if (event.getDamager() instanceof Arrow) {
                Arrow arrow = (Arrow) event.getDamager();
                if (!arrow.getCustomEffects().isEmpty()) {
                    event.setCancelled(true);
                }
            }
        });
    }

    @EventHandler
    public void onSnowGolemSpawn(EntitySpawnEvent event) {
        Utils.onDay(18, null, () -> {
            if (event.getEntity() instanceof Snowman) {
                Snowman snowman = (Snowman) event.getEntity();
                snowman.setCustomName("SnowGolemWithPotion");
            }
        });
    }

    @EventHandler
    public void onHorseSpawn(EntitySpawnEvent event) {
        Utils.onDay(18, null, () -> {
            if (event.getEntity() instanceof Horse) {
                Horse horse = (Horse) event.getEntity();
                Skeleton skeleton = (Skeleton) horse.getWorld().spawnEntity(horse.getLocation(), EntityType.SKELETON);
                horse.addPassenger(skeleton);
            }
        });
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Utils.onDay(18, null, () -> {
            Player player = event.getPlayer();
            Location location = player.getLocation();
            World world = player.getWorld();
            Biome biome = world.getBiome(location);

            if (biome == Biome.PLAINS) {
                applyEffectIfNotPresent(player, PotionEffectType.HUNGER, Integer.MAX_VALUE, 0);
            } else if (biome == Biome.SNOWY_TAIGA) {
                applyEffectIfNotPresent(player, PotionEffectType.WEAKNESS, Integer.MAX_VALUE, 1);
            } else if (biome == Biome.DESERT) {
                applyEffectIfNotPresent(player, PotionEffectType.BLINDNESS, Integer.MAX_VALUE, 0);
            } else if (biome == Biome.TAIGA) {
                applyEffectIfNotPresent(player, PotionEffectType.SLOW, Integer.MAX_VALUE, 2);
            }
        });
    }

    private void applyEffectIfNotPresent(Player player, PotionEffectType type, int duration, int amplifier) {
        if (!player.hasPotionEffect(type)) {
            player.addPotionEffect(new PotionEffect(type, duration, amplifier));
        }
    }

    @EventHandler
    public void onBeeAttack(EntityDamageByEntityEvent event) {
        Utils.onDay(18, null, () -> {
            if (event.getDamager() instanceof Bee && event.getEntity() instanceof Player) {
                Player player = (Player) event.getEntity();
                player.addPotionEffect(new PotionEffect(PotionEffectType.POISON, Integer.MAX_VALUE, 0));
                Bee bee = (Bee) event.getDamager();
                Bukkit.getScheduler().runTaskLater(main, () -> bee.getWorld().createExplosion(bee.getLocation(), 3F), 20L);
            }
        });
    }

    @EventHandler
    public void onSquidDamage(EntityDamageByEntityEvent event) {
        Utils.onDay(18, null, () -> {
            if (event.getDamager() instanceof Squid && event.getEntity() instanceof Player) {
                Player player = (Player) event.getEntity();
                player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, Integer.MAX_VALUE, 0));
            }
        });
    }

    @EventHandler
    public void onPufferfishDamage(EntityDamageByEntityEvent event) {
        Utils.onDay(18, null, () -> {
            if (event.getDamager() instanceof PufferFish) {
                event.setDamage(1000);
            }
        });
    }

    @EventHandler
    public void onChestOpen(InventoryOpenEvent event) {
        Utils.onDay(18, null, () -> {
            if (event.getInventory().getHolder() instanceof Chest || event.getInventory().getHolder() instanceof Barrel) {
                if (new Random().nextDouble() < 0.40) {
                    event.setCancelled(true);
                }
            }
        });
    }

    @EventHandler
    public void onPlayerEat(PlayerItemConsumeEvent event) {
        Utils.onDay(18, null, () -> {
            if (event.getItem().getType() == Material.COOKIE) {
                Player player = event.getPlayer();
                player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 200, 3));
            }
        });
    }
}

