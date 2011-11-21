package net.aurora.graphics.filters;

import net.aurora.graphics.Filter;
import net.aurora.graphics.GraphicalObject;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * @author Rick van Biljouw
 *         Date: 21-11-11
 *         Time: 0:21
 *         Will use input colors to set up a heatmap-sort of thing.
 */
public class HeatFilter extends Filter {
    private GraphicalObject object;
    public Color[][] ORI_COLMAP;
    public int[][] ORI_DISTMAP;

    public HeatFilter(GraphicalObject object) {
        this.object = object;
    }

    @Override
    public BufferedImage apply(BufferedImage image) {
        ORI_COLMAP = new Color[image.getWidth()][image.getHeight()];
        ORI_DISTMAP = new int[image.getWidth()][image.getHeight()];

        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                Color current = new Color(image.getRGB(x, y));
                int lowestSum = Integer.MAX_VALUE;
                Color lowestColor = null;
                for (Color color : this.object.getColors()) {
                    int rDiff = Math.abs(current.getRed() - color.getRed());
                    int gDiff = Math.abs(current.getGreen() - color.getGreen());
                    int bDiff = Math.abs(current.getBlue() - color.getBlue());
                    int sum = (rDiff + gDiff + bDiff) / 3;
                    if (sum < lowestSum) {
                        lowestSum = sum;
                        lowestColor = color;
                    }
                }

                if (255 - (int) (lowestSum * object.getSensitivity()) > 0) {
                    Color nColor = new Color(255 - (int) (lowestSum * object.getSensitivity()), 255 - (int) (lowestSum * object.getSensitivity()), 255 - (int) (lowestSum * object.getSensitivity()));
                    image.setRGB(x, y, nColor.getRGB());
                } else {
                    Color nColor = new Color(0, 0, 0);
                    image.setRGB(x, y, nColor.getRGB());
                }

                ORI_COLMAP[x][y] = lowestColor;
                ORI_DISTMAP[x][y] = lowestSum;
            }
        }
        return image;  //To change body of implemented methods use File | Settings | File Templates.
    }

}
