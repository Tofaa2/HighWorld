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
import quest.highworld.factory.projectile.ProjectileManager;
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

        registerEvents();
        getDataFolder().mkdirs();
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

}
