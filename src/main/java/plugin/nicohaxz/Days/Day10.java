package plugin.nicohaxz.Days;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.SoundCategory;
import org.bukkit.block.data.type.TNT;
import org.bukkit.entity.Animals;
import org.bukkit.entity.Enderman;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import plugin.nicohaxz.Utils.Utils;
import plugin.nicohaxz.main;

import java.util.HashMap;
import java.util.Random;

public class Day10 implements Listener {
    private static final int DROWNING_TIME_MULTIPLIER = 6;
    private final JavaPlugin plugin;

    public Day10(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    private final HashMap<Player, Character> activeChallenges = new HashMap<>();
    private final Random random = new Random();
    private final HashMap<Player, Boolean> titleVisible = new HashMap<>();

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Utils.onDay(10, 14, () -> {
            Player player = event.getPlayer();
            startTimer(player);
        });
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
                    if (random.nextInt(6) == 0) {
                        showChallenge(player);
                    } else {
                        player.sendMessage(ChatColor.YELLOW + "El Anuncio se saturo.");
                        startTimer(player);
                    }
                }
            }
        }.runTaskTimer(plugin, 0, 20);
    }

    private void showChallenge(Player player) {
        char randomChar1 = (char) ('A' + random.nextInt(26));
        String challenge = "" + randomChar1;
        titleVisible.put(player, true);

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
                        500,
                        500
                );
            }
        }.runTaskTimer(plugin, 0, 20);
        new BukkitRunnable(){
            @Override
            public void run(){
                player.playSound(player.getLocation(), "minecraft:insomnia.anuncios", 1.0F, 1.0F);
            }
        }.runTaskTimer(main.getInstance(),0,380L);
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        Utils.onDay(10, null, () -> {
            Player player = event.getPlayer();
            if (activeChallenges.containsKey(player) && titleVisible.getOrDefault(player, false)) {
                char expectedChar = activeChallenges.get(player);
                if (event.getMessage().equalsIgnoreCase(String.valueOf(expectedChar))) {
                    activeChallenges.remove(player);
                    titleVisible.put(player, false);
                    player.sendMessage(ChatColor.GREEN + "¡Correcto! Reiniciando PC!");
                    player.stopSound("minecraft:insomnia.anuncios");
                } else {
                    player.sendMessage(ChatColor.RED + "¡Incorrecto! Intenta de nuevo.");
                }
                event.setCancelled(true);
            }
        });
    }
    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        Utils.onDay(10, null, () -> {
            if (event.getEntity() instanceof org.bukkit.entity.Player) {
                if (event.getCause() == EntityDamageEvent.DamageCause.DROWNING) {
                    double originalDamage = event.getDamage();
                    double newDamage = originalDamage * 4;
                    event.setDamage(newDamage);
                }
            }
        });
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Utils.onDay(10, null, () -> {
            if (event.getPlayer().getLocation().getBlock().getType().equals(org.bukkit.Material.WATER)) {
                if (event.getPlayer().getRemainingAir() > 0) {
                    int newAir = event.getPlayer().getRemainingAir() - (1 * DROWNING_TIME_MULTIPLIER);
                    event.getPlayer().setRemainingAir(Math.max(newAir, 0));
                }
            }
        });
    }
    @EventHandler
    public void Water(PlayerMoveEvent event) {
        Utils.onDay(10, null, () -> {
            Player player = event.getPlayer();
            if (player.getLocation().getBlock().getType() == Material.WATER) {
                player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 60, 2, false, false));
            }
        });
    }
    @EventHandler
    public void onEndermanSpawn(CreatureSpawnEvent event) {
        Utils.onDay(10, null, () -> {
            if (event.getEntityType() == EntityType.ENDERMAN) {
                Enderman enderman = (Enderman) event.getEntity();
                enderman.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, Integer.MAX_VALUE, 0, false, false));
            }
        });
    }
    @EventHandler
    public void onEntityExplode(EntityExplodeEvent event) {
        Utils.onDay(10, null, () -> {
            if (event.getEntity() instanceof Animals || event.getEntity() instanceof TNT) {
                event.setYield(10);
            }
        });
    }
}