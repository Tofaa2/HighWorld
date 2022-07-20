package quest.highworld.data.player;

import lombok.Getter;
import net.minestom.server.entity.Player;
import quest.highworld.data.player.processors.AttributeProcessor;
import java.util.UUID;

public class PlayerData {



    @Getter
    private final Player player;
    @Getter
    private final UUID uuid;

    @Getter
    private final AttributeProcessor attributeProcessor;


    public PlayerData(Player player) {
        this.player = player;
        this.uuid = player.getUuid();

        this.attributeProcessor = new AttributeProcessor(player);
    }


}
