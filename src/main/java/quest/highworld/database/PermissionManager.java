package quest.highworld.database;

import org.bukkit.entity.Player;
import quest.highworld.commands.Command;
import quest.highworld.configuration.Messages;
import quest.highworld.database.impl.PlayerDataManager;

public class PermissionManager {


    public PermissionManager() {
    }

    public boolean hasPermission(Player player, Command.Permission permission){
        int perm  = getPermissionUnsafe(player);
        return perm <= permission.getValue();
    }

    public void setPermission(Player player, Command.Permission permission){
        PlayerDataManager.getInstance().setData(player, "rank", permission.getValue());
    }

    public void setPermissionUnsafe(Player player, int permission){
        PlayerDataManager.getInstance().setData(player, "rank", permission);
    }

    public String getChatFormat(Command.Permission permission){
        switch (permission){
            case OWNER:
                return Messages.RANK_OWNER.get() + "%player%&7: &f%message%";
            case ADMIN:
                return Messages.RANK_ADMIN.get() + "%player%&7: &f%message%";
            case MODERATOR:
                return Messages.RANK_MODERATOR.get() + "%player%&7: &f%message%";
            case HELPER:
                return Messages.RANK_HELPER.get() + "%player%&7: &f%message%";
            case DONATOR:
                return Messages.RANK_DONATOR.get() + "%player%&7: &f%message%";
            case MEMBER:
                return Messages.RANK_MEMBER.get() + "%player%&7: &f%message%";
        }
        return "";
    }

    public int getPermissionUnsafe(Player player){
        return (int) PlayerDataManager.getInstance().getData(player, "rank");
    }

    public Command.Permission getPermission(Player player){
        int perm = getPermissionUnsafe(player);
        for (Command.Permission p : Command.Permission.values()){
            if (p.getValue() == perm){
                return p;
            }
        }
        return Command.Permission.MEMBER;
    }



    public void setDefault(Player player){
        setPermission(player, Command.Permission.MEMBER);
    }

}
