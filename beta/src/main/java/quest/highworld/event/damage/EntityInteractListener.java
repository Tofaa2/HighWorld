package quest.highworld.event.damage;

import net.minestom.server.event.EventListener;
import net.minestom.server.event.player.PlayerEntityInteractEvent;
import org.jetbrains.annotations.NotNull;

public class EntityInteractListener implements EventListener<PlayerEntityInteractEvent> {
    @Override
    public @NotNull Class<PlayerEntityInteractEvent> eventType() {
        return PlayerEntityInteractEvent.class;
    }

    @Override
    public @NotNull Result run(@NotNull PlayerEntityInteractEvent event) {




        return Result.SUCCESS;
    }
}
