package plugin.nicohaxz.Controller;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.boss.BossBar;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import plugin.nicohaxz.Utils.Utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

    public class CafeinaController implements Listener {

        private final JavaPlugin plugin;
        private BossBar bossBar;
        private final Map<Player, Integer> stressLevels;
        private final Random random;

        public CafeinaController(JavaPlugin plugin) {
            this.plugin = plugin;
            this.bossBar = Bukkit.createBossBar(Utils.c("&l&5&k1 <GRADIENT:f24415>Cafeina</GRADIENT:fc633a> &l&5&k1"), BarColor.PINK, BarStyle.SOLID);
            this.stressLevels = new HashMap<>();
            this.random = new Random();
            startMechanic();
        }

        private void startMechanic() {
            new BukkitRunnable() {
                private int caffeineCounter = 7200;

                @Override
                public void run() {
                    for (Player player : Bukkit.getOnlinePlayers()) {
                        bossBar.addPlayer(player);

                        if (caffeineCounter <= 0) {
                            applyFatigue(player);

                            new BukkitRunnable() {
                                @Override
                                public void run() {
                                    if (random.nextDouble() <= 0.001) {
                                        applyStress(player);
                                    }

                                    if (random.nextDouble() <= 0.0009) {
                                        applyAnxiety(player);
                                    }
                                }
                            }.runTaskTimer(plugin, 0, 200);

                        } else {
                            caffeineCounter--;
                            updateCaffeineBar();
                        }
                    }
                }
            }.runTaskTimer(plugin, 0, 20); // Runs every second
        }

        private void updateCaffeineBar() {
            double progress = Math.max(0, bossBar.getProgress() - (1.0 / 7200));
            bossBar.setProgress(progress);
        }

        private void applyFatigue(Player player) {
            player.damage(1);

            if (!player.getInventory().isEmpty()) {
                player.getInventory().forEach(item -> {
                    if (item != null) {
                        player.getWorld().dropItemNaturally(player.getLocation(), item);
                    }
                });
                player.getInventory().clear();
            }
        }

        private void applyStress(Player player) {
            stressLevels.put(player, 300);

            new BukkitRunnable() {
                @Override
                public void run() {
                    if (!stressLevels.containsKey(player) || stressLevels.get(player) <= 0) {
                        stressLevels.remove(player);
                        cancel();
                    } else {
                        stressLevels.put(player, stressLevels.get(player) - 1);
                        if (random.nextDouble() <= 0.2) {
                            duplicatePlayer(player);
                        }

                        if (stressLevels.get(player) % 20 == 0) {
                            player.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 100, 0));
                        }
                    }
                }
            }.runTaskTimer(plugin, 0, 20);
        }

        private void applyAnxiety(Player player) {
            player.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 3600, 0));
            player.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 3600, 0));
            player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 3600, 0));

            new BukkitRunnable() {
                private int anxietyCounter = 180;

                @Override
                public void run() {
                    if (anxietyCounter <= 0) {
                        cancel();
                    } else {
                        anxietyCounter--;
                        player.damage(1);
                    }
                }
            }.runTaskTimer(plugin, 0, 20);
        }

        private void duplicatePlayer(Player player) {
        }

        @EventHandler
        public void onPlayerInteract(PlayerInteractEvent event) {
            Player player = event.getPlayer();
            ItemStack item = event.getItem();
            if (item != null && item.getType() == Material.LAPIS_LAZULI && item.hasItemMeta() && item.getItemMeta().hasCustomModelData() && item.getItemMeta().getCustomModelData() == 1) {
                item.setAmount(item.getAmount() - 1);
                player.sendMessage(ChatColor.GREEN + "¡Se restablecio la cafeina!");
                bossBar.setProgress(1.0);
            }
        }
    }




