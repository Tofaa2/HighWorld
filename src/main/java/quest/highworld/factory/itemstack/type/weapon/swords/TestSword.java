package quest.highworld.factory.itemstack.type.weapon.swords;

import org.apache.commons.lang3.tuple.Pair;
import org.bukkit.Material;
import quest.highworld.factory.itemstack.Rarity;
import quest.highworld.factory.itemstack.Type;
import quest.highworld.factory.itemstack.ability.ItemStat;
import quest.highworld.factory.itemstack.ability.passive.PassiveAbility;
import quest.highworld.factory.itemstack.ability.special.SpecialAbility;
import quest.highworld.factory.itemstack.enchantment.Enchantment;
import quest.highworld.factory.itemstack.type.weapon.HighWorldWeapon;

import java.util.HashMap;
import java.util.List;

public class TestSword extends HighWorldWeapon {


    public TestSword(boolean rollStats){
        super(Type.SWORD, Material.IRON_AXE, Rarity.RARE, "&cWoah", List.of("&cWoah"), 10, 100,
                List.of(SpecialAbility.FIREBALL_LARGE_SHOOT), List.of(PassiveAbility.UNDEAD_EXPERT),
                new HashMap<>(){{
                    put(ItemStat.STRENGTH, rollStats ? roll(1, 10) : Pair.of(1, 100));
                    put(ItemStat.DAMAGE, rollStats ? roll(5, 50) : Pair.of(1, 100));
                }},
                new HashMap<>(){{
                    put(Enchantment.SHARPNESS, 100);
                }});
    }



}
