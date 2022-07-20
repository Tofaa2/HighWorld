package quest.highworld.command.worldedit;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.minestom.server.entity.Player;
import net.minestom.server.inventory.TransactionOption;
import net.minestom.server.item.ItemStack;
import net.minestom.server.item.Material;
import quest.highworld.command.HighworldCommand;
import quest.highworld.data.Rank;

import java.util.List;

public class WandCommand extends HighworldCommand {
    public WandCommand() {
        super("wand", "we");
        setRankRequired(Rank.ADMINISTRATOR);

        this.setDefaultExecutor((sender, args) ->{

            if (!(sender instanceof Player player)) {sender.sendMessage(Component.text("You have to be a player to use this command", NamedTextColor.RED)); return;}

            ItemStack wand = ItemStack.of(Material.WOODEN_AXE).withMeta((meta) -> {

                meta.displayName(Component.text("Wand", NamedTextColor.AQUA));
                meta.lore(List.of(
                        Component.space(),
                        Component.text("Left click to select position 1", NamedTextColor.AQUA),
                        Component.text("Right click to select position 2", NamedTextColor.AQUA)
                ));
            });
            player.getInventory().addItemStack(wand, TransactionOption.ALL);
        });

    }


}
