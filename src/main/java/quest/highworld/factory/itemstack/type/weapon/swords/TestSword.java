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


    public TestSword(){
        super(Type.SWORD, Material.IRON_AXE, Rarity.RARE, "&cWoah", List.of("&cWoah"), 10, 100,
                List.of(SpecialAbility.FIREBALL_LARGE_SHOOT), List.of(PassiveAbility.UNDEAD_EXPERT),
                new HashMap<>(){{
                    put(ItemStat.STRENGTH, Pair.of(0, 1));
                    put(ItemStat.DAMAGE, Pair.of(0, 1));
                }},
                new HashMap<>(){{
                    put(Enchantment.SHARPNESS, 100);
                }});
    }



}
