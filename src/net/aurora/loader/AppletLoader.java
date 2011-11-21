package net.aurora.loader;

import net.aurora.game.Server;

import java.applet.Applet;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * @author Rick van Biljouw
 *         Date: 19-11-11
 *         Time: 19:32
 */
public class AppletLoader {

	private final Applet applet;
	private final AppletLoaderContext context;

	public AppletLoader(AppletLoaderContext appletLoaderContext) {
		context = appletLoaderContext;
                    System.out.println(
					"jar:" + context.getCodeBase()
							+ context.getParameter("archive") + "!/")
					;
		try {
			JarURLConnection clientConnection = ((JarURLConnection) new URL(
					"jar:" + context.getCodeBase()
							+ context.getParameter("archive") + "!/")
					.openConnection());
			clientConnection.addRequestProperty("Protocol", "HTTP/1.1");
			clientConnection.addRequestProperty("Connection", "keep-alive");
			clientConnection.addRequestProperty("Keep-Alive", "200");
			clientConnection.addRequestProperty("Referrer", "-");
			clientConnection.addRequestProperty("User-Agent",
					"Mozilla/4.0 (" + System.getProperty("os.name") + " "
							+ System.getProperty("os.version") + ") Java/"
							+ System.getProperty("java.version"));
            URLClassLoader classLoader = new URLClassLoader(new URL[]{new URL(context.getCodeBase()
							+ context.getParameter("archive"))});

			applet = (Applet) classLoader.loadClass(
					context.getParameter("code").replaceAll("\\.class", ""))
					.newInstance();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		applet.setStub(context);
		applet.init();
		applet.start();
	}

	public Applet getApplet() {
		return applet;
	}

	public AppletLoaderContext getContext() {
		return context;
	}

}