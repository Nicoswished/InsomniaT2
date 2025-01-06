package plugin.nicohaxz.Days;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Blaze;
import org.bukkit.entity.Ghast;
import org.bukkit.entity.PigZombie;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import plugin.nicohaxz.Utils.Utils;


public class Day5 implements Listener {
    @EventHandler
    public void onGoldenCarrotEat(PlayerItemConsumeEvent event) {
        Utils.onDay(5, null, () -> {

            if (event.getItem().getType() == Material.GOLDEN_CARROT) {
                event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 200, 0)); // 200 ticks = 10 segundos
            }
        });
    }

    @EventHandler
    public void onShieldUse(PlayerInteractEvent event) {
        Utils.onDay(5, null, () -> {
            if (event.getItem() != null && event.getItem().getType() == Material.SHIELD) {
                event.getItem().removeEnchantment(Enchantment.DURABILITY);
                event.getItem().removeEnchantment(Enchantment.MENDING);
            }
        });
    }

    @EventHandler
    public void onPigmanSpawn(EntitySpawnEvent event) {
        Utils.onDay(5, null, () -> {
            if (event.getEntity() instanceof PigZombie) {
                PigZombie pigman = (PigZombie) event.getEntity();

                pigman.getEquipment().setHelmet(new ItemStack(Material.GOLDEN_HELMET));
                pigman.getEquipment().setChestplate(new ItemStack(Material.GOLDEN_CHESTPLATE));
                pigman.getEquipment().setLeggings(new ItemStack(Material.GOLDEN_LEGGINGS));
                pigman.getEquipment().setBoots(new ItemStack(Material.GOLDEN_BOOTS));

                pigman.getEquipment().getHelmet().addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 5);
                pigman.getEquipment().getChestplate().addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 5);
                pigman.getEquipment().getLeggings().addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 5);
                pigman.getEquipment().getBoots().addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 5);

                ItemStack sword = new ItemStack(Material.GOLDEN_SWORD);
                sword.addEnchantment(Enchantment.FIRE_ASPECT, 10);
                pigman.getEquipment().setItemInMainHand(sword);
            }
        });
    }

    @EventHandler
    public void onGhastDamage(EntityDamageEvent event) {
        Utils.onDay(5, null, () -> {

            if (event.getEntity() instanceof Ghast) {
                event.setCancelled(true);

                if (Math.random() < 0.01) {
                    Ghast ghast = (Ghast) event.getEntity();
                    ghast.getWorld().dropItemNaturally(ghast.getLocation(), new ItemStack(Material.GHAST_TEAR));
                }
            }
        });
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Utils.onDay(5, null, () -> {
            Block block = event.getBlock();

            if (block.getType() == Material.NETHERRACK) {
                event.getPlayer().setFireTicks(100);

                if (Math.random() < 0.05) {
                    block.getWorld().spawnFallingBlock(block.getLocation(), Material.LAVA.createBlockData());
                }
            }
        });
    }

    @EventHandler
    public void onBlazeAttack(EntityDamageByEntityEvent event) {
        Utils.onDay(5, null, () -> {
            if (event.getDamager() instanceof Blaze && event.getEntity() instanceof Player) {
                Player player = (Player) event.getEntity();
                player.getWorld().getBlockAt(player.getLocation()).setType(Material.LAVA);
            }
        });
    }

    @EventHandler
    public void onBlockBrea1k(BlockBreakEvent event) {
        Utils.onDay(5, null, () -> {
            Block block = event.getBlock();
            if (block.getType() == Material.NETHERRACK) {
                if (Math.random() < 0.05) {
                    event.getPlayer().setFireTicks(100);
                }
            }
        });
    }
}
