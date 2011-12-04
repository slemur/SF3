package net.aurora.input.model;

/**
 * @author Rick van Biljouw
 *         Date: 4-12-11
 *         Time: 3:58
 */
public interface Keyboard {

    public void press(char key);

    public void release(char key);

    public void type(String keystroke);

}
