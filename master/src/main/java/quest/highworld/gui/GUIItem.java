package quest.highworld.gui;

import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.List;

import lombok.Getter;
import org.bukkit.inventory.meta.ItemMeta;
import quest.highworld.utilities.Strings;

public abstract class GUIItem extends ItemStack {

    @Getter private final String name;
    @Getter private final int amount;
    @Getter private final List<String> lore;
    @Getter private final Material material;

    public GUIItem(Material material, String name, int amount, List<String> lore){
        super(material, amount);
        this.name = name;
        this.amount = amount;
        this.lore = lore;
        this.material = material;

        ItemMeta meta = getItemMeta();
        meta.setDisplayName(Strings.cc(name));
        lore.forEach(line -> line = Strings.cc(line));
        meta.setLore(lore);
        setItemMeta(meta);
    }


    public abstract void onClick(InventoryClickEvent event);



}
