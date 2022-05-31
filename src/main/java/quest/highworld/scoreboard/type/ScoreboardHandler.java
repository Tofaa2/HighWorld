package quest.highworld.scoreboard.type;

import org.bukkit.entity.Player;

import java.util.List;

public interface ScoreboardHandler {

    default String getTitle(Player player) {
        return null;
    }

    List<Entry> getEntries(Player player);
}
