package net.aurora.opengl;

import net.aurora.util.ThreadUtils;

/**
 * @author Rick van Biljouw
 *         Date: 26-11-11
 *         Time: 23:02
 */
public class Entity {
    private int x;
    private int y;
    private int checksum;

    public Entity(int x, int y, int checksum) {
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
    public int getChecksum() {
        return this.checksum;
    }

    /**
     * Find NPC by its checksum
     * GL wrapper stores all the npcs currently drawn into a vector??
     * @param checksum
     * @return npc object
     */
    public static native Entity getEntityByChecksum(long... checksum);

    /**
     * Returns the local player.
     * @return
     */
    public static native Entity getMyPlayer();

    /**
     * Set the checksum of the local player.
     * @param checksum
     */
    public static native void setPlayerChecksum(int checksum);

    /**
     * Toggle NPC debug (draws NPC checksum on the screen)
     */
    public static native void toggleNpcDebug();

    /**
     * Await the existence of an entity
     * @param checksum  the checksum of the entity
     * @return entity once it pops up
     */
    public static Entity awaitExistence(int checksum) {
        Entity entity;
        while((entity = getEntityByChecksum(checksum)) == null) {
            ThreadUtils.sleep(Thread.currentThread(), 100);
        }
        return entity;
    }
}
