package quest.highworld.factory.projectile;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import quest.highworld.HighWorld;

public class ProjectileManager {



    public void launchSyncProjectile(Player player, Class<? extends Projectile> projectileClass){
        Bukkit.getScheduler().runTask(HighWorld.getInstance(), () ->{
           player.launchProjectile(projectileClass);
        });
    }

}
