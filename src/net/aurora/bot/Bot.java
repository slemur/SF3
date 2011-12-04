package net.aurora.bot;

import net.aurora.input.listeners.ComponentListener;
import net.aurora.input.listeners.MouseAction;
import net.aurora.input.listeners.MouseMovement;
import net.aurora.loader.AppletLoader;
import net.aurora.loader.AppletLoaderContext;
import net.aurora.loader.webobjects.Frame;
import net.aurora.loader.webobjects.Server;

import java.applet.Applet;
import java.util.HashMap;

/**
 * @author Rick van Biljouw
 *         Date: 27-11-11
 *         Time: 4:48
 */
public class Bot {
    private static Bot _singleton;
    private HashMap<String, ComponentListener> listenerHashMap = new HashMap<String, ComponentListener>();

    //Applet loader stuff
    private AppletLoader loader;
    private AppletLoaderContext context;
    private Applet applet;

    public Bot() {
        this.context = AppletLoaderContext.create(new Frame(Server.Language.ENGLISH).getServer());
        this.loader = new AppletLoader(this.context);
        this.applet = loader.getApplet();
    }

    /**
     * Returns the game applet
     *
     * @return
     */
    public Applet getApplet() {
        return this.applet;
    }

    /**
     * Returns a bound component listener
     * @param name name of the listeners
     * @return derp
     */
    public ComponentListener getListener(String name) {
        return this.listenerHashMap.get(name);
    }

    /*
     * Bind event listeners
     */
    public void bind() {
        if (!listenerHashMap.containsKey("MouseAction")) { // Not yet initialized
            listenerHashMap.put("MouseAction", new MouseAction());
            listenerHashMap.put("MouseMovement", new MouseMovement());
        }

        listenerHashMap.get("MouseAction").attach(applet.getComponentAt(1, 1));
        listenerHashMap.get("MouseMovement").attach(applet.getComponentAt(1, 1));
    }

    /**
     * Ensures only one instance of Bot is active.
     */

    public static Bot getSingleton() {
        if (_singleton == null) _singleton = new Bot();
        return _singleton;
    }
}
