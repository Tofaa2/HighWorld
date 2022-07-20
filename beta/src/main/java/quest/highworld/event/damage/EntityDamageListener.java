package quest.highworld.event.damage;

import net.minestom.server.entity.LivingEntity;
import net.minestom.server.event.EventListener;
import net.minestom.server.event.entity.EntityDamageEvent;
import net.minestom.server.sound.SoundEvent;
import org.jetbrains.annotations.NotNull;

public class EntityDamageListener implements EventListener<EntityDamageEvent> {

    @Override
    public @NotNull Class<EntityDamageEvent> eventType() {
        return EntityDamageEvent.class;
    }

    @Override
    public @NotNull Result run(@NotNull EntityDamageEvent event) {

        LivingEntity entity = event.getEntity();
        event.setSound(SoundEvent.ENTITY_PLAYER_ATTACK_CRIT);

        return Result.SUCCESS;
    }
}
