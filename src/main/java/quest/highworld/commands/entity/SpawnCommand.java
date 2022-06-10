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
        return "Displays the help menu";
    }
    @Override public String getUsage() {
        return "spawn";
    }
    @Override public Integer minArgs() {
        return 0;
    }
    @Override public Permission getPermission() {
        return Permission.MEMBER;
    }
    @Override public boolean isSingleton() {
        return true;
    }
    @Override
    public List<String> getAliases() {
        return Collections.emptyList();
    }


    @Override
    public void execute(Player player, String label, String[] args) {
        HighWorld.getInstance().getHighWorldMobManager().spawnMob(player.getLocation(), HighWorldMob.TEST);
    }

}
