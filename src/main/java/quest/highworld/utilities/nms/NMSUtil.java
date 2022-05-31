package quest.highworld.utilities.nms;

import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class NMSUtil {






    public int getPing(Player player) {
        return ((CraftPlayer) player).getHandle().ping;
    }

}
