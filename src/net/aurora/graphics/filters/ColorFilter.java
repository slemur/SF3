package net.aurora.graphics.filters;

import net.aurora.graphics.Filter;
import net.aurora.graphics.GraphicalObject;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * @author Rick van Biljouw
 *         Date: 21-11-11
 *         Time: 0:21
 *         Changes all occurences of input colors to white and non-matching colors to black
 */
public class ColorFilter extends Filter {
    private Color[] colors;
    private int tolerance;

    public ColorFilter(int tolerance, Color... colors) {
        this.tolerance = tolerance;
        this.colors = colors;
    }

    @Override
    public BufferedImage apply(BufferedImage image) {
        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                Color current = new Color(image.getRGB(x, y));
                for (Color color : colors) {
                    int rDiff = Math.abs(current.getRed() - color.getRed());
                    int gDiff = Math.abs(current.getGreen() - color.getGreen());
                    int bDiff = Math.abs(current.getBlue() - color.getBlue());
                    int sum = (rDiff + gDiff + bDiff) / 3;
                    if(sum < tolerance) {
                        image.setRGB(x, y, Color.WHITE.getRGB());
                        break;
                    } else {
                        image.setRGB(x,y, Color.BLACK.getRGB());
                    }
                }

            }
        }
        return image;  //To change body of implemented methods use File | Settings | File Templates.
    }

}
