package plugin.nicohaxz.Days;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;
import plugin.nicohaxz.Utils.Utils;
import plugin.nicohaxz.main;

public class MobCapDay10 implements Listener {
    @EventHandler
    private void onWorldLoad(org.bukkit.event.world.WorldLoadEvent e) {
        World w = Bukkit.getWorld("world");
        w.setMonsterSpawnLimit(70);
        Utils.onDay(0, 1, () -> {
            new BukkitRunnable() {

                @Override
                public void run() {
                    World w = Bukkit.getWorld("world");
                    w.setMonsterSpawnLimit(w.getMonsterSpawnLimit() / 2);
                }
            }.runTaskLater(main.getInstance(), 100L);
        });
        Utils.onDay(2, 6, () -> {
            new BukkitRunnable() {

                @Override
                public void run() {
                    World w = Bukkit.getWorld("world");
                    w.setMonsterSpawnLimit(w.getMonsterSpawnLimit() * 4);
                }
            }.runTaskLater(main.getInstance(), 100L);
        });

        Utils.onDay(7, null, () -> {
            new BukkitRunnable() {

                @Override
                public void run() {
                    World w = Bukkit.getWorld("world");
                    w.setMonsterSpawnLimit(w.getMonsterSpawnLimit() * 2);
                }
            }.runTaskLater(main.getInstance(), 100L);
        });
    }
}

