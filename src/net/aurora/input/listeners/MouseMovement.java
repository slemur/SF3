package net.aurora.input.listeners;

import net.aurora.opengl.Settings;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

/**
 * @author Rick van Biljouw
 *         Date: 3-12-11
 *         Time: 23:19
 */
public class MouseMovement implements MouseMotionListener {
    private static Component boundObject;

    public static void attach(Component boundObjectN) {
        if((boundObject == null || boundObject != boundObjectN) && boundObjectN != null) {
            boundObject = boundObjectN;
            boundObject.addMouseMotionListener(new MouseMovement());
        }
    }

    public void mouseDragged(MouseEvent e) {
        Settings.setMousePosition(e.getX(), e.getY());
    }

    public void mouseMoved(MouseEvent e) {
        Settings.setMousePosition(e.getX(), e.getY());
    }

}
