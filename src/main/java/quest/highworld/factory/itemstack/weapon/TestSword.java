package quest.highworld.factory.itemstack.weapon;

import org.bukkit.Material;
import quest.highworld.factory.itemstack.HighWorldItem;
import quest.highworld.factory.itemstack.Rarity;

import java.util.Arrays;

public class TestSword extends HighWorldItem {




    public TestSword() {
        super("test_sword", Material.IRON_SWORD, "Test Sword", Arrays.asList(), 1, Rarity.LEGENDARY);
    }




}
