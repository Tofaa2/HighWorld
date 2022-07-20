package quest.highworld.factory.itemstack.ability.passive;

import lombok.Getter;
import quest.highworld.utilities.Strings;

import java.util.Arrays;
import java.util.List;

public enum PassiveAbility {

    UNDEAD_EXPERT("Undead Expert", Arrays.asList("Increases the &cdamage &7dealt to", "&cUndead &aby &c50%&a."));



    @Getter private final String prefix;
    @Getter private final List<String> description;
    PassiveAbility(String prefix, List<String> description) {
        this.prefix = Strings.cc(prefix);
        this.description = description;
    }

}
