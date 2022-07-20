package quest.highworld.entity.type;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.minestom.server.attribute.Attribute;
import net.minestom.server.coordinate.Pos;
import net.minestom.server.entity.EntityType;
import net.minestom.server.entity.LivingEntity;
import net.minestom.server.entity.Player;
import net.minestom.server.instance.InstanceContainer;
import net.minestom.server.item.ItemStack;
import quest.highworld.Highworld;
import quest.highworld.entity.type.loot.LootItem;
import lombok.Getter;
import java.util.List;

public enum HighworldMob {


    BOAR(
            EntityType.HOGLIN,
            3,
            "Boar",
            10000,
            1,
            5,
            0,
            null,
            null,
            null,
            null,
            null,
            null
    )

    ;
    @Getter private final int level;
    @Getter private final String name;
    @Getter private final double health, speed, strength, defense;
    @Getter private final EntityType type;
    @Getter private final List<LootItem> lootTable;

    @Getter private final ItemStack heldItem;

    @Getter private final ItemStack helmet, chestplate, leggings, boots;

    HighworldMob(EntityType type,
                 int level,
                 String name,
                 double health,
                 double speed,
                 double strength,
                 double defense,
                 List<LootItem> lootTable,
                 ItemStack heldItem,
                 ItemStack helmet,
                 ItemStack chestplate,
                 ItemStack leggings,
                 ItemStack boots) {
        this.name = name;
        this.health = health;
        this.level = level;
        this.speed = speed;
        this.strength = strength;
        this.defense = defense;
        this.type = type;
        this.lootTable = lootTable;
        this.heldItem = heldItem;
        this.helmet = helmet;
        this.chestplate = chestplate;
        this.leggings = leggings;
        this.boots = boots;
    }

    public LivingEntity spawn(Pos pos) {

        LivingEntity entity = new LivingEntity(type);

        entity.setCustomNameVisible(true);
        entity.setCustomName(buildName(health));

        entity.getAttribute(Attribute.MAX_HEALTH).setBaseValue((float) health);
        entity.setHealth((float) health);

        if (heldItem != null) entity.setItemInMainHand(heldItem);
        if (helmet != null) entity.setHelmet(helmet);
        if (chestplate != null) entity.setChestplate(chestplate);
        if (leggings != null) entity.setLeggings(leggings);
        if (boots != null) entity.setBoots(boots);

        InstanceContainer instance = Highworld.getInstance().getWorld().getInstance();
        entity.setInstance(instance, pos);
        return entity;
    }

    public Component buildName(double hp) {
        return Component
                .text("[", NamedTextColor.WHITE)
                .append(Component.text(level, NamedTextColor.GREEN))
                .append(Component.text("]", NamedTextColor.WHITE))
                .append(Component.space())
                .append(Component.text(name, NamedTextColor.YELLOW))
                .append(Component.space())
                .append(Component.text("[", NamedTextColor.WHITE))
                .append(Component.text((int) health, NamedTextColor.GREEN))
                .append(Component.text("/", NamedTextColor.GRAY))
                .append(Component.text((int) hp, NamedTextColor.GREEN))
                .append(Component.text("]", NamedTextColor.WHITE));
    }



    public void tryGiveLoot(Player player) {
        if (lootTable == null) return;
        for (LootItem lootItem : lootTable) {
            lootItem.tryGive(player);
        }
    }

}
