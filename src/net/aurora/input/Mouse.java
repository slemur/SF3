package net.aurora.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * @author Rick van Biljouw
 *         Date: 27-11-11
 *         Time: 0:04
 */
public class Mouse extends InputModule {

    public void move(int x, int y) {
        for (MouseMotionListener listener : getActiveComponent().getMouseMotionListeners()) {
            listener.mouseMoved(new MouseEvent(getActiveComponent(), MouseEvent.MOUSE_MOVED, System.currentTimeMillis(), 0, x, y, 0, false, 0));
        }
    }

    public void click(int x, int y, boolean left) {
        for (MouseListener listener : getActiveComponent().getMouseListeners()) {
            listener.mousePressed(new MouseEvent(getActiveComponent(),
                    MouseEvent.MOUSE_PRESSED, System.currentTimeMillis(), 0, x,
                    y, 1, false, left ? MouseEvent.BUTTON1
                    : MouseEvent.BUTTON3));

            listener.mouseReleased(new MouseEvent(getActiveComponent(),
                    MouseEvent.MOUSE_RELEASED, System.currentTimeMillis() + 100, 0, x,
                    y, 1, false, left ? MouseEvent.BUTTON1
                    : MouseEvent.BUTTON3));

            listener.mouseClicked(new MouseEvent(getActiveComponent(),
                    MouseEvent.MOUSE_CLICKED, System.currentTimeMillis(), 0, x,
                    y, 1, false, left ? MouseEvent.BUTTON1
                    : MouseEvent.BUTTON3));
        }
    }

}
