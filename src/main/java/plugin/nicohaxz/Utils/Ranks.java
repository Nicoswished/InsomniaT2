package plugin.nicohaxz.Utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Team;

import java.util.ArrayList;
import java.util.List;

import static plugin.nicohaxz.Utils.Utils.c;

public class Ranks {
    private static Team getScoreboardRank(Rangos rank){
        try{
            Team newTeam = Bukkit.getScoreboardManager().getMainScoreboard().registerNewTeam(rank.priority + "-" + rank.name());
            newTeam.setPrefix(c(rank.prefix));
            return newTeam;
        } catch (Exception e){
            return Bukkit.getScoreboardManager().getMainScoreboard().getTeam(rank.priority + "-" + rank.name());
        }
    }

    public static void setRank(Player target){
        Team team = getScoreboardRank(getRank(target));
        team.addEntry(target.getName());
    }
    public static void DeathRank(Player target, Rangos rank) {
        Team currentTeam = getScoreboardRank(getRank(target));
        if (currentTeam != null) {
            currentTeam.removeEntry(target.getName());
        }

        // Luego, asigna el nuevo rango
        Team team = getScoreboardRank(rank);
        team.addEntry(target.getName());
    }

    public static String getPrefix(Player target){
        return c(getRank(target).prefix);
    }

    public static Rangos getRank(Player target){
        String rank = PlayerData.getRank(target);
        for(Rangos r : Rangos.values()){
            if(r.name().equalsIgnoreCase(rank)){
                return r;
            }
        }
        return Rangos.USER;
    }
    public static List<String> listaDeRangos(){
        List<String> rangos = new ArrayList<>();
        for(Rangos r : Rangos.values()){
            rangos.add(r.name());
        }
        return rangos;
    }

    public enum Rangos{

        ADMIN(c("<GRADIENT:f12711>&lAdmin </GRADIENT:f5af19>\n"), "01"),
        DEV(c("<GRADIENT:00c6ff>&lDeveloper </GRADIENT:0072ff>\n"), "02"),
        HOST(c("<GRADIENT:ff9a9e>&lHost </GRADIENT:fad0c4>\n"), "03"),
        MOD(c("<GRADIENT:8E2DE2>&lModerador </GRADIENT:4A00E0>"), "04"),
        USER(c("<GRADIENT:11998e>&lMiembro </GRADIENT:38ef7d>\n"), "05");

        private final String prefix;
        private final String priority;

        Rangos(String prefix, String priority){
            this.prefix = prefix;
            this.priority = priority;
        }
    }

}

