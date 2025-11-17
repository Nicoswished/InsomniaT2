package plugin.nicohaxz.Days;

import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.*;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import plugin.nicohaxz.Utils.Utils;
import plugin.nicohaxz.main;

import java.util.HashMap;
import java.util.Random;

public class Day15 implements Listener {
    private final JavaPlugin plugin;

    public Day15(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    private final HashMap<Player, String> activeChallenges = new HashMap<>();
    private final Random random = new Random();
    private final HashMap<Player, Boolean> titleVisible = new HashMap<>();

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        startTimer(player);
    }

    private void startTimer(Player player) {
        new BukkitRunnable() {
            int countdown = 30;

            @Override
            public void run() {
                if (countdown > 0) {
                    countdown--;
                } else {
                    this.cancel();
                    if (random.nextInt(2) == 0) {
                        Challenge(player);
                    } else {
                        player.sendMessage(ChatColor.YELLOW + "¡El Anuncio se saturo, más cuidado para la proxima!");
                        startTimer(player);
                    }
                }
            }
        }.runTaskTimer(plugin, 0, 20);
    }

    private void Challenge(Player player) {
        char randomChar1 = (char) ('A' + random.nextInt(26));
        char randomChar2 = (char) ('A' + random.nextInt(26));
        String challenge = "" + randomChar1 + randomChar2;

        activeChallenges.put(player, challenge);
        titleVisible.put(player, true);

        // Mostrar el título
        new BukkitRunnable() {
            @Override
            public void run() {
                if (!activeChallenges.containsKey(player)) {
                    this.cancel();
                    titleVisible.put(player, false);
                    startTimer(player);
                    return;
                }
                player.sendTitle(
                        ChatColor.RED + "¡Anuncio!",
                        "Escribe estas letras: " + challenge,
                        0,
                        20,
                        0
                );
            }
        }.runTaskTimer(plugin, 0, 20);

        new BukkitRunnable() {
            @Override
            public void run() {
                anuncioslolxd(player);
            }
        }.runTaskLater(plugin, 20);
    }

    private void anuncioslolxd(Player player) {
        new BukkitRunnable() {
            @Override
            public void run() {
                if (!activeChallenges.containsKey(player)) {
                    this.cancel();
                    return;
                }
                player.playSound(player.getLocation(), "minecraft:insomnia.anuncios", 1.0F, 1.0F);
            }
        }.runTaskTimer(plugin, 0, 380L);
    }
    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        if (activeChallenges.containsKey(player) && titleVisible.getOrDefault(player, false)) {
            String expected = activeChallenges.get(player);
            if (event.getMessage().equalsIgnoreCase(expected)) {
                activeChallenges.remove(player);
                titleVisible.put(player, false);
                player.sendMessage(ChatColor.GREEN + "¡Correcto! Reiniciando el ciclo...");
                player.stopSound("minecraft:insomnia.anuncios");
            } else {
                player.sendMessage(ChatColor.RED + "¡Incorrecto! Intenta de nuevo.");
            }
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onMagmaCubeDeath(EntityDeathEvent event) {
        Utils.onDay(15, null, () -> {
            Entity entity = event.getEntity();
            if (entity instanceof MagmaCube) {
                Location location = entity.getLocation();
                location.getWorld().spawnFallingBlock(location, Material.LAVA, (byte) 0);
            }
        });
    }

    @EventHandler
    public void onShieldExplosionDamage(EntityDamageEvent event) {
        Utils.onDay(15, null, () -> {
            if (event.getEntity() instanceof Player player) {
                if (event.getCause() == EntityDamageEvent.DamageCause.ENTITY_EXPLOSION ||
                        event.getCause() == EntityDamageEvent.DamageCause.BLOCK_EXPLOSION) {
                    ItemStack offHandItem = player.getInventory().getItemInOffHand();
                    if (offHandItem.getType() == Material.SHIELD) {
                        player.getInventory().setItemInOffHand(null);
                        player.playSound(player.getLocation(), Sound.ITEM_SHIELD_BREAK, 1.0f, 1.0f);
                        player.sendMessage(ChatColor.RED + "¡Tu escudo se rompió por la explosión!");
                    }
                }
            }
        });
    }
    @EventHandler
    public void onCreeperDeath(EntityDeathEvent event) {
        Utils.onDay(15, null, () -> {
            if (event.getEntity() instanceof Creeper) {
                if (random.nextInt(100) < 5) {
                    Bukkit.getScheduler().runTaskLater(main.getInstance(), () -> {
                        Location location = event.getEntity().getLocation();
                        location.getWorld().spawnEntity(location, EntityType.CREEPER);
                    },20L);
                }
            }
        });
    }
}
//            pl.playSound(p.getLocation(), "minecraft:insomnia.death", 1.0F, 1.0F);
