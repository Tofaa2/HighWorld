package quest.highworld.world;

import lombok.Getter;
import net.minestom.server.MinecraftServer;
import net.minestom.server.coordinate.Pos;
import net.minestom.server.entity.Player;
import net.minestom.server.instance.InstanceContainer;
import quest.highworld.util.Cuboid;

import java.util.LinkedHashSet;

public class World {

    @Getter private final InstanceContainer instance = MinecraftServer.getInstanceManager().createInstanceContainer();
    @Getter private final LinkedHashSet<Floor> floors = new LinkedHashSet<>(){{
        add(new Floor(1));
        add(new Floor(2));
        add(new Floor(3));
        add(new Floor(4));
    }};


    public Floor isInWhatFloor(Player player) {
        for (Floor floor : floors) {
            if (floor.location.isInside((int) player.getPosition().x(), (int) player.getPosition().z())) {
                return floor;
            }
        }
        return null;
    }

    public void teleport(Player player, int floor) {

        final int levelGroundX = 5000;
        final int levelGroundZ = 5000;

        final int x = levelGroundX * floor;
        final int z = levelGroundZ * floor;

        player.teleport(new Pos(x, 64, z));
    }
    public void save() {
        instance.saveInstance();
        instance.saveChunksToStorage();
    }

    private static class Floor {

        @Getter private final int floorNumber;
        @Getter private final Cuboid location;

        public Floor(final int floorNumber) {
            this.floorNumber = floorNumber;
            this.location = new Cuboid(floorNumber * 200, floorNumber * 200, (floorNumber + 1) * 200, (floorNumber + 1) * 200);
        }
    }
}
