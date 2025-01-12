package plugin.nicohaxz.Items.Function;

import org.bukkit.inventory.ItemStack;
import plugin.nicohaxz.Utils.ConfigData;
import plugin.nicohaxz.Utils.Utils;

import javax.annotation.Nullable;
import java.awt.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LoreBuilder {

    private ItemStack item;
    private String type;
    private String description;
    private List<String> loreLines = new ArrayList<>();
    private Integer cooldown;
    private String string;
    private Integer range;
    private Boolean bool;

    public void setItem(ItemStack item) {
        this.item = item;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setDescription(@Nullable String description) {
        this.description = description;
    }

    public void addLine(String line, boolean jumpLine, boolean decoration) {
        if (jumpLine == true) {
            if (decoration == true) {
                loreLines.add("  &8>> &r" + line);
                loreLines.add("");
            }else {
                loreLines.add(line);
                loreLines.add("");
            }
        }else if (jumpLine == false) {
            if (decoration == true) {
                loreLines.add("  &8>> &r" + line);
            }else {
                loreLines.add(line);
            }
        }
    }
    public void addContinuous(String line) {
        loreLines.add(line);
    }
    public void setCooldown(Integer cooldown, String string) {
        this.cooldown = cooldown;
        this.string = string;
    }
    public void setRange(Integer range) {
        this.range = range;
    }
    public void setObtainTime(boolean bool) {
        this.bool = bool;
    }

    public List<String> generateLore() {
        Color color1 = new Color(255, 214, 0, 255);
        Color color2 = new Color(255, 127, 0);
        List<String> lore = new ArrayList<>();
        lore.add(Utils.ib(color1, color2,"≫ ───────── ≪•◦ ❈ ◦•≫ ───────── ≪"));
        lore.add(Utils.chatcolor("&7Calidad: &r" + type));
        lore.add("");

        if (description != null) {
            lore.add(Utils.c("&7Descripción:"));
            lore.add(Utils.chatcolor("&8- " + description));
            lore.add(Utils.chatcolor(""));
        }

        for (String line : loreLines) {
            if (line != null) {
                lore.add(Utils.chatcolor(line));
            }
        }

        if (bool == true) {
            lore.add(Utils.chatcolor("  &8>> &r" + Utils.solid(new Color(255, 223, 140, 255)) + "Item Obtenido en: " + Utils.solid(new Color(255, 255, 255, 255)) + "Day: " + Utils.solid(new Color(25, 255, 98, 255)) + ConfigData.getDay() + Utils.solid(new Color(255, 255, 255, 255)) + " Hour: " + Utils.solid(new Color(25, 255, 98, 255)) + Utils.serverHour(new Date())));
            lore.add("");
        }
        if (range == null) {
        } else {
            lore.add(Utils.chatcolor("\uD83C\uDFF9 " + Utils.solid(new Color(155, 120, 54)) + "&lRango: " + range + " block/s"));
        }
        if (cooldown == null && string != null) {
            lore.add(Utils.chatcolor("⌚ &b&lCooldown: " + string));
        }else if (string == null && cooldown != null) {
            lore.add(Utils.chatcolor("⌚ &b&lCooldown: " + cooldown + "s"));
        }else if (string == null && cooldown == null) {
            lore.add(Utils.chatcolor("⌚ &b&lSin cooldown"));
        }else {
            lore.add(Utils.chatcolor("⌚ &b&lCooldown: " + cooldown + string));
        }
        lore.add(Utils.ib(color1, color2,"≫ ───────── ≪•◦ ❈ ◦•≫ ───────── ≪"));

        return lore;
    }

}


