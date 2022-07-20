package quest.highworld.entity.type.loot;

import net.minestom.server.entity.Player;
import net.minestom.server.inventory.TransactionOption;
import net.minestom.server.item.ItemStack;
import java.util.Random;

public class LootItem {

    private static final Random random = new Random();

    private final ItemStack item;
    private int min = 1, max = 1;
    private final double dropChance;

    public LootItem(ItemStack item, int min, int max, double dropChance) {
        this.item = item;
        this.min = min;
        this.max = max;
        this.dropChance = dropChance;
    }

    public LootItem(ItemStack item, double dropChance) {
        this(item, 1, 1, dropChance);
    }


    public void tryGive(Player player) {
        if (Math.random() * 101 > dropChance) return;
        int amount = random.nextInt(max - min + 1) + min;
        player.getInventory().addItemStack(item.withAmount(amount), TransactionOption.ALL);
    }


}
