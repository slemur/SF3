package net.aurora.bot;

import net.aurora.api.Methods;
import net.aurora.game.Server;
import net.aurora.graphics.Buffer;
import net.aurora.graphics.GraphicalObject;
import net.aurora.graphics.QueryResult;
import net.aurora.input.listeners.MouseEventListener;
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
    private final MouseEventListener listener = new MouseEventListener();
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
        this.buffer = new Buffer(this);

        source.addMouseMotionListener(listener);
        AuroraUI.statusLabel.setText("All done!");
    }

    /**
     * Called whenver the game buffer is updated
     * @param g
     */
    public void onRepaint(Graphics g) {
        g.setColor(Color.WHITE);
        g.drawLine(this.listener.getX() - 5, this.listener.getY() - 5, this.listener.getX() + 5, this.listener.getY() + 5);
        g.drawLine(this.listener.getX() + 5, this.listener.getY() - 5, this.listener.getX() + -5, this.listener.getY() + 5);
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
     * Returns the canvas component of the applet
     *
     * @return
     */
    public Canvas getCanvas() {
        return (Canvas) getLoader().getApplet().getComponentAt(1, 1);
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
