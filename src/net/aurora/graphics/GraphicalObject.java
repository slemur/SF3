package net.aurora.graphics;

import java.awt.*;

/**
 * @author Rick van Biljouw
 *         Date: 21-11-11
 *         Time: 1:27
 */
public class GraphicalObject {
    private Color[] colors;
    private int threshold;
    private int width = 20, height = 20;
    private double sensitivity;

    private int id;

    public GraphicalObject(int id, int threshold, double sensitivity, Color... colors) {
        this.id = id;
        this.threshold = threshold;
        this.sensitivity = sensitivity;
        this.colors = colors;
    }

    public int getId() {
        return this.id;
    }

    public int getThreshold() {
        return this.threshold;
    }

    public Color[] getColors() {
        return this.colors;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public double getSensitivity() {
        return sensitivity;
    }

    public void setSensitivity(double sensitivity) {
        this.sensitivity = sensitivity;
    }
}
