package quest.highworld.commands;

import lombok.Getter;
import org.bukkit.entity.Player;

import java.util.List;

public interface Command {

    String getName();
    String getDescription();
    String getUsage();
    Integer minArgs();
    Permission getPermission();
    void execute(Player player, String label, String[] args);

    boolean isSingleton();

    List<String> getAliases();
    enum Permission {

        MEMBER(50),
        DONATOR(40),
        HELPER(30),
        MODERATOR(20),
        ADMIN(10),
        OWNER(0);

        @Getter private final int value;
        Permission(int value){
            this.value = value;
        }
    }



}
