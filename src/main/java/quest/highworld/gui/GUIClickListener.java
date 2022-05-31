package quest.highworld.gui;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.InventoryHolder;
import quest.highworld.event.BukkitHighWorldListener;

public class GUIClickListener extends BukkitHighWorldListener {

    @EventHandler(priority = EventPriority.LOW)
    public void onInventoryClick(InventoryClickEvent event) {
        InventoryHolder holder = event.getInventory().getHolder();
        if (!(holder instanceof GUI)) return;
        event.setCancelled(true);
        GUI gui = (GUI) holder;
        gui.playClick((Player) event.getWhoClicked());
        if (gui.getInventory().getItem(event.getSlot()).isSimilar(GUI.close())){
            event.getWhoClicked().closeInventory();
            return;
        }
        gui.onClick(event);
    }

}
