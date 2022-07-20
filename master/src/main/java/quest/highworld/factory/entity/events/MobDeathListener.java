package quest.highworld.factory.entity.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDeathEvent;
import quest.highworld.event.types.BukkitHighWorldListener;
import quest.highworld.factory.entity.HighWorldMob;

public class MobDeathListener extends BukkitHighWorldListener {

    @EventHandler
    public void onDeath(EntityDeathEvent event){
        if (highworld.getHighWorldMobManager().getMob(event.getEntity()) == null) return;

        HighWorldMob mob = highworld.getHighWorldMobManager().getMob(event.getEntity());
        event.setDroppedExp(0);
        event.getDrops().clear();
        highworld.getHighWorldMobManager().removeMob(event.getEntity());
        mob.dropLoot(event.getEntity().getLocation());
     }
}
