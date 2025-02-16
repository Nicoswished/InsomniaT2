package plugin.nicohaxz.Items.Function;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityResurrectEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import plugin.nicohaxz.Utils.Utils;

public class GlobalItemFuntion implements Listener {

    @EventHandler
    public void Interact(PlayerInteractEvent event) {
        Player p = event.getPlayer();
        if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK) || event.getAction().equals(Action.RIGHT_CLICK_AIR)) {
            ItemStack mainhand = p.getInventory().getItemInMainHand();
            if (mainhand.getType() == Material.YELLOW_DYE) {
                if (p.getCooldown(Material.YELLOW_DYE) == 0) {
                    if (mainhand.hasItemMeta()) {
                        if (mainhand.getItemMeta().hasCustomModelData()) {
                            int cmd = mainhand.getItemMeta().getCustomModelData();
                            if (cmd == 1011) {
                                p.sendMessage(Utils.getPrefix() + Utils.c("&7¡&Usaste el Helado de pesadillas !"));
                                p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 100, 0));
                                p.playSound(p, Sound.BLOCK_NOTE_BLOCK_PLING, 100, 0);
                                p.setNoDamageTicks(80);
                                p.setMaximumNoDamageTicks(80);
                                p.setCooldown(Material.STICK, 5000);
                            } else {
                                p.sendMessage(Utils.getPrefix() + Utils.c("&7¡&lAun tienes cooldown!"));
                            }
                        }

                    }
                }
            }
        }
    }
}
