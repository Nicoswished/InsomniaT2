package plugin.nicohaxz.Utils;

import com.iridium.iridiumcolorapi.IridiumColorAPI;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import plugin.nicohaxz.main;

import javax.annotation.Nullable;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

import static plugin.nicohaxz.Utils.ConfigData.getDay;

public class Utils {
    private static JavaPlugin plugin;
    private static Map<Player, BukkitTask> nervtaks = new HashMap<>();

    public static void console(String msg) {
        System.out.println(chatcolor(msg));
    }


    public static void setPlugin(JavaPlugin p) {
        plugin = p;
    }


    public static String chatColor(String message) {
        return message.replace("&", "§");
    }



    public static String Timer(int Seconds, boolean IncludeDays) {

        int num, hor, min, seg, day;
        num = Seconds;


        if (IncludeDays == true) {
            day = num / (3600 * 24);
            hor = ((num - (day * (3600 * 24))) / 3600);
            min = (num - (3600 * hor)) / 60;
            seg = num - ((day * (3600 * 24)) + (hor * 3600) + (min * 60));
            String diasreales = (String.format("%02d", day));
            String horasreales = (String.format("%02d", hor));
            String minutosreales = (String.format("%02d", min));
            String segundosreales = (String.format("%02d", seg));
            return diasreales + ":" + horasreales + ":" + minutosreales + ":" + segundosreales;


        } else {


            hor = num / 3600;
            min = (num - (3600 * hor)) / 60;
            seg = num - (((hor * 3600) + (min * 60)));

            String horasreales = (String.format("%02d", hor));
            String minutosreales = (String.format("%02d", min));
            String segundosreales = (String.format("%02d", seg));
            return horasreales + ":" + minutosreales + ":" + segundosreales;

        }


    }


    public static String getCustomCause(EntityDamageEvent e, String s) {
        String reasonDeath = "Desconocido";
        switch (e.getCause()) {
            case FALL, FALLING_BLOCK -> reasonDeath = "Caída";
            case FIRE, FIRE_TICK -> reasonDeath = "Fuego";
            case LAVA -> reasonDeath = "Lava";
            case DROWNING -> reasonDeath = "Ahogamiento";
            case BLOCK_EXPLOSION -> reasonDeath = "Explosion";
            case VOID -> reasonDeath = "Vacío";
            case LIGHTNING -> reasonDeath = "Rayo";
            case POISON -> reasonDeath = "Veneno";
            case MAGIC -> reasonDeath = "Magia";
            case WITHER -> reasonDeath = "Wither";
            case THORNS -> reasonDeath = "Espinas";
            case DRAGON_BREATH -> reasonDeath = "Aliento de dragón";
            case CONTACT -> reasonDeath = "Contacto";
            case CRAMMING -> reasonDeath = "Entity Cramming";
            case HOT_FLOOR -> reasonDeath = "Piso en llamas";
            case DRYOUT -> reasonDeath = "Secado";
            case STARVATION -> reasonDeath = "Hambre";
            case SUFFOCATION -> reasonDeath = "Asfixia";
            case FLY_INTO_WALL -> reasonDeath = "Colisión";
            case FREEZE -> reasonDeath = "Congelación";
            case ENTITY_ATTACK, ENTITY_SWEEP_ATTACK -> {
                if (e instanceof EntityDamageByEntityEvent) {
                    reasonDeath = "Entidad [" + ((EntityDamageByEntityEvent) e).getDamager().getName() + "]";
                }
            }
            case PROJECTILE -> {
                if (e instanceof EntityDamageByEntityEvent) {
                    Projectile proj = (Projectile) ((EntityDamageByEntityEvent) e).getDamager();
                    if (proj.getShooter() != null) {
                        Entity shooter = (Entity) proj.getShooter();
                        reasonDeath = "Proyectil [" + shooter.getName() + "]";
                    } else {
                        reasonDeath = "Proyectil [" + ((EntityDamageByEntityEvent) e).getDamager().getName() + "]";
                    }
                }
            }
            case ENTITY_EXPLOSION -> {
                if (e instanceof EntityDamageByEntityEvent) {
                    if (((EntityDamageByEntityEvent) e).getDamager() instanceof Fireball ball && ((Fireball) ((EntityDamageByEntityEvent) e).getDamager()).getShooter() != null) {
                        LivingEntity et = (LivingEntity) ball.getShooter();
                        reasonDeath = "Explosión [" + et.getName() + "]";
                    } else {
                        reasonDeath = Utils.chatColor("Explosión [" + ((EntityDamageByEntityEvent) e).getDamager().getName() + "]");
                    }
                }
            }
            case SUICIDE -> reasonDeath = "Suicidio";
            default -> reasonDeath = "Desconocido";
        }
        return reasonDeath;
    }

    public static String TimestampWithDay(String timestamp) {
        Date currentDate = new Date(Long.parseLong(timestamp) * 1000L);
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        formatter.setTimeZone(TimeZone.getTimeZone("Atlantic/Madeira")); // UTC
        return getDayDE(timestamp) + formatter.format(currentDate);
    }

    public static String c(String s) {
        return IridiumColorAPI.process(s);
    }

    public static String chatcolor(String message) {
        return message.replace("&", "§");
    }

    public static String getPrefix() {
        return Utils.c("&8&l[" + Utils.ib(new java.awt.Color(10, 236, 228), new java.awt.Color(6, 169, 163), "&lInsomnia") + "&8&l]&r ");
    }

    public static String getLocationString(Location location) {
        String x = (String.valueOf(location.getBlockX()));
        String y = (String.valueOf(location.getBlockY()));
        String z = (String.valueOf(location.getBlockZ()));
        return "X: " + x + " Y: " + y + " Z: " + z;
    }

    public static String getWorld(Player target) {
        return switch (target.getWorld().getName()) {
            case "world" -> "Overworld";
            case "world_nether" -> "Nether";
            case "world_the_end" -> "The End";
            default -> "Desconocido";
        };
    }

    public static String ib(java.awt.Color Colora, java.awt.Color Colorb, String StringToGradient) {
        net.md_5.bungee.api.ChatColor Color2 = net.md_5.bungee.api.ChatColor.of(Colorb);
        net.md_5.bungee.api.ChatColor Color1 = ChatColor.of(Colora);
        int r = Color1.getColor().getRed();
        int g = Color1.getColor().getGreen();
        int b = Color1.getColor().getBlue();
        int r2 = Color2.getColor().getRed();
        int g2 = Color2.getColor().getGreen();
        int b2 = Color2.getColor().getBlue();
        String Hex = String.format("%02X%02X%02X", r, g, b);
        String Hex2 = String.format("%02X%02X%02X", r2, g2, b2);
        String Final = IridiumColorAPI.process("<GRADIENT:" + Hex + ">" + StringToGradient + "</GRADIENT:" + Hex2 + ">");


        return Final;


    }

    public static String solid(java.awt.Color color) {
        int r1 = color.getRed();
        int g2 = color.getGreen();
        int b3 = color.getBlue();
        String Final = IridiumColorAPI.process("<SOLID:" + String.format("%02X%02X%02X", r1, g2, b3) + ">");
        return Final;
    }

    public static String dateFormat(Date date) {
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        return sdf.format(date);
    }


    public static String getDayDE(String timestamp) {
        long day = Long.parseLong(timestamp) / 86400L;
        day = (long) Math.floor(day);
        return String.format("%03d:", day);
    }

    public static void notifyPlayers() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 60, 0));
            player.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 100, 0));
            player.addPotionEffect(new PotionEffect(PotionEffectType.DARKNESS, 60, 0));
            player.playSound(player.getLocation(), Sound.BLOCK_RESPAWN_ANCHOR_DEPLETE, 2, 1.2F);
            player.playSound(player.getLocation(), Sound.ENTITY_WITHER_SPAWN, 2, 0.6F);

            player.sendTitle(Utils.c("&7!!! <GRADIENT:F22E2E>&lDía " + getDay() + "</GRADIENT:B32502> &r&7!!!"), Utils.c("&7Felicidades por superar el &ddía: " + (getDay() - 1)), 30, 140, 40);
            player.sendMessage(Utils.c(Utils.getPrefix() + Utils.ib(new java.awt.Color(28, 161, 146), new java.awt.Color(220, 68, 220), "&k!!! Bienvenidos al apocalipsis &k!!!")));
            new BukkitRunnable() {

                @Override
                public void run() {
                    player.sendMessage(Utils.c(Utils.getPrefix() + "&eRevisa los nuevos crafteos con /dp crafteos"));
                    player.playSound(player.getLocation(), Sound.BLOCK_RESPAWN_ANCHOR_SET_SPAWN, 1.4F, 0.8F);
                }
            }.runTaskLater(main.getInstance(), 60);
            if (Objects.requireNonNull(player.getInventory().getItemInMainHand().getItemMeta()).hasCustomModelData() && player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1) {
                player.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 200, 0));
            }
            new BukkitRunnable() {

                @Override
                public void run() {
                    for (Player pl : Bukkit.getOnlinePlayers()) {
                        if (!pl.isOp()) {
                            pl.kickPlayer(Utils.c("&6&lEl servidor ha hecho un cambio de día, vuelve a entrar"));
                        }


                    }
                }
            }.runTaskLater(main.getInstance(), 160);
        }
    }

    public static void updateDay() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        int day = getDay();
        ConfigData.setConfigValue("day", String.valueOf(day));
        ConfigData.setConfigValue("date", String.valueOf(LocalDate.now()));
        ManagerUtils s = new ManagerUtils(main.getInstance());
        main.getInstance().getServer().getPluginManager().registerEvents((Listener) s, main.getInstance());
    }

    public static void taskDay() {
        new BukkitRunnable() {
            @Override
            public void run() {
                String dateUtils = ConfigData.getServerDate();
                LocalDate fechaActual = LocalDate.now();
                if (!dateUtils.equals(fechaActual.toString())) {
                    try {
                        updateDay();
                    } catch (InvocationTargetException e) {
                        throw new RuntimeException(e);
                    } catch (NoSuchMethodException e) {
                        throw new RuntimeException(e);
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                    notifyPlayers();
                }
            }
        }.runTaskTimer(main.getPlugin(main.class), 0, 20L); //20 (ticks) = 1 segundo
    }

    public static void onDay(int day, @Nullable Integer until, Runnable run) {
        int nowDay = getDay();
        if (nowDay >= day && (until == null || (nowDay <= until))) {
            run.run();
        }
    }


    public static void forcedRespawn() {
        // Lista de nombres de los mundos donde deseas establecer el GameRule
        String[] worlds = {"world", "world_nether", "world_the_end", "world_underdarkness", "world_endverseed"};

        for (String worldName : worlds) {
            World world = Bukkit.getWorld(worldName);
            if (world != null) { // Verifica si el mundo está cargado
                Boolean immediateRespawn = world.getGameRuleValue(GameRule.DO_IMMEDIATE_RESPAWN);
                if (immediateRespawn == null || !immediateRespawn) { // Manejo seguro en caso de valores nulos
                    world.setGameRule(GameRule.DO_IMMEDIATE_RESPAWN, true);
                    System.out.println("GameRule DO_IMMEDIATE_RESPAWN activado para el mundo: " + worldName);
                }
            } else {
                System.out.println("Advertencia: El mundo '" + worldName + "' no está cargado o no existe.");
            }
        }
    }

    public static void sendStringForAllPlayers(String string) {
        for (Player pl : Bukkit.getOnlinePlayers()) {
            pl.sendMessage(string);


        }


    }

    public static World getWorld() {
        return Bukkit.getWorld("world");
    }

    public static String getStormDuration(World world) {
        int duration = world.getWeatherDuration();
        int seconds = duration / 20;
        int minutes = duration / 1200;
        int hours = duration / 72000;
        int days = duration / 1728000;
        String tiempototal = days + " D/s, " + (hours - days * 24) + " H/s, " + (minutes - hours * 60) + " M/s, " + (seconds - minutes * 60) + " S/s";
        if (days == 0) {
            tiempototal = (hours) + " H/s, " + (minutes - hours * 60) + " M/s, " + (seconds - minutes * 60) + " S/s";
        }
        if (days == 0 && hours == 0) {
            tiempototal = (minutes) + " M/s, " + (seconds - minutes * 60) + " S/s";
        }
        if (days == 0 && hours == 0 && minutes == 0) {
            tiempototal = (seconds) + " S/s";
        }
        return tiempototal;
    }


    public static void onDay(int targetHour, int i, Runnable runnable) {
    }


    private static void updateCooldown(ItemStack item, int seconds) {
        ItemMeta meta = item.getItemMeta();
        meta.getPersistentDataContainer().set(PDC.Key("cooldown"), PDC.INT, seconds);
        item.setItemMeta(meta);
    }

    public static void setCustomCooldown(Player target, ItemStack item, int seconds) {
        if (seconds <= 0) return; // No se puede poner un cooldown negativo
        updateCooldown(item, seconds + 1);
        target.setCooldown(item.getType(), seconds * 20);
        new BukkitRunnable() {
            @Override
            public void run() {
                int tiempo = getCustomCooldown(item);
                if (tiempo <= 0 || !target.isOnline()) cancel();
                updateCooldown(item, tiempo - 1);
            }
        }.runTaskTimerAsynchronously(main.getInstance(), 0, 20L);
    }

    public static void checkCooldowns() {
        for (Player target : Bukkit.getOnlinePlayers()) {
            for (ItemStack item : target.getInventory().getContents()) {
                if (item != null && item.hasItemMeta() && getCustomCooldown(item) != 0)
                    setCustomCooldown(target, item, getCustomCooldown(item));
            }
        }
    }

    public static int getCustomCooldown(ItemStack item) {
        PersistentDataContainer data = item.getItemMeta().getPersistentDataContainer();
        if (data.has(PDC.Key("cooldown"), PersistentDataType.INTEGER))
            return data.get(PDC.Key("cooldown"), PersistentDataType.INTEGER);
        else return 0;
    }

    public static void VisualRandomTotem(Player p, int r, ItemStack totesm) {
        final int[] totem = {0};
        new BukkitRunnable() {
            @Override
            public void run() {

                if (totem[0] >= r) {
                    cancel();
                } else {
                    if (totem[0] != 0) {
                        ItemStack mainhand = p.getInventory().getItemInMainHand();
                        p.getInventory().setItemInMainHand(totesm);
                        p.playEffect(EntityEffect.TOTEM_RESURRECT);
                        p.getInventory().setItemInMainHand(mainhand);
                    }
                    totem[0]++;


                }
            }
        }.runTaskTimer(main.getPlugin(main.class), 0, 20L); //20 (ticks) = 1 segundoi
    }

    public static String getTotemNameMC(ItemStack totem, boolean plural) {
        String message = "Unknown";


        if (Objects.requireNonNull(totem.getItemMeta().hasCustomModelData())) {
            switch (Objects.requireNonNull(totem.getItemMeta().getCustomModelData())) {
                case 1 -> // DemoniacTotem
                        message = Utils.c("<GRADIENT:fa7a1b>&lTwilight Totem</GRADIENT:f69b58>");
                case 2 -> // ???
                        message = Utils.c("???");
                case 3 -> // NanoTotem
                        message = Utils.c("<GRADIENT:807776>&lGlitch Totem</GRADIENT:cdc2c1>");

            }
        } else {
            message = Utils.c("Tótem");


        }
        if (plural == true) {
            message = message + "s";

        }
        return message;

    }

    public static String getRandomTotemValue(Player p, int r) {

        String s = "";

        //??
        int v = PlayerData.getTotems(p);


        for (int i = 1; i <= r; i++) {

            s = s + "#" + (v + i) + " ";

        }

        return " (" + s + ")";
    }

    public static String serverHour(Date date) {
        final SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        return sdf.format(date);
    }

    public static void setCasco(Player p, String boleano) {
        p.getPersistentDataContainer().set(new NamespacedKey(main.getInstance(), "CascoSkullSkill"), PersistentDataType.STRING, boleano);
    }

    public static String getCasco(Player player) {
        if (player.getPersistentDataContainer().get(new NamespacedKey(main.getInstance(), "CascoSkullSkill"), PersistentDataType.STRING) == null) {
            player.getPersistentDataContainer().set(new NamespacedKey(main.getInstance(), "CascoSkullSkill"), PersistentDataType.STRING, "true");
        }

        return (String) player.getPersistentDataContainer().get(new NamespacedKey(main.getInstance(), "CascoSkullSkill"), PersistentDataType.STRING);
    }

    public static String format(String msg) {
        return org.bukkit.ChatColor.translateAlternateColorCodes('&', msg);
    }

    public static String GetType(Entity p) {


        if (PDC.EntityPDC(p).get(PDC.Key(ListHandler.mobtype()), PDC.STRING) == null) {


            return "vanilla";
        } else {


            return (String) PDC.EntityPDC(p).get(PDC.Key(ListHandler.mobtype()), PDC.STRING);
        }


    }
}
