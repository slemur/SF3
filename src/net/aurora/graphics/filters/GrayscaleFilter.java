package net.aurora.graphics.filters;

import net.aurora.graphics.Filter;

import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;

/**
 * @author Rick van Biljouw
 *         Date: 21-11-11
 *         Time: 0:02
 */
public class GrayscaleFilter extends Filter {

    @Override
    public BufferedImage apply(BufferedImage image) {
        ColorConvertOp op = new ColorConvertOp(ColorSpace
                .getInstance(ColorSpace.CS_GRAY), null);

        op.filter(image, image);
        return image;
    }

}
