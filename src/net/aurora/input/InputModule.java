package net.aurora.input;

import java.awt.*;

/**
 * @author Rick van Biljouw
 *         Date: 27-11-11
 *         Time: 0:05
 */
public abstract class InputModule {
    private BotLeaf bot;

    public Component getActiveComponent() {
        return bot.getLoader().getApplet().getComponentAt(1,1);
    }

    public BotLeaf getBot() {
        return bot;
    }

    public void setBot(BotLeaf bot) {
        this.bot = bot;
    }
}
