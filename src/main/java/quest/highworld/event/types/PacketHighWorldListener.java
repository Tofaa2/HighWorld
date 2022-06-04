package quest.highworld.event.types;

import com.github.retrooper.packetevents.event.*;
import com.github.retrooper.packetevents.event.simple.*;
import quest.highworld.HighWorld;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PacketHighWorldListener extends SimplePacketListenerAbstract {

    protected HighWorld highworld = HighWorld.getInstance();
    protected static final ExecutorService executor = Executors.newSingleThreadExecutor();

    public PacketHighWorldListener(PacketListenerPriority priority) {
        super(priority);
    }

    @Override public void onPacketPlayReceive(PacketPlayReceiveEvent event) {super.onPacketPlayReceive(event);}
    @Override public void onPacketHandshakeReceive(PacketHandshakeReceiveEvent event) {super.onPacketHandshakeReceive(event);}
    @Override public void onPacketStatusReceive(PacketStatusReceiveEvent event) {super.onPacketStatusReceive(event);}
    @Override public void onPacketStatusSend(PacketStatusSendEvent event) {super.onPacketStatusSend(event);}
    @Override public void onPacketLoginReceive(PacketLoginReceiveEvent event) {super.onPacketLoginReceive(event);}
    @Override public void onPacketLoginSend(PacketLoginSendEvent event) {super.onPacketLoginSend(event);}
    @Override public void onPacketPlaySend(PacketPlaySendEvent event) {super.onPacketPlaySend(event);}


}

