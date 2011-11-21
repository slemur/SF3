package net.aurora.bot;

import net.aurora.game.Server;
import net.aurora.graphics.Buffer;
import net.aurora.loader.AppletLoader;
import net.aurora.loader.AppletLoaderContext;
import net.aurora.ui.AuroraUI;

import java.awt.*;
import java.util.HashMap;

/**
 * @author Rick van Biljouw
 *         Date: 20-11-11
 *         Time: 22:57
 */
public class Bot {
    private final static HashMap<Integer, Bot> INSTANCE_MAP = new HashMap<Integer, Bot>();
    private AppletLoader loader;
    private Buffer buffer;

    public Bot() {
        AuroraUI.statusLabel.setText("Navigating runescape.com to find applet data...");
        AppletLoaderContext context = AppletLoaderContext.create(new net.aurora.game.Frame(Server.Language.ENGLISH).getServer());
        AuroraUI.statusLabel.setText("Loading applet...");
        this.loader = new AppletLoader(context);
        this.loader.getApplet().setPreferredSize(new Dimension(762, 530));

        INSTANCE_MAP.put(this.loader.getApplet().hashCode(), this);
    }

    public void init(Canvas source) {
        this.buffer = new Buffer();

        AuroraUI.statusLabel.setText("All done!");
    }

    /**
     * Returns the applet loader for this bot
     *
     * @return loader
     */
    public AppletLoader getLoader() {
        return this.loader;
    }

    /**
     * Returns the screen buffer for this bot
     *
     * @return buffer
     */
    public Buffer getBuffer() {
        return this.buffer;
    }

    /**
     * Returns a bot instance related to the provided hash.
     *
     * @param hash
     * @return bot
     */
    public static Bot getInstance(int hash) {
        return INSTANCE_MAP.get(hash);
    }

}
