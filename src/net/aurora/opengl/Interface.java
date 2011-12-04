package net.aurora.opengl;

import java.awt.*;

/**
 * @author Rick van Biljouw
 *         Date: 30-11-11
 *         Time: 5:05
 */
public class Interface {
    private int x;
    private int y;
    private int width;
    private int height;
    private long checksum;

    public Interface(int x, int y, int width, int height, long checksum) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.checksum = checksum;
    }


    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public long getChecksum() {
        return checksum;
    }

    public static native Interface getInterfaceByChecksum(long checksum);

    public static native void toggleInterfaceDebug();
}
