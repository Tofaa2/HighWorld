package quest.highworld.commands.item;

import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import quest.highworld.commands.Command;
import quest.highworld.configuration.Messages;
import quest.highworld.factory.itemstack.ability.ItemStat;
import quest.highworld.utilities.Strings;

import java.util.ArrayList;
import java.util.List;

public class ItemCommand implements Command {

    @Override public String getName() {return "item";}

    @Override public String getDescription() {return "Gives you a list of stuff to do with items (VERY WIP)";}

    @Override public String getUsage() {return "item update/listtags/give";}

    @Override public Integer minArgs() {return 1;}

    @Override public Permission getPermission() {return Permission.ADMIN;}

    @Override public boolean isSingleton() {return false;}

    @Override public List<String> getAliases() {return List.of("i", "items");}

    @Override
    public void execute(Player player, String label, String[] args) {

        if (args[0].equalsIgnoreCase("update")) {
            if (args.length < 3) {player.sendMessage(Strings.cc("&c/item update <tag> <integer>")); return;}
            String tagStr = args[1];
            ItemStat tag = ItemStat.getStat(tagStr);
            if (tag == null) {player.performCommand("highworld item listtags"); return;}

            ItemStack bukkitStack = player.getInventory().getItemInHand();
            if (bukkitStack == null || bukkitStack.getType() == Material.AIR) {player.sendMessage(Strings.cc("&cYou must be holding an item to update it")); return;}

            try {
                int value = Integer.parseInt(args[2]);
                NBTItem nbtStack = new NBTItem(bukkitStack);
                nbtStack.setInteger(tag.getNbtKey(), value);
                nbtStack.applyNBT(bukkitStack);
                updateLore(bukkitStack, tag, value);

                player.sendMessage(Strings.cc("&aSuccessfully updated the tag " + tag.getNbtKey() + " to " + value));
            }
            catch (NumberFormatException e) {
                player.sendMessage(Strings.cc("&cThe value must be an integer"));
            }




        }
        else if (args[0].equalsIgnoreCase("listtags")) {
            player.sendMessage(Strings.cc(Messages.PREFIX + "&7Available tags: (CASE SENSITIVE)"));
            for (ItemStat tag : ItemStat.values()) {
                player.sendMessage(Strings.cc("&7- &a" + tag.getNbtKey()));
            }
        }

    }



    private void updateLore(ItemStack item, ItemStat stat, int value) {
        List<String> lore = item.getItemMeta().getLore();
        ItemMeta meta = item.getItemMeta();
        List<String> rebuilt = new ArrayList<>();
        for (String line : lore) {

            if (line.contains(stat.getPrefix())) {
                String[] split = line.split(": ");
                split[1] = stat.getCc() + value;
                rebuilt.add(Strings.cc("&7" + split[0] + ": " + split[1]));
            }
            else {
                rebuilt.add(Strings.cc(line));
            }
        }

        meta.setLore(rebuilt);
        item.setItemMeta(meta);
    }

}
