package net.aurora.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

/**
 * @author Rick van Biljouw
 *         Date: 27-11-11
 *         Time: 0:04
 */
public class Mouse extends InputModule {

    public void move(int x, int y) {
        for(MouseMotionListener listener : getActiveComponent().getMouseMotionListeners()) {
            listener.mouseMoved(new MouseEvent(getActiveComponent(), MouseEvent.MOUSE_MOVED, System.currentTimeMillis(), 0, x, y, 0, false, 0));
        }
    }

}
