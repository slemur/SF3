package net.aurora.jni;

/**
 * @author Rick van Biljouw
 *         Date: 20-11-11
 *         Time: 4:09
 */
public class NLibrary {

    public native static void createLink();

    public native static void toggleWireframe();

    public static void onLink() {
        System.out.println("Linked with ogl library");
    }
}
