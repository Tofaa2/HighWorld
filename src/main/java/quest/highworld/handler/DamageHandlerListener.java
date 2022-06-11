package quest.highworld.handler;

import de.tr7zw.nbtapi.NBTEntity;
import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import quest.highworld.HighWorld;
import quest.highworld.database.StatsManager;
import quest.highworld.event.types.BukkitHighWorldListener;
import quest.highworld.factory.itemstack.ability.ItemStat;

@Deprecated
public class DamageHandlerListener extends BukkitHighWorldListener {


    @EventHandler
    public void onDamage(EntityDamageByEntityEvent e){

        if (!(e.getDamager() instanceof Player p1)) return;

        ItemStack item = p1.getInventory().getItemInHand();
        if (item == null || item.getType() == Material.AIR) return;
        NBTItem nbtItem = new NBTItem(item);

        double finalDamage = 0;

        if (e.getEntity() instanceof Player p2){finalDamage = calculateP1P2(p1, nbtItem, p2);}

        else if (e.getEntity() instanceof LivingEntity e2){finalDamage = calculateP1E2(p1, nbtItem, new NBTEntity(e2));}

        e.setDamage(0);

        ((LivingEntity) e.getEntity()).damage(finalDamage);

    }

    private double calculateP1P2(Player p1, NBTItem item, Player p2){

        // ( DMG * (1 + ( STR / 100 ) ) - (DMG * (DEF/(100+DEF)))
        int wDmg = 0;

        int[] damamgeArr = item.getIntArray(ItemStat.DAMAGE.getNbtKey());
        if (damamgeArr != null){
            wDmg = rand(damamgeArr[0], damamgeArr[1]);
        }

        if (isCrit(p1)){
            p1.sendMessage("Critical Hit!");
            int wCC = 0;
            if (item.getIntArray(ItemStat.CRIT_DAMAGE.getNbtKey()) != null){
                wCC = rand(item.getIntArray(ItemStat.CRIT_DAMAGE.getNbtKey())[0], item.getIntArray(ItemStat.CRIT_DAMAGE.getNbtKey())[1]);
            }
            wDmg = (int) (wDmg * ((HighWorld.getInstance().getStatsManager().getStat(p1, StatsManager.Stat.CRITICAL_DAMAGE) + wCC) /100f));
        }


        double p1Str = HighWorld.getInstance().getStatsManager().getStat(p1, StatsManager.Stat.STRENGTH);

        if (item.getIntArray(ItemStat.STRENGTH.getNbtKey()) != null) p1Str += rand(item.getIntArray(ItemStat.STRENGTH.getNbtKey())[0], item.getIntArray(ItemStat.STRENGTH.getNbtKey())[1]);

        double p2Def = HighWorld.getInstance().getStatsManager().getStat(p2, StatsManager.Stat.DEFENSE);

        double finalDamage = (wDmg * (1 + (p1Str / 100f)) - (wDmg * (p2Def / (100f + p2Def))));
        if (finalDamage < 0) finalDamage = 0;

        return finalDamage;
    }
    private int calculateP1E2(Player p1, NBTItem item, NBTEntity e2) {

        // ( DMG * (1 + ( STR / 100 ) ) - (DMG * (DEF/(100+DEF)))
        int weaponDamage = 0;
        int[] weaponDamageNBT = item.getIntArray(ItemStat.DAMAGE.getNbtKey());
        if (weaponDamageNBT.length > 0){
            if (weaponDamageNBT.length > 1) weaponDamage = rand(weaponDamageNBT[0], weaponDamageNBT[1]);
            else weaponDamage = weaponDamageNBT[0];
        }


        if (isCrit(p1)) weaponDamage = (int) (weaponDamage * ((HighWorld.getInstance().getStatsManager().getStat(p1, StatsManager.Stat.CRITICAL_DAMAGE)) /100f));
        double player1Strength = HighWorld.getInstance().getStatsManager().getStat(p1, StatsManager.Stat.STRENGTH);

        int entity2Defense = 0;
        if (e2.getInteger(ItemStat.DEFENSE.getNbtKey()) != null) entity2Defense = e2.getInteger(ItemStat.DEFENSE.getNbtKey());

        int finalDamage = (int) (weaponDamage * (1 + (player1Strength / 100f)) - (weaponDamage * (entity2Defense / (100f + entity2Defense))));
        if (finalDamage < 0) finalDamage = 0;
        p1.sendMessage(weaponDamage + "WDMG " + player1Strength + "P1STR " + entity2Defense + "E2DEF " + finalDamage + "FDMG");
        return finalDamage;

    }



    // Checks if the hit should be a critical hit or not
    private boolean isCrit(Player p1){
        double cc = HighWorld.getInstance().getStatsManager().getStat(p1, StatsManager.Stat.CRITICAL_CHANCE);
        int random = (int) (Math.random() * 100);
        return random <= cc;
    }

    private int rand(int min, int max){return (int) (Math.random() * (max - min) + min);}

}
