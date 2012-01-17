package net.aurora.sorcery;

import net.aurora.rs.JavaRendererPlanePrototype;
import serp.bytecode.BCClass;
import serp.bytecode.Project;
import serp.framework.prototype.PrototypeMatcher;

import java.io.ByteArrayInputStream;

/**
 * @author Rick van Biljouw
 *         Date: 13-1-12
 *         Time: 21:13
 */
public class ClassContainer {
    private Project project = new Project("client");
    private PrototypeMatcher matcher = new PrototypeMatcher(new JavaRendererPlanePrototype());

    /**
     * Called by ClassLoader.class
     * @param bytes the bytes of the class.
     */
    public void defineClass(byte[] bytes) {
        BCClass clazz = project.loadClass(new ByteArrayInputStream(bytes));
        matcher.match(clazz);
    }

    /**
     * Get a class by its name
     * @param name the name of the class
     * @return the class
     */
    public BCClass getClassByName(String name) {
        return this.project.loadClass(name);
    }

    /**
     * Get all classes defined in the project.
     * @return all classes
     */
    public BCClass[] getClasses() {
        return this.project.getClasses();
    }

}
