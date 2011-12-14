package net.aurora.external;

import java.awt.*;

/**
 * @author Rick van Biljouw
 *         Date: 14-12-11
 *         Time: 21:35
 *         An implementation of JAWT used for performing operations on a canvas.
 */
public class NativeCanvas {

    /**
     * Draw a string to the screen
     * @param text the string
     * @param x the x position of the string
     * @param y the y position of the string
     */
    public static native void drawString(String text, int x, int y);

    /**
     * Draw a rectangle on the screen
     * @param x x position of rectangle
     * @param y y position of rectangle
     * @param width width of rectangle
     * @param height height of rectangle
     */
    public static native void drawRect(int x, int y, int width, int height);

    /**
     * Set the color to be used to draw stuff
     * @param color color
     */
    public static native void setColor(Color color);
}
