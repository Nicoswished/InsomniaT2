package plugin.nicohaxz.Utils;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Entity;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import plugin.nicohaxz.main;

public class PDC {
    public static PersistentDataContainer GPDC = Bukkit.getWorld("world").getPersistentDataContainer();

    /*General pdc osea el pdc del mundo normal.*/
    public static PersistentDataContainer EntityPDC(Entity entity) {
        return entity.getPersistentDataContainer();
    }

    public static PersistentDataContainer ChunkPDC(Chunk chunk) {
        return chunk.getPersistentDataContainer();
    }

    public static PersistentDataContainer MetaPDC(ItemMeta meta) {
        return meta.getPersistentDataContainer();
    }

    /*
    Pdc de Chunks, nose de que sirve realmente pero puede ser util
     */
    public static NamespacedKey Key(String Key) {
        return new NamespacedKey(main.getInstance(), Key);
    }
    /*Para ahorrar Codigo*/


    /*Para ahorrar Codigo X2*/
    public static PersistentDataType<Integer, Integer> INT = PersistentDataType.INTEGER;
    public static PersistentDataType<String, String> STRING = PersistentDataType.STRING;
    public static PersistentDataType<Float, Float> FLOAT = PersistentDataType.FLOAT;
    public static PersistentDataType<Double, Double> DOUBLE = PersistentDataType.DOUBLE;


    public static int getTorment() {
        if (!PDC.GPDC.has(PDC.Key("torment"), PDC.INT)) {
            return 0;

        } else {


            return PDC.GPDC.get(PDC.Key("torment"), PDC.INT);
        }

    }

    public static void setTorment(int torment) {

        PDC.GPDC.set(PDC.Key("torment"), PDC.INT, torment);
    }

    public static void setTormentMax(int torment) {
        PDC.GPDC.set(PDC.Key("tormentmax"), PDC.INT, torment);
    }


    public static int getTormentMax() {
        if (!PDC.GPDC.has(PDC.Key("tormentmax"), PDC.INT)) {
            return 0;

        } else {


            return PDC.GPDC.get(PDC.Key("tormentmax"), PDC.INT);
        }

    }
}

