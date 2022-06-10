package quest.highworld.factory.itemstack;

import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class LootItem {

    private final AbstractHighWorldItem dropItem;
    private final int minDrops;
    private final int maxDrops;
    private final float chance;
    private static final Random random = new Random();

    public LootItem(AbstractHighWorldItem dropItem, int minDrops, int maxDrops, float chance) {
        this.dropItem = dropItem;
        this.minDrops = minDrops;
        this.maxDrops = maxDrops;
        this.chance = chance;
    }

    public LootItem(AbstractHighWorldItem item, int chance) {
        this(item, 1, 1, chance);
    }


    public void tryDrop(Location location){
        if (Math.random() * 101 > chance) return;

        // Performance!
        ItemStack clone = this.dropItem.clone();
        int amount = random.nextInt(maxDrops - minDrops + 1) + minDrops;
        clone.setAmount(amount);
        location.getWorld().dropItemNaturally(location, clone);
    }


}
