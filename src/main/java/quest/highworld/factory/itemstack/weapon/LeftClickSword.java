package quest.highworld.factory.itemstack.weapon;

import org.bukkit.Material;
import quest.highworld.factory.itemstack.HighWorldItem;
import quest.highworld.factory.itemstack.Rarity;
import quest.highworld.factory.itemstack.ability.ItemAbility;
import quest.highworld.factory.itemstack.ability.ItemAbilityInteractionType;

import java.util.ArrayList;
import java.util.Collections;

public class LeftClickSword extends HighWorldItem {

    public LeftClickSword() {
        super("test_sword", Material.IRON_SWORD, "Left CLick", Collections.emptyList(), 1, Rarity.LEGENDARY);
        updatedLore(new ArrayList<>(Collections.singletonList("Left click to attack")));
        setAbility(ItemAbility.FIREBALL_LARGE_SHOOT, ItemAbilityInteractionType.LEFT_CLICK, 4);
    }
}
