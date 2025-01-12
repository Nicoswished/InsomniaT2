package plugin.nicohaxz.Mobs.Principales;

import org.bukkit.entity.LivingEntity;
import plugin.nicohaxz.Mobs.MobBuilder;
import plugin.nicohaxz.Utils.ListHandler;
import plugin.nicohaxz.Utils.Utils;

import java.awt.*;

public class Creepers {
    public static LivingEntity MiniCreeper(org.bukkit.entity.Creeper entity){
        Color color1 = new Color(0, 185, 199);
        Color color2 = new Color(24, 44, 131, 255);
        return MobBuilder.CreeperBuilder((org.bukkit.entity.Creeper) entity, Utils.ib(color1, color2, ""),6,80,4,0.6F,true, 50, ListHandler.creepernormal());
    }
}
