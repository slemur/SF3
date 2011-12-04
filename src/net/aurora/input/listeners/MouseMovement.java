package net.aurora.input.listeners;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

/**
 * @author Rick van Biljouw
 *         Date: 3-12-11
 *         Time: 23:19
 */
public class MouseMovement implements ComponentListener, MouseMotionListener {
    private Component boundObject;
    private int x, y;

    public void attach(Component boundObjectN) {
        if ((boundObject == null || boundObject != boundObjectN) && boundObjectN != null) {
            boundObject = boundObjectN;
            boundObject.addMouseMotionListener(this);
        }
    }

    public void mouseDragged(MouseEvent e) {
        x = e.getX();
        y = e.getY();
    }

    public void mouseMoved(MouseEvent e) {
        x = e.getX();
        y = e.getY();
    }

    /**
     * Get the X position of the mouse
     * @return x coordinate
     */
    public int getX() {
        return this.x;
    }

    /**
     * Get the Y position of the mouse
     * @return Y coordinate
     */
    public int getY() {
        return this.y;
    }
}
