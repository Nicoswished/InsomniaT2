package plugin.nicohaxz.Controller;

import co.aikar.taskchain.BukkitTaskChainFactory;
import co.aikar.taskchain.TaskChain;
import co.aikar.taskchain.TaskChainFactory;
import com.iridium.iridiumcolorapi.IridiumColorAPI;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import plugin.nicohaxz.Utils.Utils;
import plugin.nicohaxz.main;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class DeathController {
    private static final TaskChainFactory taskChainFactory = BukkitTaskChainFactory.create(main.getInstance());

    public static <T> TaskChain<T> newChain() {
        return taskChainFactory.newChain();
    }

    public static void startAnimationDeath(Player muerto, String configAnimation, Player publico) {
        if (Objects.equals(configAnimation, "animation")) {
            new BukkitRunnable() {
                int init = 0;

                @Override
                public void run() {
                    char lol = (char) Integer.parseInt(String.format("\\uE%03d", init++).substring(2), 16);
                    publico.sendTitle(String.valueOf(lol), null, 0, 10, 20);
                    if (init >= 34 + 1) { //34 num de frames a mostrar
                        cancel();
                    }
                }
            }.runTaskTimer(main.getPlugin(main.class), 4, 0);
        } else {
            TaskChain<Object> chain = newChain();
            String[] titles = {
                    Utils.c("&8&k1 T &8&k2"), // Frame 1
                    Utils.c("&8&k1 Tw&8&k2"), // Frame 2
                    Utils.c("&8&k1 <GRADIENT:09909e>A</GRADIENT:06def4> &8&k2"), // Frame 3
                    Utils.c("&8&k1 <GRADIENT:09909e>Ai</GRADIENT:06def4> &8&k2"), // Frame 4
                    Utils.c("&8&k1 <GRADIENT:09909e>Ain</GRADIENT:06def4> &8&k2"), // Frame 5
                    Utils.c("&8&k1 <GRADIENT:09909e>Ainm</GRADIENT:06def4> &8&k2"), // Frame 6
                    Utils.c("&8&k1 <GRADIENT:09909e>Ainmo</GRADIENT:06def4> &8&k2"), // Frame 7
                    Utils.c("&8&k1 <GRADIENT:09909e>Ainmos</GRADIENT:06def4> &8&k2"), // Frame 8
                    Utils.c("&8&k1 <GRADIENT:09909e>Ainmosn</GRADIENT:06def4> &8&k2"), // Frame 9
                    Utils.c("&8&k1 <GRADIENT:09909e>Ainmosni</GRADIENT:06def4> &8&k2"), // Frame 10
                    Utils.c("&8&k1 <GRADIENT:09909e>&kAinmosni</GRADIENT:06def4> &8&k2"), // Frame 11
                    Utils.c("&8&k1 <GRADIENT:09909e>&kAinmosni</GRADIENT:06def4> &8&k2"), // Frame 12
                    Utils.c("&8&k1 <GRADIENT:09909e>&kAinmosni</GRADIENT:06def4> &8&k2"), // Frame 13
                    Utils.c("&8&k1 <GRADIENT:09909e>&kAinmosni</GRADIENT:06def4> &8&k2"), // Frame 14
                    Utils.c("&8&k1 <GRADIENT:06def4>Insomnia</GRADIENT:09909e> &8&k2"), // Frame 15
                    Utils.c("&8&k1 <GRADIENT:06def4>Insomnia</GRADIENT:09909e> &8&k2"), // Frame 16
                    Utils.c("&8&k1 <GRADIENT:06def4>Insomnia</GRADIENT:09909e> &8&k2"), // Frame 17
                    Utils.c("&8&k1 <GRADIENT:06def4>Insomnia</GRADIENT:09909e> &8&k2"), // Frame 18
                    Utils.c("&8&k1 <GRADIENT:06def4>Insomni</GRADIENT:09909e> &8&k2"), // Frame 19
                    Utils.c("&8&k1 <GRADIENT:06def4>Insomn</GRADIENT:09909e> &8&k2"), // Frame 20
                    Utils.c("&8&k1 <GRADIENT:06def4>Insom</GRADIENT:09909e> &8&k2"), // Frame 21
                    Utils.c("&8&k1 <GRADIENT:06def4>Inso</GRADIENT:09909e> &8&k2"), // Frame 22 (distorsión)
                    Utils.c("&8&k1 <GRADIENT:06def4>Ins</GRADIENT:09909e> &8&k2"), // Frame 23 (distorsión)
                    Utils.c("&8&k1 <GRADIENT:06def4>In</GRADIENT:09909e> &8&k2"),
                    Utils.c("&8&k1 <GRADIENT:06def4>I</GRADIENT:09909e> &8&k2"),
            };
            List.of(titles).
                    forEach(title -> chain.delay(100, TimeUnit.MILLISECONDS).
                            sync(() -> publico.sendTitle(title, IridiumColorAPI.process("<SOLID:807e73>Muerte de: <SOLID:5a5951>" + muerto.getDisplayName()), 0, 20, 60)));
            chain.sync(TaskChain::abort).
                    execute();
        }
    }
}

