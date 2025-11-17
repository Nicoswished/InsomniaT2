package plugin.nicohaxz;

import co.aikar.commands.BukkitCommandManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import plugin.nicohaxz.Commands.AdminCommands;
import plugin.nicohaxz.Controller.CafeinaController;
import plugin.nicohaxz.Controller.StormController;
import plugin.nicohaxz.Days.Day15;
import plugin.nicohaxz.Days.Day18;
import plugin.nicohaxz.RegisterEvents.RegisterEvents;
import plugin.nicohaxz.Utils.Ranks;
import plugin.nicohaxz.Utils.Task;
import plugin.nicohaxz.Utils.Utils;

public final class main extends JavaPlugin {

    public static main plugin;
    public static main instance;
    public static World world = null;
    private StormController freezeMoonManager;



    public static Plugin getPlugin() {
        return plugin;

    }
    @Override
    public void onEnable() {
        instance = this;
        plugin = this;
        CafeinaController cafeinaController = new CafeinaController(this);
        getServer().getPluginManager().registerEvents(cafeinaController, this);
        freezeMoonManager = new StormController(this);
        Task fr3 = new Task(this,100);
        RegisterEvents.loadListeners();
        Utils.forcedRespawn();
        fr3.tablistTask();
        Utils.taskDay();
        initCommand();
        Utils.console(ChatColor.GREEN + ("InsomniaT2: Checkeando errores para un inicio correcto"));
        Utils.console(ChatColor.YELLOW + ("InsomniaT2: Plugin By NicoHaxz"));
        Utils.console(ChatColor.RED + ("InsomniaT2: Iniciado correctamente, enjoy :)"));
        // ESTO ES UN EVENTO PRIVADO NO VA RegisterEvents
        // ESTO ACA SE REGISTRAN LOS EVENTOS PRIVADOS APARTE
        Bukkit.getServer().getPluginManager().registerEvents(new Day15(this), this);
        Bukkit.getServer().getPluginManager().registerEvents(new Day18(this), this);
        Bukkit.getServer().getPluginManager().registerEvents(new StormController(this), this);
        //estoy bien pendejo en fin mi yo del pasado programando esto era muy xd


    }
    public void initCommand () {
        final BukkitCommandManager commandManager = new BukkitCommandManager(this);
        commandManager.registerCommand(new AdminCommands());
        commandManager.getCommandCompletions().registerAsyncCompletion("rankList", c -> Ranks.listaDeRangos());
    }

    @Override
    public void onDisable() {
    }
    public static main getInstance() {
        return instance;
    }
    //UTILS FREEZE MOON
    public StormController getFreezeMoonManager() {
        return freezeMoonManager;
    }
}

