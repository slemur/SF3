package net.aurora.input;

import net.aurora.bot.Bot;

import java.awt.*;

/**
 * @author Rick van Biljouw
 *         Date: 27-11-11
 *         Time: 0:05
 */
public abstract class InputModule {
    private Bot bot;

    public InputModule(Bot bot) {
        this.bot = bot;
    }

    public Component getActiveComponent() {
        return bot.getApplet().getComponentAt(1,1);
    }

    public Bot getBot() {
        return bot;
    }
}
