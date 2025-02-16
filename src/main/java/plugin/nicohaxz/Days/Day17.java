package plugin.nicohaxz.Days;

import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.*;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemBreakEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import plugin.nicohaxz.Utils.Utils;
import plugin.nicohaxz.main;
import java.util.Random;

import java.util.Random;
import java.util.Vector;

public class Day17 implements Listener {

    @EventHandler
    public void onPhantomHitByArrow(EntityDamageByEntityEvent event) {
        Utils.onDay(17, null, () -> {
            if (event.getEntityType() == EntityType.PHANTOM && event.getDamager() instanceof Arrow) {
                event.setCancelled(true);
            }
        });
    }

    @EventHandler
    public void onTNTExplode(EntityExplodeEvent event) {
        Utils.onDay(17, null, () -> {
            if (event.getEntityType() == EntityType.PRIMED_TNT) {
                Location explosionLocation = event.getLocation();
                World world = explosionLocation.getWorld();
                if (world != null) {
                    Bukkit.getScheduler().runTaskLater(main.getInstance(), () -> {
                        world.spawnEntity(explosionLocation, EntityType.CREEPER);
                    }, 20L);
                }
            }
        });
    }

    @EventHandler
    public void onBedrockTouch(PlayerMoveEvent event) {
        Utils.onDay(17, null, () -> {
            Player player = event.getPlayer();
            if (player.getGameMode() != GameMode.CREATIVE) {
                Material blockType = player.getLocation().getBlock().getType();
                if (blockType == Material.BEDROCK) {
                    player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, Integer.MAX_VALUE, 0, true, false));
                }
            }
        });
    }


    @EventHandler
    public void onEntityDamage(EntityDamageByEntityEvent event) {
        Utils.onDay(17, null, () -> {
            if (event.getEntity() instanceof Player) {
                Player player = (Player) event.getEntity();
                if (event.getDamager() instanceof Stray) {
                    player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, Integer.MAX_VALUE, 0, true, false));
                } else if (event.getDamager() instanceof Husk) {
                    player.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, Integer.MAX_VALUE, 0, true, false));
                }
            }
        });
    }


    @EventHandler
    public void onLlamaSpitHit(ProjectileHitEvent event) {
        Utils.onDay(17, null, () -> {
            if (event.getEntity() instanceof LlamaSpit) {
                Location location = event.getEntity().getLocation();
                location.getWorld().createExplosion(location, 5.0F, false, false);
            }
        });
    }


    @EventHandler
    public void onEndCrystalHit(EntityDamageByEntityEvent event) {
        Utils.onDay(17, null, () -> {
            if (event.getEntity() instanceof EnderCrystal && event.getDamager() instanceof Projectile) {
                event.setCancelled(true);
            }
        });
    }

    @EventHandler
    public void onPlayerDamage(EntityDamageEvent event) {
        Utils.onDay(17, null, () -> {
            if (event.getEntity() instanceof Player) {
                Player player = (Player) event.getEntity();
                if (event.getFinalDamage() >= 2.0) {
                    player.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 200, 0, true, false));
                }
            }
        });
    }


    @EventHandler
    public void onWitchSpawn(EntitySpawnEvent event) {
        Utils.onDay(17, null, () -> {

            if (event.getEntity() instanceof Witch) {
                Witch witch = (Witch) event.getEntity();
                witch.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, Integer.MAX_VALUE, 9, true, false));
            }
        });
    }

    @EventHandler
    public void onWitchTarget(EntityTargetLivingEntityEvent event) {
        Utils.onDay(17, null, () -> {
            if (event.getEntity() instanceof Witch && event.getTarget() instanceof Player) {
                Witch witch = (Witch) event.getEntity();
                Player target = (Player) event.getTarget();
                Bukkit.getScheduler().runTaskLater(main.getInstance(), () -> {
                    if (witch.isDead() || !witch.hasLineOfSight(target)) return;
                    target.addPotionEffect(new PotionEffect(PotionEffectType.HARM, 1, 4, true, true)); // Daño instantáneo V
                    target.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 40, 0, true, true));
                    target.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 40, 1, true, true));
                    target.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 40, 0, true, true));
                    target.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 40, 1, true, true));
                    target.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 40, 0, true, true));
                }, 20L);
            }
        });
    }

   /* @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        if (!event.getPlayer().isOp()) {
            ItemStack item = event.getPlayer().getInventory().getItemInMainHand();
            if (item != null && item.getType().toString().contains("AXE")) {
                Random random = new Random();
                int chance = random.nextInt(100);
                if (chance < 1) {
                    item.setDurability((short) (item.getDurability() + 0));
                    if (item.getDurability() >= item.getType().getMaxDurability()) {
                        event.getPlayer().getInventory().setItemInMainHand(null);
                        event.getPlayer().sendMessage(Utils.c(Utils.getPrefix() + "&cTu hacha se ha roto!"));
                    }
                }
            }
        }
    }*/

    @EventHandler
    public void onAnimalHit(EntityDamageByEntityEvent event) {
        Utils.onDay(17, null, () -> {
            if (event.getEntity() instanceof Animals) {
                Animals animal = (Animals) event.getEntity();
                if (animal instanceof Llama) {
                    return;
                }
                animal.getWorld().createExplosion(animal.getLocation(), 8.0f);
            }
        });
    }
    @EventHandler
    public void llamam(ProjectileHitEvent event) {
        Utils.onDay(17, null, () -> {
            if (event.getEntity() instanceof LlamaSpit) {
                LlamaSpit spit = (LlamaSpit) event.getEntity();
                spit.getWorld().createExplosion(spit.getLocation(), 5.0f);
            }
        });
    }
}



