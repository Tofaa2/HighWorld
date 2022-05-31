package quest.highworld.factory.itemstack.ability;

import com.github.retrooper.packetevents.event.PacketListenerPriority;
import com.github.retrooper.packetevents.event.simple.PacketPlayReceiveEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientInteractEntity;
import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.Bukkit;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.inventory.ItemStack;
import quest.highworld.HighWorld;
import quest.highworld.event.PacketHighWorldListener;

public class PacketItemAbilityListener extends PacketHighWorldListener {


    public PacketItemAbilityListener() {
        super(PacketListenerPriority.HIGH);
    }

    @Override
    public void onPacketPlayReceive(PacketPlayReceiveEvent event){
        PacketType.Play.Client packetType = event.getPacketType();

        if(packetType != PacketType.Play.Client.ENTITY_ACTION) return;

        WrapperPlayClientInteractEntity wrapper = new WrapperPlayClientInteractEntity(event);
        WrapperPlayClientInteractEntity.InteractAction action = wrapper.getAction();

        if (action != WrapperPlayClientInteractEntity.InteractAction.ATTACK) return;

        Player player = Bukkit.getPlayerExact(event.getUser().getName());
        ItemStack heldItem = player.getItemInHand();

        if(heldItem == null) return;

        NBTItem nbtItem = new NBTItem(heldItem);

        if (!nbtItem.hasKey("Ability") || !nbtItem.hasKey("Ability-Interaction")) return;

        String abilityStr = nbtItem.getString("Ability");
        String interactionStr = nbtItem.getString("Ability-Interaction");
        ItemAbility ability = ItemAbility.valueOf(abilityStr);
        ItemAbilityInteractionType interactionType = ItemAbilityInteractionType.valueOf(interactionStr);

        if (interactionType != ItemAbilityInteractionType.ENTITY_HIT) return;
        System.out.println("Ability: " + ability);
        System.out.println("Interaction: " + interactionType);

        switch (ability){
            case FIREBALL_LARGE_SHOOT:
                HighWorld.getInstance().getProjectileManager().launchSyncProjectile(player, Fireball.class);
                break;
            }
    }

}
