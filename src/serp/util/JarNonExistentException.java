package serp.util;

/**
 * @author rvbiljouw
 *         Date: 17-1-12
 *         Time: 14:13
 */
public class JarNonExistentException extends RuntimeException {
    
    public JarNonExistentException(String jarPath) {
        super("Jar file " + jarPath + " does not exist or has issues.");
    }
}
