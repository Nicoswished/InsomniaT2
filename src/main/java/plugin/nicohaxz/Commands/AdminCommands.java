package plugin.nicohaxz.Commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import org.bukkit.entity.Player;
import plugin.nicohaxz.Utils.ConfigData;
import plugin.nicohaxz.Utils.StormUtils;

import static plugin.nicohaxz.Utils.Utils.chatcolor;

@CommandAlias("Insomnia|ins|staff")
@CommandPermission("staff.admin")
public class AdminCommands extends BaseCommand{
    @Subcommand("storm")
    @CommandCompletion("set|reset <duration> @nothing")
    @Syntax("<duración|reset>")
    public void storm(Player player, String action, @Optional Integer duration) {
        if (action.equalsIgnoreCase("set")) {
            if (duration != null && duration > 0) {
                StormUtils.setDuration(player, duration);
                StormUtils.setMaxValue(player, duration);
                player.sendMessage(chatcolor("&aSe estableció correctamente la duración de la tormenta a &c" + duration + " &asegundos."));
            } else {
                player.sendMessage(chatcolor("&cPor favor, ingresa una duración válida en segundos."));
            }
        } else if (action.equalsIgnoreCase("reset")) {
            StormUtils.setDuration(player, 0);
            StormUtils.setMaxValue(player, 0);
            player.sendMessage(chatcolor("&aLa tormenta ha sido detenida."));
        } else {
            player.sendMessage(chatcolor("&cUso incorrecto. Usa: /storm set <duración> o /storm reset"));
        }
    }
    @Subcommand("day")
    @CommandCompletion("day <día> @nothing")
    @Syntax("<nuevoDía>")
    public void setDay(Player player, int day) {
        ConfigData.setConfigValue("day", String.valueOf(day));
        player.sendMessage(chatcolor("&aSe establecio correctamente el día &c" + ConfigData.getDay() + "&a."));
    }
}
