package quest.highworld.floor;

import org.bukkit.WorldCreator;
import org.bukkit.WorldType;
import quest.highworld.HighWorld;

public class FloorManager {


    public FloorManager() {
        init();
    }

    public void init(){
        WorldCreator wc = new WorldCreator("floor-1");
        wc.type(WorldType.FLAT);
        wc.generatorSettings("2;0;1;");
        HighWorld.getInstance().getServer().createWorld(wc);

        wc = new WorldCreator("floor-2");
        wc.type(WorldType.FLAT);
        wc.generatorSettings("2;0;1;");
        HighWorld.getInstance().getServer().createWorld(wc);

        wc = new WorldCreator("floor-3");
        wc.type(WorldType.FLAT);
        wc.generatorSettings("2;0;1;");
        HighWorld.getInstance().getServer().createWorld(wc);

        wc = new WorldCreator("floor-4");
        wc.type(WorldType.FLAT);
        wc.generatorSettings("2;0;1;");
        HighWorld.getInstance().getServer().createWorld(wc);

        wc = new WorldCreator("floor-5");
        wc.type(WorldType.FLAT);
        wc.generatorSettings("2;0;1;");
        HighWorld.getInstance().getServer().createWorld(wc);
    }





}
