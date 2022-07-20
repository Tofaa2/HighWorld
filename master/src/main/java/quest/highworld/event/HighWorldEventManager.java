package quest.highworld.event;

import com.github.retrooper.packetevents.PacketEvents;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import quest.highworld.HighWorld;
import quest.highworld.chat.PacketPlayerChatListener;
import quest.highworld.event.types.PacketHighWorldListener;
import quest.highworld.factory.entity.events.MobDamageListener;
import quest.highworld.factory.entity.events.MobDeathListener;
import quest.highworld.factory.itemstack.ability.PacketItemAbilityListener;
import quest.highworld.gui.GUIClickListener;
import quest.highworld.scoreboard.ScoreboardSetupListener;

import java.util.ArrayList;
import java.util.List;

public class HighWorldEventManager {

    @Getter private final List<PacketHighWorldListener> packetEvents;
    @Getter private final List<Listener> bukkitEvents;


    public HighWorldEventManager(){
        // Since we are creating this list once and never updating on runtime, we can use an ArrayList
        this.packetEvents = new ArrayList<>();
        this.bukkitEvents = new ArrayList<>();

        //Events
        addListener(new PacketPlayerChatListener());
        addListener(new ScoreboardSetupListener());
        addListener(new GUIClickListener());

        addListener(new PacketItemAbilityListener());

        //addListener(new DamageHandlerListener());
        addListener(new MobDamageListener());
        addListener(new MobDeathListener());


        register();
    }


    public void addListener(PacketHighWorldListener event){
        this.packetEvents.add(event);
    }
    public void addListener(Listener listener){this.bukkitEvents.add(listener);}

    public void register(){
        for (PacketHighWorldListener event : packetEvents){
            HighWorld.getInstance().getLogger().info("Registering packet event: " + event.getClass().getSimpleName());
            PacketEvents.getAPI().getEventManager().registerListener(event);
        }
        for (Listener listener : bukkitEvents){
            HighWorld.getInstance().getLogger().info("Registering bukkit event: " + listener.getClass().getSimpleName());
            Bukkit.getServer().getPluginManager().registerEvents(listener, HighWorld.getInstance());
        }
    }

}
