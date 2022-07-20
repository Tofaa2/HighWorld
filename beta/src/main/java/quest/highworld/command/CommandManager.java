package quest.highworld.command;

import net.minestom.server.MinecraftServer;
import quest.highworld.command.player.GiveWeaponCommand;
import quest.highworld.command.player.InfoCommand;
import quest.highworld.command.player.SpawnMobCommand;
import quest.highworld.command.server.GamemodeCommand;
import quest.highworld.command.server.GrantCommand;
import quest.highworld.command.server.StopCommand;
import quest.highworld.command.worldedit.WandCommand;

public class CommandManager {

    public CommandManager() {

        // Worldedit
        register(new WandCommand());

        // Player
        register(new GiveWeaponCommand());
        register(new SpawnMobCommand());
        register(new InfoCommand());

        // Server
        register(new StopCommand());
        register(new GrantCommand());
        register(new GamemodeCommand());
    }

    public void register(HighworldCommand command) {
        MinecraftServer.getCommandManager().register(command);
    }
}
