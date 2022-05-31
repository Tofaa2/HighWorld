package quest.highworld.gui;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

public abstract class GUI implements InventoryHolder {

    private final Inventory inventory;
    @Getter private final String title;
    @Getter private final int rows; // Rows to not avoid confusion with size (9 * rows)



    public GUI(String title, int rows){
        this.title = title;
        this.rows = rows;
        inventory = Bukkit.createInventory(this, rows * 9, title);
    }


    public abstract void setContents();
    public abstract void onClick(InventoryClickEvent event);

    public void open(Player player) {
        player.getOpenInventory().close();
        fill();
        setContents();
        player.openInventory(inventory);
        playOpen(player);
    }

    private void fill() {
        for (int i = 0; i < inventory.getSize(); i++) {
            inventory.setItem(i, null); //TODO: Add items
        }

    }


    @Override
    public Inventory getInventory() {
        return inventory;
    }

    protected void playClick(Player player) {
        player.playSound(player.getLocation(), Sound.CLICK, 1, 1);
    }

    protected void playOpen(Player player) {
        player.playSound(player.getLocation(), Sound.CHEST_OPEN, 1, 1);
    }
}
