package quest.highworld.event.player;

import net.minestom.server.coordinate.Pos;
import net.minestom.server.entity.Player;
import net.minestom.server.event.EventListener;
import net.minestom.server.event.player.PlayerLoginEvent;
import net.minestom.server.instance.Chunk;
import net.minestom.server.instance.InstanceContainer;
import net.minestom.server.instance.block.Block;
import org.jetbrains.annotations.NotNull;
import quest.highworld.Highworld;

import java.util.concurrent.CompletableFuture;

public class PlayerLoginListener implements EventListener<PlayerLoginEvent> {
    @Override
    public @NotNull Class<PlayerLoginEvent> eventType() {
        return PlayerLoginEvent.class;
    }

    @Override
    public @NotNull Result run(@NotNull PlayerLoginEvent event) {

        Player player = event.getPlayer();


        if (Highworld.getInstance().getDataHelper().getRank(player) == null) {
            Highworld.getInstance().getDataHelper().setDefaults(player);
        }
        InstanceContainer world = Highworld.getInstance().getWorld().getInstance();
        event.setSpawningInstance(world);
        @NotNull CompletableFuture<Chunk> chunk = world.loadChunk(0, 0);
        chunk.thenRun(() -> world.setBlock(0, 100, 0, Block.BEDROCK));
        player.setRespawnPoint(new Pos(0, 102, 0));

        Highworld.getInstance().getPlayerDataManager().addPlayerData(player);
        Highworld.getInstance().getPlayerDataManager().getPlayerData(player).getAttributeProcessor().setupLogin();

        return Result.SUCCESS;
    }
}
