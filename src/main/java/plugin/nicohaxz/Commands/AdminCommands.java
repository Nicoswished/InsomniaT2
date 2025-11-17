package plugin.nicohaxz.Commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import plugin.nicohaxz.Controller.StormController;
import plugin.nicohaxz.Items.ItemGlobal;
import plugin.nicohaxz.Utils.*;

import java.util.Arrays;
import java.util.Locale;
import java.util.logging.Level;

import static plugin.nicohaxz.Utils.Utils.chatcolor;

@CommandAlias("Insomnia|ins|staff")
@CommandPermission("staff.admin")
public class AdminCommands extends BaseCommand {


    private static String command(String s) {
        return Utils.c(Utils.getPrefix() + s);
    }


    @Subcommand("storm")
    @CommandCompletion("set|remove <segundos>")
    @Syntax("<set|remove> <segundos>")
    @Description("Inicia o resta tiempo a la Corrupted")
    public void stormCommandxd(Player player, String[] args) {
        Bukkit.getLogger().info("[StormCmd] args: " + Arrays.toString(args));

        if (args.length < 2) {
            player.sendMessage("§cUso correcto: /storm <set|remove> <segundos>");
            return;
        }

        String action = args[0].toLowerCase(Locale.ROOT);

        try {
            if (action.equals("set")) {
                long tiempo;
                try {
                    tiempo = Long.parseLong(args[1]);
                } catch (NumberFormatException nfe) {
                    player.sendMessage("§cEl valor '" + args[1] + "' no es un número válido.");
                    return;
                }

                boolean wasActive = StormController.isStormActive();

                try {
                    StormController.startStorm(tiempo, false);
                    if (wasActive) {
                        player.sendMessage("§aHas extendido la tormenta por " + tiempo + " segundos.");
                    } else {
                        player.sendMessage("§aHas iniciado una tormenta por " + tiempo + " segundos.");
                    }
                } catch (Exception e) {
                    Bukkit.getLogger().log(Level.SEVERE, "Error al ejecutar stormController.startStorm", e);
                    player.sendMessage("§cError al iniciar la tormenta. Mira la consola del servidor para más detalles.");
                }

            } else if (action.equals("remove")) {
                long segundos;
                try {
                    segundos = Long.parseLong(args[1]);
                } catch (NumberFormatException nfe) {
                    player.sendMessage("§cEl valor '" + args[1] + "' no es un número válido.");
                    return;
                }

                if (!StormController.isStormActive()) {
                    player.sendMessage("§cNo hay una tormenta activa.");
                    return;
                }

                try {
                    long timeLeft = StormController.removeTime(segundos);
                    player.sendMessage("§aHas restado " + segundos + " segundos. Tiempo restante: " + timeLeft + " segundos.");
                } catch (Exception e) {
                    Bukkit.getLogger().log(Level.SEVERE, "Error al ejecutar stormController.removeTime", e);
                    player.sendMessage("§cError al restar tiempo. Mira la consola del servidor para más detalles.");
                }

            } else {
                player.sendMessage("§cComando desconocido. Usa 'set' o 'remove'.");
            }

        } catch (Exception e) {
            Bukkit.getLogger().log(Level.SEVERE, "Error ejecutando /storm", e);
            player.sendMessage("§cError en el comando. Revisa la consola del servidor.");
        }
    }

    @Subcommand("day")
    @CommandCompletion("day <día> @nothing")
    @Syntax("<nuevoDía>")
    public void setDay(Player player, int day) {
        ConfigData.setConfigValue("day", String.valueOf(day));
        player.sendMessage(chatcolor("&aSe establecio correctamente el día &c" + ConfigData.getDay() + "&a."));
    }


    @Subcommand("remove")
    @CommandCompletion("eliminate|reset <duration> @nothing")
    public void onRemoveEntities(CommandSender sender, @Optional String worldName, @Optional String type, String action) {
        if (action.equalsIgnoreCase("eliminate")) {
            World world = (worldName != null) ? Bukkit.getWorld(worldName) : sender.getServer().getWorlds().get(0);

            if (world == null) {
                sender.sendMessage("\u00a7cEl mundo especificado no existe.");
                return;
            }

            EntityType filterType = null;
            if (type != null) {
                try {
                    filterType = EntityType.valueOf(type.toUpperCase());
                } catch (IllegalArgumentException e) {
                    sender.sendMessage("\u00a7cEl tipo de entidad especificado no es válido. Ejemplo: ZOMBIE, CREEPER.");
                    return;
                }
            }

            int removedCount = 0;
            for (Entity entity : world.getEntities()) {
                if (filterType == null || entity.getType() == filterType) {
                    entity.remove();
                    removedCount++;
                }
            }

            sender.sendMessage(String.format("\u00a7aSe han eliminado %d entidades%s del mundo %s.",
                    removedCount, (filterType != null ? " del tipo " + filterType.name() : ""), world.getName()));
        }
    }
    @Subcommand("item")
    @CommandCompletion("cafeina|totempesadillas|helaodepepesadillas|@nothing")
    public void item(Player sender, String[] args) {
        if (args.length == 0) {
            sender.sendMessage(command("&eUso correcto del comando: &f/twi item <item> <cantidad>"));
            return;
        }

        int cantidad = 1;
        if (args.length > 1) {
            try {
                cantidad = Integer.parseInt(args[1]);
                if (cantidad <= 0 || cantidad > 576) {
                    sender.sendMessage(command("&cError: La cantidad debe estar entre 1 y 576."));
                    return;
                }
            } catch (NumberFormatException e) {
                sender.sendMessage(command("&cError: La cantidad debe ser un número válido."));
                return;
            }
        }
        try {
            for (int i = 1; i <= cantidad; i++) {
                giveitem(args, sender, i, i == cantidad);
            }
        } catch (NumberFormatException e) {
            sender.sendMessage(command("&cError: Debes ingresar una cantidad en números enteros."));
        }
    }

    public void giveitem(String args[], Player sender, int cantidad, boolean isLast) {
        Item item = (Item) sender.getWorld().spawnEntity(sender.getLocation(), EntityType.DROPPED_ITEM, false);
        ItemStack defaultItem = new ItemStack(Material.AIR);
        item.setItemStack(defaultItem);
        switch (args[0].toLowerCase()) {
            case "cafeina" -> registerItem(sender, item, ItemGlobal.Cafeina(), cantidad, isLast);
            case "totempesadillas" -> registerItem(sender, item, ItemGlobal.totemdepesadillascongeladasxdxd(), cantidad, isLast);
            case "helaodepepesadillas" -> registerItem(sender, item, ItemGlobal.heladodepepesadillas(), cantidad, isLast);

            default -> registerItem(sender, item, defaultItem, cantidad, isLast);
        }
    }

    public static void registerItem(Player sender, Item item, ItemStack itemStack, int cantidad, boolean isLast) {
        ItemStack itemToGive = new ItemStack(itemStack);
        item.setItemStack(itemToGive);

        if (isLast) {
            sender.sendMessage(command("&eItem dado: &r&l" + itemToGive.getItemMeta().getDisplayName() + " &ex" + cantidad));
        }
    }
    @Subcommand("rank")
    @CommandCompletion("@rankList @players @nothing")
    public void setRank(Player sender, String[] args) {
        if (args.length == 0) return;
        try {
            Player target = Bukkit.getPlayer(args[1]);
            assert target != null;
            PlayerData.setPlayerData(target, "rank", args[0]);
            sender.sendMessage(chatcolor("&aTu rango ha sido cambiado a &r" + Ranks.getRank(target) + "&a."));
            Ranks.setRank(target);
        } catch (Exception e) {
            sender.sendMessage(chatcolor("&cError en el comando."));
            sender.sendMessage(chatcolor("&c/insomnia rank <rango> <jugador>"));
        }
    }
}

