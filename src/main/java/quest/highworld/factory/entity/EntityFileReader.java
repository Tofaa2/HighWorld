package quest.highworld.factory.entity;

import lombok.Getter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import quest.highworld.HighWorld;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class EntityFileReader {

    private final File file = new File(HighWorld.getInstance().getDataFolder(), "entity.yml");
    @Getter private final FileConfiguration entityConfiguration = YamlConfiguration.loadConfiguration(file);

    public EntityFileReader() {

        if (!file.exists()) {
            try {
                file.createNewFile();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void save() {
        HighWorld.getInstance().getLogger().info("Saving entity.yml");
        try {
            entityConfiguration.save(file);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        HighWorld.getInstance().getLogger().info("Saved entity.yml");
    }

    public HashMap<Integer, Object> load(){
        HashMap<Integer, Object> map = new HashMap<>();
        for (String key : entityConfiguration.getKeys(false)) {
            map.put(Integer.parseInt(key), entityConfiguration.get(key));
        }
        return map;
    }

    public Object get(String path) {
        return entityConfiguration.get(path);
    }


}
