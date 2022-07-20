package quest.highworld.gui.guis.player;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import quest.highworld.HighWorld;
import quest.highworld.database.StatsManager;
import quest.highworld.gui.GUI;
import quest.highworld.utilities.Strings;

import java.util.ArrayList;
import java.util.List;

public class PlayerGUI extends GUI {

    private final Player player;

    public PlayerGUI(Player player){
        super("Player Info", 6);
        this.player = player;
    }


    @Override
    public void setContents() {

        ItemStack head = new ItemStack(Material.SKULL_ITEM, 1, (byte) 3);
        SkullMeta headMeta = (SkullMeta) head.getItemMeta();
        headMeta.setOwner(player.getName());
        List<String> lore = new ArrayList<>();
        lore.add(" ");
        lore.add(Strings.cc("&7Level: &e" + HighWorld.getInstance().getStatsManager().getStat(player, StatsManager.Stat.PLAYER_LEVEL)));
        lore.add(Strings.cc("&7XP: &e" + HighWorld.getInstance().getStatsManager().getStat(player, StatsManager.Stat.PLAYER_XP)));
        lore.add(" ");
        lore.add(Strings.cc("&7Health: &a" + HighWorld.getInstance().getStatsManager().getStat(player, StatsManager.Stat.MAX_HEALTH)));
        lore.add(Strings.cc("&7Defense: &a" + HighWorld.getInstance().getStatsManager().getStat(player, StatsManager.Stat.DEFENSE)));
        lore.add(Strings.cc("&7Mana: &b" + HighWorld.getInstance().getStatsManager().getStat(player, StatsManager.Stat.MAX_MANA)));
        lore.add(" ");
        lore.add(Strings.cc("&7Strength: &c" + HighWorld.getInstance().getStatsManager().getStat(player, StatsManager.Stat.STRENGTH)));
        lore.add(Strings.cc("&7Critical Chance: &9" + HighWorld.getInstance().getStatsManager().getStat(player, StatsManager.Stat.CRITICAL_CHANCE)) + "%");
        lore.add(Strings.cc("&7Critical Damage: &9" + HighWorld.getInstance().getStatsManager().getStat(player, StatsManager.Stat.CRITICAL_DAMAGE)) + "%");
        lore.add(" ");
        lore.add(Strings.cc("&7Speed: &f" + HighWorld.getInstance().getStatsManager().getStat(player, StatsManager.Stat.SPEED)));

        headMeta.setLore(lore);
        headMeta.setDisplayName(Strings.cc("&e" + player.getName()));
        head.setItemMeta(headMeta);

        getInventory().setItem(13, head);
    }

    @Override
    public void onClick(InventoryClickEvent event) {

    }
}
