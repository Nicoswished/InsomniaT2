package plugin.nicohaxz.Items;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import plugin.nicohaxz.Items.Function.ItemBuilder;
import plugin.nicohaxz.Items.Function.LoreBuilder;
import plugin.nicohaxz.Utils.Utils;

import java.awt.*;

public class ItemGlobal {

    public static ItemStack Cafeina() {
        ItemBuilder builder = new ItemBuilder(Material.LAPIS_LAZULI)
                .setDisplayName(Utils.ib(new Color(246, 0, 0), new Color(134, 4, 4), "&lInyección Cafeina"))
                .setAmount(1)
                .addItemFlags(ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS)
                .setCustomModelData(1)
                .addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1)
                .setUnbreakable(true);

        LoreBuilder loreBuilder = new LoreBuilder();
        loreBuilder.setItem(builder.build());
        loreBuilder.setDescription("&3Te permite reiniciar tu Cafeina");
        loreBuilder.addLine(Utils.solid(new Color(255, 229, 111, 255)) + "Uso:", false, true);
        loreBuilder.addLine(Utils.solid(new Color(255, 223, 140, 255)) + "      - &aClick derecho restablecera tu cafeina.", false, false);
        loreBuilder.setObtainTime(true);

        builder.addLore(loreBuilder.generateLore().toArray(new String[0]));
        return builder.build();
    }
}
