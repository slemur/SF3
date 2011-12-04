package net.aurora.input.model;

/**
 * @author Rick van Biljouw
 *         Date: 4-12-11
 *         Time: 3:58
 */
public interface Mouse {

    /**
     * Moves the mouse to specific position
     * @param x x coordinate
     * @param y y coordinate
     */
    public void move(int x, int y);

    /**
     * Drag mouse from current position to target
     * @param targetX target x coordinate
     * @param targetY target y coordinate
     */
    public void drag(int targetX, int targetY);

    /**
     * Click mouse at current position
     * @param left left click?
     */
    public void click(boolean left);

    /**
     * Press mouse at current position
     * @param left left click?
     */
    public void press(boolean left);

    /**
     * Release mouse at current position
     */
    public void release(boolean left);

}
