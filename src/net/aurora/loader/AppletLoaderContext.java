package net.aurora.loader;

import net.aurora.game.Server;

import java.applet.Applet;
import java.applet.AppletContext;
import java.applet.AppletStub;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Rick van Biljouw
 *         Date: 19-11-11
 *         Time: 19:33
 */
public class AppletLoaderContext implements AppletStub, AppletContext, Enumeration<Applet> {

    public static boolean ALLOW_SHOW_DOCUMENT = true;

    private int nextElementCalled;

    private HashMap<String, InputStream> appletStreams;

    private final HashMap<String, String> parameters;

    private URL codeBase;

    private URL documentBase;

    public AppletLoaderContext() {
        nextElementCalled = 0;
        appletStreams = new HashMap<String, InputStream>();
        this.parameters = new HashMap<String, String>();
    }

    public void appletResize(int width, int height) {
    }

    public AppletContext getAppletContext() {
        return this;
    }

    public URL getCodeBase() {
        return codeBase; // e.g. world\\d+\\.runescape\\.com/
    }

    public void setCodeBase(URL codeBase) {
        this.codeBase = codeBase;
    }

    public URL getDocumentBase() {
        return documentBase; // e.g. world\\d+\\.runescape\\.com/a2,m0,j0,o0
    }

    public void setDocumentBase(URL documentBase) {
        this.documentBase = documentBase;
    }

    public String getParameter(String name) {
        return this.parameters.get(name);
    }

    public void putParameter(String key, String value) {
        this.parameters.put(key, value);
    }

    public boolean isActive() {
        return true;
    }

    public java.applet.AudioClip getAudioClip(URL url) {
        return new AudioClip(url);
    }

    public Image getImage(URL url) {
        return Toolkit.getDefaultToolkit().createImage(url);
    }

    public Applet getApplet(String name) {
        return null;
    }

    public Enumeration<Applet> getApplets() {
        return this;
    }

    public void showDocument(URL url) {
        if (ALLOW_SHOW_DOCUMENT && Desktop.isDesktopSupported()) {
            try {
                Desktop.getDesktop().browse(url.toURI());
            } catch (IOException e) {
                e.printStackTrace();
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        } else {
            if (url.toString().contains("runescape.com/l=")) {

            }
        }
    }

    public void showDocument(URL url, String target) {
        showDocument(url);
    }

    public void showStatus(String status) {
        System.out.println(status);
    }

    public void setStream(String key, InputStream stream) throws IOException {
        appletStreams.put(key, stream);
    }

    public InputStream getStream(String key) {
        return appletStreams.get(key);
    }

    public Iterator<String> getStreamKeys() {
        return appletStreams.keySet().iterator();
    }

    public boolean hasMoreElements() {
        return (nextElementCalled == 0);
    }

    public Applet nextElement() throws NoSuchElementException {
        nextElementCalled++;
        if (nextElementCalled != 1)
            throw new NoSuchElementException();
        return null;
    }

    public static AppletLoaderContext create(Server server) {
        try {
            AppletLoaderContext ctx = new AppletLoaderContext();
            HashMap<String, String> parameters = server.getParameters();

            ctx.setCodeBase(server.getCodeBase());
            ctx.setDocumentBase(server.getDocumentBase());
            for (String key : parameters.keySet()) {
                ctx.putParameter(key, parameters.get(key));
            }

            return ctx;
        } catch (MalformedURLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return null;
    }
}
