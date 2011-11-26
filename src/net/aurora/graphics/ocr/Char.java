package net.aurora.graphics.ocr;

import java.awt.*;

/**
 * @author Rick van Biljouw
 *         Date: 22-11-11
 *         Time: 0:23
 */
public class Char {
    private Rectangle r;
    private int key;

    public Char(Rectangle r, int key) {
        this.r = r;
        this.key = key;
    }

    public Rectangle getBounds() {
        return this.r;
    }

    public int getKey() {
        return this.key;
    }
}
