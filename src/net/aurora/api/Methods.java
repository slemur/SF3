package net.aurora.api;

import net.aurora.bot.Bot;
import net.aurora.graphics.GraphicalObject;
import net.aurora.graphics.QueryResult;
import net.aurora.graphics.detect.BlobDetector;
import net.aurora.graphics.filters.ColorFilter;
import net.aurora.input.Mouse;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Rick van Biljouw
 *         Date: 21-11-11
 *         Time: 1:32
 */
public class Methods {
    private final HashMap<Integer, BufferedImage> characters = new HashMap<Integer, BufferedImage>();
    private final BlobDetector blobs = new BlobDetector();
    private final Mouse mouse = new Mouse(this);
    ColorFilter filter = new ColorFilter(new GraphicalObject(0, 0, 5, new Color(212, 211, 210), new Color(255, 255, 255), new Color(13, 8, 1), new Color(2, 211, 210)));

    private Bot bot;

    public Methods() {
        for (File f : new File("C:\\rs\\OCR\\").listFiles()) {
            try {
                if (f.getName().equals("result.png")) continue;
                characters.put(Integer.parseInt(f.getName().replace(".bmp", "")), filter.apply(ImageIO.read(f)));
            } catch (IOException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }
    }

    /**
     * Finds an object on the screen and returns the results
     *
     * @param object
     * @return
     */
    public QueryResult findObject(GraphicalObject object) {
        ColorFilter filter = new ColorFilter(object);
        ArrayList<Rectangle> results = blobs.getResults(object, filter.apply(this.bot.getBuffer().getGameBuffer()), filter.ORI_COLMAP);

        return new QueryResult(object, results.toArray(new Rectangle[results.size()]));
    }

    /**
     * Sets the bot for performing methods in this class.
     *
     * @param bot
     */
    public void setBot(Bot bot) {
        this.bot = bot;
    }

    /**
     * Returns the bot used for performing methods in this class.
     *
     * @return bot
     */
    public Bot getBot() {
        return this.bot;
    }

    public Mouse getMouse() {
        return mouse;
    }
}
