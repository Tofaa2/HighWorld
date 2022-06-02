package quest.highworld.factory.itemstack.ability;

import com.github.retrooper.packetevents.event.PacketListenerPriority;
import com.github.retrooper.packetevents.event.simple.PacketPlayReceiveEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import com.github.retrooper.packetevents.protocol.player.InteractionHand;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientAnimation;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientInteractEntity;
import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import quest.highworld.HighWorld;
import quest.highworld.configuration.Messages;
import quest.highworld.event.PacketHighWorldListener;

import java.util.HashMap;

public class PacketItemAbilityListener extends PacketHighWorldListener {


    public static final HashMap<Player, HashMap<ItemAbility, Integer>> cooldowns = new HashMap<>();

    public PacketItemAbilityListener() {
        super(PacketListenerPriority.HIGH);
    }

    @Override
    public void onPacketPlayReceive(PacketPlayReceiveEvent event){
        PacketType.Play.Client packetType = event.getPacketType();

        switch (packetType){
            case INTERACT_ENTITY: switchPacketEntityHit(event); break;
            case ANIMATION: switchPacketLeftClick(event); break;
        }

    }

    private void useAbility(Player player, ItemAbility ability){

        switch (ability) {
            case FIREBALL_LARGE_SHOOT: {
                HighWorld.getInstance().getProjectileManager().launchSyncProjectile(player, Fireball.class);
                break;
            }

            case FIREBALL_MINI_SHOOT: {

            }
        }

    }
    private boolean inCooldown(Player player, ItemAbility ability, int col){

        if (!cooldowns.containsKey(player)) return false;

        HashMap<ItemAbility, Integer> cd = cooldowns.get(player);

        if (!cd.containsKey(ability)) return false;

        int cdTime = cd.get(ability);

        if (HighWorld.getInstance().getSystemUtilities().getSecondsSince(cdTime) >= col) return false;
        player.sendMessage(Messages.ABILITY_ON_COOLDOWN.get().replace("%cooldown%", String.valueOf(col - HighWorld.getInstance().getSystemUtilities().getSecondsSince(cdTime))));
        player.playSound(player.getLocation(), Sound.VILLAGER_NO, 1, 1);
        return true;
    }


    private void switchPacketEntityHit(final PacketPlayReceiveEvent event){

        WrapperPlayClientInteractEntity wrapper = new WrapperPlayClientInteractEntity(event);
        WrapperPlayClientInteractEntity.InteractAction action = wrapper.getAction();
        Player player = Bukkit.getPlayerExact(event.getUser().getName());

        if (action != WrapperPlayClientInteractEntity.InteractAction.ATTACK) return;

        ItemStack heldItem = player.getItemInHand();
        if (heldItem == null || heldItem.getType() == Material.AIR) return;

        NBTItem nbtItem = new NBTItem(heldItem);

        if (!nbtItem.hasKey("Ability") || !nbtItem.hasKey("Ability-Interaction")) return;

        String abilityStr = nbtItem.getString("Ability");
        String interactionStr = nbtItem.getString("Ability-Interaction");
        Integer cooldown = nbtItem.getInteger("Ability-Cooldown");

        ItemAbility ability = ItemAbility.valueOf(abilityStr);
        ItemAbilityInteractionType interactionType = ItemAbilityInteractionType.valueOf(interactionStr);
        if (interactionType != ItemAbilityInteractionType.ENTITY_HIT) return;

        if (inCooldown(player, ability, cooldown)) return;
        updateCooldown(player, ability, cooldown);
        useAbility(player, ability);
    }
    private void switchPacketLeftClick(final PacketPlayReceiveEvent event){
        WrapperPlayClientAnimation wrapper = new WrapperPlayClientAnimation(event);
        Player player = Bukkit.getPlayerExact(event.getUser().getName());


        // Don't think we need this check cause 1.8 but just in case
        if (wrapper.getHand() != InteractionHand.MAIN_HAND) return;

        ItemStack heldItem = player.getItemInHand();
        if (heldItem == null || heldItem.getType() == Material.AIR) return;

        NBTItem nbtItem = new NBTItem(heldItem);

        if (!nbtItem.hasKey("Ability") || !nbtItem.hasKey("Ability-Interaction")) return;

        String abilityStr = nbtItem.getString("Ability");
        String interactionStr = nbtItem.getString("Ability-Interaction");
        Integer cooldown = nbtItem.getInteger("Ability-Cooldown");

        ItemAbility ability = ItemAbility.valueOf(abilityStr);
        ItemAbilityInteractionType interactionType = ItemAbilityInteractionType.valueOf(interactionStr);

        if (interactionType != ItemAbilityInteractionType.LEFT_CLICK) return;

        if (inCooldown(player, ability, cooldown)) return;
        updateCooldown(player, ability, cooldown);
        useAbility(player, ability);

    }

    private void updateCooldown(Player player, ItemAbility ability, int cooldown){
        HashMap<ItemAbility, Integer> updated = cooldowns.get(player);
        updated.put(ability, cooldown);
        cooldowns.put(player, updated);
    }

}
