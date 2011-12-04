package net.aurora.opengl;

import net.aurora.util.ThreadUtils;

/**
 * @author Rick van Biljouw
 *         Date: 26-11-11
 *         Time: 23:02
 */
public class Object {
    private int x;
    private int y;
    private long checksum;

    public Object(int x, int y, long checksum) {
        this.x = x;
        this.y = y;
        this.checksum = checksum;
    }

    /**
     * Returns the 2d X coordinate for the NPC (caught from random vertex)
     * @return
     */
    public int getX() {
        return this.x;
    }

    /**
     * Returns the 2d Y coordinate for the NPC (caught from random vertex)
     * @return
     */
    public int getY() {
        return this.y;
    }

    /**
     * Returns the checksum for the NPC (used for identifying)
     * @return
     */
    public long getChecksum() {
        return this.checksum;
    }

    /**
     * Find NPC by its checksum
     * GL wrapper stores all the npcs currently drawn into a vector??
     * @param checksum
     * @return npc object
     */
    public static native Object getObjectByChecksum(long checksum);

    /**
     * Toggle NPC debug (draws object checksum on the screen)
     */
    public static native void toggleObjectDebug();

    /**
     * Await the existence of an entity
     * @param checksum  the checksum of the entity
     * @return entity once it pops up
     */
    public static Object awaitExistence(long checksum) {
        Object object;
        while((object = getObjectByChecksum(checksum)) == null) {
            ThreadUtils.sleep(Thread.currentThread(), 100);
        }
        return object;
    }
}
