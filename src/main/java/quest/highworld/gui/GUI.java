package quest.highworld.gui;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import quest.highworld.utilities.Strings;

import java.util.Arrays;


public abstract class GUI implements InventoryHolder {

    private final Inventory inventory;
    @Getter private final String title;
    @Getter private final int rows; // Rows to not avoid confusion with size (9 * rows)

    public GUI(String title, int rows){
        this.title = Strings.cc(title);
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
        for (int i = 0; i < inventory.getSize() - 9; i++) {
            inventory.setItem(i, filler(Material.STAINED_GLASS_PANE, (short) 7)); //TODO: Add items
        }
        int lastNine = inventory.getSize() - 9;
        for (int i = lastNine; i < inventory.getSize(); i++) {
            inventory.setItem(i, filler(Material.STAINED_GLASS_PANE, (short) 14));
        }
        // get the middle of lastNine
        int middle = lastNine + 4;
        inventory.setItem(middle, close());
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

    public static ItemStack filler(Material material, short data){
        ItemStack item = new ItemStack(material, 1, data);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(" ");
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack close(){
        ItemStack item = new ItemStack(Material.BARRIER);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§cClose");
        meta.setLore(Arrays.asList("", "§7Click to close"));
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack goBack(){
        ItemStack item = new ItemStack(Material.ARROW);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§cGo Back");
        meta.setLore(Arrays.asList("", "§7Click to go back"));
        item.setItemMeta(meta);
        return item;
    }

}
