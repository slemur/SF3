package net.aurora.input;

import net.aurora.api.Methods;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

/**
 * @author Rick van Biljouw
 *         Date: 21-11-11
 *         Time: 18:18
 */
public class Mouse {
    private Methods methods;

    public Mouse(Methods methods) {
        this.methods = methods;
    }

    /**
     * Needs shitty mouse path etc
     * @param x
     * @param y
     */
    public void moveMouse(int x, int y) {
        for(MouseMotionListener listener : methods.getBot().getCanvas().getMouseMotionListeners()) {
            listener.mouseMoved(new MouseEvent(methods.getBot().getCanvas(), MouseEvent.MOUSE_MOVED, System.currentTimeMillis(), 0, x, y, 0, false, 0));
        }
    }

}
