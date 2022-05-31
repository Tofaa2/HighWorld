package quest.highworld.factory.itemstack;

import lombok.Getter;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public class ItemManager {

    @Getter private final HashMap<String, ItemStack> defaultItems;

    public ItemManager() {
        defaultItems = new HashMap<>();
    }

    public void addItem(String name, ItemStack item) {
        defaultItems.put(name, item);
    }

    public ItemStack getItem(String name) {
        return defaultItems.get(name);
    }

}
