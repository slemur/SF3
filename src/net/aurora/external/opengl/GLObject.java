package net.aurora.external.opengl;

/**
 * @author Rick van Biljouw
 *         Date: 14-12-11
 *         Time: 20:57
 *         An object rendered by OpenGL
 */
public class GLObject {
    private long checksum;
    private int templateId;
    private int x;
    private int y;

    /**
     * Constructor of a GLObject
     * @param checksum
     * @param templateId
     * @param x
     * @param y
     */
    public GLObject(long checksum, int templateId, int x, int y) {
        this.checksum = checksum;
        this.templateId = templateId;
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
     * Return the ID of the template
     * @return template id
     */
    public int getTemplateId() {
        return this.templateId;
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
