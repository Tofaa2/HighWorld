package quest.highworld.event.player;

import net.minestom.server.entity.Player;
import net.minestom.server.event.EventListener;
import net.minestom.server.event.item.EntityEquipEvent;
import org.jetbrains.annotations.NotNull;
import quest.highworld.Highworld;

public class PlayerEquipmentListener implements EventListener<EntityEquipEvent> {
    @Override
    public @NotNull Class<EntityEquipEvent> eventType() {
        return EntityEquipEvent.class;
    }

    @Override
    public @NotNull Result run(@NotNull EntityEquipEvent event) {

        if (!(event.getEntity() instanceof Player player)) return Result.SUCCESS;

        Highworld.getInstance().getPlayerDataManager().getPlayerData(player).getAttributeProcessor().refreshStats();

        return Result.SUCCESS;
    }
}
