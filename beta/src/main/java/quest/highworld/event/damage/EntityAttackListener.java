package quest.highworld.event.damage;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.minestom.server.entity.Entity;
import net.minestom.server.entity.EntityProjectile;
import net.minestom.server.entity.LivingEntity;
import net.minestom.server.entity.Player;
import net.minestom.server.entity.damage.DamageType;
import net.minestom.server.event.EventListener;
import net.minestom.server.event.entity.EntityAttackEvent;
import net.minestom.server.tag.Tag;
import net.minestom.server.utils.MathUtils;
import org.jetbrains.annotations.NotNull;
import quest.highworld.Highworld;
import quest.highworld.data.player.PlayerData;
import quest.highworld.entity.other.DamageHologram;

public class EntityAttackListener implements EventListener<EntityAttackEvent> {

    private static final Tag<Long> INVINCIBILITY_TAG = Tag.Long("invincibility").defaultValue(0L);

    @Override
    public @NotNull Class<EntityAttackEvent> eventType() {
        return EntityAttackEvent.class;
    }

    @Override
    public @NotNull Result run(@NotNull EntityAttackEvent event) {

        if (!(event.getTarget() instanceof LivingEntity target) || ((LivingEntity) event.getEntity()).isDead()) return Result.SUCCESS;
        if (!(event.getEntity() instanceof Player attacker)) return Result.SUCCESS;
        if (target instanceof Player) return Result.SUCCESS;

        final long now = System.currentTimeMillis();
        final long invincibility = (target.getTag(INVINCIBILITY_TAG) == null) ? 0L : target.getTag(INVINCIBILITY_TAG);
        if (now < invincibility) return Result.SUCCESS;

        PlayerData playerData = Highworld.getInstance().getPlayerDataManager().getPlayerData(attacker);


        final double entityDefense = (target.hasTag(Tag.Double("defense")) ? target.getTag(Tag.Double("defense")) : 0D);
        final float damage = playerData.getAttributeProcessor().getFinalDamage((int) entityDefense);
        ((LivingEntity) event.getTarget()).damage(DamageType.fromPlayer(attacker), damage);
        spawnHologram(target, damage);
        takeKnockback(target, attacker);
        return Result.SUCCESS;
    }


    private static void takeKnockback(Entity target, Entity source) {
        target.takeKnockback(
                0.3f,
                Math.sin(source.getPosition().yaw() * (Math.PI / 180)),
                -Math.cos(source.getPosition().yaw() * (Math.PI / 180))
        );
    }

    private static void takeKnockbackFromArrow(Entity target, EntityProjectile source) {
        if (source.getShooter() == null) return;
        takeKnockback(target, source.getShooter());
    }

    private static void spawnHologram(Entity target, float damage) {
        damage = MathUtils.round(damage, 1);
        new DamageHologram(
                target.getInstance(),
                target.getPosition().add(0, target.getEyeHeight(), 0),
                Component.text(damage, NamedTextColor.RED)
        );
    }

}
