package quest.highworld.factory.itemstack.weapon;

import org.bukkit.Material;
import quest.highworld.factory.itemstack.HighWorldItem;
import quest.highworld.factory.itemstack.Rarity;
import quest.highworld.factory.itemstack.ability.ItemAbility;
import quest.highworld.factory.itemstack.ability.ItemAbilityInteractionType;

import java.util.Collections;

public class EntityHitSword extends HighWorldItem {




    public EntityHitSword() {
        super("test_sword", Material.IRON_SWORD, "Entity Hit", Collections.emptyList(), 1, Rarity.LEGENDARY);
        setAbility(ItemAbility.FIREBALL_LARGE_SHOOT, ItemAbilityInteractionType.ENTITY_HIT, 10);
    }

}
