package plugin.nicohaxz.GameEvent;

import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import plugin.nicohaxz.Controller.DeathController;
import plugin.nicohaxz.Utils.PlayerData;
import plugin.nicohaxz.Utils.Utils;
import plugin.nicohaxz.main;

import java.util.Objects;

public class DeathEvent implements Listener {
    
    @EventHandler
    public void GameDeathEvent(PlayerDeathEvent e) {
        Player player = e.getEntity();
        for (Player pl : Bukkit.getOnlinePlayers()) {
            player.setHealth(20);
            player.setSaturation(20);
            player.setGameMode(GameMode.SPECTATOR);
            DeathController.startAnimationDeath(player, PlayerData.getAnimation(player), pl);
            e.setDeathMessage(null);
        }
    }

    @EventHandler
    public void DeathMessage(PlayerDeathEvent event) {
        Player p = event.getEntity();

        for (Player pl : Bukkit.getOnlinePlayers()) {

            pl.playSound(p.getLocation(), Sound.AMBIENT_CAVE, 1.0F, 0.6F);

            pl.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 100, 0));
            pl.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 200, 0));

            pl.sendMessage(Utils.chatcolor(
                    "<GRADIENT:3a7bd5:00d4ff>&l⚠ Un alma ha caído en las tierras heladas...</GRADIENT:3a7bd5>"));

            pl.sendMessage(Utils.chatcolor("&7&lCausa: &b" +
                    Utils.getCustomCause(Objects.requireNonNull(p.getLastDamageCause()), "<SOLID:00d4ff>")));

            pl.sendMessage(Utils.chatcolor("&7&lÚltimas palabras:"));
            pl.sendMessage(Utils.chatcolor("&7&o\"" + PlayerData.getDeathMessage(p) + "\""));

            pl.sendMessage(Utils.chatcolor("&7&lÚltima posición: &b" +
                    Utils.getLocationString(p.getLocation())));

            pl.sendMessage(Utils.chatcolor(
                    "<GRADIENT:3a7bd5:00d4ff>&l────────────────────────────</GRADIENT:00d4ff>"));
        }
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        if (event.getEntity().getLastDamageCause() != null &&
                event.getEntity().getLastDamageCause().getCause() == org.bukkit.event.entity.EntityDamageEvent.DamageCause.VOID) {

            World overworld = Bukkit.getWorld("world");
            if (overworld != null) {
                Location spawnLocation = overworld.getSpawnLocation();

                Bukkit.getScheduler().runTaskLater(main.getInstance(), () -> {
                    event.getEntity().teleport(spawnLocation);
                }, 1L);
            }
        }
    }

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent event) {
        if (event.getPlayer().getLastDamageCause() != null &&
                event.getPlayer().getLastDamageCause().getCause() == org.bukkit.event.entity.EntityDamageEvent.DamageCause.VOID) {
            World overworld = Bukkit.getWorld("world");
            if (overworld != null) {
                Location spawnLocation = overworld.getSpawnLocation();
                event.setRespawnLocation(spawnLocation);
            }
        }
    }
}
