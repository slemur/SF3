package net.aurora.external.opengl;

/**
 * @author Rick van Biljouw
 *         Date: 14-12-11
 *         Time: 20:48
 *         A class containing method declarations/interfaces for the native opengl API.
 */
public class OpenGL {
    public final static int MODEL_DEBUG = 0;
    public final static int TEXTURE_DEBUG = 1;

    /**
     * Toggle a debug which draws through OpenGL itself.
     *
     * @param debugId the ID of the debug (can be obtained from the constants declared in this class.
     */
    public static native void toggleDebug(int debugId);

    /**
     * Set the frequency of a cache update (models and textures)
     * @param milliseconds the time in milliseconds to wait between updates.
     */
    public static native void setUpdateFrequency(int milliseconds);

    /**
     * Returns all the models currently known in the OpenGL context.
     * @return models
     */
    public static native GLObject[] getModelCache();

    /**
     * Returns all the textures currently known in the OpenGL context.
     * @return textures
     */
    public static native GLObject[] getTextureCache();

}
