package quest.highworld.database;

import org.bukkit.entity.Player;
import quest.highworld.database.impl.PlayerDataManager;

public class StatsManager {



    public int getStat(Player player, Stat stat){
        return (int) PlayerDataManager.getInstance().getData(player, "stats." + stat.toString().toLowerCase());
    }

    public void setStat(Player player, Stat stat, int value){
        PlayerDataManager.getInstance().setData(player, "stats." + stat.toString().toLowerCase(), value);
    }

    public void setDefault(Player player){

        setStat(player, Stat.PLAYER_LEVEL, 1);
        setStat(player, Stat.PLAYER_XP, 0);
        setStat(player, Stat.COINS, 0);
        setStat(player, Stat.FLOOR, 1);

        setStat(player, Stat.COMBAT_LEVEL, 0);
        setStat(player, Stat.COMBAT_XP, 0);
        setStat(player, Stat.MINING_LEVEL, 0);
        setStat(player, Stat.MINING_XP, 0);
        setStat(player, Stat.WOODWORKING_LEVEL, 0);
        setStat(player, Stat.WOODWORKING_XP, 0);
        setStat(player, Stat.CRAFTING_LEVEL, 0);
        setStat(player, Stat.CRAFTING_XP, 0);
        setStat(player, Stat.FISHING_LEVEL, 0);
        setStat(player, Stat.FISHING_XP, 0);
        setStat(player, Stat.ENCHANTING_LEVEL, 0);
        setStat(player, Stat.ENCHANTING_XP, 0);
        setStat(player, Stat.BREWING_LEVEL, 0);
        setStat(player, Stat.BREWING_XP, 0);

        setStat(player, Stat.MAX_HEALTH, 100);
        setStat(player, Stat.HEALTH, 100);
        setStat(player, Stat.DEFENSE, 0);
        setStat(player, Stat.MAX_MANA, 100);
        setStat(player, Stat.MANA, 100);
        setStat(player, Stat.SPEED, 100); // Percent
        setStat(player, Stat.STRENGTH, 0);
        setStat(player, Stat.CRITICAL_CHANCE, 30); // Percent
        setStat(player, Stat.CRITICAL_DAMAGE, 50); // Percent (Addition)

        setStat(player, Stat.KILLS, 0);
        setStat(player, Stat.DEATHS, 0);
    }

    public enum Stat {
        PLAYER_LEVEL,
        PLAYER_XP,
        COINS,
        FLOOR,
        COMBAT_XP,
        COMBAT_LEVEL,
        MINING_XP,
        MINING_LEVEL,
        WOODWORKING_XP,
        WOODWORKING_LEVEL,
        CRAFTING_XP,
        CRAFTING_LEVEL,
        FISHING_XP,
        FISHING_LEVEL,
        ENCHANTING_XP,
        ENCHANTING_LEVEL,
        BREWING_XP,
        BREWING_LEVEL,
        HEALTH,
        MAX_HEALTH,
        MANA,
        MAX_MANA,
        STRENGTH,
        SPEED,
        DEFENSE,
        CRITICAL_DAMAGE,
        CRITICAL_CHANCE,
        KILLS,
        DEATHS
    }



}
