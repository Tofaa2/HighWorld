package quest.highworld.util;

import net.minestom.server.MinecraftServer;
import org.apache.commons.io.Charsets;
import org.apache.commons.io.FileUtils;
import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class JSONConfiguration {

    private final File file;
    private final Map<String, Object> options;

    public JSONConfiguration(File file, Map<String, Object> options) {
        this.file = file;

        if (!file.exists()) {
            MinecraftServer.LOGGER.info("Configuration file does not exist, creating it.");
            try {
                file.createNewFile();
                FileWriter wr = new FileWriter(file);
                wr.write("{");
                wr.write("}");
                wr.flush();
                wr.close();
                MinecraftServer.LOGGER.info("Configuration file created.");
            }
            catch (IOException e) {
                MinecraftServer.LOGGER.error("Could not create configuration file", e);
            }
        }

        this.options = options;
    }

    public JSONConfiguration(File file) {
        this(file, new HashMap<>());
    }

    public Object get(String key) {
        return options.get(key);
    }

    public void set(String key, Object value) {
        options.put(key, value);
    }


    public boolean isEmpty() {
        return options.isEmpty();
    }

    public void save() {
        try {
            JSONObject json = new JSONObject(options);
            file.createNewFile();
            FileWriter writer = new FileWriter(file);
            writer.write(json.toString(4));
            writer.flush();
            writer.close();
        }
        catch (IOException e) {
            MinecraftServer.LOGGER.error("Could not save configuration to " + file.getAbsolutePath());
        }
    }



    public static JSONConfiguration loadExistingConfiguration(File file) {
        try {
            JSONObject json = new JSONObject(FileUtils.readFileToString(file, Charsets.UTF_8));
            return new JSONConfiguration(file, json.toMap());
        }
        catch (IOException e) {
            MinecraftServer.LOGGER.error("Could not load configuration from " + file.getAbsolutePath() + ". Creating a blank one.");
            return new JSONConfiguration(file);
        }
    }

    public static JSONConfiguration createNewConfiguration(File file) {
        return new JSONConfiguration(file);
    }


}
