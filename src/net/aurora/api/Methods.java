package net.aurora.api;

import net.aurora.bot.Bot;
import net.aurora.graphics.FilterChain;
import net.aurora.graphics.GraphicalObject;
import net.aurora.graphics.QueryResult;
import net.aurora.graphics.detect.BlobDetector;
import net.aurora.graphics.filters.ColorFilter;

import java.awt.*;
import java.util.ArrayList;

/**
 * @author Rick van Biljouw
 *         Date: 21-11-11
 *         Time: 1:32
 */
public class Methods {
    private final BlobDetector blobs = new BlobDetector();
    private Bot bot;

    public QueryResult findObject(GraphicalObject object) {
        ColorFilter filter = new ColorFilter(object);
        ArrayList<Rectangle> results = blobs.getResults(object, filter.apply(this.bot.getBuffer().getGameBuffer()), filter.ORI_COLMAP);

        return new QueryResult(object, results.toArray(new Rectangle[results.size()]));
    }

    /**
     * Sets the bot for performing methods in this class.
     * @param bot
     */
    public void setBot(Bot bot) {
        this.bot = bot;
    }

    /**
     * Returns the bot used for performing methods in this class.
     * @return bot
     */
    public Bot getBot() {
        return this.bot;
    }
}
