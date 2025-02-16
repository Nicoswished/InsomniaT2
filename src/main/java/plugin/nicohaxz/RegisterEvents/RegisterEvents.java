package plugin.nicohaxz.RegisterEvents;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import plugin.nicohaxz.Controller.StormController;
import plugin.nicohaxz.Days.*;
import plugin.nicohaxz.GameEvent.ChatEvent;
import plugin.nicohaxz.GameEvent.DeathEvent;
import plugin.nicohaxz.GameEvent.JoinEvent;
import plugin.nicohaxz.GameEvent.TotemListener;
import plugin.nicohaxz.Items.Function.GlobalItemFuntion;
import plugin.nicohaxz.main;

public class RegisterEvents {

    //EVENTOS QUE NO SON PRIVADOS VAN ACA
        public static void loadListeners() {
            registerListeners(
                    new DeathEvent(),
                    new TotemListener(),
                    new SpawnMobsDays(),
                    new MobCapDay10(),
                    new ChatEvent(),
                    new JoinEvent(),
                    new GlobalItemFuntion(),
                    new Day0(),
                    new Day1(),
                    new Day2(),
                    new Day3(),
                    new Day4(),
                    new Day5(),
                    new Day6(),
                    new Day8(),
                    new Day9(),
                    new Day11(),
                    new Day12(),
                    new Day13(),
                    new Day14(),
                    new Day16(),
                    new Day17(),
                    new Day19(),
                    new Day20()









            );
        }

        private static void registerListeners(Listener... listeners) {
            for (Listener listenerGen : listeners) {
                Bukkit.getPluginManager().registerEvents(listenerGen, main.getInstance());
            }

        }
    }

