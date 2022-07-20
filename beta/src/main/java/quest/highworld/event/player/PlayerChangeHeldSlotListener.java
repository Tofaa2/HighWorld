package quest.highworld.event.player;

import net.minestom.server.event.EventListener;
import net.minestom.server.event.player.PlayerChangeHeldSlotEvent;
import net.minestom.server.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import quest.highworld.Highworld;

public class PlayerChangeHeldSlotListener implements EventListener<PlayerChangeHeldSlotEvent> {
    @Override
    public @NotNull Class<PlayerChangeHeldSlotEvent> eventType() {
        return PlayerChangeHeldSlotEvent.class;
    }

    @Override
    public @NotNull Result run(@NotNull PlayerChangeHeldSlotEvent event) {
        byte slot = event.getSlot();
        ItemStack item = event.getPlayer().getItemInMainHand();
        if (item.isAir()) {
            event.getPlayer().sendMessage("AADOSAJDOSAIJDASO");
            return Result.SUCCESS;
        }
        Highworld.getInstance().getPlayerDataManager().getPlayerData(event.getPlayer()).getAttributeProcessor().refreshStats();
        return Result.SUCCESS;
    }
}
