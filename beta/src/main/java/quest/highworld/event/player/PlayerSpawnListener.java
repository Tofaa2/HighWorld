package quest.highworld.event.player;

import net.minestom.server.MinecraftServer;
import net.minestom.server.entity.Player;
import net.minestom.server.event.EventListener;
import net.minestom.server.event.player.PlayerSpawnEvent;
import net.minestom.server.timer.Task;
import org.jetbrains.annotations.NotNull;
import quest.highworld.Highworld;

public class PlayerSpawnListener implements EventListener<PlayerSpawnEvent> {
    @Override
    public @NotNull Class<PlayerSpawnEvent> eventType() {
        return PlayerSpawnEvent.class;
    }

    @Override
    public @NotNull Result run(@NotNull PlayerSpawnEvent event) {

        if (event.isFirstSpawn() || event.getPlayer().getInstance() != null || event.getPlayer().getInstance().equals(Highworld.getInstance().getWorld().getInstance())) {
            return Result.SUCCESS;
        }

        event.getPlayer().setInstance(Highworld.getInstance().getWorld().getInstance());
        return Result.SUCCESS;
    }
}
