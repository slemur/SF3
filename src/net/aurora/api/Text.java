package net.aurora.api;

import net.aurora.graphics.detect.BitmapDetector;
import net.aurora.graphics.filters.ColorFilter;
import net.aurora.graphics.ocr.Char;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

/**
 * @author Rick van Biljouw
 *         Date: 22-11-11
 *         Time: 1:05
 */
public class Text {
    private final static HashMap<Integer, BufferedImage> toptextCharacters = new HashMap<Integer, BufferedImage>();
    private final static ColorFilter toptextFilter = new ColorFilter(20, new Color(212, 211, 210), new Color(255, 255, 255), new Color(2, 211, 210));
    private Methods methods;

    public Text(Methods methods) {
        this.methods = methods;
    }

    /**
     * Slow as fuck PoC lool
     * @return
     */
    public String getToptext() {
        String toptext = "";
        final BufferedImage search = toptextFilter.apply(this.methods.getBot().getBuffer().getGameBuffer().getSubimage(4, 4, 140, 27));

        ArrayList<Char> foundCharacters = new ArrayList<Char>();
        for (int key : toptextCharacters.keySet()) {
            Rectangle found;

            if ((found = BitmapDetector.getResult(search, toptextCharacters.get(key), 2.0f)) != null) {
                foundCharacters.add(new Char(found, key));
            }
        }

        Collections.sort(foundCharacters, new Comparator<Char>() {
            public int compare(Char a, Char b) {
                return a.getBounds().x - b.getBounds().x;
            }
        });

        for(Char c : foundCharacters) {
            toptext += (char)c.getKey();
        }
        return toptext;
    }

    static {
        File f = new File("C:\\rs\\OCR\\");
        for (File file : f.listFiles()) {
            if (file.getName().endsWith(".bmp")) {
                try {
                    toptextCharacters.put(Integer.parseInt(file.getName().replace(".bmp", "")), toptextFilter.apply(ImageIO.read(file)));
                } catch (IOException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
            }
        }
    }
}
