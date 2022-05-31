package quest.highworld.scoreboard;


import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Team;
import quest.highworld.scoreboard.type.Entry;
import quest.highworld.scoreboard.type.Scoreboard;
import quest.highworld.scoreboard.type.ScoreboardHandler;
import quest.highworld.utilities.FakePlayer;
import quest.highworld.utilities.Strings;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class HighWorldScoreboard implements Scoreboard {

    private static final String TEAM_PREFIX = "Scoreboard_";
    private static int TEAM_COUNTER = 0;

    private final org.bukkit.scoreboard.Scoreboard scoreboard;
    private final Objective objective;
    protected Player holder;
    private ScoreboardHandler handler;
    private final Map<FakePlayer, Integer> entryCache = new ConcurrentHashMap<>();
    private final Table<String, Integer, FakePlayer> playerCache = HashBasedTable.create();
    private final Table<Team, String, String> teamCache = HashBasedTable.create();

    public HighWorldScoreboard(Player holder) {
        this.holder = holder;

        scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        scoreboard.registerNewObjective("board", "dummy").setDisplaySlot(DisplaySlot.SIDEBAR);
        objective = scoreboard.getObjective(DisplaySlot.SIDEBAR);

        holder.setScoreboard(scoreboard);
    }

    @Override
    public ScoreboardHandler getHandler() {
        return handler;
    }

    @Override
    public Scoreboard setHandler(ScoreboardHandler handler) {
        this.handler = handler;
        return this;
    }

    @Override
    public Player getHolder() {
        return holder;
    }

    public boolean display(String title) {
        if (!holder.isOnline()) {
            return false;
        }
        // Title
        String handlerTitle = handler.getTitle(holder);
        String finalTitle = Strings.cc(handlerTitle != null ? handlerTitle : title);
        if (!objective.getDisplayName().equals(finalTitle)) objective.setDisplayName(Strings.cc(finalTitle));
        // Entries
        List<Entry> passed = handler.getEntries(holder);
        Map<String, Integer> appeared = new HashMap<>();
        Map<FakePlayer, Integer> current = new HashMap<>();
        if (passed == null) return true;
        for (Entry entry : passed) {
            // Handle the entry
            String key = entry.getName();
            Integer score = entry.getPosition();
            if (key.length() > 48) key = key.substring(0, 47);
            String appearance;
            if (key.length() > 16) {
                appearance = key.substring(16);
            } else {
                appearance = key;
            }
            if (!appeared.containsKey(appearance)) appeared.put(appearance, -1);
            appeared.put(appearance, appeared.get(appearance) + 1);
            // Get fake player
            FakePlayer faker = getFakePlayer(key, appeared.get(appearance));
            // Set score
            objective.getScore(faker).setScore(score);
            // Update references
            entryCache.put(faker, score);
            current.put(faker, score);
        }
        appeared.clear();
        // Remove duplicated or non-existent entries
        for (FakePlayer fakePlayer : entryCache.keySet()) {
            if (!current.containsKey(fakePlayer)) {
                entryCache.remove(fakePlayer);
                scoreboard.resetScores(fakePlayer.getName());
            }
        }
        return true;
    }

    private FakePlayer getFakePlayer(String text, int offset) {
        Team team = null;
        String name;
        // If the text has a length less than 16, teams need not be created
        if (text.length() <= 16) {
            name = text + Strings.repeat(" ", offset);
        } else {
            String prefix;
            String suffix = "";
            offset++;
            // Otherwise, iterate through the string and cut off prefix and suffix
            prefix = text.substring(0, 16 - offset);
            name = text.substring(16 - offset);
            if (name.length() > 16) name = name.substring(0, 16);
            if (text.length() > 32) suffix = text.substring(32 - offset);
            // If teams already exist, use them
            for (Team other : teamCache.rowKeySet()) {
                if (other.getPrefix().equals(prefix) && other.getSuffix().equals(suffix)) {
                    team = other;
                }
            }
            // Otherwise create them
            if (team == null) {
                team = scoreboard.registerNewTeam(TEAM_PREFIX + TEAM_COUNTER++);
                team.setPrefix(prefix);
                team.setSuffix(suffix);
                teamCache.put(team, prefix, suffix);
            }
        }
        FakePlayer faker;
        if (!playerCache.contains(name, offset)) {
            faker = new FakePlayer(name, team, offset);
            playerCache.put(name, offset, faker);
            if (faker.getTeam() != null) {
                faker.getTeam().addPlayer(faker);
            }
        } else {
            faker = playerCache.get(name, offset);
            if (team != null && faker.getTeam() != null) {
                faker.getTeam().removePlayer(faker);
            }
            faker.setTeam(team);
            if (faker.getTeam() != null) {
                faker.getTeam().addPlayer(faker);
            }
        }
        return faker;
    }

    public Objective getObjective() {
        return objective;
    }

    public org.bukkit.scoreboard.Scoreboard getScoreboard() {
        return scoreboard;
    }

}
