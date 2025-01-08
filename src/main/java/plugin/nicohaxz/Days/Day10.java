package plugin.nicohaxz.Days;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import plugin.nicohaxz.main;

import java.util.HashMap;
import java.util.Random;

public class Day10 implements Listener {
        private final JavaPlugin plugin;

        public Day10(JavaPlugin plugin) {
            this.plugin = plugin;
        }

        private final HashMap<Player, Character> activeChallenges = new HashMap<>();
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
                        if (random.nextInt(6) == 0) {
                            showChallenge(player);
                        } else {
                            player.sendMessage(ChatColor.YELLOW + "Nada ocurrió esta vez. Espera el próximo ciclo.");
                            startTimer(player);
                        }
                    }
                }
            }.runTaskTimer(plugin, 0, 20);
        }

        private void showChallenge(Player player) {
            char randomChar = (char) ('A' + random.nextInt(26));
            activeChallenges.put(player, randomChar);
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
                    player.sendTitle(ChatColor.RED + "挑战", "Escribe esta letra: " + randomChar, 0, 20, 0);
                }
            }.runTaskTimer(plugin, 0, 20);
        }

        @EventHandler
        public void onPlayerChat(AsyncPlayerChatEvent event) {
            Player player = event.getPlayer();
            if (activeChallenges.containsKey(player) && titleVisible.getOrDefault(player, false)) {
                char expectedChar = activeChallenges.get(player);
                if (event.getMessage().equalsIgnoreCase(String.valueOf(expectedChar))) {
                    activeChallenges.remove(player);
                    titleVisible.put(player, false);
                    player.sendMessage(ChatColor.GREEN + "¡Correcto! Reiniciando el ciclo...");
                } else {
                    player.sendMessage(ChatColor.RED + "¡Incorrecto! Intenta de nuevo.");
                }
                event.setCancelled(true);
            }
        }
    }
