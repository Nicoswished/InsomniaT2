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
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import plugin.nicohaxz.Utils.PDC;
import plugin.nicohaxz.Utils.Utils;

public class StormController implements Listener {
    public static JavaPlugin plugin;

    public static long timeLeftInSeconds = 0;
    public static long totalDuration = 0;

    public static BukkitRunnable countdownTask;

    public static World stormWorld;

    public static boolean pendingStart = false;
    public static long pendingDuration = 0;
    public static boolean pendingReload = false;

    public StormController(JavaPlugin plugin) {
        StormController.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }


    public static void startStorm(long duration, boolean reloadCause) {

        stormWorld = Bukkit.getWorld("world");

        if (stormWorld == null) return;

        stormWorld.setGameRule(GameRule.DO_DAYLIGHT_CYCLE, false);
        stormWorld.setTime(18000);

        if (reloadCause) {
            startCountdown();
            if (duration > PDC.getTormentMax()) {
                timeLeftInSeconds = 0;
                totalDuration = 1;
            } else {
                timeLeftInSeconds = duration;
                totalDuration = PDC.getTormentMax();
            }
            return;
        }

        if (!isStormActive()) {
            timeLeftInSeconds = duration;
            totalDuration = duration;
            startCountdown();
        } else {
            addTime(duration);
        }
    }


    public static synchronized void deferStart(long duration, boolean reloadCause) {
        if (!Bukkit.getOnlinePlayers().isEmpty()) {
            startStorm(duration, reloadCause);
            return;
        }

        pendingStart = true;
        pendingDuration = duration;
        pendingReload = reloadCause;
    }


    public static boolean isStormActive() {
        return countdownTask != null && !countdownTask.isCancelled();
    }



    public static synchronized void startCountdown() {

        if (countdownTask != null)
            countdownTask.cancel();

        countdownTask = new BukkitRunnable() {

            @Override
            public void run() {

                if (timeLeftInSeconds <= 0) {
                    stopStorm();
                    cancel();
                    return;
                }

                timeLeftInSeconds--;

                sendActionBarToPlayers();
            }
        };

        countdownTask.runTaskTimer(plugin, 0L, 20L);
    }




    public static void sendActionBarToPlayers() {

        String bar = IridiumColorAPI.process(
                "<GRADIENT:06def4>&lFreeze Moon </GRADIENT:09909e>" +
                        "<GRADIENT:3aa2f4>" + Timer((int) timeLeftInSeconds) + "</GRADIENT:0890fa>"
        );

        for (Player p : Bukkit.getOnlinePlayers()) {
            p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 40, 0));
            p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(bar));
        }
    }




    public static void stopStorm() {

        if (stormWorld != null) {
            stormWorld.setGameRule(GameRule.DO_DAYLIGHT_CYCLE, true);
            stormWorld.setTime(0);
        }

        if (countdownTask != null) {
            countdownTask.cancel();
            countdownTask = null;
        }

        for (Player p : Bukkit.getOnlinePlayers()) {
            p.playSound(p.getLocation(), Sound.ENTITY_LIGHTNING_BOLT_THUNDER, 2f, 0.5f);
            p.sendMessage(Utils.c("<GRADIENT:06def4>&lFreeze Moon Finalizada</GRADIENT:09909e>"));
        }
    }



    public static synchronized void addTime(long extraSeconds) {
        timeLeftInSeconds += extraSeconds;
        totalDuration += extraSeconds;
    }


    public static synchronized long removeTime(long seconds) {
        timeLeftInSeconds = Math.max(0, timeLeftInSeconds - seconds);
        totalDuration = Math.max(timeLeftInSeconds, totalDuration - seconds);

        if (timeLeftInSeconds <= 0)
            stopStorm();

        return timeLeftInSeconds;
    }


    public static synchronized void setTime(long seconds) {
        timeLeftInSeconds = Math.max(0, seconds);
        totalDuration = Math.max(timeLeftInSeconds, totalDuration);

        if (timeLeftInSeconds <= 0)
            stopStorm();
    }


    public static long getTimeLeft() {
        return timeLeftInSeconds;
    }


    @EventHandler
    public void onJoin(PlayerJoinEvent e) {

        if (pendingStart) {
            pendingStart = false;
            startStorm(pendingDuration, pendingReload);
        }

        if (isStormActive()) {
            e.getPlayer().spigot().sendMessage(
                    ChatMessageType.ACTION_BAR,
                    TextComponent.fromLegacyText(Utils.c("<GRADIENT:06def4>&lFreeze Moon Activa</GRADIENT:09909e>"))
            );
        }
    }


    @EventHandler
    public void onDeath(PlayerDeathEvent e) {

        Bukkit.getScheduler().runTaskLater(plugin, () -> {

            startStorm(1200, false);

            for (Player p : Bukkit.getOnlinePlayers()) {

                p.playSound(p.getLocation(), Sound.ENTITY_ELDER_GUARDIAN_CURSE, 2f, 0.5f);
                p.sendTitle(
                        Utils.c("<GRADIENT:06def4>&lFreeze Moon</GRADIENT:09909e>"),
                        Utils.c("&7Duración: &b" + Timer((int) timeLeftInSeconds)),
                        20, 100, 40
                );

            }

        }, 400L);

    }

    public static String Timer(int t) {

        int h = (t % (3600 * 24)) / 3600;
        int m = (t % 3600) / 60;
        int s = t % 60;

        return String.format("%02d:%02d:%02d", h, m, s);
    }

}