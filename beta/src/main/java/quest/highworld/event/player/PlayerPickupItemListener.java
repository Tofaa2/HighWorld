package quest.highworld.event.player;

import kotlin.reflect.jvm.internal.impl.descriptors.Named;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.minestom.server.entity.Player;
import net.minestom.server.event.EventListener;
import net.minestom.server.event.item.PickupItemEvent;
import net.minestom.server.inventory.TransactionOption;
import org.jetbrains.annotations.NotNull;

public class PlayerPickupItemListener implements EventListener<PickupItemEvent> {
    @Override
    public @NotNull Class<PickupItemEvent> eventType() {
        return PickupItemEvent.class;
    }

    @Override
    public @NotNull Result run(@NotNull PickupItemEvent event) {
        if (!(event.getLivingEntity() instanceof Player player)) return Result.SUCCESS;
        player.getInventory().addItemStack(event.getItemStack(), TransactionOption.ALL);
        return Result.SUCCESS;
    }
}
