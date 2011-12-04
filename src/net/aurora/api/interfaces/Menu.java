package net.aurora.api.interfaces;

import net.aurora.opengl.Interface;

import java.awt.*;

/**
 * @author Rick van Biljouw
 *         Date: 4-12-11
 *         Time: 2:27
 */
public class Menu {
    private static long menuChecksum = 2916666005L;

    public static boolean isMenuOpen() {
        return Interface.getInterfaceByChecksum(menuChecksum) != null;
    }

    public static Point getItemPosition(int index) {
        Interface menu = Interface.getInterfaceByChecksum(menuChecksum);

        int x = menu.getX() + 10;
        int y = 20 + (index * 17);

        return new Point(x,y);
    }
}
