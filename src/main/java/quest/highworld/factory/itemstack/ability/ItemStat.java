package quest.highworld.factory.itemstack.ability;

import lombok.Getter;
import quest.highworld.utilities.Strings;

public enum ItemStat {

    HEALTH("&a", "Health"),
    DEFENSE("&a", "Defense"),

    DAMAGE("&c", "Damage"),
    STRENGTH("&c", "Strength"),

    MANA("&9", "Mana"),

    CRIT_CHANCE("&b", "Crit Chance"),
    CRIT_DAMAGE("&b", "Crit Damage"),

    SPEED("&e", "Speed");

    @Getter private final String prefix;
    @Getter private final String cc;

    ItemStat(String cc, String prefix) {
        this.cc = Strings.cc(cc);
        this.prefix = Strings.cc(prefix);
    }

    public static ItemStat getStat(String name) {
        for(ItemStat stat : ItemStat.values()) {
            if(stat.name().equalsIgnoreCase(name)) {
                return stat;
            }
        }
        return null;
    }

}
