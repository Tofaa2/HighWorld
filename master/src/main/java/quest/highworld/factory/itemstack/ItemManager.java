package quest.highworld.factory.itemstack;

import lombok.Getter;
import org.bukkit.inventory.ItemStack;
import quest.highworld.HighWorld;
import quest.highworld.factory.itemstack.type.material.leather.BoarSkin;

import java.util.HashMap;

public class ItemManager {


    // Materials can be cached instead of creating new ones every time
    @Getter private final HashMap<String, AbstractHighWorldItem> materials;

    public ItemManager() {
        materials = new HashMap<>();

        addItem("boar_skin", new BoarSkin());

    }

    public void addItem(String name, AbstractHighWorldItem item) {
        HighWorld.getInstance().getLogger().info("Adding Item ID: " + name);
        materials.put(name, item);
    }

    public ItemStack getItem(String name) {
        return materials.get(name);
    }



}
