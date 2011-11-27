package net.aurora.opengl;

/**
 * @author Rick van Biljouw
 *         Date: 26-11-11
 *         Time: 23:02
 */
public class ObjectOGL {
    private int x;
    private int y;
    private int checksum;

    public ObjectOGL(int x, int y, int checksum) {
        this.x = x;
        this.y = y;
        this.checksum = checksum;
    }

    /**
     * Returns the 2d X coordinate for the object (caught from random vertex)
     * @return
     */
    public int getX() {
        return this.x;
    }

    /**
     * Returns the 2d Y coordinate for the object (caught from random vertex)
     * @return
     */
    public int getY() {
        return this.y;
    }

    /**
     * Returns the checksum for the object (used for identifying)
     * @return
     */
    public int getChecksum() {
        return this.checksum;
    }

    /**
     * Find object by its checksum
     * GL wrapper stores all the objects currently drawn into a vector??
     * @param checksum
     * @return npc object
     */
    public static native NpcOGL getObjectByChecksum(int checksum);

    /**
     * Toggle object debug (draws object checksum on the screen)
     */
    public static native void toggleObjectDebug();
}
