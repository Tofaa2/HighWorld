package quest.highworld.scoreboard;


import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import quest.highworld.HighWorld;
import quest.highworld.database.StatsManager;
import quest.highworld.scoreboard.type.Scoreboard;
import quest.highworld.scoreboard.util.EntryBuilder;
import quest.highworld.scoreboard.util.HighlightedString;

import java.util.HashMap;
import java.util.UUID;

@SuppressWarnings("unused")
public class ScoreboardManager {

    private final HashMap<UUID, Scoreboard> scoreboards;
    private final HighlightedString title = new HighlightedString(" §c§lHIGHWORLD §e(BETA)", "&l&7");


    public ScoreboardManager() {
        this.scoreboards = new HashMap<>();
        BukkitTask updateLoopTask = new BukkitRunnable() {

            @Override
            public void run() {
                String titleText = title.next();
                for (UUID uuid : scoreboards.keySet()) {
                    Scoreboard scoreboard = scoreboards.get(uuid);
                    if (!scoreboard.display(titleText)) {
                        scoreboards.remove(uuid);
                    }
                }
            }
        }.runTaskTimer(HighWorld.getInstance(), 0L, 4L);
    }


    public void createScoreboard(Player player) {
        Scoreboard scoreboard = new HighWorldScoreboard(player);
        scoreboard.setHandler(player1 -> new EntryBuilder().blank()
                .next("&eInfo:                        ")
                .next("  &7Name: &e" + player1.getName())
                .next("  &7Level: &e" +  HighWorld.getInstance().getStatsManager().getIntStat(player1, StatsManager.Stat.PLAYER_LEVEL))
                .next("  &7Coins: &e" +  HighWorld.getInstance().getStatsManager().getIntStat(player1, StatsManager.Stat.COINS))
                .next("&eServer:")
                .next("  &7Floor: &e" + player.getWorld().getName().replace("floor-", ""))
                .next("  &7Highest Floor: &e" + HighWorld.getInstance().getStatsManager().getIntStat(player, StatsManager.Stat.FLOOR))
                .next("  &7Players: &e" + HighWorld.getInstance().getServer().getOnlinePlayers().size() + "&7/&e" + HighWorld.getInstance().getServer().getMaxPlayers())
                .next("  &7Ping: &e" + HighWorld.getInstance().getNMSUtil().getPing(player1))
                .next("&e")
                .next("&ehighworld.quest")
                .build());
        scoreboards.put(player.getUniqueId(), scoreboard);
    }


    public void terminateScoreboard(Player... players) {
        for (Player player : players) {
            scoreboards.remove(player.getUniqueId());
        }
    }
    public boolean isScoreboardEnabled(Player player) {
        return scoreboards.containsKey(player.getUniqueId());
    }

}
