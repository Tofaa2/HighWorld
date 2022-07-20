package quest.highworld.world.worldedit.util;

import net.minestom.server.coordinate.Pos;
import net.minestom.server.instance.block.Block;

import java.util.HashMap;

public class PasteInformation {

    private final HashMap<Pos, Block> blocks = new HashMap<>();


    public void addBlock(Pos pos, Block block) {
        blocks.put(pos, block);
    }

    public HashMap<Pos, Block> getBlocks(){return blocks;}

}
