package quest.highworld.factory.itemstack.ability;

import lombok.Getter;
import quest.highworld.utilities.Strings;

public enum ItemStat {

    HEALTH("&a", "Health", "Health"),
    DEFENSE("&a", "Defense", "Defense"),

    DAMAGE("&c", "Damage", "Damage"),
    STRENGTH("&c", "Strength", "Strength"),

    MANA("&9", "Mana", "Mana"),

    CRIT_CHANCE("&b", "Crit Chance", "Crit-Chance"),
    CRIT_DAMAGE("&b", "Crit Damage", "Crit-Damage"),

    SPEED("&e", "Speed", "Speed");

    @Getter private final String prefix;
    @Getter private final String cc;
    @Getter private final String nbtKey;

    ItemStat(String cc, String prefix, String nbtKey) {
        this.cc = Strings.cc(cc);
        this.prefix = Strings.cc(prefix);
        this.nbtKey = nbtKey;
    }

    public static ItemStat getStat(String name) {
        for(ItemStat stat : ItemStat.values()) {
            if(stat.nbtKey.equals(name)) {
                return stat;
            }
        }
        return null;
    }

}
