package quest.highworld.command.player;

import net.minestom.server.command.ConsoleSender;
import net.minestom.server.entity.Player;
import quest.highworld.Highworld;
import quest.highworld.command.HighworldCommand;
import quest.highworld.data.DataHelper;
import quest.highworld.data.Rank;
import quest.highworld.data.player.processors.AttributeProcessor;

public class InfoCommand extends HighworldCommand {

    public InfoCommand() {
        super("info");
        setRankRequired(Rank.MEMBER);

        setDefaultExecutor((sender, args) -> {
            if (sender instanceof ConsoleSender) return;
            Player player = (Player) sender;
            AttributeProcessor ap = Highworld.getInstance().getPlayerDataManager().getPlayerData(player).getAttributeProcessor();
            DataHelper dh = Highworld.getInstance().getDataHelper();

            String maxHp = "Max HP: " + ap.getMaxHealth() + " Database HP: " + dh.getMaxHealth(player);
            String maxMana = "Max Mana: " + ap.getMaxMana() + " Database Mana: " + dh.getMaxMana(player);
            String strength = "Strength: " + ap.getStrength() + " Database Strength: " + dh.getStrength(player);
            String critChance = "Crit Chance: " + ap.getCritChance() + " Database Crit Chance: " + dh.getCriticalChance(player);
            String critDamage = "Crit Damage: " + ap.getCritDamage() + " Database Crit Damage: " + dh.getCriticalDamage(player);

            sender.sendMessage(maxHp);
            sender.sendMessage(maxMana);
            sender.sendMessage(strength);
            sender.sendMessage(critChance);
            sender.sendMessage(critDamage);
        });
    }

}
