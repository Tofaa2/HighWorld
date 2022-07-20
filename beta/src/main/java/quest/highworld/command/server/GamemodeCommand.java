package quest.highworld.command.server;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.minestom.server.command.builder.arguments.ArgumentEnum;
import net.minestom.server.command.builder.arguments.ArgumentType;
import net.minestom.server.command.builder.arguments.minecraft.ArgumentEntity;
import net.minestom.server.entity.GameMode;
import net.minestom.server.entity.Player;
import net.minestom.server.utils.entity.EntityFinder;
import quest.highworld.command.HighworldCommand;
import quest.highworld.data.Rank;

public class GamemodeCommand extends HighworldCommand {

    public GamemodeCommand() {
        super("gamemode", "gm");
        setRankRequired(Rank.ADMINISTRATOR);

        ArgumentEnum<GameMode> gamemodeArg = ArgumentType.Enum("gamemode", GameMode.class).setFormat(ArgumentEnum.Format.LOWER_CASED);
        ArgumentEntity playerArg = ArgumentType.Entity("player").onlyPlayers(true).singleEntity(true);

        gamemodeArg.setCallback((sender, e) -> {
            sender.sendMessage(Component.text("Invalid gamemode.", NamedTextColor.RED));
        });

        addSyntax((sender, args) -> {

            if (!(sender instanceof Player player)) {
                sender.sendMessage(Component.text("This command is for players only!", NamedTextColor.RED));
                return;
            }
            player.setGameMode(args.get(gamemodeArg));
            player.sendMessage(Component.text("Your gamemode has been set to " + args.get(gamemodeArg), NamedTextColor.GREEN));
        }, gamemodeArg);

        addSyntax((sender, args) -> {
            EntityFinder finder = args.get(playerArg);


            if (finder.findFirstEntity(sender) == null) {
                sender.sendMessage(Component.text("No player found!", NamedTextColor.RED));
                return;
            }
            Player player = (Player) finder.findFirstEntity(sender);
            player.setGameMode(args.get(gamemodeArg));
        }, gamemodeArg, playerArg);


    }

}
