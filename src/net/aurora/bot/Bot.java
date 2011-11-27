package net.aurora.bot;

import net.aurora.loader.AppletLoader;
import net.aurora.loader.AppletLoaderContext;
import net.aurora.loader.webobjects.Frame;
import net.aurora.loader.webobjects.Server;

import java.applet.Applet;

/**
 * @author Rick van Biljouw
 *         Date: 27-11-11
 *         Time: 4:48
 */
public class Bot {
    private AppletLoaderContext context;
    private Applet applet;

    public Bot() {
        this.context = AppletLoaderContext.create(new Frame(Server.Language.ENGLISH).getServer());

        AppletLoader loader = new AppletLoader(this.context);
        this.applet = loader.getApplet();
    }

    public Applet getApplet() {
        return applet;
    }
}
