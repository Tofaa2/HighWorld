package quest.highworld.factory.entity.events;

import de.tr7zw.nbtapi.NBTEntity;
import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import quest.highworld.HighWorld;
import quest.highworld.database.StatsManager;
import quest.highworld.event.types.BukkitHighWorldListener;
import quest.highworld.factory.entity.HighWorldMob;
import quest.highworld.factory.entity.HighWorldMobManager;
import quest.highworld.utilities.Math;
import quest.highworld.utilities.Strings;


/*
 * Formula to calculate damage
 * ( DMG * (1 + ( STR / 100 ) ) - (DMG * (DEF/(100+DEF)))
 */
public class MobDamageListener extends BukkitHighWorldListener {

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event) {
        if (event.getEntity() instanceof Player) {event.setCancelled(true); return;}
        if (!(event.getDamager() instanceof Player player)) return;

        Entity raw = event.getEntity();
        if (highworld.getHighWorldMobManager().getMob(raw) == null) return;

        LivingEntity entity = (LivingEntity) raw;
        HighWorldMob mob = highworld.getHighWorldMobManager().getMob(entity);
        NBTEntity nbtEntity = new NBTEntity(entity);

        double entityDefense = 0;
        if (nbtEntity.hasKey("Defense")) entityDefense = nbtEntity.getDouble("Defense");


        ItemStack heldItem = player.getItemInHand();
        if (heldItem == null || heldItem.getType() == Material.AIR) {event.setCancelled(true); return;}
        NBTItem nbtItem = new NBTItem(heldItem);

        int[] weaponDamage = {0, 0};
        if (nbtItem.getIntArray("Damage") != null){
            int[] damage = nbtItem.getIntArray("Damage");
            if (damage.length == 2){
                weaponDamage[0] = damage[0];
                weaponDamage[1] = damage[1];
            }
            else if (damage.length == 1){
                weaponDamage[0] = damage[0];
                weaponDamage[1] = damage[0];
            }
        }
        if (isCritical(player)){
            int crit = HighWorld.getInstance().getStatsManager().getStat(player, StatsManager.Stat.CRITICAL_DAMAGE);
            weaponDamage[0] = (int) (weaponDamage[0] * ((crit + weaponDamage[0]) / 100f));
            weaponDamage[1] = (int) (weaponDamage[1] * ((crit + weaponDamage[1]) / 100f));
        }

        int wDamage = Math.random(weaponDamage[0], weaponDamage[1]);
        int strength = HighWorld.getInstance().getStatsManager().getStat(player, StatsManager.Stat.STRENGTH);

        //( DMG * (1 + ( STR / 100 ) ) - (DMG * (DEF/(100+DEF)))
        int leftSide = (int) (wDamage * (entityDefense / (100 + entityDefense)));
        int rightSide = wDamage * (strength / 100);
        int finalDamage = (rightSide - leftSide);
        if (finalDamage  < 2) finalDamage = wDamage;
        event.setDamage(0);
        entity.damage(finalDamage);


        /*
            Some armor-stand damage indicators code
         */
        Location loc = entity.getLocation().clone().add(0, 1,0);
        ArmorStand armorStand = loc.getWorld().spawn(loc, ArmorStand.class);
        armorStand.setVisible(false);
        armorStand.setSmall(true);
        armorStand.setCustomName("§c-" + finalDamage);
        armorStand.setCustomNameVisible(true);
        armorStand.setGravity(false);
        armorStand.setMarker(true);
        HighWorld.getInstance().runSyncTaskLater(armorStand::remove, 20);

        /*
            Updating the entity health tag
         */
        entity.setCustomName(Strings.cc(
                HighWorldMobManager.MOB_NAME_STYLE.replace("%level%", String.valueOf(mob.getLevel()))
                        .replace("%name%", mob.getName())
                        .replace("%current%", String.valueOf(entity.getHealth()))
                        .replace("%max%", String.valueOf(mob.getMaxHealth()))));
    }


    private boolean isCritical(Player p){
        return Math.random(0, 100) <= HighWorld.getInstance().getStatsManager().getStat(p, StatsManager.Stat.CRITICAL_CHANCE);
    }



}
