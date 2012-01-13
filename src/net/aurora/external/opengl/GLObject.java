package net.aurora.external.opengl;

/**
 * @author Rick van Biljouw
 *         Date: 9-1-12
 *         Time: 21:30
 */
public class GLObject {
    private long checksum;
    private byte[] pixels;
    private int x;
    private int y;

    public GLObject(long checksum, int x, int y, byte[] pixels) {
        this.checksum = checksum;
        this.x = x;
        this.y = y;
        this.pixels = pixels;
    }

    public long getChecksum() {
        return this.checksum;
    }

    public byte[] getPixels() {
        return this.pixels;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }
}
