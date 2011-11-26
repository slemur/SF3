package net.aurora.graphics;

import net.aurora.api.Methods;

import java.awt.*;

/**
 * @author Rick van Biljouw
 *         Date: 21-11-11
 *         Time: 1:36
 */
public class GraphicalObject {
    private GraphicalObjectDefinition objectDefinition;
    private Rectangle bounds;
    private Methods methods;

    public GraphicalObject(Methods methods, GraphicalObjectDefinition objectDefinition, Rectangle bounds) {
        this.objectDefinition = objectDefinition;
        this.bounds = bounds;
        this.methods = methods;
    }

    /**
     * Gets the definition of this object
     * @return objectDefinition
     */
    public GraphicalObjectDefinition getObjectDefinition() {
        return this.objectDefinition;
    }

    /**
     * Gets the bounds of this object
     * @return bounds
     */
    public Rectangle getBounds() {
        return this.bounds;
    }

}
