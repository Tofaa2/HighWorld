package quest.highworld.factory.itemstack;

import lombok.Getter;
import org.apache.commons.lang.WordUtils;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import quest.highworld.utilities.Strings;

import java.util.ArrayList;
import java.util.List;

public class HighWorldItem extends ItemStack {


    @Getter private final Material material;
    @Getter private final int amount;
    @Getter private final Rarity rarity;
    @Getter private final String id;
    @Getter private final String name;
    @Getter private final List<String> lore;

    public HighWorldItem(String id, Material material, String name, List<String> lore,  int amount, Rarity rarity){
        super(material, amount);
        this.material = material;
        this.amount = amount;
        this.rarity = rarity;
        this.id = id;
        this.name = name;
        this.lore = updatedLore(lore);

        ItemMeta meta = this.getItemMeta();
        meta.setDisplayName(Strings.cc(rarity.getCc() + name));
        meta.setLore(this.lore);
        this.setItemMeta(meta);


    }

    protected List<String> updatedLore(List<String> a){
        List<String> b = new ArrayList<>();
        for (String s : a){
            b.add(Strings.cc(s));
        }
        b.add(Strings.cc(""));
        b.add(Strings.cc(rarity.getPrefix()));
        return b;
    }



}
