package net.aurora.api;

import net.aurora.graphics.GraphicalObject;
import net.aurora.graphics.GraphicalObjectDefinition;
import net.aurora.graphics.detect.BlobDetector;
import net.aurora.graphics.filters.HeatFilter;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author Rick van Biljouw
 *         Date: 22-11-11
 *         Time: 1:05
 */
public class WorldObjects {
    private Methods methods;

    public WorldObjects(Methods methods) {
        this.methods = methods;
    }

        /**
     * Tries to find objects on the screen and returns the results
     * @param objectDefinition The definition of the object to search for
     * @return objects
     */
    public GraphicalObject[] findObject(GraphicalObjectDefinition objectDefinition) {


        HeatFilter filter = new HeatFilter(objectDefinition);
        BufferedImage heatImg = filter.apply(this.methods.getBot().getBuffer().getGameBuffer());
        ArrayList<Rectangle> bounds = BlobDetector.getResults(objectDefinition, heatImg, filter.ORI_COLMAP);

        GraphicalObject[] results = new GraphicalObject[bounds.size()];
        for(int i = 0; i < bounds.size(); i++) {
            results[i] = new GraphicalObject(this.methods, objectDefinition, bounds.get(i));
        }
        return results;
    }
}
