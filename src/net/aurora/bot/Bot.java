package net.aurora.bot;

import net.aurora.input.InputManager;
import net.aurora.input.listeners.ComponentListener;
import net.aurora.input.listeners.MouseAction;
import net.aurora.input.listeners.MouseMovement;
import net.aurora.loader.AppletLoader;
import net.aurora.loader.AppletLoaderContext;
import net.aurora.loader.page.Frame;
import net.aurora.loader.page.Server;
import net.aurora.rs.NodePrototype;
import net.aurora.sorcery.ClassContainer;
import net.aurora.sorcery.Sorcery;

import java.applet.Applet;
import java.awt.*;
import java.lang.reflect.Field;
import java.util.HashMap;
import javax.swing.JOptionPane;
import net.aurora.sorcery.reflection.FieldQuery;
import net.aurora.sorcery.reflection.FieldValue;
import net.aurora.util.ThreadUtils;
import serp.framework.prototype.PrototypeMatcher;

/**
 * @author Rick van Biljouw
 *         Date: 27-11-11
 *         Time: 4:48
 */
public class Bot {

    private static volatile Bot _singleton;
    private HashMap<String, ComponentListener> listenerHashMap = new HashMap<String, ComponentListener>();
    private InputManager manager = InputManager.create(this, true);
    private Sorcery sorcery = new Sorcery();
    //Applet loader stuff
    private AppletLoader loader;
    private AppletLoaderContext context;
    private Applet applet;

    public void init() {
        this.context = AppletLoaderContext.create(new Frame(Server.Language.ENGLISH).getServer());
        this.loader = new AppletLoader(this.context);
        this.applet = this.loader.getApplet();
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
     *
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
     * Get the "Sorcery" util package for client exploration.
     * @return
     */
    public Sorcery getSorcery() {
        return this.sorcery;
    }

    /**
     * Ensures only one instance of Bot is active.
     */
    public static synchronized Bot getSingleton() {
        if (_singleton == null) {
            _singleton = new Bot();
        }
        return _singleton;

    }
}
