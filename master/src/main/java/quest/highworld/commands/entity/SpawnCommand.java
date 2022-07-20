package quest.highworld.commands.entity;

import org.bukkit.entity.Player;
import quest.highworld.HighWorld;
import quest.highworld.commands.Command;
import quest.highworld.factory.entity.HighWorldMob;

import java.util.Collections;
import java.util.List;

public class SpawnCommand implements Command {

    @Override public String getName() {
        return "spawn";
    }
    @Override public String getDescription() {
        return "Spawns a mob with a specific id";
    }
    @Override public String getUsage() {
        return "spawn <id>";
    }
    @Override public Integer minArgs() {
        return 1;
    }
    @Override public Permission getPermission() {
        return Permission.ADMIN;
    }
    @Override public boolean isSingleton() {
        return false;
    }
    @Override
    public List<String> getAliases() {
        return Collections.emptyList();
    }


    @Override
    public void execute(Player player, String label, String[] args) {
        String id = args[0];
        if (HighWorldMob.getMob(id) == null) {
            player.sendMessage("Invalid mob id");
            return;
        }

        HighWorldMob mob = HighWorldMob.getMob(id);

        HighWorld.getInstance().getHighWorldMobManager().spawnMob(player.getLocation(), mob);


    }

}
