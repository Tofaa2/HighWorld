package quest.highworld.command.server;

import net.kyori.adventure.audience.MessageType;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.minestom.server.command.builder.arguments.ArgumentEnum;
import net.minestom.server.command.builder.arguments.ArgumentType;
import net.minestom.server.command.builder.arguments.minecraft.ArgumentEntity;
import net.minestom.server.entity.Player;
import net.minestom.server.utils.entity.EntityFinder;
import quest.highworld.Highworld;
import quest.highworld.command.HighworldCommand;
import quest.highworld.data.Rank;

public class GrantCommand extends HighworldCommand {

    public GrantCommand() {
        super("grant");
        setRankRequired(Rank.ADMINISTRATOR);

        ArgumentEnum<Rank> rank = ArgumentType.Enum("rank", Rank.class).setFormat(ArgumentEnum.Format.LOWER_CASED);
        rank.setCallback((sender, exception) -> {
            sender.sendMessage(Component.text("Invalid rank provided!", NamedTextColor.RED));
        });

        ArgumentEntity player = ArgumentType.Entity("player").onlyPlayers(true);

        setDefaultExecutor((sender, args) -> {
            String commandName = args.getCommandName();
            sender.sendMessage(Component.text("Usage: /" + commandName + " <gamemode> [targets]", NamedTextColor.RED), MessageType.SYSTEM);
        });


        addSyntax((sender, args) -> {

            EntityFinder finder = args.get(player);
            Player p = finder.findFirstPlayer(sender);

            if (p == null) {
                sender.sendMessage(Component.text("No player found!", NamedTextColor.RED));
                return;
            }

            Rank playerRank = Highworld.getInstance().getDataHelper().getRank(p);
            Rank rankToGrant = args.get(rank);

            if (sender instanceof Player sendingPlayer) {
                Rank sendingRank = Highworld.getInstance().getDataHelper().getRank(sendingPlayer);
                if (sendingRank.weight > playerRank.weight) {
                    sender.sendMessage(Component.text("You can't grant a to a player higher rank than you!", NamedTextColor.RED));
                    return;
                }
                if (sendingRank.weight > rankToGrant.weight) {
                    sender.sendMessage(Component.text("You can't grant a rank higher than yours!", NamedTextColor.RED));
                    return;
                }
                Highworld.getInstance().getDataHelper().setRank(p, rankToGrant);
            }
            else {
                Highworld.getInstance().getDataHelper().setRank(p, rankToGrant);
            }
            sender.sendMessage(
                    Component.text(
                            "Successfully granted ", NamedTextColor.GREEN)
                            .append(Component.text(p.getUsername(), NamedTextColor.GREEN)
                                    .append(Component.text(" the rank ", NamedTextColor.GREEN)
                                            .append(Component.text(rankToGrant.name(), NamedTextColor.GREEN)))));


        }, player, rank);
    }

}
