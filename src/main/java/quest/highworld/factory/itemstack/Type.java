package quest.highworld.factory.itemstack;

import lombok.Getter;
import quest.highworld.utilities.Strings;

@SuppressWarnings("unused")
public enum Type {
    SWORD("&9Sword"),
    AXE("&9Axe"),
    PICKAXE("&9Pickaxe"),
    HOE("&9Hoe"),
    SHOVEL("&9Shovel"),
    BOW("&9Bow"),
    HELMET("&9Helmet"),
    CHESTPLATE("&9Chestplate"),
    LEGGINGS("&9Leggings"),
    BOOTS("&9Boots"),
    POTION("&9Potion"),
    FOOD("&9Food"),
    OTHER("&9Other"),
    BLOCK("&9Block"),
    MISC("&9Misc"),
    MATERIAL("&9Material");


    @Getter
    private final String name;
    Type(String name) {
        this.name = Strings.cc(name);
    }
}