package quest.highworld.gui;

import quest.highworld.gui.guis.help.HelpGUI;
import quest.highworld.gui.guis.help.stats.StatsInfoGUI;

import java.util.HashMap;

public class GUIManager {


    //  Both of these are cache methods to avoid recreating the gui every time.
    private final HashMap<String, GUIItem> items;
    private final HashMap<String, GUI> guis;

    public GUIManager() {
        items = new HashMap<>();
        guis = new HashMap<>();

        addGUI("stats_info_gui", new StatsInfoGUI());
        addGUI("help_gui", new HelpGUI());
    }


    public void addItem(String name, GUIItem item) {
        items.put(name, item);
    }

    public void addGUI(String name, GUI gui) {
        guis.put(name, gui);
    }

    public GUIItem getItem(String name) {
        return items.get(name);
    }

    public GUI getGUI(String name) {
        return guis.get(name);
    }

}
