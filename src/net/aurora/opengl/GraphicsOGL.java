package net.aurora.opengl;

import java.awt.*;

/**
 * @author Rick van Biljouw
 *         Date: 26-11-11
 *         Time: 22:50
 */
public class GraphicsOGL {

    /**
     * Draws a string on the screen using OpenGL.
     * @param text
     * @param x
     * @param y
     */
    public static native void drawString(String text, int x, int y);

    /**
     * Sets the color drawing operations are done with using OpenGL
     * @param color
     */
    public static native void setColor(Color color);

    /**
     * Gets the pixels on the screen using OpenGL.
     * @return pixels
     */
    public static native int[][] getPixels();

}
