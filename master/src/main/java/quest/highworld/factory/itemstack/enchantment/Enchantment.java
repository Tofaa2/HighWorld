package quest.highworld.factory.itemstack.enchantment;

import lombok.Getter;
import quest.highworld.utilities.Strings;

public enum Enchantment {

    SHARPNESS("Sharpness", 1);

    @Getter private final String prefix;
    @Getter private final int ID;
    Enchantment(String prefix, int id){
        this.prefix = Strings.cc(prefix);
        this.ID = id;
    }
}
