package quest.highworld.factory.entity;

import com.github.retrooper.packetevents.protocol.entity.data.provider.EntityDataProvider;
import com.github.retrooper.packetevents.protocol.entity.type.EntityType;
import com.github.retrooper.packetevents.protocol.entity.type.EntityTypes;
import com.github.retrooper.packetevents.util.Vector3d;
import com.github.retrooper.packetevents.wrapper.play.server.WrapperPlayServerSpawnEntity;

import java.util.Optional;
import java.util.UUID;

public class Hologram extends WrapperPlayServerSpawnEntity {

    private final Optional<UUID> UUID;
    private Vector3d location;
    private int entityId;
    private final EntityType entityType = EntityTypes.ARMOR_STAND;
    private float pitch;
    private float yaw;
    private int data;
    private Optional<Vector3d> velo;


    public Hologram(int entityID, Optional<UUID> uuid, EntityType entityType, Vector3d position, float pitch, float yaw, int data, Optional<Vector3d> velocity) {
        super(entityID, uuid, entityType, position, pitch, yaw, data, velocity);
        this.UUID = uuid;
        this.entityId = entityID;
        this.location = position;
        this.pitch = pitch;
        this.yaw = yaw;
        this.data = data;
        this.velo = velocity;
    }

    public void spawn(){

    }
}
