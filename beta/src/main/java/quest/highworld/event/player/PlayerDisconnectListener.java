package quest.highworld.event.player;

import net.minestom.server.event.EventListener;
import net.minestom.server.event.player.PlayerDisconnectEvent;
import org.jetbrains.annotations.NotNull;
import quest.highworld.Highworld;

public class PlayerDisconnectListener implements EventListener<PlayerDisconnectEvent> {
    @Override
    public @NotNull Class<PlayerDisconnectEvent> eventType() {
        return PlayerDisconnectEvent.class;
    }

    @Override
    public @NotNull Result run(@NotNull PlayerDisconnectEvent event) {

        Highworld.getInstance().getPlayerDataManager().removePlayerData(event.getPlayer());
        return Result.SUCCESS;
    }
}
