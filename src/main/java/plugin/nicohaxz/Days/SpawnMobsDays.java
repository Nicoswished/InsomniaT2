package plugin.nicohaxz.Days;

import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import plugin.nicohaxz.Mobs.Principales.Creepers;
import plugin.nicohaxz.Utils.Utils;

public class SpawnMobsDays implements Listener {

    //NO TOCAR spawn de los mobs normales y custom + modificados

    @EventHandler
    public void onSpawn(CreatureSpawnEvent e) {

        if (!e.getEntity().getWorld().getName().equals("world")) {
            e.setCancelled(true);
            return;
        }

        if (e.getEntityType() != EntityType.CREEPER) {
            return;
        }

        Utils.onDay(19, null, () -> {
            return;
        });

        Creeper creeper = (Creeper) e.getEntity();

        if (creeper.getScoreboardTags().contains("processed")) {
            return;
        }

        creeper.addScoreboardTag("processed");

        Creeper miniCreeper = (Creeper) creeper.getWorld().spawnEntity(
                creeper.getLocation(),
                EntityType.CREEPER
        );

        miniCreeper.addScoreboardTag("processed");

        Creepers.MiniCreeper(miniCreeper);

        // 6. Remover el creeper original
        creeper.remove();
    }
}