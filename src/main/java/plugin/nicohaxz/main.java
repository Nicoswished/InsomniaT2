package plugin.nicohaxz;

import org.bukkit.World;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import plugin.nicohaxz.Controller.StormController;
import plugin.nicohaxz.GameEvent.DeathEvent;
import plugin.nicohaxz.GameEvent.TotemListener;

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
        getServer().getPluginManager().registerEvents(new DeathEvent(), this);
        getServer().getPluginManager().registerEvents(new TotemListener(), this);
        getServer().getPluginManager().registerEvents(new StormController(), this);

    }

    @Override
    public void onDisable() {
    }
    public static main getInstance() {
        return instance;
    }
}

