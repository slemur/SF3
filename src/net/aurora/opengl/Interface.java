package net.aurora.opengl;

/**
 * @author Rick van Biljouw
 *         Date: 30-11-11
 *         Time: 5:05
 */
public class Interface {
    private Interface[] children;
    private Interface parent;
    private String text;
    private int x;
    private int y;

    public Interface(Interface[] children, int x, int y) {
        this.x = x;
        this.y = y;
        this.children = children;
    }

    public Interface(Interface[] children, Interface parent, int x, int y) {
        this.children = children;
        this.parent = parent;
        this.x = x;
        this.y = y;
    }

    /**
     * Gets the X position of the interface
     * @return
     */
    public int getX() {
        return this.x;
    }

    /**
     * Gets the Y position of the interface
     * @return
     */
    public int getY() {
        return this.y;
    }

    /**
     * Set the text contained by this interface
     * @param text text
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * Returns the text contained by this interface.
     * @return text
     */
    public String getText() {
        return this.text;
    }

    /**
     * Returns an interface child (contained interface)
     * @param id
     * @return
     */
    public Interface getChild(int id) {
        return children[id];
    }

    /**
     * Returns the interface parent (if current interface is a child)
     */
    public Interface getParent() {
        return this.parent;
    }

}
