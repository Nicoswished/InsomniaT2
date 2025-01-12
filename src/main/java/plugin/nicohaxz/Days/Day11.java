package plugin.nicohaxz.Days;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Wither;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import plugin.nicohaxz.Utils.Utils;

public class Day11 implements Listener {
    @EventHandler
    public void onPlayerDamage(EntityDamageEvent event) {
        Utils.onDay(11, null, () -> {
            if (event.getEntity() instanceof Player) {
                Player player = (Player) event.getEntity();

                if (player.getInventory().getItemInMainHand().getType() == Material.TOTEM_OF_UNDYING) {
                    double random = Math.random() * 5;
                    if (random < 5) {
                        player.getInventory().getItemInMainHand().setAmount(0);
                        player.sendMessage(ChatColor.RED + "¡Ups...!");
                    }
                }
            }
        });
    }
    @EventHandler
    public void onCreatureSpawn(CreatureSpawnEvent event) {
        Utils.onDay(11, null, () -> {
            if (event.getEntityType() == EntityType.ENDERMITE || event.getEntityType() == EntityType.SILVERFISH) {
                event.getEntity().addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, Integer.MAX_VALUE, 0, false, false));
                event.getEntity().addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, Integer.MAX_VALUE, 9, false, false)); // Fuerza X = nivel 9
            }
        });
    }
    @EventHandler
    public void onWitherSpawn(CreatureSpawnEvent event) {
        Utils.onDay(11, null, () -> {
            if (event.getEntityType() == EntityType.WITHER) {
                Wither wither = (Wither) event.getEntity();
                wither.setHealth(2000.0);
            }
        });
    }
}
