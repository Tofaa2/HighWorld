package quest.highworld.event.player;

import net.minestom.server.coordinate.Vec;
import net.minestom.server.entity.ItemEntity;
import net.minestom.server.entity.Player;
import net.minestom.server.event.EventListener;
import net.minestom.server.event.item.ItemDropEvent;
import net.minestom.server.item.ItemStack;
import net.minestom.server.utils.time.TimeUnit;
import org.jetbrains.annotations.NotNull;

public class PlayerItemDropListener implements EventListener<ItemDropEvent> {
    @Override
    public @NotNull Class<ItemDropEvent> eventType() {
        return ItemDropEvent.class;
    }

    @Override
    public @NotNull Result run(@NotNull ItemDropEvent event) {

        Player player = event.getPlayer();
        ItemStack item = event.getItemStack();

        ItemEntity itemEntity = new ItemEntity(item);
        itemEntity.setPickupDelay(500, TimeUnit.MILLISECOND);
        itemEntity.setInstance(player.getInstance());
        itemEntity.teleport(player.getPosition().add(0, 1.5f, 0));

        Vec velocity = player.getPosition().direction().mul(6);
        itemEntity.setVelocity(velocity);


        return Result.SUCCESS;
    }
}
