package quest.highworld.command.player;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.minestom.server.command.ConsoleSender;
import net.minestom.server.command.builder.arguments.ArgumentEnum;
import net.minestom.server.command.builder.arguments.ArgumentType;
import net.minestom.server.entity.Player;
import quest.highworld.command.HighworldCommand;
import quest.highworld.data.Rank;
import quest.highworld.item.type.HighworldWeapon;

public class GiveWeaponCommand extends HighworldCommand {

    public GiveWeaponCommand() {
        super("giveweapon", "weapon");
        this.setRankRequired(Rank.ADMINISTRATOR);

        ArgumentEnum<HighworldWeapon> weapon = ArgumentType.Enum("weapon", HighworldWeapon.class);
        weapon.setCallback(((sender, exception) -> {
            sender.sendMessage(Component.text("Invalid weapon.", NamedTextColor.RED));
        }));

        this.addSyntax((sender, args) -> {

            if (sender instanceof ConsoleSender) {
                sender.sendMessage(Component.text("This command is for players only!", NamedTextColor.RED));
                return;
            }
            Player player = (Player) sender;
            HighworldWeapon enumWeapon = args.get(weapon);
            enumWeapon.give(player);
        }, weapon);

    }
}
