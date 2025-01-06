package plugin.nicohaxz.Days;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import plugin.nicohaxz.Utils.Utils;

import java.util.Random;

public class Day8 implements Listener {
    @EventHandler
    public void onPlayerDamage(EntityDamageEvent event) {
        Utils.onDay(7, null, () -> {
            if (event.getEntity() instanceof Player) {
                event.setDamage(event.getDamage() * 2);
            }
        });
    }

    @EventHandler
    public void onBlockBreak(PlayerInteractEvent event) {
        Utils.onDay(7, null, () -> {
            if (event.getAction().toString().contains("BREAK")) {
                if (event.getClickedBlock() != null) {
                    Material blockType = event.getClickedBlock().getType();

                    if (blockType == Material.IRON_ORE || blockType == Material.GOLD_ORE || blockType == Material.DIAMOND_ORE
                            || blockType == Material.EMERALD_ORE || blockType == Material.LAPIS_ORE || blockType == Material.REDSTONE_ORE) {

                        Random rand = new Random();
                        if (rand.nextInt(100) < 10) {
                            ItemStack blockItem = new ItemStack(blockType);
                            event.getPlayer().getInventory().addItem(blockItem);
                        }
                    }
                }
            }
        });
    }
}
