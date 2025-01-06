package plugin.nicohaxz.GameEvent;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityResurrectEvent;
import plugin.nicohaxz.Utils.Utils;

public class TotemListener implements Listener {
        // Lo hice asi bien lol xdddddd
    @EventHandler
    public void TotemGlobal(EntityResurrectEvent e) {
        if (!(e.getEntity() instanceof Player p)) return;
        if (p.getInventory().getItemInMainHand().getType() == Material.TOTEM_OF_UNDYING || p.getInventory().getItemInOffHand().getType() == Material.TOTEM_OF_UNDYING) {
            for (Player pl : Bukkit.getOnlinePlayers()) {
                pl.sendMessage(Utils.c(ChatColor.GOLD + p.getName() + ChatColor.GRAY + " Uso un Tótem &e&l"));
            }
        }
    }
}
