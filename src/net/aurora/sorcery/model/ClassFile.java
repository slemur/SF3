package net.aurora.sorcery.model;

import net.aurora.sorcery.Sorcery;
import serp.bytecode.BCClass;
import serp.bytecode.BCField;
import serp.bytecode.BCMethod;

/**
 * @author Rick van Biljouw
 *         Date: 15-1-12
 *         Time: 11:57
 */
public class ClassFile {
    private BCClass classObject;

    public ClassFile(Sorcery sorceryInstance, String className) {
        this.classObject = sorceryInstance.getClassContainer().getClassByName(className);
    }

    /**
     * Get the serp class object
     *
     * @return class
     */
    public BCClass getClassObject() {
        return this.classObject;
    }

    /**
     * Get all methods in this class
     *
     * @return methods
     */
    public BCMethod[] getMethods() {
        return classObject.getMethods();
    }

    /**
     * Get all methods that match a name in this class
     *
     * @param name the name
     * @return methods
     */
    public BCMethod[] getMethodsByName(String name) {
        return classObject.getMethods(name);
    }

    /**
     * Get all methods that match specified name and argument types
     *
     * @param name      name
     * @param arguments argument types
     * @return methods
     */
    public BCMethod[] getNamedMethodsByArguments(String name, String[] arguments) {
        return classObject.getMethods(name, arguments);
    }

    /**
     * Get all fields contained by this class.
     *
     * @return fields
     */
    public BCField[] getFields() {
        return classObject.getFields();
    }

}
