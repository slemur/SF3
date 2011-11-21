package net.aurora.input.listeners;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

/**
 * @author Rick van Biljouw
 *         Date: 21-11-11
 *         Time: 18:37
 */
public class MouseEventListener implements MouseMotionListener {
    private int x;
    private int y;

    public void mouseDragged(MouseEvent e) {

    }

    public void mouseMoved(MouseEvent e) {
        this.x = e.getX();
        this.y = e.getY();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
