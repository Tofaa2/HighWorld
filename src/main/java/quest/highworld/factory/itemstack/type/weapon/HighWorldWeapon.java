package quest.highworld.factory.itemstack.type.weapon;

import org.apache.commons.lang3.tuple.Pair;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import quest.highworld.factory.itemstack.AbstractHighWorldItem;
import quest.highworld.factory.itemstack.Rarity;
import quest.highworld.factory.itemstack.Type;
import quest.highworld.factory.itemstack.ability.ItemStat;
import quest.highworld.factory.itemstack.ability.passive.PassiveAbility;
import quest.highworld.factory.itemstack.ability.special.SpecialAbility;
import quest.highworld.factory.itemstack.ability.special.SpecialAbilityInteractionType;
import quest.highworld.factory.itemstack.enchantment.Enchantment;
import quest.highworld.utilities.Strings;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class HighWorldWeapon extends AbstractHighWorldItem {

    protected HashMap<ItemStat, Pair<Integer, Integer>> weaponStats;
    protected Collection<SpecialAbility> specialAbilities;
    protected Collection<PassiveAbility> passiveAbilities;
    protected HashMap<Enchantment, Integer> enchantments;


    public HighWorldWeapon(Type type,
                           Material material,
                           Rarity rarity,
                           String name,
                           List<String> description,
                           int levelRequirement,
                           int durability,
                           List<SpecialAbility> specialAbilities,
                           List<PassiveAbility> passiveAbilities,
                           HashMap<ItemStat, Pair<Integer, Integer>> weaponStats,
                           HashMap<Enchantment, Integer> enchantments
    ){
        super(type, material, rarity, name, description, levelRequirement);
        this.weaponStats = weaponStats;
        this.specialAbilities = specialAbilities;
        this.passiveAbilities = passiveAbilities;
        this.enchantments = enchantments;
        this.nbtStack.setInteger("Durability", durability);
        this.nbtStack.setInteger("Max-Durability", durability);
        this.nbtStack.setInteger("Reforge-Level", 0);

        for (Enchantment enchantment : enchantments.keySet()) {
            this.nbtStack.setInteger("Enchantment-" + enchantment.getID(), enchantments.get(enchantment));
        }

        this.buildLore();
    }



    @Override public List<String> addToLore() {
        List<String> lore = new ArrayList<>();

        if (!this.specialAbilities.isEmpty() || !this.passiveAbilities.isEmpty() || !this.weaponStats.isEmpty()) {
            lore.add(Strings.cc("&8&m----------- &r&8[ USAGE ] &m-----------"));
        }
        if (!weaponStats.isEmpty()){
            for (ItemStat stat : weaponStats.keySet()){
                lore.add(Strings.cc("&7" + stat.getPrefix() + ": " + stat.getCc() + weaponStats.get(stat).getLeft() + " - " + weaponStats.get(stat).getRight()));
            }
            lore.add(" ");
        }
        // Yes I know its 0(n)2 but there won't be more than 1 passive/special ability 99% of the time
        if (!passiveAbilities.isEmpty()) {
            for (PassiveAbility ability : passiveAbilities) {
                lore.add(Strings.cc("&8Passive &6" + ability.getPrefix()));
                for (String line : ability.getDescription()) {
                    lore.add(Strings.cc("&7" + line));
                }
            }
            lore.add(" ");
        }
        if (!specialAbilities.isEmpty()) {
            for (SpecialAbility ability : specialAbilities) {
                lore.add(Strings.cc("&8Special &6" + ability.getPrefix()));
                for (String line : ability.getDescription()) {
                    lore.add(Strings.cc("&7" + line));
                }
                lore.add(Strings.cc("&6&l" + ability.getInteractionType().name().replace("_", " ") + " TO ACTIVATE"));
                if (this.nbtStack.getInteger("Ability-Cooldown-" + ability.name()) > 0){
                    lore.add(Strings.cc("&8Cooldown: " + this.nbtStack.getInteger("Ability-Cooldown-" + ability.name()) + " seconds"));
                }
            }
            lore.add(" ");
        }
        if (enchantments.size() > 0){
            lore.add(Strings.cc("&8&m----------- &r&8[ ENCHANTMENTS ] &m-----------"));
            for (Enchantment enchantment : enchantments.keySet()){
                lore.add(Strings.cc("&7" + enchantment.getPrefix()));
            }
        }
        lore.add(Strings.cc("&8&m---------- &r&8[ WEAPON'S STATS ] &m----------"));
        lore.add(Strings.cc("&7Durability: &e" + this.nbtStack.getInteger("Durability") + " / " + this.nbtStack.getInteger("Max-Durability")));
        lore.add(Strings.cc("&7Reforge Level: &8[" + "&f" + this.nbtStack.getInteger("Reforge-Level") + "&8]"));
        lore.add(" ");

        return lore;
    }

    public void addSpecialAbility(SpecialAbility ability, SpecialAbilityInteractionType interactionType, int cooldown){
        nbtStack.setBoolean("Ability-", true);
        nbtStack.setString("Ability-Interaction-" + ability.name(), interactionType.name());
        nbtStack.setInteger("Ability-Cooldown-" + ability.name(), cooldown);

        specialAbilities.add(ability);
    }

    public void addPassiveAbility(PassiveAbility ability){
        nbtStack.setBoolean("Ability-Passive-" + ability.name(), true);

        passiveAbilities.add(ability);
    }

    public void addEnchantment(Enchantment ench, int level){
        nbtStack.setBoolean("Enchantment-" + ench.name(), true);
        nbtStack.setInteger("Enchantment-Level-" + ench.name(), level);

        enchantments.put(ench, level);
    }

    public ItemStack nbtStackAsBukkit(){
        return this.nbtStack.getItem();
    }
}
