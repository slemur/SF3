package net.aurora.external.opengl;

/**
 * @author Rick van Biljouw
 *         Date: 14-12-11
 *         Time: 20:57
 *         An object rendered by OpenGL
 */
public class GLObject {
    private byte[] pixels;
    private long checksum;
    private int x;
    private int y;

    /**
     * Constructor of a GLObject
     * @param checksum
     * @param x
     * @param y
     */
    public GLObject(long checksum, int x, int y, byte[] pixels) {
        this.checksum = checksum;
        this.x = x;
        this.y = y;
    }

    /**
     * Get the checksum of this object
     * @return checksum
     */
    public long getChecksum() {
        return this.checksum;
    }

    /**
     * Return the X position of the object (2d space)
     * @return x
     */
    public int getX() {
        return this.x;
    }

    /**
     * Return the Y position of the object (2d space)
     * @return y
     */
    public int getY() {
        return this.y;
    }

}
