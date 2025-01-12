package plugin.nicohaxz.Days;

import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Evoker;
import org.bukkit.entity.Player;
import org.bukkit.entity.Vex;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffectType;
import plugin.nicohaxz.Utils.Utils;

public class Day12 implements Listener {

    //!ME DA PAJA CREAR UNA CLASE SOLO PARA ESTE CODIGO ASI QUE XD NO TRAE DIAS IGUALMENTE
    @EventHandler
    public void GeneralDamage(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player) {
            Player p = (Player) event.getEntity();
            if (p.hasPotionEffect(PotionEffectType.HARM)) {
                event.setCancelled(true);
            }
        }
    }
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Utils.onDay(12, null, () -> {
            ItemStack item = event.getItem();
            if (item != null && item.getType() == Material.GOLDEN_APPLE) {
                event.setCancelled(true);
            }
        });
    }
    @EventHandler
    public void onCreatureSpawn(CreatureSpawnEvent event) {
        Utils.onDay(12, null, () -> {
            if (event.getEntityType() == EntityType.VINDICATOR || event.getEntityType() == EntityType.PILLAGER || event.getEntityType() == EntityType.RAVAGER) {
                Evoker evoker = (Evoker) event.getEntity().getWorld().spawnEntity(event.getEntity().getLocation(), EntityType.EVOKER);
                evoker.setHealth(100.0);
                event.setCancelled(true);
                evoker.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(20.0);
            }
        });
    }
    @EventHandler
    public void onPlayerHungerDamage(EntityDamageEvent event) {
        Utils.onDay(12, null, () -> {
            if (event.getEntity() instanceof Player) {
                Player player = (Player) event.getEntity();
                if (player.getFoodLevel() <= 6) {
                    double damage = event.getDamage();
                    event.setDamage(damage * 5);
                }
            }
        });
    }
    @EventHandler
    public void onVexSpawn(CreatureSpawnEvent event) {
        Utils.onDay(12, null, () -> {
            if (event.getEntityType() == EntityType.VEX) {
                Vex vex = (Vex) event.getEntity();
                ItemStack goldenSword = new ItemStack(Material.GOLDEN_SWORD);
                ItemMeta meta = goldenSword.getItemMeta();
                if (meta != null) {
                    meta.addEnchant(Enchantment.FIRE_ASPECT, 2, true);  // Fire Aspect II
                    goldenSword.setItemMeta(meta);
                }
                vex.getEquipment().setItemInMainHand(goldenSword);
            }
        });
    }
    @EventHandler
    public void onEvokerDeath(EntityDeathEvent event) {
        Utils.onDay(12, null, () -> {
            if (event.getEntityType() == EntityType.EVOKER) {
                event.getDrops().removeIf(item -> item.getType() == Material.TOTEM_OF_UNDYING);
            }
        });
    }
    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {
        Utils.onDay(12, null, () -> {
            if (event.getEntity().getType() == EntityType.VINDICATOR) {
                event.getDrops().clear();
            }
        });
    }
}
