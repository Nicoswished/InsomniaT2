package plugin.nicohaxz.Controller;

import com.iridium.iridiumcolorapi.IridiumColorAPI;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.GameRule;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import plugin.nicohaxz.Utils.StormUtils;
import plugin.nicohaxz.Utils.Utils;
import plugin.nicohaxz.main;

public class StormController implements Listener {
    private int remainingTimeInSeconds;
    private final long interval = 12000;
    private long maxDuration = 0;
    private final main main;
    public StormController(main main) {
        this.main = main;
    }

    @EventHandler
    public void DeathEvent(PlayerDeathEvent event) {
        Bukkit.getScheduler().runTaskLater(plugin.nicohaxz.main.getInstance(), () -> {
            for (Player pl : Bukkit.getOnlinePlayers()) {
                pl.playSound(pl.getLocation(), Sound.AMBIENT_CAVE, 100, 0);
                TormentaCountdown(pl, false);
                int maxValue = StormUtils.getMaxValue(pl);
                int duration = StormUtils.getDuration(pl);
                pl.sendTitle(Utils.c("<GRADIENT:06def4>&lFreeze Moon</GRADIENT:09909e>"),
                        Utils.c("&5&l Duración: " + duration), 100, 20, 100);

                System.out.println("Valor máximo de la tormenta: " + maxValue);
            }
        }, 180L);
    }

    @EventHandler
    public void UHCEvent(PlayerDeathEvent e) {
        Utils.onDay(10, null, () -> {
            Bukkit.getScheduler().runTaskLater(plugin.nicohaxz.main.getInstance(), () -> {
                for (Player pl : Bukkit.getOnlinePlayers()) {
                    pl.getWorld().setGameRule(GameRule.NATURAL_REGENERATION, false);
                    pl.sendMessage(Utils.c("<GRADIENT:06def4>MODO UHC ACTIVADO</GRADIENT:09909e>"));
                }
            }, 180L);
        });
    }

    public void TormentaCountdown(Player pl, Boolean isReloadCause) {
        pl.getWorld().setGameRule(GameRule.DO_DAYLIGHT_CYCLE, false);
        setMoonToMidnight(pl.getWorld());

        if (isReloadCause) {
            if (remainingTimeInSeconds > 0) {
                startCountdown();
            }
        } else {
            if (remainingTimeInSeconds == 0) {
                remainingTimeInSeconds = (int) interval;
                maxDuration = interval;
                StormUtils.setDuration(pl, remainingTimeInSeconds);
                StormUtils.setMaxValue(pl, (int) maxDuration);
                startCountdown();
            } else {
                remainingTimeInSeconds += 2000;
                maxDuration += 2000;
                StormUtils.setDuration(pl, remainingTimeInSeconds);
                StormUtils.setMaxValue(pl, (int) maxDuration);
            }
        }
    }

    private void setMoonToMidnight(World world) {
        BukkitRunnable task = new BukkitRunnable() {
            final long targetTime = 18000;
            final long steps = 6 * 20;
            long increment = (targetTime - world.getTime()) / steps;
            int counter = 0;

            @Override
            public void run() {
                if (counter >= steps || world.getTime() == targetTime) {
                    world.setTime(targetTime);
                    cancel();
                } else {
                    world.setTime(world.getTime() + increment);
                    counter++;
                }
            }
        };
        task.runTaskTimer(plugin.nicohaxz.main.getInstance(), 20L, 1L);
    }

    public void startCountdown() {
        BukkitRunnable task = new BukkitRunnable() {
            @Override
            public void run() {
                if (remainingTimeInSeconds <= 0) {
                    World world = Bukkit.getWorld("world");
                    if (world != null) {
                        world.setGameRule(GameRule.DO_DAYLIGHT_CYCLE, true);
                    }
                    for (Player pl : Bukkit.getOnlinePlayers()) {
                        pl.sendMessage(Utils.c("<GRADIENT:06def4>&lFreeze Moon ha terminado</GRADIENT:09909e>"));
                        pl.getWorld().setGameRule(GameRule.NATURAL_REGENERATION, true);
                        StormUtils.setDuration(pl, 0);
                        StormUtils.setMaxValue(pl, 0);
                    }

                    remainingTimeInSeconds = 0;
                    maxDuration = 0;
                    cancel();
                } else {
                    for (Player pl : Bukkit.getOnlinePlayers()) {
                        String mensaje = IridiumColorAPI.process("<GRADIENT:06def4>&lFreeze Moon: </GRADIENT:09909e>"
                                + "<GRADIENT:3aa2f4>" + Timer(remainingTimeInSeconds) + "</GRADIENT:0890fa>");
                        pl.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 20, 0));
                        pl.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(mensaje));
                    }
                    remainingTimeInSeconds--;
                }
            }
        };
        task.runTaskTimer(plugin.nicohaxz.main.getInstance(), 20L, 20L);
    }

    @EventHandler
    public void JoinEvent(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (StormUtils.getDuration(player) > 0) {
            player.spigot().sendMessage(ChatMessageType.ACTION_BAR,
                    TextComponent.fromLegacyText(Utils.c("<GRADIENT:06def4>&lFreeze Moon Activa</GRADIENT:09909e>")));
        }
    }

    public String Timer(int t) {
        int num = t, day, hor, min, seg;
        day = num / (3600 * 24);
        hor = (num % (3600 * 24)) / 3600;
        min = (num % 3600) / 60;
        seg = num % 60;

        return String.format("%02d:%02d:%02d:%02d", day, hor, min, seg);
    }

    public int getRemainingTimeInSeconds() {
        return remainingTimeInSeconds;
    }

    public void setRemainingTimeInSeconds(int remainingTimeInSeconds) {
        this.remainingTimeInSeconds = remainingTimeInSeconds;
    }
}