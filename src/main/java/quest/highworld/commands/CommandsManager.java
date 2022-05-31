package quest.highworld.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_8_R3.CraftServer;
import org.bukkit.entity.Player;
import quest.highworld.HighWorld;
import quest.highworld.commands.utility.HelpCommand;
import quest.highworld.configuration.Messages;
import quest.highworld.utilities.Strings;

import java.util.ArrayList;
import java.util.List;

public class CommandsManager {

    private final List<Command> commands;

    public CommandsManager(HighWorld plugin){
        this.commands = new ArrayList<>();// Since we are creating this list once and never updating on runtime, we can use an ArrayList


        // Adding commands
        registerCommand(new HelpCommand());

        for (Command command : commands) {
            if (!command.isSingleton()) continue;
            ((CraftServer) plugin.getServer()).getCommandMap().register("highworld", new org.bukkit.command.Command(command.getName(), command.getDescription(), command.getUsage(), command.getAliases()) {
                @Override
                public boolean execute(CommandSender commandSender, String s, String[] args) {
                    if (!(commandSender instanceof Player)) {
                        commandSender.sendMessage(Strings.cc("&cYou must be a player to use this command!"));
                        return false;
                    }
                    Player player = (Player) commandSender;
                    if (!HighWorld.getInstance().getPermissionManager().hasPermission(player, command.getPermission())){
                        player.sendMessage(Strings.cc(Messages.PREFIX.get()) + Messages.NO_PERMISSION.get());
                        return false;
                    }

                    if (args.length < command.minArgs()){
                        String format = Strings.cc(
                                Messages.PREFIX.get()
                                        + Messages.INVALID_USAGE.get().replace("%usage%", "/highworld " + command.getUsage())
                        );
                        player.sendMessage(format);
                        return false;
                    }

                    command.execute(player, s, args);
                    return true;
                }
            });
        }

        plugin.getCommand("highworld").setExecutor((commandSender, cmd, label, args) -> {

            if (!(commandSender instanceof Player)) {
                commandSender.sendMessage(Strings.cc("&cYou must be a player to use this command!"));
                return false;
            }

            Player player = (Player) commandSender;

            if (args.length == 0) {
                String format = Strings.cc(
                        Messages.PREFIX.get()
                              + Messages.THEME_MAIN.get()
                              + "/highworld " + Messages.THEME_SECONDARY.get() + "%name%" + Messages.THEME_MAIN.get() + " - %description%"
                );
                for (Command command : commands) {
                    player.sendMessage(format.replace("%name%", command.getName()).replace("%description%", command.getDescription()));
                }
                return false;
            }


            String name = args[0];
            String[] newArgs = new String[args.length - 1];
            System.arraycopy(args, 1, newArgs, 0, args.length - 1);
            Command command = getCommand(args[0]);
            if (command == null) {
                player.sendMessage(Strings.cc(Messages.PREFIX.get() + Messages.THEME_MAIN.get() + "Unknown command: " + Messages.THEME_SECONDARY.get() + args[0]));
                return false;
            }

            if (!HighWorld.getInstance().getPermissionManager().hasPermission(player, command.getPermission())){
                player.sendMessage(Strings.cc(Messages.PREFIX.get()) + Messages.NO_PERMISSION.get());
                return false;
            }


            if (args.length - 1 < command.minArgs()){
                String format = Strings.cc(
                        Messages.PREFIX.get()
                                + Messages.INVALID_USAGE.get().replace("%usage%", "/highworld " + command.getUsage())
                );
                player.sendMessage(format);
                return false;
            }

            command.execute(player, name, newArgs);
            return true;
        });
    }

    public void registerCommand(Command command){
        commands.add(command);
    }

    public Command getCommand(String name){
        for (Command command : commands){
            if (command.getName().equalsIgnoreCase(name)){
                return command;
            }
        }
        return null;
    }


}