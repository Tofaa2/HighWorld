package quest.highworld.factory.itemstack.weapon;

import org.bukkit.Material;
import quest.highworld.factory.itemstack.HighWorldItem;
import quest.highworld.factory.itemstack.Rarity;

import java.util.Collections;

public class RightClickSword extends HighWorldItem {
    public RightClickSword() {
        super("test_sword", Material.IRON_SWORD, "Right CLick", Collections.emptyList(), 1, Rarity.LEGENDARY);
    }
}
