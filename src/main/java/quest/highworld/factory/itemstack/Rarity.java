package quest.highworld.factory.itemstack;

import lombok.Getter;
import org.bukkit.ChatColor;

public enum Rarity {

    COMMON("&f", "COMMON"),
    UNCOMMON("&a", "UNCOMMON"),
    RARE("&b", "RARE"),
    EPIC("&5", "EPIC"),
    LEGENDARY("&6", "LEGENDARY"),
    MYTHIC("&d", "MYTHIC");

    @Getter private final String prefix;
    @Getter private final String cc;

    Rarity(String cc, String prefix) {
        this.cc = ChatColor.translateAlternateColorCodes('&', cc + "&l");
        this.prefix = cc + ChatColor.translateAlternateColorCodes('&', prefix);
    }



}
