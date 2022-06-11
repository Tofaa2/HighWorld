package quest.highworld;

import com.github.retrooper.packetevents.PacketEvents;
import io.github.retrooper.packetevents.factory.spigot.SpigotPacketEventsBuilder;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;
import quest.highworld.commands.CommandsManager;
import quest.highworld.database.PermissionManager;
import quest.highworld.database.PlayDateManager;
import quest.highworld.database.SettingsManager;
import quest.highworld.database.StatsManager;
import quest.highworld.database.impl.PlayerDataManager;
import quest.highworld.event.HighWorldEventManager;
import quest.highworld.factory.entity.HighWorldMobManager;
import quest.highworld.factory.projectile.ProjectileManager;
import quest.highworld.floor.FloorManager;
import quest.highworld.gui.GUIManager;
import quest.highworld.scoreboard.ScoreboardManager;
import quest.highworld.utilities.nms.NMSUtil;

public class HighWorld extends JavaPlugin {


    @Getter private static HighWorld instance;

    @Getter private HighWorldEventManager eventManager;

    @Getter private PermissionManager permissionManager;
    @Getter private PlayDateManager playDateManager;
    @Getter private StatsManager statsManager;
    @Getter private SettingsManager settingsManager;

    @Getter private CommandsManager commandsManager;

    @Getter private ScoreboardManager scoreboardManager;

    @Getter private NMSUtil NMSUtil;

    @Getter private ProjectileManager projectileManager;

    @Getter private SystemUtilities systemUtilities;

    @Getter private FloorManager floorManager;

    @Getter private HighWorldMobManager highWorldMobManager;

    @Getter private GUIManager GUIManager;

    @Override
    public void onEnable() {
        instance = this;
        eventManager = new HighWorldEventManager();

        permissionManager = new PermissionManager();
        statsManager = new StatsManager();
        playDateManager = new PlayDateManager();
        settingsManager = new SettingsManager();
        NMSUtil = new NMSUtil();
        scoreboardManager = new ScoreboardManager();
        commandsManager = new CommandsManager(this);
        projectileManager = new ProjectileManager();
        systemUtilities = new SystemUtilities();
        highWorldMobManager = new HighWorldMobManager();
        floorManager = new FloorManager();

        GUIManager = new GUIManager();

        registerEvents();
        getDataFolder().mkdirs();

        runSyncTaskTimer(() ->{getServer().getOnlinePlayers().forEach(player -> {
            String reg = "&c%h%&8/&c%mh%❤ &9%m%&8/&9%mm%";

            String msg = reg.replace("%h%", String.valueOf(statsManager.getIntStat(player, StatsManager.Stat.HEALTH)));
            msg = msg.replace("%mh%", String.valueOf(statsManager.getIntStat(player, StatsManager.Stat.MAX_HEALTH)));
            msg = msg.replace("%m%", String.valueOf(statsManager.getIntStat(player, StatsManager.Stat.MANA)));
            msg = msg.replace("%mm%", String.valueOf(statsManager.getIntStat(player, StatsManager.Stat.MAX_MANA)));
            NMSUtil.sendActionbar(player, msg);
            });
        }, 0, 10);
    }

    @Override
    public void onDisable() {

    }


    @Override
    public void onLoad() {
        instance = this;
        PacketEvents.setAPI(SpigotPacketEventsBuilder.build(this));
        //Are all listeners read only?
        PacketEvents.getAPI().getSettings().readOnlyListeners(true)
                .checkForUpdates(false)
                .bStats(false);
        PacketEvents.getAPI().load();
    }

    private void registerEvents(){
        getServer().getPluginManager().registerEvents(PlayerDataManager.getInstance(), this); // Data manager
    }
    public void runSyncTask(Runnable runnable){getServer().getScheduler().runTask(this, runnable);}
    public void runAsyncTask(Runnable runnable){getServer().getScheduler().runTaskAsynchronously(this, runnable);}
    public void runSyncTaskLater(Runnable runnable, long delay){getServer().getScheduler().runTaskLater(this, runnable, delay);}
    public void runAsyncTaskLater(Runnable runnable, long delay){getServer().getScheduler().runTaskLaterAsynchronously(this, runnable, delay);}
    public void runSyncTaskTimer(Runnable runnable, long delay, long period){getServer().getScheduler().runTaskTimer(this, runnable, delay, period);}
    public void runAsyncTaskTimer(Runnable runnable, long delay, long period){getServer().getScheduler().runTaskTimerAsynchronously(this, runnable, delay, period);}

}
