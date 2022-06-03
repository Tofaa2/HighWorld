package quest.highworld.factory.itemstack;

import de.tr7zw.nbtapi.NBTItem;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import quest.highworld.utilities.Strings;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractHighWorldItem extends ItemStack {

    @Getter protected NBTItem nbtStack;

    @Getter private final String name;
    @Getter private final List<String> description;

    private final Type type;
    @Getter private final Rarity rarity;
    @Getter private final Material material;

    @Getter private final int levelRequirement;

    public AbstractHighWorldItem(Type type, Material material, Rarity rarity, String name, List<String> description, int levelRequirement, boolean isGlowing) {
        super(material);
        this.type = type;
        this.rarity = rarity;
        this.name = name;
        this.description = description;
        this.material = material;
        this.levelRequirement = levelRequirement;
        ItemMeta meta = getItemMeta();
        meta.setDisplayName(Strings.cc(name));
        if (isGlowing){meta.addEnchant(Enchantment.DURABILITY, 1, true);meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);}
        setItemMeta(meta);
        this.nbtStack = new NBTItem(this);
    }

    public AbstractHighWorldItem(Type type, Material material, Rarity rarity, String name, List<String> description, int levelRequirement) {
        this(type, material, rarity, name, description, levelRequirement, false);
    }

    public AbstractHighWorldItem(Type type, Material material, Rarity rarity, String name, List<String> description) {
        this(type, material, rarity, name, description, 0);
    }

    public abstract List<String> addToLore();

    protected void buildLore(){
        List<String> lore = new ArrayList<>();
        lore.add(Strings.cc("&8&m----------- &r&8[ INFORMATION ] &m-----------"));
        lore.add(Strings.cc("&7Rarity: " + rarity.getPrefix()));
        lore.add(Strings.cc("&7Type: " + type.getName()));
        lore.add(" ");
        for (String line : description){
            lore.add(Strings.cc(line));
        }
        lore.add(" ");
        if (levelRequirement > 0){
            lore.add(Strings.cc("&cRequires Level " + levelRequirement + "+ to use."));
            lore.add(" ");
        }
        lore.add(" ");
        lore.addAll(addToLore());

        ItemMeta meta = getItemMeta();
        meta.setLore(lore);
        setItemMeta(meta);
        this.nbtStack = new NBTItem(this);
    }




}
