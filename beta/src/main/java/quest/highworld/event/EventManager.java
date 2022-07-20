package quest.highworld.event;

import net.minestom.server.MinecraftServer;
import net.minestom.server.event.EventListener;
import quest.highworld.event.custom.ServerStartEvent;
import quest.highworld.event.damage.EntityAttackListener;
import quest.highworld.event.player.*;
import quest.highworld.event.server.ServerStartListener;

public class EventManager {


    public EventManager() {

        // Damage
        register(new EntityAttackListener());;

        // Player
        register(new PlayerLoginListener());
        register(new PlayerChatListener());
        register(new PlayerPickupItemListener());
        register(new PlayerItemDropListener());
        register(new PlayerSpawnListener());
        register(new PlayerDisconnectListener());
        register(new PlayerChangeHeldSlotListener());
        register(new PlayerEquipmentListener());

        // Server
        register(new ServerStartListener());

        MinecraftServer.getGlobalEventHandler().call(new ServerStartEvent());
    }

    public void register(EventListener<?> listener) {
        MinecraftServer.getGlobalEventHandler().addListener(listener);
        MinecraftServer.LOGGER.info("Registered event listener: " + listener.getClass().getSimpleName());
    }





}
