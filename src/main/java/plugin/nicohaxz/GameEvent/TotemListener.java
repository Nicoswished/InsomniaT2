package plugin.nicohaxz.GameEvent;

import org.bukkit.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityResurrectEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import plugin.nicohaxz.Utils.PlayerData;
import plugin.nicohaxz.Utils.Utils;
import plugin.nicohaxz.main;

public class TotemListener implements Listener {
    @EventHandler
    public void onTotem(EntityResurrectEvent e) {
        if (!(e.getEntity() instanceof Player p)) return;
        if (p.getInventory().getItemInMainHand().getType() == Material.TOTEM_OF_UNDYING ||
                p.getInventory().getItemInOffHand().getType() == Material.TOTEM_OF_UNDYING) {
            if (p.getCooldown(Material.TOTEM_OF_UNDYING) > 0) {
                e.setCancelled(true);
                for (Player pl : Bukkit.getOnlinePlayers()) {
                    pl.sendMessage(Utils.getPrefix() + Utils.c("&7El totem del jugador: &e&l" + p.getName() + " &7Estaba en cooldown y ha muerto por: " + Utils.getCustomCause(p.getLastDamageCause(), "&d&l")));
                }
                return;
            }
        }
    }

    @EventHandler
    public void onEntityResurrect(EntityResurrectEvent event) {
        if (!(event.getEntity() instanceof Player player)) return;
        ItemStack totemInHand = player.getInventory().getItemInOffHand();
        if (player.getInventory().getItemInMainHand().getType() == Material.TOTEM_OF_UNDYING ||
            player.getInventory().getItemInOffHand().getType() == Material.TOTEM_OF_UNDYING) {

            ItemMeta meta = totemInHand.getItemMeta();

            if (meta == null || !meta.hasCustomModelData()) return;
            int customModelData = meta.getCustomModelData();

            if (customModelData == 1) {
                Bukkit.getScheduler().runTaskLater(main.getInstance(), () -> {
                    player.setNoDamageTicks(80);
                    player.playSound(player.getLocation(), Sound.BLOCK_BEACON_ACTIVATE, 5.5F, 1.4F);
                    player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 20 * 5, 1));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 20 * 1, 1));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 20 * 10, 1));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, 20 * 3, 4));
                }, 20L);
            }
        }
    }
}
