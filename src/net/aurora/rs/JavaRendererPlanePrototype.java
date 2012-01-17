package net.aurora.rs;

import serp.bytecode.BCClass;
import serp.bytecode.Constants;
import serp.framework.prototype.PrototypeClass;
import serp.framework.prototype.PrototypeMatch;
import serp.framework.prototype.PrototypeSpecialAccess;

/**
 * @author Rick van Biljouw
 *         Date: 17-1-12
 *         Time: 19:27
 */
public class JavaRendererPlanePrototype implements PrototypeClass {
    private int[][] tileHeights;
    private float afloat;
    private float bfloat;
    private float cfloat;
    private float dfloat;
    private int aint;
    private byte[][] abytearray2d;
    private byte[][] anotherBytearray2d;

    void somevoid(int a, int b, int c) {

    }

    void somevoid(int[] is) {

    }

    final boolean aboolean() {
        return false;
    }

    final int getTileHeight(int x, int y) {
        return tileHeights[x][y];
    }

    public void onValidated(BCClass clazz) {
        System.out.println(clazz.getClassName());
    }

    public int getAccessFlags() {
        return Constants.ACCESS_PUBLIC;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public PrototypeMatch getExpectations() {
        return PrototypeMatch.LIKE;
    }

}
