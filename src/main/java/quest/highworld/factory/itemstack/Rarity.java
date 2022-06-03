package quest.highworld.factory.itemstack;

import lombok.Getter;
import quest.highworld.utilities.Strings;

public enum Rarity {

    COMMON("&f", "Common"),
    UNCOMMON("&a", "Uncommon"),
    RARE("&b", "Rare"),
    EPIC("&5", "Epic"),
    LEGENDARY("&6", "Legendary"),
    MYTHIC("&d", "Mythic");

    @Getter private final String prefix;
    @Getter private final String cc;

    Rarity(String cc, String prefix) {
        this.cc = Strings.cc(cc);
        this.prefix = Strings.cc(cc  + prefix);
    }



}
