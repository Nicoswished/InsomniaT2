package plugin.nicohaxz.GameEvent;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import plugin.nicohaxz.Utils.ConfigData;
import plugin.nicohaxz.Utils.PDC;
import plugin.nicohaxz.Utils.Ranks;
import plugin.nicohaxz.Utils.Utils;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class JoinEvent implements Listener {

    //PARA QUE SE GUARDEN LOS COOLDOWNS XD PORQUE SE BUGEA MUCHO XDDD
    private Map<UUID, Map<ItemStack, Long>> cooldowns = new HashMap<>();
    @EventHandler
    public void onPlayerChangedWorld(PlayerChangedWorldEvent event) {
        saveCooldowns(event.getPlayer());
        loadCooldowns(event.getPlayer());
    }

    private void saveCooldowns(Player player) {
        UUID playerId = player.getUniqueId();
        Map<ItemStack, Long> playerCooldowns = new HashMap<>();

        for (ItemStack item : player.getInventory().getContents()) {
            if (item != null && item.getType() != Material.AIR) {
                long cooldown = player.getCooldown(item.getType());
                if (cooldown > 0) {
                    playerCooldowns.put(item, cooldown);
                }
            }
        }

        cooldowns.put(playerId, playerCooldowns);
    }

    private void loadCooldowns(Player player) {
        UUID playerId = player.getUniqueId();
        Map<ItemStack, Long> playerCooldowns = cooldowns.get(playerId);

        if (playerCooldowns != null) {
            for (Map.Entry<ItemStack, Long> entry : playerCooldowns.entrySet()) {
                player.setCooldown(entry.getKey().getType(), Math.toIntExact(entry.getValue()));
            }
        }
    }

    @EventHandler
    private void onEnter(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        loadCooldowns(p);

        if (!p.hasPlayedBefore()) {
            {
                for (Player pl : Bukkit.getOnlinePlayers()) {
                    pl.playSound(pl.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 10, 1.2F);
                }
                p.playSound(p.getLocation(), Sound.ENTITY_WITHER_SPAWN, 10, 0.8F);
                p.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 100, 0, false, false));
                p.sendMessage(ChatColor.GOLD + "Bienvenido. Hardcore by NicoHaxz and Kechappu");
            }

            for (ItemStack material : p.getInventory().getContents()) {
                if (material != null && Utils.getCustomCooldown(material) != 0)
                    Utils.setCustomCooldown(p, material, Utils.getCustomCooldown(material));
            }
            Ranks.setRank(p);
            if (Boolean.parseBoolean(ConfigData.getConfigValue("mantenimiento", "false"))) {
                if (!p.hasPermission("twi.user.mantenimiento")) {
                    e.setJoinMessage(null);
                    p.kickPlayer(Utils.c("&c&lMantenimiento &8| &7El servidor se encuentra en mantenimiento"));
                }
            }
        }
    }
    /*     if (!p.isOp()) {
             if (p.getStatistic(Statistic.PLAY_ONE_MINUTE) / (20 * 3600) >= ConfigData.getDay() * 2) {
                 new BukkitRunnable() {
                     @Override
                     public void run() {
                         p.sendMessage(Utils.getPrefix() + Utils.c("&eHas jugado tu cuota diaria, felicidades. Tu tiempo de juego es de: &c&l"
                                 + p.getStatistic(Statistic.PLAY_ONE_MINUTE) / (20 * 3600) + "&eHr/s"));
                         p.sendMessage(Utils.getPrefix() + Utils.c("&cAhora habrás de jugar &4&l"
                                 + (ConfigData.getDay() + 1) + " &cHoras para poder entrar el siguiente día"));
                     }
                 }.runTaskLater(main.getInstance(), 20);
             } else {
                 BanList banList = Bukkit.getBanList(BanList.Type.NAME);
                 banList.addBan(p.getName(), Utils.ib(new java.awt.Color(255, 89, 0, 255), new Color(255, 7, 7, 255),
                         "Racha de juego no superada"), null, null);
                 p.kickPlayer("Has sido baneado permanentemente.\nMotivo: " + "No has jugado suficiente.");
             }
         }
     }
 } */
    @EventHandler
    private void onLeave(PlayerQuitEvent e) {
        saveCooldowns(e.getPlayer());

    }

    @EventHandler
    public void JoinRankMessage (PlayerJoinEvent e){
        Player player = e.getPlayer();
        e.setJoinMessage(Ranks.getPrefix(e.getPlayer()) + ChatColor.RED + player.getName() + ChatColor.GRAY + " Entro Al Servidor ");
    }
}

