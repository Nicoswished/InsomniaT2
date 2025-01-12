package plugin.nicohaxz.Commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Description;
import co.aikar.commands.annotation.Subcommand;
import org.bukkit.entity.Player;
import plugin.nicohaxz.Controller.StormController;
import plugin.nicohaxz.Utils.Utils;
import plugin.nicohaxz.main;

@CommandAlias("FreezeMoon|FM")
@CommandPermission("FM.admin")
public class FreezeMoonCommands extends BaseCommand {
    private boolean isPaused = false;
    private final StormController freezeMoonManager;

    public FreezeMoonCommands(main main, StormController freezeMoonManager) {
        this.freezeMoonManager = freezeMoonManager;
    }

    @Subcommand("pause")
    @Description("Pausar la tormenta Freeze Moon")
    public void onPause(Player sender) {
        if (!isPaused && freezeMoonManager.getRemainingTimeInSeconds() > 0) {
            isPaused = true;
            sender.sendMessage(Utils.c("<GRADIENT:06def4>&lFreeze Moon pausada</GRADIENT:09909e>"));
        } else if (isPaused) {
            sender.sendMessage(Utils.c("&cLa tormenta ya está pausada."));
        } else {
            sender.sendMessage(Utils.c("&cNo hay tormenta activa para pausar."));
        }
    }

    @Subcommand("resume")
    @Description("Reanudar la tormenta Freeze Moon")
    public void onResume(Player sender) {
        if (isPaused) {
            isPaused = false;
            freezeMoonManager.startCountdown();
            sender.sendMessage(Utils.c("<GRADIENT:06def4>&lFreeze Moon reanudada</GRADIENT:09909e>"));
        } else {
            sender.sendMessage(Utils.c("&cNo hay una tormenta pausada para reanudar."));
        }
    }

    @Subcommand("reset")
    @Description("Reiniciar la tormenta Freeze Moon")
    public void onReset(Player sender) {
        isPaused = false;
        freezeMoonManager.setRemainingTimeInSeconds(0);
        sender.sendMessage(Utils.c("<GRADIENT:06def4>&lFreeze Moon reiniciada</GRADIENT:09909e>"));
    }
}

