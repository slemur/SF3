package net.aurora.graphics.detect;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * @author Rick van Biljouw
 *         Date: 22-11-11
 *         Time: 1:07
 */
public class BitmapDetector {

    public static Rectangle getResult(BufferedImage haystack, BufferedImage needle, float threshold) {
        for (int y = 0; y < haystack.getHeight() - needle.getHeight(); y++) {
            for (int x = 0; x < haystack.getWidth() - needle.getWidth(); x++) {
                if (getDistance(haystack, x, y, needle) < threshold) {
                    return new Rectangle(x, y, needle.getWidth(), needle.getHeight());
                }
            }
        }
        return null;
    }

    private static double getDistance(BufferedImage haystack, int bx, int by, BufferedImage needle) {
        double dist = 0.0;
        for (int y = 0; y < needle.getHeight(); y++) {
            for (int x = 0; x < needle.getWidth(); x++) {
                dist += Math.pow(needle.getRGB(x, y) - haystack.getRGB(bx + x, by + y), 2);
            }
        }
        return Math.sqrt(dist) / needle.getWidth() / needle.getHeight();
    }

}
