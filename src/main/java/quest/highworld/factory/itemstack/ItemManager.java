package quest.highworld.factory.itemstack;

import com.github.retrooper.packetevents.PacketEvents;
import com.github.retrooper.packetevents.event.PacketListenerPriority;
import com.github.retrooper.packetevents.event.simple.PacketPlayReceiveEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientInteractEntity;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import quest.highworld.HighWorld;
import quest.highworld.event.PacketHighWorldListener;

import java.util.Arrays;
import java.util.HashMap;

public class ItemManager {

    @Getter private final HashMap<String, HighWorldItem> items;

    public ItemManager() {
        items = new HashMap<>();
    }

    public void addItem(String name, HighWorldItem item) {
        HighWorld.getInstance().getLogger().info("Adding Item ID: " + name);
        items.put(name, item);
    }

    public ItemStack getItem(String name) {
        return items.get(name);
    }




}
