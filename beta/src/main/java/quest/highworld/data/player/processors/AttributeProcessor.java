package quest.highworld.data.player.processors;

import net.minestom.server.entity.Player;
import net.minestom.server.item.ItemStack;
import net.minestom.server.tag.Tag;
import quest.highworld.Highworld;
import lombok.Getter;

/*
    Class that handles all the player's attributes
    This class calculates the damage a player can deal at the current moment.
    This class works together with DataHelper
    Since data helper only shows external buffs, this class attributes combines player's held item, armor, etc.
    Damage formula: ( DMG * (1 + ( STR / 100 ) ) - (DMG * (DEF/(100+DEF)))
 */
public class AttributeProcessor {

    private final Player player;
    public AttributeProcessor(final Player player) {this.player = player;}


    // These variables will be the ones the server uses, the database will just be a middle man for stuff outside of player usables
    // These will be synced with the database ones whenever needed
    @Getter private int strength;
    @Getter private int critChance;
    @Getter private int critDamage;

    @Getter private int maxHealth;
    @Getter private int health;
    @Getter private int defense;
    @Getter private int maxMana;
    @Getter private int mana;

    public void setupLogin() {
        refreshStats();
        health = maxHealth;
        mana = maxMana;
    }


    public void refreshStats() {

        strength = Highworld.getInstance().getDataHelper().getStrength(player);
        critChance = Highworld.getInstance().getDataHelper().getCriticalChance(player);
        critDamage = Highworld.getInstance().getDataHelper().getCriticalDamage(player);
        maxHealth = Highworld.getInstance().getDataHelper().getMaxHealth(player);
        maxMana = Highworld.getInstance().getDataHelper().getMaxMana(player);
        defense = Highworld.getInstance().getDataHelper().getDefense(player);

        final ItemStack held = player.getItemInMainHand();
        final ItemStack helmet = player.getHelmet();
        final ItemStack chestplate = player.getChestplate();
        final ItemStack leggings = player.getLeggings();
        final ItemStack boots = player.getBoots();

        if (!held.isAir()) {
            if (held.hasTag(Tag.Double("strength"))) {
                strength += player.getItemInMainHand().getTag(Tag.Double("strength"));
            }
            if (held.hasTag(Tag.Double("critical_chance"))) {
                critChance += player.getItemInMainHand().getTag(Tag.Double("critical_chance"));
            }
            if (held.hasTag(Tag.Double("critical_damage"))) {
                critDamage += player.getItemInMainHand().getTag(Tag.Double("critical_damage"));
            }
            if (held.hasTag(Tag.Double("max_health"))) {
                maxHealth += player.getItemInMainHand().getTag(Tag.Double("max_health"));
            }
            if (held.hasTag(Tag.Double("max_mana"))) {
                maxMana += player.getItemInMainHand().getTag(Tag.Double("max_mana"));
            }
            if (held.hasTag(Tag.Double("defense"))) {
                defense += player.getItemInMainHand().getTag(Tag.Double("defense"));
            }
        }
        if (!helmet.isAir()) {
            if (helmet.hasTag(Tag.Double("strength"))) {
                strength += player.getHelmet().getTag(Tag.Double("strength"));
            }
            if (helmet.hasTag(Tag.Double("critical_chance"))) {
                critChance += player.getHelmet().getTag(Tag.Double("critical_chance"));
            }
            if (helmet.hasTag(Tag.Double("critical_damage"))) {
                critDamage += player.getHelmet().getTag(Tag.Double("critical_damage"));
            }
            if (helmet.hasTag(Tag.Double("max_health"))) {
                maxHealth += player.getHelmet().getTag(Tag.Double("max_health"));
            }
            if (helmet.hasTag(Tag.Double("max_mana"))) {
                maxMana += player.getHelmet().getTag(Tag.Double("max_mana"));
            }
            if (held.hasTag(Tag.Double("defense"))) {
                defense += player.getItemInMainHand().getTag(Tag.Double("defense"));
            }
        }
        if (!chestplate.isAir()) {
            if (chestplate.hasTag(Tag.Double("strength"))) {
                strength += player.getChestplate().getTag(Tag.Double("strength"));
            }
            if (chestplate.hasTag(Tag.Double("critical_chance"))) {
                critChance += player.getChestplate().getTag(Tag.Double("critical_chance"));
            }
            if (chestplate.hasTag(Tag.Double("critical_damage"))) {
                critDamage += player.getChestplate().getTag(Tag.Double("critical_damage"));
            }
            if (chestplate.hasTag(Tag.Double("max_health"))) {
                maxHealth += player.getChestplate().getTag(Tag.Double("max_health"));
            }
            if (chestplate.hasTag(Tag.Double("max_mana"))) {
                maxMana += player.getChestplate().getTag(Tag.Double("max_mana"));
            }
            if (held.hasTag(Tag.Double("defense"))) {
                defense += player.getItemInMainHand().getTag(Tag.Double("defense"));
            }
        }
        if (!leggings.isAir()) {
            if (leggings.hasTag(Tag.Double("strength"))) {
                strength += player.getLeggings().getTag(Tag.Double("strength"));
            }
            if (leggings.hasTag(Tag.Double("critical_chance"))) {
                critChance += player.getLeggings().getTag(Tag.Double("critical_chance"));
            }
            if (leggings.hasTag(Tag.Double("critical_damage"))) {
                critDamage += player.getLeggings().getTag(Tag.Double("critical_damage"));
            }
            if (leggings.hasTag(Tag.Double("max_health"))) {
                maxHealth += player.getLeggings().getTag(Tag.Double("max_health"));
            }
            if (leggings.hasTag(Tag.Double("max_mana"))) {
                maxMana += player.getLeggings().getTag(Tag.Double("max_mana"));
            }
            if (held.hasTag(Tag.Double("defense"))) {
                defense += player.getItemInMainHand().getTag(Tag.Double("defense"));
            }
        }
        if (!boots.isAir()) {
            if (boots.hasTag(Tag.Double("strength"))) {
                strength += player.getBoots().getTag(Tag.Double("strength"));
            }
            if (boots.hasTag(Tag.Double("critical_chance"))) {
                critChance += player.getBoots().getTag(Tag.Double("critical_chance"));
            }
            if (boots.hasTag(Tag.Double("critical_damage"))) {
                critDamage += player.getBoots().getTag(Tag.Double("critical_damage"));
            }
            if (boots.hasTag(Tag.Double("max_health"))) {
                maxHealth += player.getBoots().getTag(Tag.Double("max_health"));
            }
            if (boots.hasTag(Tag.Double("max_mana"))) {
                maxMana += player.getBoots().getTag(Tag.Double("max_mana"));
            }
            if (held.hasTag(Tag.Double("defense"))) {
                defense += player.getItemInMainHand().getTag(Tag.Double("defense"));
            }
        }
    }



    public float getFinalDamage(int entityDefense) {
        ItemStack item = player.getItemInMainHand();
        if (item.isAir()) return calculateFist(entityDefense);
        else return calculateWeapon(item, entityDefense);
    }

    private float calculateWeapon(ItemStack item, int entityDefense) {
        float personalDamage = item.getTag(Tag.Double("damage")).floatValue();
        if (isCrit()) {
            final float apply = (critDamage / 100f) * personalDamage;
            personalDamage += apply;
        }
        return (personalDamage * (1 + (strength / 100f)) - (personalDamage * (entityDefense / (100f + entityDefense))));
    }

    private float calculateFist(int entityDefense) {
        float personalDamage = 1;
        if (isCrit()) {
            final float apply = (critDamage / 100f) * personalDamage;
            personalDamage += apply;
        }
        return (personalDamage * (1 + (strength / 100f)) - (personalDamage * (entityDefense / (100f + entityDefense))));
    }


    private boolean isCrit() {
        int random = (int) (Math.random() * 101) + 1;

        return random <= critChance;
    }


}
