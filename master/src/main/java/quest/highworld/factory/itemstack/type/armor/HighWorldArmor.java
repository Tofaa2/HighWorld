package quest.highworld.factory.itemstack.type.armor;

import org.apache.commons.lang3.tuple.Pair;
import org.bukkit.Material;
import quest.highworld.factory.itemstack.AbstractHighWorldItem;
import quest.highworld.factory.itemstack.Rarity;
import quest.highworld.factory.itemstack.Type;
import quest.highworld.factory.itemstack.ability.ItemStat;
import quest.highworld.factory.itemstack.ability.passive.PassiveAbility;
import quest.highworld.factory.itemstack.ability.special.SpecialAbility;
import quest.highworld.factory.itemstack.enchantment.Enchantment;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class HighWorldArmor extends AbstractHighWorldItem {

    protected HashMap<ItemStat, Pair<Integer, Integer>> armorStats;
    protected Collection<SpecialAbility> specialAbilities;
    protected Collection<PassiveAbility> passiveAbilities;
    protected HashMap<Enchantment, Integer> enchantments;

    public HighWorldArmor(Type type,
                          Material material,
                          Rarity rarity,
                          String name,
                          List<String> description,
                          int levelRequirement,
                          int durability,
                          List<SpecialAbility> specialAbilities,
                          List<PassiveAbility> passiveAbilities,
                          HashMap<ItemStat, Pair<Integer, Integer>> armorStats,
                          HashMap<Enchantment, Integer> enchantments){
        super(type, material, rarity, name, description, levelRequirement);
        this.armorStats = armorStats;
        this.enchantments = enchantments;
        this.specialAbilities = specialAbilities;
        this.passiveAbilities = passiveAbilities;
    }

    @Override
    public List<String> addToLore() {
        return null;
    }
}
