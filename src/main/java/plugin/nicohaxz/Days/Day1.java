package plugin.nicohaxz.Days;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.Spider;
import org.bukkit.entity.Villager;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import plugin.nicohaxz.Utils.Utils;

public class Day1 implements Listener {
    @EventHandler
    public void onZombieSpawn(EntitySpawnEvent event) {
        Utils.onDay(0, null, () -> {
            if (event.getEntity() instanceof Zombie) {
                Zombie zombie = (Zombie) event.getEntity();
                ItemStack ironSword = new ItemStack(Material.IRON_SWORD);
                ItemMeta meta = ironSword.getItemMeta();
                ironSword.setItemMeta(meta);
                zombie.getEquipment().setItemInMainHand(ironSword);
            }
        });
    }

    @EventHandler
    public void onSpiderHit(EntityDamageByEntityEvent event) {
        Utils.onDay(0, null, () -> {

            if (event.getDamager() instanceof Spider && event.getEntity() instanceof Player) {
                Player player = (Player) event.getEntity();
                player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 100, 1));
            }
        });
    }

    @EventHandler
    public void onVillagerDamage(EntityDamageByEntityEvent event) {
        Utils.onDay(0, null, () -> {
            if (event.getEntity() instanceof Villager) {
                double originalDamage = event.getDamage();
                event.setDamage(originalDamage * 2);
            }
        });
    }
}
