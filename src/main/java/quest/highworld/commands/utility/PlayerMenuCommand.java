package quest.highworld.commands.utility;

import org.bukkit.entity.Player;
import quest.highworld.commands.Command;
import quest.highworld.gui.player.PlayerGUI;
import java.util.Collections;
import java.util.List;

public class PlayerMenuCommand implements Command {
    @Override public String getName() {
        return "playermenu";
    }
    @Override public String getDescription() {
        return "Displays the player menu";
    }
    @Override public String getUsage() {
        return "playermenu";
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
        new PlayerGUI(player).open(player);
    }

}
