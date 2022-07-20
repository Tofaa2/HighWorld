package quest.highworld.commands.world;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.entity.Player;
import quest.highworld.HighWorld;
import quest.highworld.commands.Command;
import quest.highworld.configuration.Messages;
import quest.highworld.utilities.Strings;

import java.util.Arrays;
import java.util.List;

public class TPFloorCommand implements Command {

    @Override public String getName() {
        return "tpfloor";
    }

    @Override public String getDescription() {
        return "Lets you teleport to a specific floor";
    }

    @Override public String getUsage() {
        return "tpfloor <floor>";
    }

    @Override public Integer minArgs() {return 1;}

    @Override public Permission getPermission() {
        return Permission.MEMBER;
    }

    @Override public boolean isSingleton() {return false;}

    @Override
    public List<String> getAliases() {return Arrays.asList("teleportfloor", "tpgameworld", "tptofloor");}


    @Override
    public void execute(Player player, String label, String[] args) {
        int floor = Integer.parseInt(player.getWorld().getName().replace("floor-", ""));
        try {
            floor = Integer.parseInt(args[0]);
        }
        catch (NumberFormatException e) {
            player.sendMessage(Strings.cc(Messages.PREFIX + "§cInvalid floor number"));
        }

        if (floor > 100 || floor < 1) {
            player.sendMessage(Strings.cc(Messages.PREFIX +"§cInvalid floor number"));
            return;
        }

        WorldCreator wc = new WorldCreator("floor-" + floor);
        wc.generatorSettings("2;0;1;");
        World worldFloor = HighWorld.getInstance().getServer().createWorld(wc);

        player.teleport(new Location(worldFloor, 0, 0, 0));
        player.sendMessage(Strings.cc(Messages.PREFIX + "You have been teleported to floor " + floor));
    }
}

