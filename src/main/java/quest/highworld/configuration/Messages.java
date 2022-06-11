package quest.highworld.configuration;

import org.bukkit.ChatColor;

public enum Messages {

    // Main plugin messages
    PREFIX("&7[&cHighWorld&7] "),
    CHAT_LOCAL_PREFIX("&7[&eL&7] "),
    CHAT_GLOBAL_PREFIX("&7[&eG&7] "),
    THEME_MAIN("&e"),
    THEME_SECONDARY("&7"),

    // Rank
    RANK_MEMBER("&7"),
    RANK_DONATOR("&f[&eDONATOR&f] &e"),
    RANK_HELPER("&f[&aHELPER&f] &a"),
    RANK_MODERATOR("&f[&bMODERATOR&f] &b"),
    RANK_ADMIN("&f[&cADMIN&f] &c"),
    RANK_OWNER("&f[&cOWNER&f] &c"),


    NO_PERMISSION(THEME_MAIN.msg + "You don't have permission to do that!"),
    INVALID_USAGE(THEME_MAIN.msg + "Invalid usage! Try %usage%"),
    INVALID_TARGET(THEME_MAIN.msg + "Invalid target! Make sure you're targeting an online player!"),

    // Player messages
    TARGET_GAMEMODE_CHANGED(THEME_MAIN.msg + "You set " + THEME_SECONDARY.msg +"%target%'s" + THEME_MAIN.msg + " gamemode to " + THEME_SECONDARY + "%gamemode%"),
    GAMEMODE_CHANGED(THEME_MAIN.msg + "Your gamemode has been set to "+ THEME_SECONDARY.msg + "%gamemode%"),

    TELEPORT_TO_TARGET(THEME_MAIN.msg + "You teleported to" + THEME_SECONDARY.msg + "%target%"),
    TELEPORT_TARGET1_TARGET2(THEME_MAIN.msg + "You teleported %target1% to" + THEME_SECONDARY.msg + "%target2%"),

    // World messages
    TELEPORTED_TO_FLOOR(THEME_MAIN.msg + "You teleported to the floor" + THEME_SECONDARY.msg + " %floor%"),

    ABILITY_ON_COOLDOWN(THEME_MAIN.msg + "You can't use that ability for another %cooldown% seconds!");


    private final String msg;
    Messages(String msg){
        this.msg = ChatColor.translateAlternateColorCodes('&', msg);
    }
    public String get(){
        return msg;
    }
}
