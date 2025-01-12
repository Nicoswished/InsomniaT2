package plugin.nicohaxz.Days;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.*;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import plugin.nicohaxz.Utils.Utils;

public class Day13 implements Listener {
    @EventHandler
    public void onSlimeSpawn(CreatureSpawnEvent event) {
        Utils.onDay(13, null, () -> {
            if (event.getEntityType() == EntityType.SLIME) {
                Slime slime = (Slime) event.getEntity();
                slime.setSize(5);
                slime.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, Integer.MAX_VALUE, 4, false, false)); // Fuerza V
                slime.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, Integer.MAX_VALUE, 4, false, false)); // Jump Boost V
                slime.setInvulnerable(true);
            }
        });
    }
    @EventHandler
    public void onEquipJackOLantern(PlayerItemHeldEvent event) {
        Utils.onDay(13, null, () -> {
            Player player = event.getPlayer();
            ItemStack item = player.getInventory().getItem(event.getNewSlot());
            if (item.getType() == Material.PUMPKIN) {
                ItemMeta meta = item.getItemMeta();
                if (meta != null) {
                    meta.addEnchant(Enchantment.BINDING_CURSE, 1, true);
                    item.setItemMeta(meta);
                }
            }
        });
    }
    @EventHandler
    public void onEnderPearlCooldown(PlayerInteractEvent event) {
        Utils.onDay(13, null, () -> {
            if (event.getItem() != null && event.getItem().getType() == Material.ENDER_PEARL) {
                Player player = event.getPlayer();
                player.setCooldown(Material.ENDER_PEARL, 40);
            }
        });
    }
    @EventHandler
    public void onTotemUse(EntityResurrectEvent event) {
        Utils.onDay(13, null, () -> {
            if (!(event.getEntity() instanceof Player p)) return;
            if (p.getInventory().getItemInMainHand().getType() == Material.TOTEM_OF_UNDYING || p.getInventory().getItemInOffHand().getType() == Material.TOTEM_OF_UNDYING) {
                p.setCooldown(Material.TOTEM_OF_UNDYING, 20);
            }else{
                event.setCancelled(true);
            }
        });
    }
    @EventHandler
    public void onPetNearPlayer(EntityDamageByEntityEvent event) {
        Utils.onDay(13, null, () -> {
            if (event.getEntity() instanceof Player) {
                Player player = (Player) event.getEntity();
                if (event.getDamager() instanceof Wolf || event.getDamager() instanceof Cat) {
                    player.damage(1);
                }
            }
        });
    }
    @EventHandler
    public void onEntityKill(EntityDeathEvent event) {
        Utils.onDay(13, null, () -> {
            if (Math.random() < 0.01) {
                Location location = event.getEntity().getLocation();
                event.getEntity().getWorld().spawnEntity(location, EntityType.WARDEN);
            }
        });
    }
    @EventHandler
    public void onHotbarChange(PlayerItemHeldEvent event) {
        Utils.onDay(13, null, () -> {
            Player player = event.getPlayer();
            //EZZZZZZZZZZZZ
            if (Math.random() < 0.05) {
                player.damage(1);
            }
        });
    }
    @EventHandler
    public void onExplosion(EntityExplodeEvent event) {
        Utils.onDay(13, null, () -> {
            //MUY ZZZZZZ
            event.blockList().removeIf(block -> block.getType() == Material.WATER || block.getType() == Material.LAVA);
        });
    }
}
