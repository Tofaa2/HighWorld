package quest.highworld.database;

import org.bukkit.entity.Player;
import quest.highworld.database.impl.PlayerDataManager;

public class PlayDateManager {



    // Hours
    public long getPlayTime(Player player){
        return (int) PlayerDataManager.getInstance().getData(player, "playtime");
    }

    public long getFirstJoin(Player player) {
        return (long) PlayerDataManager.getInstance().getData(player, "first_join");
    }

    public void setPlayTime(Player player, long time) {
        PlayerDataManager.getInstance().setData(player, "playtime", time);
    }








}
