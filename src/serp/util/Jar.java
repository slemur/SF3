package serp.util;

import serp.bytecode.Project;

import java.io.IOException;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * @author rvbiljouw
 *         Date: 17-1-12
 *         Time: 14:00
 */
public class Jar {
    private Project aJarProject;
    private JarFile file;

    public Jar(String path) throws IOException {
        this(new JarFile(path));
    }

    public Jar(JarFile file) {
        this.file = file;
        this.aJarProject = new Project(this.file.getName());
    }

    public void load() throws IOException {
        Enumeration<JarEntry> entries = this.file.entries();

        while (entries.hasMoreElements()) {

            JarEntry entry = entries.nextElement();

            if (entry.getName().endsWith(".class")) {
                this.aJarProject.loadClass(this.file.getInputStream(entry));
            }

        }
    }

    public Project getProject() {
        return this.aJarProject;
    }
}
