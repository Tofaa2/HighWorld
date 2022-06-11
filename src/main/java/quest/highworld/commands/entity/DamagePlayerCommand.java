package quest.highworld.commands.entity;

import org.bukkit.entity.Player;
import quest.highworld.HighWorld;
import quest.highworld.commands.Command;
import quest.highworld.configuration.Messages;
import quest.highworld.database.StatsManager;
import quest.highworld.utilities.PlayerUtil;
import quest.highworld.utilities.Strings;

import java.util.Collections;
import java.util.List;

public class DamagePlayerCommand implements Command {

    @Override public String getName() {
        return "damageplayer";
    }
    @Override public String getDescription() {
        return "Damages a player by a certain amount";
    }
    @Override public String getUsage() {
        return "damageplayer <player> <amount>";
    }
    @Override public Integer minArgs() {
        return 2;
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

        Player target = HighWorld.getInstance().getServer().getPlayer(args[0]);
        if (target == null){
            player.sendMessage(Strings.cc(Messages.INVALID_TARGET.name()));
            return;
        }

        int amount = Integer.parseInt(args[1]);

        PlayerUtil.damage(target, amount);

    }


}
