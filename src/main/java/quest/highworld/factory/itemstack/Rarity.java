package quest.highworld.factory.itemstack;

import lombok.Getter;
import quest.highworld.utilities.Strings;

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
        this.cc = Strings.cc(cc);
        this.prefix = Strings.cc(cc + "&l" + prefix);
    }



}
