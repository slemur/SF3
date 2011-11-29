package net.aurora.bot;

import net.aurora.Application;
import net.aurora.input.InputModule;
import net.aurora.input.Mouse;
import net.aurora.loader.AppletLoader;
import net.aurora.loader.AppletLoaderContext;
import net.aurora.loader.webobjects.Frame;
import net.aurora.loader.webobjects.Server;
import net.aurora.opengl.GraphicsOGL;
import net.aurora.opengl.NpcOGL;
import net.aurora.util.Logger;

import java.applet.Applet;
import java.util.HashMap;

/**
 * @author Rick van Biljouw
 *         Date: 27-11-11
 *         Time: 4:48
 */
public class Bot {
    private HashMap<String, InputModule> inputDevices = new HashMap<String, InputModule>();
    private AppletLoaderContext context;
    private Applet applet;

    public Bot() {
        this.context = AppletLoaderContext.create(new Frame(Server.Language.ENGLISH).getServer());

        AppletLoader loader = new AppletLoader(this.context);
        this.applet = loader.getApplet();

        this.attachInput();
        final Bot bot = this;
        new Thread("NPC Test") {
            public void run() {
                while(Application.getUI() == null);
                Logger.log(bot, "Starting NPC test...");

                while(true) {
                    NpcOGL npc = NpcOGL.getNpcByChecksum(2013165131);
                    if(npc != null) {
                        Logger.log(bot, "Found goblin with checksum " + npc.getChecksum() + " at " + npc.getX() + ", " + npc.getY());
                        Logger.log(bot, "Moved mouse to " + npc.getX() + ", " + npc.getY());
                        ((Mouse)getInputDevice("Mouse")).move(npc.getX(), npc.getY());
                    } else {

                    }

                    try {
                        Thread.sleep(600);
                    } catch (InterruptedException e) {
                        e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                    }
                }
            }
        }.start();
    }

    /**
     * Returns the game applet
     * @return
     */
    public Applet getApplet() {
        return this.applet;
    }

    /**
     * Gets an attached input device.
     * @param name the device name
     * @return
     */
    public InputModule getInputDevice(String name) {
        return this.inputDevices.get(name);
    }

    /**
     * Attach devices to the applet
     */
    private void attachInput() {
        Mouse mouse = new Mouse();
        mouse.setBot(this);

        inputDevices.put("Mouse", mouse);
    }
}
