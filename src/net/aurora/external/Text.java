package net.aurora.external;

import java.awt.*;

/**
 * @author Rick van Biljouw
 *         Date: 14-12-11
 *         Time: 20:54
 *         Native Text helper that uses OpenGL for grabbing data.
 */
public class Text {

    /**
     * Gets the text in a specified area using Text.
     * @param bounds area
     * @return text
     */
    public static native String getText(Rectangle bounds); // TODO: Font?

}
