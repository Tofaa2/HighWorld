package quest.highworld.factory.entity;

import lombok.Getter;
import org.bukkit.entity.EntityType;

public enum HighWorldEntityType {

    ZOMBIE(EntityType.ZOMBIE);

    @Getter private final EntityType entityType;

    HighWorldEntityType(EntityType type){
        this.entityType = type;
    }

}
