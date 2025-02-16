package plugin.nicohaxz.Days;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.entity.VillagerAcquireTradeEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import plugin.nicohaxz.Utils.Utils;

public class Day20 implements Listener {
    @EventHandler
    public void onPlayerDamage(EntityDamageByEntityEvent event) {
        Utils.onDay(20, null, () -> {
            if (event.getEntity() instanceof Player) {
                Player player = (Player) event.getEntity();
                for (ItemStack armor : player.getInventory().getArmorContents()) {
                    if (armor.getType() == Material.NETHERITE_HELMET || armor.getType() == Material.NETHERITE_CHESTPLATE ||
                            armor.getType() == Material.NETHERITE_LEGGINGS || armor.getType() == Material.NETHERITE_BOOTS) {
                        armor.setDurability((short) 0);
                    }
                }
            }
        });
    }

    @EventHandler
    public void onCreeperSpawn(EntitySpawnEvent event) {
        Utils.onDay(20, null, () -> {
            if (event.getEntity() instanceof Creeper) {
                Creeper creeper = (Creeper) event.getEntity();
                creeper.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, Integer.MAX_VALUE, 1));
            }
        });
    }

    @EventHandler
    public void onCreeperExplosion(EntityExplodeEvent event) {
        Utils.onDay(20, null, () -> {
            if (event.getEntity() instanceof Creeper) {
                for (Entity entity : event.getEntity().getNearbyEntities(10, 10, 10)) {
                    if (entity instanceof Player) {
                        Player player = (Player) entity;
                        player.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, 200, 9));  // 10 seconds of Levitation X
                    }
                }
            }
        });
    }

    @EventHandler
    public void onPhantomSpawn(EntitySpawnEvent event) {
        Utils.onDay(20, null, () -> {
            if (event.getEntity() instanceof Phantom) {
                Phantom phantom = (Phantom) event.getEntity();
                phantom.setSilent(true);
            }
        });
    }

    @EventHandler
    public void onPlayerConsume(PlayerItemConsumeEvent event) {
        Utils.onDay(20, null, () -> {
            ItemStack item = event.getItem();
            if (item.getType() == Material.GOLDEN_APPLE || item.getType() == Material.GOLDEN_CARROT) {
                event.setCancelled(true);
            }
        });
    }

    @EventHandler
    public void onPlayerEat(PlayerItemConsumeEvent event) {
        Utils.onDay(20, null, () -> {
            if (event.getItem().getType() == Material.COOKIE) {
                Player player = event.getPlayer();
                player.addPotionEffect(new PotionEffect(PotionEffectType.POISON, Integer.MAX_VALUE, 0));  // Veneno permanente
            }
        });
    }

    @EventHandler
    public void onVillagerTrade(VillagerAcquireTradeEvent event) {
        Utils.onDay(20, null, () -> {
            event.setCancelled(true);
        });
    }

    @EventHandler
    public void onSkeletonSpawn(EntitySpawnEvent event) {
        Utils.onDay(20, null, () -> {
            if (event.getEntity() instanceof Skeleton) {
                Skeleton skeleton = (Skeleton) event.getEntity();
                ItemStack bow = new ItemStack(Material.BOW);
                ItemMeta meta = bow.getItemMeta();
                if (meta != null) {
                    meta.addEnchant(Enchantment.ARROW_KNOCKBACK, 255, true);
                    meta.addEnchant(Enchantment.ARROW_DAMAGE, 255, true);
                    bow.setItemMeta(meta);
                }
                skeleton.getEquipment().setItemInMainHand(bow);
            }
        });
    }

    @EventHandler
    public void onCowSpawn(EntitySpawnEvent event) {
        Utils.onDay(20, null, () -> {
            if (event.getEntity() instanceof Cow) {
                Cow cow = (Cow) event.getEntity();
                Location location = cow.getLocation();
                Ravager ravager = (Ravager) cow.getWorld().spawnEntity(location, EntityType.RAVAGER);
                ravager.setCustomName("Bull");
                cow.remove();
            }
        });
    }

    @EventHandler
    public void onBullDamage(EntityDamageByEntityEvent event) {
        Utils.onDay(20, null, () -> {
            if (event.getEntity() instanceof Player && event.getDamager() instanceof Ravager) {
                Ravager bull = (Ravager) event.getDamager();
                Player player = (Player) event.getEntity();
                event.setDamage(Double.MAX_VALUE);
            }
        });
    }
}
