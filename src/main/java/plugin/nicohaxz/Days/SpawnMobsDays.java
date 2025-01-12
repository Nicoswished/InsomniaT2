package plugin.nicohaxz.Days;

import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import plugin.nicohaxz.Mobs.Principales.Creepers;
import plugin.nicohaxz.Utils.Utils;

public class SpawnMobsDays implements Listener {

    //NO TOCAR spawn de los mobs normales y custom + modificados PURO NATA A LA VRG


    @EventHandler
    public void onSpawn(CreatureSpawnEvent e) {
        Utils.onDay(15, null, () -> {
            if (e.getEntity().getWorld().getName().equals("world")) {
                if (e.getEntity().getType() == EntityType.CREEPER) {
                    Creeper creeper = (Creeper) e.getEntity();
                    if (!creeper.getScoreboardTags().contains("processed")) {
                        creeper.addScoreboardTag("processed");
                        Creeper miniCreeper = (Creeper) e.getEntity().getWorld().spawnEntity(creeper.getLocation(), EntityType.CREEPER);
                        Creepers.MiniCreeper(miniCreeper);
                        e.getEntity().remove();
                    }
                }
            } else {
                e.setCancelled(true);
            }
        });
    }
}
