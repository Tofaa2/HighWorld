package quest.highworld.factory.entity;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import quest.highworld.HighWorld;

import java.util.*;

public class HighWorldMobManager {

    private final HashMap<Entity, HighWorldMob> mobs;
    public static final String MOB_NAME_STYLE = "&bLVL %level% &c%name% &7[&6%current%&7/&a%max%&7]";

    private HighWorld hw = HighWorld.getInstance();


    public HighWorldMobManager() {
        mobs = new HashMap<>();
        hw.getServer().getScheduler().runTaskTimer(hw, () ->{
            Set<Entity> spawned = mobs.keySet();
            List<Entity> toRemove = new ArrayList<>();
            for (Entity e : mobs.keySet()) {
                if (e.isDead() || !e.isValid()) {
                    toRemove.add(e);
                }
            }
            toRemove.forEach(spawned::remove);
        }, 0L, 20L);

    }

    public void addMob(Entity entity, HighWorldMob mob) {
        mobs.put(entity, mob);
    }

    public HighWorldMob getMob(Entity entity) {
        return mobs.get(entity);
    }

    public void removeMob(Entity entity) {
        mobs.remove(entity);
    }

    public void spawnMob(Location location, HighWorldMob mob){
        addMob(mob.spawn(location), mob);
    }
}
