package quest.highworld.command;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.minestom.server.command.CommandSender;
import net.minestom.server.command.builder.Command;
import net.minestom.server.entity.Player;
import quest.highworld.Highworld;
import quest.highworld.data.Rank;

public class HighworldCommand extends Command {


    private Rank rankRequired;


    public HighworldCommand(String label, String... aliases) {
        super(label, aliases);

        this.setCondition((sender, args) -> hasPermission(sender));
    }
    protected void setRankRequired(Rank rankRequired) {
        this.rankRequired = rankRequired;
    }

    public boolean hasPermission(CommandSender sender) {
        if (rankRequired == Rank.MEMBER) return true;
        if (rankRequired == null) return true;

        if (sender instanceof Player player) {
            Rank playerRank = Highworld.getInstance().getDataHelper().getRank(player);
            if (playerRank.weight > rankRequired.weight) {
                sender.sendMessage(Component.text("You do not have permission to execute this command.", NamedTextColor.RED));
                return false;
            }
            return true;
        }
        return true;
    }
}
