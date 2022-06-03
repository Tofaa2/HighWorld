package quest.highworld.factory.itemstack.ability.passive.undeadexpert;

import com.github.retrooper.packetevents.event.PacketListenerPriority;
import quest.highworld.event.types.PacketHighWorldListener;

public class PacketPassiveUndeadExpertListener extends PacketHighWorldListener {

    public PacketPassiveUndeadExpertListener() {
        super(PacketListenerPriority.HIGH);
    }
}
