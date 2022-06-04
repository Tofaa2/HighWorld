package quest.highworld.factory.entity;

import net.minecraft.server.v1_8_R3.EntityArmorStand;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_8_R3.CraftServer;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftArmorStand;
import quest.highworld.HighWorld;
import quest.highworld.utilities.Strings;

public class Hologram extends CraftArmorStand {

    public Hologram(String name, World world) {
        super((CraftServer) HighWorld.getInstance().getServer(), new EntityArmorStand((net.minecraft.server.v1_8_R3.World) world));
        this.setCustomNameVisible(true);
        this.setCustomName(Strings.cc(name));
        this.setGravity(false);
        this.setVisible(false);
        this.setSmall(true);
        this.setMarker(true);
        this.setBasePlate(false);
        this.setArms(false);
    }
}
