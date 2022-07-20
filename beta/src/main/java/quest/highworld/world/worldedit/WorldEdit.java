package quest.highworld.world.worldedit;

import net.minestom.server.coordinate.Pos;
import net.minestom.server.instance.block.Block;
import quest.highworld.world.worldedit.util.PasteInformation;

import java.io.*;
import java.util.HashMap;

public class WorldEdit {

    private final HashMap<File, PasteInformation> pasteInformation = new HashMap<>();

    public PasteInformation getPasteInformation(File file) {
        return pasteInformation.get(file);
    }

    public void readFile(File file) {
        PasteInformation pi = new PasteInformation();
        FileInputStream fis;
        try {
            fis = new FileInputStream(file);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            String line;
            while ((line = br.readLine()) != null) {
                String[] split = line.split(" ");
                if (split.length == 4) {
                    pi.addBlock(new Pos(Integer.parseInt(split[0]), Integer.parseInt(split[1]), Integer.parseInt(split[2])), Block.fromBlockId(Integer.parseInt(split[3])));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        pasteInformation.put(file, pi);
    }

    public void writeFile(File file) {
        PasteInformation pi = pasteInformation.get(file);

        FileOutputStream fos;
        try {
            fos = new FileOutputStream(file);
            OutputStreamWriter osw = new OutputStreamWriter(fos);
            BufferedWriter bw = new BufferedWriter(osw);
            for (Pos pos : pi.getBlocks().keySet()) {
                bw.write(pos.x() + " " + pos.y() + " " + pos.z() + " " + pi.getBlocks().get(pos).id());
                bw.newLine();
            }
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
