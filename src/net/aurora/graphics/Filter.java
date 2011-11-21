package net.aurora.graphics;

import java.awt.image.BufferedImage;

/**
 * @author Rick van Biljouw
 *         Date: 20-11-11
 *         Time: 23:36
 */
public abstract class Filter {

    public abstract BufferedImage apply(BufferedImage image);

}
