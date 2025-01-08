package plugin.nicohaxz;

import co.aikar.commands.BukkitCommandManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import plugin.nicohaxz.Commands.AdminCommands;
import plugin.nicohaxz.Controller.StormController;
import plugin.nicohaxz.Days.Day10;
import plugin.nicohaxz.GameEvent.DeathEvent;
import plugin.nicohaxz.GameEvent.TotemListener;
import plugin.nicohaxz.RegisterEvents.RegisterEvents;
import plugin.nicohaxz.Utils.Utils;

public final class main extends JavaPlugin {

    // Yo y los estaticos cuando:
    public static main plugin;
    public static main instance;
    public static World world = null;


    public static Plugin getPlugin() {
        return plugin;

    }
    @Override
    public void onEnable() {
        plugin = this;
        instance = this;
        initCommand();
        Utils.taskDay();
        Utils.forcedRespawn();
        RegisterEvents.loadListeners();
        Utils.console(ChatColor.GREEN + ("INICIANDO INSOMNIA HMP T2 "));
        Utils.console(ChatColor.YELLOW + ("PLUGIN BY KECHAKBOOM Y NICO PACK"));
        Utils.console(ChatColor.RED + ("INICIADO CORRECTAMENTE !!!!"));



        // ESTO ES UN EVENTO PRIVADO NO VA RegisterEvents
        // ESTO ACA SE REGISTRAN LOS EVENTOS PRIVADOS APARTE
        Bukkit.getServer().getPluginManager().registerEvents(new Day10(this), this);
    }
    public void initCommand () {
        final BukkitCommandManager commandManager = new BukkitCommandManager(this);
        commandManager.registerCommand(new AdminCommands());

        //rangos pero no los he echo hasta tener la textura
        //commandManager.getCommandCompletions().registerAsyncCompletion("rankList", c -> Ranks.listaDeRangos());
    }

    @Override
    public void onDisable() {
    }
    public static main getInstance() {
        return instance;
    }
}

