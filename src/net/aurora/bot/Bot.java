package net.aurora.bot;

import net.aurora.input.InputManager;
import net.aurora.input.listeners.ComponentListener;
import net.aurora.input.listeners.MouseAction;
import net.aurora.input.listeners.MouseMovement;
import net.aurora.loader.AppletLoader;
import net.aurora.loader.AppletLoaderContext;
import net.aurora.loader.page.Frame;
import net.aurora.loader.page.Server;
import net.aurora.util.ThreadUtils;

import java.applet.Applet;
import java.awt.*;
import java.util.HashMap;

/**
 * @author Rick van Biljouw
 *         Date: 27-11-11
 *         Time: 4:48
 */
public class Bot {
    private static Bot _singleton;
    private HashMap<String, ComponentListener> listenerHashMap = new HashMap<String, ComponentListener>();
    private InputManager manager = InputManager.create(this, true);

    //Applet loader stuff
    private AppletLoader loader;
    private AppletLoaderContext context;
    private Applet applet;

    public Bot() {
        this.context = AppletLoaderContext.create(new Frame(Server.Language.ENGLISH).getServer());
        this.loader = new AppletLoader(this.context);
        this.applet = loader.getApplet();

        new Thread() {
            public void run() {
                while(true) {
                    ThreadUtils.sleep(this, 500);
                }
            }
        }.start();
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
    public void bind(Component component) {
        if (!listenerHashMap.containsKey("MouseAction")) { // Not yet initialized
            listenerHashMap.put("MouseAction", new MouseAction());
            listenerHashMap.put("MouseMovement", new MouseMovement());
        }

        listenerHashMap.get("MouseAction").attach(component);
        listenerHashMap.get("MouseMovement").attach(component);
    }

    /**
     * Ensures only one instance of Bot is active.
     */
    public static Bot getSingleton() {
        if (_singleton == null) _singleton = new Bot();
        return _singleton;
    }
}
