package quest.highworld.commands.utility;

import org.bukkit.entity.Player;
import quest.highworld.commands.Command;

import java.util.List;

public class HelpCommand implements Command {
    @Override
    public String getName() {
        return "help";
    }

    @Override
    public String getDescription() {
        return "Displays the help menu";
    }

    @Override
    public String getUsage() {
        return "help";
    }

    @Override
    public Integer minArgs() {
        return 0;
    }

    @Override
    public Permission getPermission() {
        return Permission.MEMBER;
    }
    @Override
    public boolean isSingleton() {
        return true;
    }

    @Override
    public List<String> getAliases() {
        return List.of("?", "h", "help", "pleasehelpme", "info");
    }


    @Override
    public void execute(Player player, String label, String[] args) {

    }


}
