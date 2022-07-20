package quest.highworld.factory.entity;

import de.tr7zw.nbtapi.NBTEntity;
import lombok.Getter;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.EntityEquipment;
import quest.highworld.HighWorld;
import quest.highworld.factory.itemstack.AbstractHighWorldItem;
import quest.highworld.factory.itemstack.LootItem;
import quest.highworld.factory.itemstack.type.armor.HighWorldArmor;
import quest.highworld.utilities.Strings;
import java.util.List;

public enum HighWorldMob {


    BOAR("Boar", 3, 100, 0, 15, 2, EntityType.PIG, null, null,
            List.of(
                    new LootItem(HighWorld.getInstance().getItemManager().getItem("boar_skin"), 1, 3, 100)
            )
    );




    @Getter private final String name;
    @Getter private final int level;
    @Getter private final double maxHealth, defense, damage, strength;
    @Getter private final EntityType type;
    @Getter private final List<LootItem> lootTable;
    @Getter private final AbstractHighWorldItem heldItem;
    @Getter private final HighWorldArmor[] armor;

    HighWorldMob(String name, int level, double maxHealth, double defense, double damage, double strength, EntityType type, AbstractHighWorldItem heldItem, HighWorldArmor[] armor, List<LootItem> loot) {
        this.name = name;
        this.level = level;
        this.maxHealth = maxHealth;
        this.defense = defense;
        this.type = type;
        this.heldItem = heldItem;
        this.armor = armor;
        this.lootTable = loot;
        this.damage = damage;
        this.strength = strength;
    }

    HighWorldMob(String name, EntityType type, List<LootItem> loot) {
        this(name, 1,  100, 0, 0, 0, type, null, null, loot);
    }



    public LivingEntity spawn(Location location){
        LivingEntity entity = (LivingEntity) location.getWorld().spawnEntity(location, type);
        NBTEntity nbtEntity = new NBTEntity(entity);

        nbtEntity.setInteger("level", level);
        nbtEntity.setDouble("Defense", defense);
        nbtEntity.setDouble("Damage", damage);
        nbtEntity.setDouble("Strength", strength);

        entity.setCustomNameVisible(true);
        entity.setCustomName(Strings.cc(HighWorldMobManager.MOB_NAME_STYLE
                .replace("%level%", String.valueOf(level))
                .replace("%name%", name)
                .replace("%current%", String.valueOf((int) maxHealth))
                .replace("%max%", String.valueOf((int) maxHealth))));

        entity.setMaxHealth(maxHealth);
        entity.setHealth(maxHealth);

        EntityEquipment equipment = entity.getEquipment();
        if (armor != null) equipment.setArmorContents(armor);
        equipment.setBootsDropChance(0.0F);
        equipment.setLeggingsDropChance(0.0F);
        equipment.setChestplateDropChance(0.0F);
        equipment.setHelmetDropChance(0.0F);
        equipment.setItemInHand(heldItem);
        equipment.setItemInHandDropChance(0.0F);

        return entity;
    }

    public void dropLoot(Location location) {
        for (LootItem loot : lootTable) {
            loot.tryDrop(location);
        }
    }



    public static HighWorldMob getMob(String name) {
        for (HighWorldMob mob : values()) {
            if (mob.getName().equalsIgnoreCase(name)) return mob;
        }
        return null;
    }


}
