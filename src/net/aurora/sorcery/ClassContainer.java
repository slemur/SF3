package net.aurora.sorcery;

import serp.bytecode.BCClass;
import serp.bytecode.Project;

import java.util.ArrayList;

/**
 * @author Rick van Biljouw
 *         Date: 13-1-12
 *         Time: 21:13
 */
public class ClassContainer {
    private ArrayList<BCClass> classes = new ArrayList<BCClass>();
    private Project project = new Project("runescape");

    public void defineClass(String name, ClassLoader classLoader) {
        project.loadClass(name, classLoader);
        System.out.println("Loaded " + name + " from " + classLoader.hashCode());
    }
}
