package plugin.nicohaxz.Utils;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import plugin.nicohaxz.File.CreateFile;
import plugin.nicohaxz.main;

import java.util.UUID;

public class PlayerData {
    private String playerName;
    private UUID uuid;

    private int temperature;

    public PlayerData(String playerName, UUID id) {
        this.playerName = playerName;
        this.uuid = (id == null ? Bukkit.getOfflinePlayer(playerName).getUniqueId() : id);

        Player p = Bukkit.getPlayer(uuid);
        if (p != null) loadData(p);
    }

    private void loadData(Player p) {
        this.temperature = getOrAddData(p, "temperatura", PersistentDataType.INTEGER, 30);
    }

    public void saveData(Player p) {
        setData(p, "temperatura", PersistentDataType.INTEGER, this.temperature);
    }

    private void printDebug(Player p) {
        Bukkit.broadcastMessage("-------[ DEBUG DATA " + getName() + " ]-------");
        Bukkit.broadcastMessage("Temperatura: " + temperature + " | " + getData(p, "temperatura", PersistentDataType.INTEGER));
    }

    public void tick() {

    }

    private <T, Z> Z setData(Player p, String id, PersistentDataType<T, Z> type, Z value) {
        PersistentDataContainer data = p.getPersistentDataContainer();
        NamespacedKey key = new NamespacedKey(main.getInstance(), id);
        data.set(key, type, value);

        return value;
    }

    private <T, Z> Z getOrAddData(Player p, String id, PersistentDataType<T, Z> type, Z defValue) {
        PersistentDataContainer data = p.getPersistentDataContainer();
        NamespacedKey key = new NamespacedKey(main.getInstance(), id);

        if (!data.has(key, type)) {
            data.set(key, type, defValue);
        }

        return data.get(key, type);
    }

    private <T, Z> Z getData(Player p, String id, PersistentDataType<T, Z> type) {
        PersistentDataContainer data = p.getPersistentDataContainer();
        NamespacedKey key = new NamespacedKey(main.getInstance(), id);

        return data.get(key, type);
    }

    private NamespacedKey key(String id) {
        return NamespacedKey.minecraft(id);
    }

    public String getName() {
        return playerName;
    }

    public UUID getID() {
        return uuid;
    }


    public PlayerData() {
        this.temperature = 20;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public void modifyTemperature(int amount) {
        this.temperature += amount;
    }

    private static final CreateFile data = new CreateFile(main.getInstance(), "data.yml");


    public static void setPlayerData(Player target, String id, String value) {
        data.setConfig(target.getName() + "." + id, value);
    }

    public static void reloadPlayerData() {
        data.reloadConfig();
    }

    public static String getPlayerData(Player target, String id, String defaultValue) {
        if (data.getConfig().getString(target.getName() + "." + id) == null) {
            setPlayerData(target, id, defaultValue);
        }
        return data.getConfig().getString(target.getName() + "." + id);
    }

    public static int getTotems(Player target) {
        return target.getStatistic(Statistic.USE_ITEM, Material.TOTEM_OF_UNDYING);
    }

    public static String getTiempoJugado(Player target) {
        int timePlayed = target.getStatistic(Statistic.PLAY_ONE_MINUTE) / 20;
        return Utils.TimestampWithDay(String.valueOf(timePlayed));
    }

    public static void setRadiation(Player target, int value) {
        setPlayerData(target, "radiation", String.valueOf(value));
    }

    public static int getRadiation(Player target) {
        return Integer.parseInt(getPlayerData(target, "radiation", "0"));
    }

    public static String getDeathMessage(Player target) {
        return getPlayerData(target, "death-message", "Ninguna.");
    }

    public static Boolean getToggleChatPing(Player target) {
        return Boolean.parseBoolean(getPlayerData(target, "chatPing", "true"));
    }

    public static String getAnimation(Player target) {
        return getPlayerData(target, "death-animation", "default");
    }

    public static String getRank(Player target) {
        return getPlayerData(target, "rank", "default");
    }

}
