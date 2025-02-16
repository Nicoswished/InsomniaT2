package plugin.nicohaxz.Utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import plugin.nicohaxz.main;

import static plugin.nicohaxz.Utils.ConfigData.getDay;

public class Task {
    long ticks;
    private main plugin;
    public Task(main plugin, long ticks) {
        this.plugin = plugin;
        this.ticks = ticks;
    }

    public void tablistTask() {

        new BukkitRunnable() {
            @Override
            public void run() {
                for (Player pl : Bukkit.getOnlinePlayers()) {
                    int c = Bukkit.getMaxPlayers();
                    int c1 = Bukkit.getOnlinePlayers().size();
                    String t = Ranks.getPrefix(pl);
                    pl.setPlayerListHeaderFooter(
                            Utils.c("<GRADIENT:ed3619>&l>≪ ≪ ◦•❖•◦ ≫ ≫ ≪•◦ ✦ ◦•≫ ≪ ≪</GRADIENT:6f6564>\n"
                                    + Utils.getPrefix()),
                            Utils.c(
                                    "<SOLID:35ed4e>Jugadores Conectados: &7" + c1 + " / " + c + "\n"
                                            + "<SOLID:35ed4e>Ping: &7" + pl.getPing() + "ms\n"
                                            + "<SOLID:35ed4e>Día Actual: &6" + getDay() + "\n" + "<GRADIENT:ed3619>&l≪ ≪ ◦•❖•◦ ≫ ≫ ≪•◦ ✦ ◦•≫ ≪ ≪</GRADIENT:6f6564>"
                            )
                    );
                }
            }
        }.runTaskTimer(main.getPlugin(main.class), 0, 20L);
    }
}
