package plugin.nicohaxz.Utils;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;
import plugin.nicohaxz.List.KeyList;
import plugin.nicohaxz.main;

public class StormUtils {
    private static final JavaPlugin plugin = JavaPlugin.getPlugin(main.class);
    public static final NamespacedKey Key(String key) {
        return new NamespacedKey(plugin, key);
    }

    public static void setDuration(Player player, int value) {
        PersistentDataContainer pdc = player.getPersistentDataContainer();
        pdc.set(Key("permanoche"), PersistentDataType.INTEGER, value);
    }

    public static int getDuration(Player player) {
        PersistentDataContainer pdc = player.getPersistentDataContainer();
        Integer duration = pdc.get(Key("permanoche"), PersistentDataType.INTEGER);
        if (duration == null) {
            setDuration(player, 0);
            return 0;
        }
        return duration;
    }

    public static void setMaxValue(Player player, int value) {
        PersistentDataContainer pdc = player.getPersistentDataContainer();
        pdc.set(Key("permanoche_max"), PersistentDataType.INTEGER, value);
    }

    // Obtención del valor máximo
    public static int getMaxValue(Player player) {
        PersistentDataContainer pdc = player.getPersistentDataContainer();
        Integer maxValue = pdc.get(Key("permanoche_max"), PersistentDataType.INTEGER);
        if (maxValue == null) {
            setMaxValue(player, 0);
            return 0;
        }
        return maxValue;
    }

    // Verificación si la tormenta está activa
    public static boolean isActive(Player player) {
        return getDuration(player) > 0;
    }
}
