package quest.highworld.command.player;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.minestom.server.command.ConsoleSender;
import net.minestom.server.command.builder.arguments.ArgumentEnum;
import net.minestom.server.command.builder.arguments.ArgumentType;
import net.minestom.server.entity.Player;
import quest.highworld.command.HighworldCommand;
import quest.highworld.data.Rank;
import quest.highworld.entity.type.HighworldMob;

public class SpawnMobCommand extends HighworldCommand {

    public SpawnMobCommand() {
        super("spawnmob", "mob");
        this.setRankRequired(Rank.ADMINISTRATOR);

        ArgumentEnum<HighworldMob> mob = ArgumentType.Enum("mob", HighworldMob.class);
        mob.setCallback(((sender, exception) -> {
            sender.sendMessage(Component.text("Invalid mob.", NamedTextColor.RED));
        }));

        this.addSyntax((sender, args) -> {

            if (sender instanceof ConsoleSender) {
                sender.sendMessage(Component.text("This command is for players only!", NamedTextColor.RED));
                return;
            }
            Player player = (Player) sender;
            HighworldMob enumMob = args.get(mob);
            enumMob.spawn(player.getPosition());
        }, mob);

    }
}
