package quest.highworld.command.server;

import quest.highworld.Highworld;
import quest.highworld.command.HighworldCommand;
import quest.highworld.data.Rank;

public class StopCommand extends HighworldCommand {

    public StopCommand() {
        super("stop", "shutdown");
        setRankRequired(Rank.ADMINISTRATOR);

        setDefaultExecutor(((sender, context) -> {Highworld.getInstance().shutdown();}));

    }


}
