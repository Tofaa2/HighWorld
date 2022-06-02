package quest.highworld.factory.itemstack;

import de.tr7zw.nbtapi.NBTItem;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import quest.highworld.factory.itemstack.ability.ItemAbility;
import quest.highworld.factory.itemstack.ability.ItemAbilityInteractionType;
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

    @Getter private final NBTItem nbtItemClone;

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
        this.nbtItemClone = new NBTItem(this);
//        nbtItem.setString("id", id);
//        nbtItem.setString("Ability", ItemAbility.FIREBALL_LARGE_SHOOT.name());
//        nbtItem.setString("Ability-Interaction", ItemAbilityInteractionType.ENTITY_HIT.name());
//        nbtItem.getItem();

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

    protected void setAbility(ItemAbility ability, ItemAbilityInteractionType interactionType, int cooldown){
        nbtItemClone.setString("Ability", ability.name());
        nbtItemClone.setString("Ability-Interaction", interactionType.name());
        nbtItemClone.setInteger("Ability-Cooldown", cooldown);
    }

    public ItemStack getNBTItem(){
        return nbtItemClone.getItem();
    }


}
