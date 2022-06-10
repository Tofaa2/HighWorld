package quest.highworld.utilities;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import quest.highworld.HighWorld;
import quest.highworld.database.StatsManager;

public class PlayerUtil {


    public static void killPlayer(Player player){
        Location toSpawn = new Location(player.getWorld(), 0, 100, 0);

        HighWorld.getInstance().getStatsManager().setStat(player, StatsManager.Stat.DEATHS, HighWorld.getInstance().getStatsManager().getStat(player, StatsManager.Stat.DEATHS) + 1);
        HighWorld.getInstance().getStatsManager().setStat(player, StatsManager.Stat.HEALTH, HighWorld.getInstance().getStatsManager().getStat(player, StatsManager.Stat.MAX_HEALTH));

        player.teleport(toSpawn);
        player.sendMessage(Strings.cc("&cYou have died."));
    }


}
