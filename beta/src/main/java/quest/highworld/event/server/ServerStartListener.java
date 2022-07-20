package quest.highworld.event.server;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.minestom.server.MinecraftServer;
import net.minestom.server.entity.Player;
import net.minestom.server.event.EventListener;
import net.minestom.server.timer.Task;
import net.minestom.server.utils.time.TimeUnit;
import org.jetbrains.annotations.NotNull;
import quest.highworld.Highworld;
import quest.highworld.data.player.PlayerData;
import quest.highworld.data.player.processors.AttributeProcessor;
import quest.highworld.event.custom.ServerStartEvent;

import java.time.Duration;

public class ServerStartListener implements EventListener<ServerStartEvent> {
    @Override
    public @NotNull Class<ServerStartEvent> eventType() {
        return ServerStartEvent.class;
    }

    @Override
    public @NotNull Result run(@NotNull ServerStartEvent event) {


        Task.Builder actionbarTask = MinecraftServer.getSchedulerManager().buildTask(() -> {
            for (Player player : MinecraftServer.process().connection().getOnlinePlayers()) {
                PlayerData playerData = Highworld.getInstance().getPlayerDataManager().getPlayerData(player);
                if (playerData == null) continue; // This is a check that issues every once in like 1000/20/15 times
                AttributeProcessor ap = playerData.getAttributeProcessor();


                TextComponent actionbar =
                    Component.text(ap.getHealth(), NamedTextColor.RED)
                            .append(Component.text("/", NamedTextColor.GRAY))
                            .append(Component.text(ap.getMaxHealth(), NamedTextColor.RED)
                            .append(Component.text("❤", NamedTextColor.RED)))
                            .append(Component.space())
                            .append(Component.text(ap.getMana(), NamedTextColor.BLUE)
                            .append(Component.text("/", NamedTextColor.GRAY))
                            .append(Component.text(ap.getMaxMana(), NamedTextColor.BLUE)
                            .append(Component.text("⸎", NamedTextColor.BLUE)))
                            .append(Component.space())
                            .append(Component.text(ap.getDefense(), NamedTextColor.GREEN)))
                            .append(Component.space())
                            .append(Component.text(ap.getStrength()))
                            .append(Component.space())
                            .append(Component.text(ap.getCritChance(), NamedTextColor.YELLOW))
                            .append(Component.space())
                            .append(Component.text(ap.getCritDamage(), NamedTextColor.YELLOW))
                            .append(Component.space());

                player.sendActionBar(actionbar);
            }
        });

        actionbarTask.repeat(Duration.of(10, TimeUnit.SERVER_TICK)).schedule();
        return Result.SUCCESS;
    }
}
