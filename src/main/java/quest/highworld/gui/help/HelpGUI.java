package quest.highworld.gui.help;

import org.bukkit.event.inventory.InventoryClickEvent;
import quest.highworld.gui.GUI;

public class HelpGUI extends GUI {

    public HelpGUI(){
        super("&c&lHelp", 6);
    }

    @Override
    public void setContents() {
    }

    @Override
    public void onClick(InventoryClickEvent event) {
        int slot = event.getSlot();
        switch (slot){

        }
    }
}
