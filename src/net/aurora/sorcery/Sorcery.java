package net.aurora.sorcery;

import net.aurora.bot.Bot;

/**
 * @author Rick van Biljouw
 *         Date: 15-1-12
 *         Time: 11:09
 */
public class Sorcery {
    private final ClassContainer classContainer = new ClassContainer();
    private ClassLoader classLoader;

    /**
     * Get the class container
     * @return class container
     */
    public ClassContainer getClassContainer() {
        return this.classContainer;
    }

    /**
     * Get the runescape classloader that contains all the runescape classes. (inner.pack.gz)
     * @return classloader
     */
    public ClassLoader getClassLoader() {
        return this.classLoader;
    }

    /**
     * Set the classloader
     * @param classLoader
     */
    public void setClassLoader(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    /**
     * Convenience method for grabbing sorcery instance
     * @return
     */
    public static Sorcery getSorcery() {
        return Bot.getSingleton().getSorcery();
    }
}
