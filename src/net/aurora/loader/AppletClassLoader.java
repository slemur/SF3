package net.aurora.loader;

import java.io.IOException;
import java.net.JarURLConnection;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * @author Rick van Biljouw
 *         Date: 19-11-11
 *         Time: 19:36
 */
public class AppletClassLoader extends ClassLoader {
    private JarURLConnection connection;

    public AppletClassLoader(JarURLConnection connection) {
        this.connection = connection;
    }

    public void load() throws IOException {
        JarFile file = connection.getJarFile();

        Enumeration<JarEntry> entries = file.entries();
        while(entries.hasMoreElements()) {
            JarEntry entry = entries.nextElement();

            if(entry.getName().endsWith(".class")) {
                byte[] buffer = new byte[(int)entry.getSize()];
                file.getInputStream(entry).read(buffer, 0, (int)entry.getSize());

                System.out.println("Defined class " + entry.getName().replace(".class", "") + ", " +  buffer + ", " +  0 + ", "+  buffer.length);
                this.defineClass(entry.getName().replace(".class", ""), buffer, 0, buffer.length);
            }
        }
    }


}
