package quest.highworld.chat;

import com.github.retrooper.packetevents.event.PacketListenerPriority;
import com.github.retrooper.packetevents.event.simple.PacketPlayReceiveEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientChatMessage;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;
import quest.highworld.HighWorld;
import quest.highworld.commands.Command;
import quest.highworld.database.StatsManager;
import quest.highworld.event.types.PacketHighWorldListener;
import quest.highworld.factory.itemstack.type.weapon.swords.TestSword;
import quest.highworld.utilities.Strings;

import java.text.SimpleDateFormat;

public class PacketPlayerChatListener extends PacketHighWorldListener {

    public PacketPlayerChatListener(){
        super(PacketListenerPriority.HIGH);
    }

    @Override
    public void onPacketPlayReceive(PacketPlayReceiveEvent event) {

        if (event.getPacketType() == PacketType.Play.Client.CHAT_MESSAGE){
            WrapperPlayClientChatMessage packet = new WrapperPlayClientChatMessage(event);
            String message = packet.getMessage();
            if (message.startsWith("/")){return;}
            event.setCancelled(true);
            Player player = HighWorld.getInstance().getServer().getPlayer(event.getUser().getProfile().getUUID());
            Command.Permission permission = HighWorld.getInstance().getPermissionManager().getPermission(player);
            String formatted = HighWorld.getInstance().getPermissionManager().getChatFormat(permission).replace("%message%", message).replace("%player%", player.getDisplayName());
            TextComponent textComponent = new TextComponent(Strings.cc(formatted));

            String playerInfo = "&aPlayer: &2" + player.getDisplayName() + "\n" +
                    "&aRank: &2" + permission.name() + "\n" +
                    "&aFirst Join: &2" + new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(HighWorld.getInstance().getPlayDateManager().getFirstJoin(player)) + "\n" +
                    "&aStats: " + "\n" +
                    "  &2Level: &6" + HighWorld.getInstance().getStatsManager().getStat(player, StatsManager.Stat.PLAYER_LEVEL) + "\n" +
                    "  &2XP: &6" + HighWorld.getInstance().getStatsManager().getStat(player, StatsManager.Stat.PLAYER_XP);

            textComponent.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(Strings.cc(playerInfo)).create()));
            for (Player p : HighWorld.getInstance().getServer().getOnlinePlayers()){
                p.spigot().sendMessage(textComponent);
            }
            player.getInventory().addItem(new TestSword(true));
        }
    }

}
