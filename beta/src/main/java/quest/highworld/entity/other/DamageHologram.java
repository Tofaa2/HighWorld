package quest.highworld.entity.other;

import net.kyori.adventure.text.Component;
import net.minestom.server.coordinate.Pos;
import net.minestom.server.entity.hologram.Hologram;
import net.minestom.server.instance.Instance;
import net.minestom.server.utils.time.TimeUnit;

import java.time.Duration;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class DamageHologram extends Hologram {

    public DamageHologram(Instance instance, Pos spawnPos, Component text) {
        super(instance, spawnPos, text, true, true);
        Random random = ThreadLocalRandom.current();


        getEntity().getEntityMeta().setHasNoGravity(true);
        getEntity().setVelocity(getPosition().direction()
                .withX(random.nextDouble(2))
                .withY(3)
                .withZ(random.nextDouble(2))
                .normalize().mul(3)
        );
        getEntity().scheduleRemove(Duration.of(20, TimeUnit.SERVER_TICK));
    }


}
