package quest.highworld.item.type;

import lombok.Getter;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.minestom.server.entity.Player;
import net.minestom.server.item.ItemStack;
import net.minestom.server.item.Material;
import net.minestom.server.tag.Tag;

import java.util.List;


public enum HighworldWeapon {

    TEST_SWORD(
            Material.DIAMOND_SWORD,
            Component.text("Test Sword", NamedTextColor.YELLOW),
            List.of(
                    Component.text("Lore 1", NamedTextColor.YELLOW),
                    Component.text("Lore 2", NamedTextColor.GREEN)
            ),
            100, 100, 100, 100, 100
    )



    ;

    @Getter private final Material material;
    @Getter private final Component name;
    @Getter private final List<Component> lore;

    @Getter private final double damage, strength, speed, health, defense;

    HighworldWeapon(Material material,
                    Component name,
                    List<Component> lore,
                    double damage,
                    double strength,
                    double speed,
                    double health,
                    double defense) {
        this.material = material;
        this.name = name;
        this.lore = lore;
        this.damage = damage;
        this.strength = strength;
        this.speed = speed;
        this.health = health;
        this.defense = defense;
    }

    public ItemStack give(Player player) {

        ItemStack item = ItemStack.of(material).withMeta((meta) -> {
            meta.lore(lore);
            meta.displayName(name);
            meta.setTag(Tag.Double("damage"), damage);
            meta.setTag(Tag.Double("strength"), strength);
            meta.setTag(Tag.Double("speed"), speed);
            meta.setTag(Tag.Double("health"), health);
            meta.setTag(Tag.Double("defense"), defense);
        });

        if (player == null) return item;
        player.getInventory().addItemStack(item);
        return item;
    }



}
