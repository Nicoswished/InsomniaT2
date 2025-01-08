package plugin.nicohaxz.RegisterEvents;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import plugin.nicohaxz.Controller.StormController;
import plugin.nicohaxz.Days.*;
import plugin.nicohaxz.GameEvent.DeathEvent;
import plugin.nicohaxz.GameEvent.TotemListener;
import plugin.nicohaxz.main;

public class RegisterEvents {

    //EVENTOS QUE NO SON PRIVADOS VAN ACA
        public static void loadListeners() {
            registerListeners(
                    new DeathEvent(),
                    new TotemListener(),
                    new StormController(),
                    new Day0(),
                    new Day1(),
                    new Day2(),
                    new Day3(),
                    new Day4(),
                    new Day5(),
                    new Day6(),
                    new Day8()
            );
        }

        private static void registerListeners(Listener... listeners) {
            for (Listener listenerGen : listeners) {
                Bukkit.getPluginManager().registerEvents(listenerGen, main.getInstance());
            }

        }
    }

