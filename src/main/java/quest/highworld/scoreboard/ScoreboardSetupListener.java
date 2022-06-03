package quest.highworld.scoreboard;

import com.github.retrooper.packetevents.event.PacketListenerPriority;
import com.github.retrooper.packetevents.event.simple.PacketLoginSendEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import quest.highworld.HighWorld;
import quest.highworld.event.types.PacketHighWorldListener;

public class ScoreboardSetupListener extends PacketHighWorldListener {

    public ScoreboardSetupListener() {
        super(PacketListenerPriority.LOW);
    }

    @Override
    public void onPacketLoginSend(PacketLoginSendEvent event) {
        PacketType.Login.Server packetType = event.getPacketType();
        if (packetType == PacketType.Login.Server.LOGIN_SUCCESS) {
            Bukkit.getServer().getScheduler().runTaskLater(HighWorld.getInstance(), () -> {
                Player player = Bukkit.getPlayerExact(event.getUser().getName());
                HighWorld.getInstance().getScoreboardManager().createScoreboard(player);
            }, 10);
        }
        else if (packetType == PacketType.Login.Server.DISCONNECT) {
            Bukkit.getServer().getScheduler().runTaskLater(HighWorld.getInstance(), () -> {
                Player player = Bukkit.getPlayerExact(event.getUser().getName());
                HighWorld.getInstance().getScoreboardManager().terminateScoreboard(player);
            }, 10);
        }
    }

}
