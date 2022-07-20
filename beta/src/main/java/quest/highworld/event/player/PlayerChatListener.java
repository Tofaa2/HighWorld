package quest.highworld.event.player;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.minestom.server.entity.Player;
import net.minestom.server.event.EventListener;
import net.minestom.server.event.player.PlayerChatEvent;
import org.jetbrains.annotations.NotNull;
import quest.highworld.Highworld;
import quest.highworld.data.Rank;

public class PlayerChatListener implements EventListener<PlayerChatEvent> {
    @Override
    public @NotNull Class<PlayerChatEvent> eventType() {
        return PlayerChatEvent.class;
    }

    @Override
    public @NotNull Result run(@NotNull PlayerChatEvent event) {

        Player player = event.getPlayer();
        Rank rank = Highworld.getInstance().getDataHelper().getRank(player);
        String message = event.getMessage();

        TextComponent format;

        switch (rank) {
            case DONATOR -> {
                format = Component.text("Donator " + player.getUsername(), NamedTextColor.GOLD).append(Component.text(": ").append(Component.text(message, NamedTextColor.WHITE)));
            }
            case VIP -> {
                format = Component.text("VIP " + player.getUsername(), NamedTextColor.GREEN).append(Component.text(": ").append(Component.text(message, NamedTextColor.WHITE)));
            }
            case MVP -> {
                format = Component.text("MVP " + player.getUsername(), NamedTextColor.AQUA).append(Component.text(": ").append(Component.text(message, NamedTextColor.WHITE)));
            }
            case HELPER -> {
                format = Component.text("Helper " + player.getUsername(), NamedTextColor.DARK_GREEN).append(Component.text(": ").append(Component.text(message, NamedTextColor.WHITE)));
            }
            case MODERATOR -> {
                format = Component.text("Moderator " + player.getUsername(), NamedTextColor.LIGHT_PURPLE).append(Component.text(": ").append(Component.text(message, NamedTextColor.WHITE)));
            }
            case ADMINISTRATOR -> {
                format = Component.text("Administrator " + player.getUsername(), NamedTextColor.RED).append(Component.text(": ").append(Component.text(message, NamedTextColor.WHITE)));
            }
            case OWNER -> {
                format = Component.text("Owner " + player.getUsername(), NamedTextColor.RED).append(Component.text(": ").append(Component.text(message, NamedTextColor.WHITE)));
            }
            default -> {
                format = Component.text(player.getUsername(), NamedTextColor.GRAY).append(Component.text(": ").append(Component.text(message, NamedTextColor.WHITE)));
            }
        }

        event.setChatFormat((e) -> format);
        return Result.SUCCESS;
    }


}
