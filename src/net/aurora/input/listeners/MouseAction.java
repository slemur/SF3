package net.aurora.input.listeners;

import net.aurora.opengl.Settings;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * @author Rick van Biljouw
 *         Date: 4-12-11
 *         Time: 3:06
 */
public class MouseAction implements ComponentListener, MouseListener {

    public void attach(Component boundObjectN) {
        if(boundObjectN != null) {
            boundObjectN.addMouseListener(this);
        }
    }

    public void mouseClicked(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON2) {
            Settings.setMousePosition(e.getX(), e.getY());
            System.out.println("Updated OGL mouse position");
        }
    }

    public void mousePressed(MouseEvent e) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void mouseReleased(MouseEvent e) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void mouseEntered(MouseEvent e) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void mouseExited(MouseEvent e) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
