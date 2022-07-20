package quest.highworld.data.player;

import net.minestom.server.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class PlayerDataManager {

    private final HashMap<UUID, PlayerData> playerData = new HashMap<>();




    public void addPlayerData(Player player) {
        playerData.put(player.getUuid(), new PlayerData(player));
    }

    public PlayerData getPlayerData(Player player) {
        return playerData.get(player.getUuid());
    }

    public PlayerData getPlayerData(UUID uuid) {
        return playerData.get(uuid);
    }

    public void removePlayerData(Player player) {
        playerData.remove(player.getUuid());
    }
}
