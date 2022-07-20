package quest.highworld.factory.itemstack.type.material;

import org.bukkit.Material;
import quest.highworld.factory.itemstack.AbstractHighWorldItem;
import quest.highworld.factory.itemstack.Rarity;
import quest.highworld.factory.itemstack.Type;

import java.util.List;

public class HighWorldMaterial extends AbstractHighWorldItem {


    public HighWorldMaterial(Material material, Rarity rarity, String name, List<String> description, int levelRequirement, boolean isGlowing) {
        super(Type.MATERIAL, material, rarity, name, description, levelRequirement, isGlowing);
    }

    @Override public List<String> addToLore() {return null;}
}
