package plugin.nicohaxz.Commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import org.bukkit.entity.Player;
import plugin.nicohaxz.Utils.StormUtils;

import static plugin.nicohaxz.Utils.Utils.chatcolor;

@CommandAlias("twi")
@CommandPermission("twi.admin")
public class AdminCommands extends BaseCommand{
    @Subcommand("storm")
    @CommandCompletion("set|reset <duration> @nothing")
    @Syntax("<duración|reset>")
    public void storm(Player player, String action, Integer duration) {
        if (action.equalsIgnoreCase("set")) {
            if (duration != null && duration > 0) {
                StormUtils.setDuration(duration);
                StormUtils.setMaxValue(duration);
                player.sendMessage(chatcolor("&aSe estableció correctamente la duración de la tormenta a &c" + duration + " &asegundos."));
            } else {
                player.sendMessage(chatcolor("&cPor favor, ingresa una duración válida en segundos."));
            }
        } else if (action.equalsIgnoreCase("reset")) {
            StormUtils.setDuration(0);
            player.sendMessage(chatcolor("&aLa tormenta ha sido detenida."));
        } else {
            player.sendMessage(chatcolor("&cUso incorrecto. Usa: /mpa storm set <duración> o /mpa storm reset"));
        }
    }
}
