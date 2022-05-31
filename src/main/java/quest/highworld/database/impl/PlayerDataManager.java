package quest.highworld.database.impl;

import lombok.Getter;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import quest.highworld.HighWorld;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

@SuppressWarnings("unused")
public class PlayerDataManager implements Listener {

    @Getter private static final PlayerDataManager instance = new PlayerDataManager();
    @Getter private final File playerDataDir = new File("plugins/HighWorld/Player Data");
    @Getter private final HashMap<UUID, PlayerData> playerData = new HashMap<>();
    @Getter private final ExecutorService executor = Executors.newCachedThreadPool();


    public PlayerDataManager(){
        playerDataDir.mkdirs();
    }

    public void setData(Player player, String variable, Object value){
        PlayerData data = playerData.get(player.getUniqueId());
        try {
            data.lock.acquireUninterruptibly();
            data.config.set(variable, value);
        }
        finally {
            data.lock.release();
        }
    }

    public void saveData(UUID uuid, YamlConfiguration config) throws IOException {
        File file = new File(playerDataDir, uuid+".yml");
        if (config.getKeys(false).isEmpty() && file.exists()) {
            file.delete();
        }
        else {
            config.save(file);
        }
    }

    public Object getData(Player player, String variable) {
        PlayerData data = playerData.get(player.getUniqueId());
        try {
            data.lock.acquireUninterruptibly();
            return data.config.get(variable);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            data.lock.release();
        }
    }

    public void saveAllData() {
        for (Map.Entry<UUID, PlayerData> entry : playerData.entrySet()) {
            try {
                entry.getValue().lock.acquireUninterruptibly();
                saveData(entry.getKey(), entry.getValue().config);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                entry.getValue().lock.release();
            }
        }
        executor.shutdown();
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onLogin(PlayerLoginEvent e) {
        if (e.getResult() == PlayerLoginEvent.Result.ALLOWED) {
            PlayerData data = new PlayerData();
            data.lock.acquireUninterruptibly();
            playerData.put(e.getPlayer().getUniqueId(), data);
            executor.submit(() -> {
                try {
                    data.config = YamlConfiguration.loadConfiguration(new File(playerDataDir, e.getPlayer().getUniqueId() + ".yml"));
                } finally {
                    data.lock.release();
                }
            });
        }
    }
    @EventHandler(priority = EventPriority.MONITOR)
    public void onQuit(PlayerQuitEvent e) {
        PlayerData data = playerData.remove(e.getPlayer().getUniqueId());
        executor.submit(() -> {
            try {
                data.lock.acquireUninterruptibly();
                saveData(e.getPlayer().getUniqueId(), data.config);
            } catch (IOException ex) {
                HighWorld.getInstance().getServer().getScheduler().runTask(HighWorld.getInstance(), ex::printStackTrace);
            } finally {
                data.lock.release();
            }
        });
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        if (getData(p, "rank") != null) return;

        setData(p, "first_join", new Date().getTime());
        setData(p, "playtime", 0);
        HighWorld.getInstance().getPermissionManager().setDefault(p);
        HighWorld.getInstance().getStatsManager().setDefault(p);
        HighWorld.getInstance().getSettingsManager().setDefault(p);

    }

    public static class PlayerData{
        private final Semaphore lock = new Semaphore(1);
        private YamlConfiguration config;
    }

}
