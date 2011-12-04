package net.aurora.input;

import net.aurora.bot.Bot;

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * @author Rick van Biljouw
 *         Date: 4-12-11
 *         Time: 2:43
 */
public class DefaultKeyboard extends InputModule {

    public DefaultKeyboard(Bot bot) {
        super(bot);
    }

    public void keyAction(boolean press, int modifier, int keyCode, char keyChar) {
        KeyEvent ke;
        if (press)
            ke = new KeyEvent(getActiveComponent(), KeyEvent.KEY_PRESSED, System.currentTimeMillis(), modifier, keyCode, keyChar, KeyEvent.KEY_LOCATION_STANDARD);
        else
            ke = new KeyEvent(getActiveComponent(), KeyEvent.KEY_RELEASED, System.currentTimeMillis(), modifier, keyCode, keyChar, KeyEvent.KEY_LOCATION_STANDARD);
        for (KeyListener k : getActiveComponent().getKeyListeners()) {
            k.keyPressed(ke);
        }
    }

    public void sendKey(final char c) {
        sendKey(c, 0);
    }

    private char getKeyChar(final char c) {
        final int i = c;
        if (i >= 36 && i <= 40) {
            return KeyEvent.VK_UNDEFINED;
        } else {
            return c;
        }
    }

    public void sendKey(final char ch, final int delay) {
        try {
            boolean shift = false;
            int code = ch;
            if (ch >= 'a' && ch <= 'z') {
                code -= 32;
            } else if (ch >= 'A' && ch <= 'Z') {
                shift = true;
            }
            KeyEvent ke;
            if (code == KeyEvent.VK_LEFT || code == KeyEvent.VK_UP
                    || code == KeyEvent.VK_UP || code == KeyEvent.VK_DOWN) {
                ke = new KeyEvent(getActiveComponent(), KeyEvent.KEY_PRESSED, System
                        .currentTimeMillis(), 0, code, getKeyChar(ch),
                        KeyEvent.KEY_LOCATION_STANDARD);
                for (KeyListener k : getActiveComponent().getKeyListeners()) {
                    k.keyPressed(ke);
                }
                Thread.sleep(delay);
                final int delay2 = random(50, 120) + random(0, 100);
                ke = new KeyEvent(getActiveComponent(), KeyEvent.KEY_RELEASED, System
                        .currentTimeMillis(), 0, code, getKeyChar(ch),
                        KeyEvent.KEY_LOCATION_STANDARD);
                for (KeyListener k : getActiveComponent().getKeyListeners()) {
                    k.keyReleased(ke);
                }
            } else {
                if (!shift) {
                    ke = new KeyEvent(getActiveComponent(), KeyEvent.KEY_PRESSED, System
                            .currentTimeMillis(), 0, code, getKeyChar(ch),
                            KeyEvent.KEY_LOCATION_STANDARD);
                    for (KeyListener k : getActiveComponent().getKeyListeners()) {
                        k.keyPressed(ke);
                    }
                    Thread.sleep(delay);
                    // Event Typed
                    ke = new KeyEvent(getActiveComponent(), KeyEvent.KEY_TYPED, System
                            .currentTimeMillis() + 0, 0, 0, ch, 0);
                    for (KeyListener k : getActiveComponent().getKeyListeners()) {
                        k.keyTyped(ke);
                    }
                    // Event Released
                    final int delay2 = random(50, 120) + random(0, 100);
                    ke = new KeyEvent(getActiveComponent(), KeyEvent.KEY_RELEASED, System
                            .currentTimeMillis(), 0, code, getKeyChar(ch),
                            KeyEvent.KEY_LOCATION_STANDARD);
                    for (KeyListener k : getActiveComponent().getKeyListeners()) {
                        k.keyReleased(ke);
                    }
                } else {
                    // Event Pressed for shift key
                    final int s1 = random(25, 60) + random(0, 50);
                    ke = new KeyEvent(getActiveComponent(), KeyEvent.KEY_PRESSED, System
                            .currentTimeMillis(), InputEvent.SHIFT_DOWN_MASK, KeyEvent.VK_SHIFT,
                            (char) KeyEvent.VK_UNDEFINED,
                            KeyEvent.KEY_LOCATION_LEFT);
                    for (KeyListener k : getActiveComponent().getKeyListeners()) {
                        k.keyPressed(ke);
                    }
                    // Event Pressed for char to send
                    ke = new KeyEvent(getActiveComponent(), KeyEvent.KEY_PRESSED, System
                            .currentTimeMillis(), InputEvent.SHIFT_DOWN_MASK, code,
                            getKeyChar(ch), KeyEvent.KEY_LOCATION_STANDARD);
                    for (KeyListener k : getActiveComponent().getKeyListeners()) {
                        k.keyPressed(ke);
                    }
                    Thread.sleep(delay);
                    // Event Typed for char to send
                    ke = new KeyEvent(getActiveComponent(), KeyEvent.KEY_TYPED, System
                            .currentTimeMillis() + 0, InputEvent.SHIFT_DOWN_MASK,
                            0, ch, 0);
                    for (KeyListener k : getActiveComponent().getKeyListeners()) {
                        k.keyTyped(ke);
                    }
                    // Event Released for char to send
                    final int delay2 = random(50, 120) + random(0, 100);
                    ke = new KeyEvent(getActiveComponent(), KeyEvent.KEY_RELEASED, System
                            .currentTimeMillis()
                            + delay2, InputEvent.SHIFT_DOWN_MASK, code,
                            getKeyChar(ch), KeyEvent.KEY_LOCATION_STANDARD);
                    for (KeyListener k : getActiveComponent().getKeyListeners()) {
                        k.keyReleased(ke);
                    }

                    // Event Released for shift key
                    final int s2 = random(25, 60) + random(0, 50);
                    ke = new KeyEvent(getActiveComponent(), KeyEvent.KEY_RELEASED, System
                            .currentTimeMillis()
                            + s2, InputEvent.SHIFT_DOWN_MASK, KeyEvent.VK_SHIFT,
                            (char) KeyEvent.VK_UNDEFINED,
                            KeyEvent.KEY_LOCATION_LEFT);
                    for (KeyListener k : getActiveComponent().getKeyListeners()) {
                        k.keyReleased(ke);
                    }
                }
            }
        } catch (Exception e) {
        }
    }

    public void sendKeys(final String text, final boolean pressEnter) {
        final char[] chs = text.toCharArray();
        for (final char element : chs) {
            sendKey(element, random(100, 220));
            try {
                Thread.sleep(random(110, 220));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        if (pressEnter) {
            sendKey((char) KeyEvent.VK_ENTER, random(100, 200));
        }
    }
    

    public static int random(int min, int max) {
        return ((int) (Math.random() * (max - min))) + min;
    }

}
