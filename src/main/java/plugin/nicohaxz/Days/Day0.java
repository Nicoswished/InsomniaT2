package plugin.nicohaxz.Days;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedEnterEvent;
import plugin.nicohaxz.Utils.Utils;

public class Day0 implements Listener {

    @EventHandler
    public void sleep (PlayerBedEnterEvent e) {
        Utils.onDay(0, null, () -> {
            Player p = e.getPlayer();
            p.sendMessage(Utils.c(Utils.getPrefix() + "<GRADIENT:f70a0a>&l ¡No puedes dormir! :)</GRADIENT:c20606>"));
            e.setCancelled(true);
        });
    }
}
