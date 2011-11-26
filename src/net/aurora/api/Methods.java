package net.aurora.api;

import net.aurora.bot.Bot;

import java.awt.*;

/**
 * @author Rick van Biljouw
 *         Date: 21-11-11
 *         Time: 1:32
 */
public class Methods {
    private final WorldObjects worldObjects = new WorldObjects(this);
    private final Text text = new Text(this);
    private Bot bot;

    /**
     * Grab graphics to draw on
     * NOTE: These graphics should be obtained and stored in a variable.
     * Multiple calls to getGraphics will result in cleaning of the previously painted region.
     *
     * @return bufferGraphics
     */
    public Graphics getGraphics() {
        return this.bot.getBuffer().getBotBuffer().getGraphics();
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

    public WorldObjects getWorldObjects() {
        return worldObjects;
    }

    public Text getText() {
        return text;
    }
}
