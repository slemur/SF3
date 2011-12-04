package net.aurora.input;

import net.aurora.bot.Bot;
import net.aurora.input.listeners.MouseMovement;
import net.aurora.input.model.Mouse;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * @author Rick van Biljouw
 *         Date: 27-11-11
 *         Time: 0:04
 */
public class DefaultMouse extends InputModule implements Mouse {

    public DefaultMouse(Bot bot) {
        super(bot);
    }

    public void move(int x, int y) {
        for (MouseMotionListener listener : getActiveComponent().getMouseMotionListeners()) {
            listener.mouseMoved(new MouseEvent(getActiveComponent(), MouseEvent.MOUSE_MOVED, System.currentTimeMillis(), 0, x, y, 0, false, 0));
        }
    }

    public void drag(int targetX, int targetY) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void click(boolean left) {
        press(left);
        release(left);

        for (MouseListener listener : getActiveComponent().getMouseListeners()) {
            listener.mouseClicked(new MouseEvent(getActiveComponent(),
                    MouseEvent.MOUSE_CLICKED, System.currentTimeMillis(), 0, ((MouseMovement)getBot().getListener("MouseMovement")).getX(),
                    ((MouseMovement)getBot().getListener("MouseMovement")).getY(), 1, false, left ? MouseEvent.BUTTON1
                    : MouseEvent.BUTTON3));
        }
    }

    public void press(boolean left) {
        for (MouseListener listener : getActiveComponent().getMouseListeners()) {
            listener.mousePressed(new MouseEvent(getActiveComponent(),
                    MouseEvent.MOUSE_PRESSED, System.currentTimeMillis(), 0, ((MouseMovement)getBot().getListener("MouseMovement")).getX(),
                    ((MouseMovement)getBot().getListener("MouseMovement")).getY(), 1, false, left ? MouseEvent.BUTTON1
                    : MouseEvent.BUTTON3));
        }
    }

    public void release(boolean left) {
        for (MouseListener listener : getActiveComponent().getMouseListeners()) {
            listener.mouseReleased(new MouseEvent(getActiveComponent(),
                    MouseEvent.MOUSE_RELEASED, System.currentTimeMillis() + 100, 0, ((MouseMovement)getBot().getListener("MouseMovement")).getX(),
                    ((MouseMovement)getBot().getListener("MouseMovement")).getY(), 1, false, left ? MouseEvent.BUTTON1
                    : MouseEvent.BUTTON3));
        }
    }

}
