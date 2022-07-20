package quest.highworld.commands;

import lombok.Getter;
import org.bukkit.entity.Player;

import java.util.List;

public interface Command {


    /**
     * @return The name of the command
     */
    String getName();

    /**
     * @return The description of the command
     */
    String getDescription();

    /**
     * @return The usage of the command
     */
    String getUsage();

    /**
     * @return Minimum number of arguments required to run the command
     */
    Integer minArgs();

    /**
     * @return The permission required to run the command
     * @see Permission
     */
    Permission getPermission();

    /**
     * @return Whether the command is a standalone command or a part of the main /highworld command
     */
    boolean isSingleton();

    /**
     * @return The aliases of the command
     */
    List<String> getAliases();

    /**
     * Method called when the command is run
     * @param player The player who ran the command
     * @param label The name of the command
     * @param args The arguments passed to the command
     */
    void execute(Player player, String label, String[] args);


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
