package plugin.nicohaxz.Days;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.generator.BiomeProvider;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginLoader;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import plugin.nicohaxz.Utils.Utils;

import java.io.File;
import java.io.InputStream;
import java.util.List;
import java.util.logging.Logger;

public class Day9 implements Listener, Plugin {
    @EventHandler
    public void onPlayerInteract(PlayerInteractEntityEvent event) {
        Utils.onDay(9, null, () -> {
            if (event.getRightClicked() instanceof Cow) {
                event.setCancelled(true);
                event.getPlayer().sendMessage(ChatColor.RED + "No puedes ordeñar vacas.");
            }
        });
    }
    @EventHandler
    public void onSkeletonShoot(EntityShootBowEvent event) {
        Utils.onDay(9, null, () -> {
            if (event.getEntity() instanceof Skeleton) {
                event.getProjectile().setMetadata("PoisonArrow", new FixedMetadataValue(this, false));
            }
        });
    }
    @EventHandler
    public void onCreatureSpawn(CreatureSpawnEvent event) {
        Utils.onDay(9, null, () -> {
            if (event.getEntityType() == EntityType.BLAZE) {
                if (!event.getLocation().getWorld().getEnvironment().equals(World.Environment.NORMAL)) {
                    return;
                }

                Location loc = event.getLocation();
                if (loc.getBlock().getLightFromBlocks() < 8) {
                    loc.getWorld().spawnEntity(loc, EntityType.BLAZE);
                }
            }
        });
    }
    @EventHandler
    public void onPiglinSpawn(CreatureSpawnEvent event) {
        Utils.onDay(9, null, () -> {
            if (event.getEntityType() == EntityType.PIGLIN) {
                Piglin piglin = (Piglin) event.getEntity();
                piglin.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, Integer.MAX_VALUE, 0, false, false));
            }
        });
    }


    // NO TOCAR ES LA DATA DE LAS FLECHAS

    @Override
    public @NotNull File getDataFolder() {
        return null;
    }

    @Override
    public @NotNull PluginDescriptionFile getDescription() {
        return null;
    }

    @Override
    public @NotNull FileConfiguration getConfig() {
        return null;
    }

    @Override
    public @Nullable InputStream getResource(@NotNull String s) {
        return null;
    }

    @Override
    public void saveConfig() {

    }

    @Override
    public void saveDefaultConfig() {

    }

    @Override
    public void saveResource(@NotNull String s, boolean b) {

    }

    @Override
    public void reloadConfig() {

    }

    @Override
    public @NotNull PluginLoader getPluginLoader() {
        return null;
    }

    @Override
    public @NotNull Server getServer() {
        return null;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    @Override
    public void onDisable() {

    }

    @Override
    public void onLoad() {

    }

    @Override
    public void onEnable() {

    }

    @Override
    public boolean isNaggable() {
        return false;
    }

    @Override
    public void setNaggable(boolean b) {

    }

    @Override
    public @Nullable ChunkGenerator getDefaultWorldGenerator(@NotNull String s, @Nullable String s1) {
        return null;
    }

    @Override
    public @Nullable BiomeProvider getDefaultBiomeProvider(@NotNull String s, @Nullable String s1) {
        return null;
    }

    @Override
    public @NotNull Logger getLogger() {
        return null;
    }

    @Override
    public @NotNull String getName() {
        return "";
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        return false;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        return List.of();
    }
}
