package quest.highworld.database;

import org.bukkit.entity.Player;
import quest.highworld.database.impl.PlayerDataManager;

public class SettingsManager {


    public boolean getSetting(Player player, Setting setting){
        return (boolean) PlayerDataManager.getInstance().getData(player, "settings." + setting.toString().toLowerCase());
    }

    public void setSetting(Player player, Setting setting, boolean value){
        PlayerDataManager.getInstance().setData(player, "settings." + setting.toString().toLowerCase(), value);
    }

    public void setDefault(Player player){
        setSetting(player, Setting.CHAT_ENABLED, true);
        setSetting(player, Setting.SCOREBOARD_ENABLED, true);
        setSetting(player, Setting.PERSONAL_MESSAGES_ENABLED, true);
        setSetting(player, Setting.COMBAT_INDICATOR_ENABLED, true);
        setSetting(player, Setting.NPC_MESSAGES_ENABLED, true);
        setSetting(player, Setting.FRIEND_REQUESTS_ENABLED, true);
        setSetting(player, Setting.PARTY_REQUESTS_ENABLED, true);
    }
    public enum Setting {

        CHAT_ENABLED,
        SCOREBOARD_ENABLED,
        PERSONAL_MESSAGES_ENABLED,
        COMBAT_INDICATOR_ENABLED,
        NPC_MESSAGES_ENABLED,
        FRIEND_REQUESTS_ENABLED,
        PARTY_REQUESTS_ENABLED,

    }
}
