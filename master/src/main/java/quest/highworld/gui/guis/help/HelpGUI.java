package quest.highworld.gui.guis.help;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import quest.highworld.HighWorld;
import quest.highworld.gui.GUI;
import quest.highworld.gui.GUIItem;
import quest.highworld.utilities.Strings;

import java.util.List;

public class HelpGUI extends GUI {

    public HelpGUI(){
        super("&c&lHelp", 6);
    }

    @Override
    public void setContents() {
        ItemStack stats = new ItemStack(Material.PAPER);
        ItemMeta statsMeta = stats.getItemMeta();
        statsMeta.setDisplayName(Strings.cc("&c&lStats Information"));
        List<String> statsLore = Strings.ccList(" ", "&7Click to view information about different stats.");
        stats.setItemMeta(statsMeta);


        getInventory().setItem(11, stats);


    }

    @Override
    public void onClick(InventoryClickEvent event) {
        int slot = event.getSlot();
        switch (slot){
            case 11:
                HighWorld.getInstance().getGUIManager().getGUI("stats_info_gui").open((Player) event.getWhoClicked());
                break;
        }
    }
}
