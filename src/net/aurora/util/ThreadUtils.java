package net.aurora.util;

/**
 * @author Rick van Biljouw
 *         Date: 30-11-11
 *         Time: 5:15
 */
public class ThreadUtils {

    /**
     * Put a thread to sleep for a set number of milliseconds
     * @param thread the thread
     * @param milliseconds the sleep time
     */
    public static void sleep(Thread thread, int milliseconds) {
        try {
            thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            thread.interrupt();
        }
    }

}
