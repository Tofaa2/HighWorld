package quest.highworld.factory.itemstack.ability.special;

import lombok.Getter;
import quest.highworld.factory.itemstack.ability.ItemAbilityInteractionType;
import quest.highworld.utilities.Strings;

import java.util.Collections;
import java.util.List;

public enum SpecialAbility {

    FIREBALL_MINI_SHOOT("Small Fireball", Collections.singletonList("Shoot a &aSmall Fireball"), ItemAbilityInteractionType.RIGHT_CLICK),
    FIREBALL_LARGE_SHOOT("Large Fireball", Collections.singletonList("Shoot a &aLarge Fireball"), ItemAbilityInteractionType.RIGHT_CLICK);


    @Getter private final String prefix;
    @Getter private final List<String> description;
    @Getter private final ItemAbilityInteractionType interactionType;
    SpecialAbility(String prefix, List<String> description, ItemAbilityInteractionType interactionType) {
        this.prefix = Strings.cc(prefix);
        this.description = description;
        this.interactionType = interactionType;
    }


}
