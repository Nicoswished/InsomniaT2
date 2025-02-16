package plugin.nicohaxz.GameEvent;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import plugin.nicohaxz.Utils.Ranks;

public class ChatEvent implements Listener {
    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        String rankPrefix = Ranks.getPrefix(player);
        String format = ChatColor.translateAlternateColorCodes('&', rankPrefix)
                + ChatColor.GRAY + player.getName() + ": " + event.getMessage();
        event.setFormat(format);
    }
}
