package net.aurora.sorcery;

import serp.bytecode.BCClass;
import serp.bytecode.Project;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author Rick van Biljouw
 *         Date: 13-1-12
 *         Time: 21:13
 */
public class ClassContainer {
    private Project project = new Project("client");

    /**
     * Called by ClassLoader.class
     * @param bytes the bytes of the class.
     */
    public void defineClass(byte[] bytes) {
        project.loadClass(new ByteArrayInputStream(bytes));
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
