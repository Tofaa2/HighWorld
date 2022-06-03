package quest.highworld.handler;

import de.tr7zw.nbtapi.NBTEntity;
import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;
import quest.highworld.HighWorld;
import quest.highworld.database.StatsManager;
import quest.highworld.event.types.BukkitHighWorldListener;

public class DamageCalculator extends BukkitHighWorldListener {


    @EventHandler
    public void onDamage(EntityDamageByEntityEvent e){

        if (!(e.getDamager() instanceof Player p1)) return;

        e.setCancelled(true);

        if (e.getEntity() instanceof Player p2){
            p2.setVelocity(new Vector(0, 0.3, 0.7));
            ItemStack item = p1.getItemInHand();
            if (item == null || item.getType() == Material.AIR) return;

            NBTItem nbtItem = new NBTItem(item);
            double finalDamage = calculateP1P2(p1, nbtItem, p2);
            p2.damage(finalDamage);
        }
        else if (e.getEntity() instanceof LivingEntity e2){
            e2.setVelocity(new Vector(0, 0.3, 0.7));
            ItemStack item = p1.getItemInHand();
            if (item == null || item.getType() == Material.AIR) return;

            NBTItem nbtItem = new NBTItem(item);
            double finalDamage = calculateP1E2(p1, nbtItem, new NBTEntity(e2));
            e2.damage(finalDamage);
        }


    }

    private double calculateP1P2(Player p1, NBTItem item, Player p2){

        // ( DMG * (1 + ( STR / 100 ) ) - (DMG * (DEF/(100+DEF)))
        int wDmg = item.getInteger("Damage");
        if (isCrit(p1)){
            int wCC = item.getInteger("CritDamage");
            wDmg = (int) (wDmg * ((HighWorld.getInstance().getStatsManager().getStat(p1, StatsManager.Stat.CRITICAL_DAMAGE) + wCC) /100f));
        }


        int p1Str = HighWorld.getInstance().getStatsManager().getStat(p1, StatsManager.Stat.STRENGTH) + item.getInteger("Strength");
        int p2Def = HighWorld.getInstance().getStatsManager().getStat(p2, StatsManager.Stat.DEFENSE);

        double finalDamage = (wDmg * (1 + (p1Str / 100f)) - (wDmg * (p2Def / (100f + p2Def))));
        if (finalDamage < 0) finalDamage = 0;

        return finalDamage;
    }
    private double calculateP1E2(Player p1, NBTItem item, NBTEntity e2){

        // ( DMG * (1 + ( STR / 100 ) ) - (DMG * (DEF/(100+DEF)))
        int wDmg = item.getInteger("Damage");
        if (isCrit(p1)){
            int wCC = item.getInteger("CritDamage");
            wDmg = (int) (wDmg * ((HighWorld.getInstance().getStatsManager().getStat(p1, StatsManager.Stat.CRITICAL_DAMAGE) + wCC) /100f));
        }


        int p1Str = HighWorld.getInstance().getStatsManager().getStat(p1, StatsManager.Stat.STRENGTH) + item.getInteger("Strength");
        int p2Def = e2.getInteger("Defense");

        double finalDamage = (wDmg * (1 + (p1Str / 100f)) - (wDmg * (p2Def / (100f + p2Def))));
        if (finalDamage < 0) finalDamage = 0;

        return finalDamage;
    }



    // Checks if the hit should be a critical hit or not
    private boolean isCrit(Player p1){
        int cc = HighWorld.getInstance().getStatsManager().getStat(p1, StatsManager.Stat.CRITICAL_CHANCE);
        int random = (int) (Math.random() * 100);
        return random <= cc;
    }


}
