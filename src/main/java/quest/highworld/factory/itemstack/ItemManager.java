package quest.highworld.factory.itemstack;

import lombok.Getter;
import org.bukkit.inventory.ItemStack;
import quest.highworld.HighWorld;

import java.util.HashMap;

public class ItemManager {

    @Getter private final HashMap<String, HighWorldItem> items;

    public ItemManager() {
        items = new HashMap<>();
    }

    public void addItem(String name, HighWorldItem item) {
        HighWorld.getInstance().getLogger().info("Adding Item ID: " + name);
        items.put(name, item);
    }

    public ItemStack getItem(String name) {
        return items.get(name);
    }

}
