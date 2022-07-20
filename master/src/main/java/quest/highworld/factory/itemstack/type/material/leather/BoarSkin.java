package quest.highworld.factory.itemstack.type.material.leather;

import org.bukkit.Material;
import quest.highworld.factory.itemstack.Rarity;
import quest.highworld.factory.itemstack.type.material.HighWorldMaterial;

import java.util.List;

public class BoarSkin extends HighWorldMaterial {

    public BoarSkin() {
        super(Material.LEATHER, Rarity.COMMON, "&fBoar Skin", List.of("&7The raw, hardened skin of a boar") , 0, false);
    }

}
