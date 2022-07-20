package quest.highworld;

import net.minestom.server.MinecraftServer;
import lombok.Getter;
import net.minestom.server.extras.MojangAuth;
import net.minestom.server.extras.optifine.OptifineSupport;
import quest.highworld.command.CommandManager;
import quest.highworld.data.DataHelper;
import quest.highworld.data.player.PlayerDataManager;
import quest.highworld.entity.EntityManager;
import quest.highworld.event.EventManager;
import quest.highworld.world.World;

import java.io.File;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

public class Highworld {

    @Getter private static Highworld instance;

    @Getter private final MinecraftServer server = MinecraftServer.init();
    @Getter private final DataHelper dataHelper = new DataHelper();
    @Getter private final PlayerDataManager playerDataManager = new PlayerDataManager();

    @Getter private final World world = new World();
    @Getter private final CommandManager commandManager = new CommandManager();
    @Getter private final EventManager eventManager;

    @Getter private final EntityManager entityManager = new EntityManager();

    public Highworld() {
        if (instance == null) {
            instance = this;
        } else {
            throw new IllegalStateException("Highworld is already initialized");
        }

        eventManager = new EventManager();
    }


    public void run(){
        MojangAuth.init();
        OptifineSupport.enable();
        server.start("0.0.0.0", 25565);
    }

    public void shutdown() {
        world.save();

        MinecraftServer.stopCleanly();
    }


    public static File folder() {
        String decoded = URLDecoder.decode(Highworld.class.getProtectionDomain().getCodeSource().getLocation().getPath(), StandardCharsets.UTF_8);
        decoded = decoded.substring(0, decoded.lastIndexOf("/"));
        return new File(decoded);
    }
    public static void main(String[] args) {new Highworld().run();}
}
