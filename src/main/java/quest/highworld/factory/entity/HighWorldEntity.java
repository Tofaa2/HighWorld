package quest.highworld.factory.entity;

import lombok.Getter;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.entity.Entity;
import org.bukkit.event.entity.CreatureSpawnEvent;
import quest.highworld.HighWorld;

public class HighWorldEntity implements IHighWorldEntity{

    private final HighWorld highworld = HighWorld.getInstance();

    @Getter private Entity bukkitEntity;
    @Getter private CraftWorld craftWorld;
    @Getter private net.minecraft.server.v1_8_R3.Entity nmsEntity;

    private HighWorldEntityType type;
    private Location location;

    public HighWorldEntity(HighWorldEntityType type, Location location) {
        this.type = type;
        this.location = location;
        this.craftWorld = (CraftWorld) location.getWorld();
        this.nmsEntity = this.craftWorld.createEntity(location, type.getEntityType().getEntityClass());
    }

    @Override
    public void spawn() {
        this.bukkitEntity = this.craftWorld.addEntity(nmsEntity, CreatureSpawnEvent.SpawnReason.CUSTOM);
    }

    @Override
    public void remove() {
        this.bukkitEntity.remove();
        this.nmsEntity.dead = true; // GOOFY IK
    }

    @Override
    public HighWorldEntityType getType() {
        return this.type;
    }

    @Override
    public int getHealth() {
        return 10;
    }

    @Override
    public void setHealth(int health) {

    }

    @Override
    public int getDefense() {
        return 0;
    }

    @Override
    public void setDefense(int defense) {

    }

    @Override
    public Location getLocation() {
        return location;
    }

    @Override
    public void setLocation(Location location) {
        this.location = location;
    }
}
