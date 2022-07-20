package quest.highworld.entity;

import net.minestom.server.MinecraftServer;
import net.minestom.server.entity.Entity;
import quest.highworld.entity.type.HighworldMob;

import java.time.Duration;
import java.util.HashMap;
import java.util.Set;

public class EntityManager {

    private final HashMap<Entity, HighworldMob> entities = new HashMap<>();

    public EntityManager() {
        MinecraftServer.getSchedulerManager().buildTask(() -> {
            if (entities.isEmpty()) {
                return;
            }
            Set<Entity> toRemove = entities.keySet();
            for (Entity entity : toRemove) {
                if (entity.isRemoved()) {
                    entities.remove(entity);
                }
            }
        }).delay(Duration.ofSeconds(3)).schedule();
    }



}
