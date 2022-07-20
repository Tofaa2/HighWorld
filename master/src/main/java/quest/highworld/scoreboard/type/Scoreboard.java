package quest.highworld.scoreboard.type;

import org.bukkit.entity.Player;

public interface Scoreboard {


    boolean display(String title);

    ScoreboardHandler getHandler();

    Scoreboard setHandler(ScoreboardHandler handler);

    Player getHolder();

}
