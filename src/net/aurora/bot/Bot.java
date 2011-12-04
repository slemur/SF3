package net.aurora.bot;

import net.aurora.input.InputModule;
import net.aurora.input.Mouse;
import net.aurora.input.listeners.MouseMovement;
import net.aurora.loader.AppletLoader;
import net.aurora.loader.AppletLoaderContext;
import net.aurora.loader.webobjects.Frame;
import net.aurora.loader.webobjects.Server;
import net.aurora.opengl.Interface;
import net.aurora.opengl.Settings;

import java.applet.Applet;
import java.awt.*;
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

        new Thread() {
            public void run() {


                while(true) {
                    try {
                        Interface i;
                        if((i = Interface.getInterfaceByChecksum(2521822756L)) != null) {



                            ((Mouse)getInputDevice("Mouse")).move(i.getX() + (i.getWidth()) / 2, i.getY() + (i.getHeight() / 2));
                            ((Mouse)getInputDevice("Mouse")).click(i.getX() + (i.getWidth()) / 2, i.getY() + (i.getHeight() / 2), true);
                        }

                        MouseMovement.attach(applet.getComponentAt(1,1));

                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                    }
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
     * Gets an attached input device.
     *
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
