package plugin.nicohaxz.Days;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import plugin.nicohaxz.Utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class Day6 implements Listener {
    @EventHandler
    public void onLavaDamage(EntityDamageEvent event) {
        Utils.onDay(6, null, () -> {
            if (event.getCause() == EntityDamageEvent.DamageCause.LAVA) {
                event.setDamage(event.getDamage() * 2);
            }
        });
    }
    @EventHandler
    public void onIronGolemDamage(EntityDamageByEntityEvent event) {
        Utils.onDay(6, null, () -> {
            if (event.getEntity() instanceof IronGolem) {
                IronGolem golem = (IronGolem) event.getEntity();
                golem.setTarget(event.getDamager() instanceof Player ? (LivingEntity) event.getDamager() : null);
                for (Block block : getAdjacentBlocks(golem.getLocation())) {
                    block.breakNaturally();
                }
            }
        });
    }

    private List<Block> getAdjacentBlocks(Location location) {
        List<Block> adjacentBlocks = new ArrayList<>();
        World world = location.getWorld();
        for (int x = -1; x <= 1; x++) {
            for (int y = -1; y <= 1; y++) {
                for (int z = -1; z <= 1; z++) {
                    adjacentBlocks.add(world.getBlockAt(location.clone().add(x, y, z)));
                }
            }
        }
        return adjacentBlocks;
    }
    @EventHandler
    public void onCreeperSpawn(EntitySpawnEvent event) {
        Utils.onDay(6, null, () -> {
            if (event.getEntity() instanceof Creeper) {
                Creeper creeper = (Creeper) event.getEntity();
                creeper.setPowered(true);
            }
        });
    }
    @EventHandler
    public void onExplosionDamage(EntityDamageEvent event) {
        Utils.onDay(6, null, () -> {
            if (event.getCause() == EntityDamageEvent.DamageCause.ENTITY_EXPLOSION) {
                event.setDamage(event.getDamage() * 1.5);
            }
        });
    }
    @EventHandler
    public void onSkeletonSpawn(EntitySpawnEvent event) {
        Utils.onDay(6, null, () -> {
            if (event.getEntity() instanceof Skeleton) {
                Skeleton skeleton = (Skeleton) event.getEntity();
                Spider spider = (Spider) skeleton.getWorld().spawnEntity(skeleton.getLocation(), EntityType.SPIDER);
                skeleton.setPassenger(spider);

                ItemStack bow = new ItemStack(Material.BOW);
                ItemMeta meta = bow.getItemMeta();
                if (meta != null) {
                    meta.addEnchant(Enchantment.KNOCKBACK, 10, true);
                    bow.setItemMeta(meta);
                }
                skeleton.getEquipment().setItemInMainHand(bow);
            }
        });
    }


    @EventHandler
    public void onWardenDamage(EntityDamageByEntityEvent event) {
        Utils.onDay(6, null, () -> {
            if (event.getEntity() instanceof Warden) {
                Warden warden = (Warden) event.getEntity();
                event.setCancelled(true);

                if (event.getDamager() instanceof Player) {
                    Player player = (Player) event.getDamager();
                    player.damage(1000);
                }
            }
        });
    }
    @EventHandler
    public void onAnimalHit(EntityDamageByEntityEvent event) {
        Utils.onDay(6, null, () -> {
            if (event.getEntity() instanceof Animals) {
                Animals animal = (Animals) event.getEntity();
                animal.getWorld().createExplosion(animal.getLocation(), 4.0f);
            }
        });
    }
}
