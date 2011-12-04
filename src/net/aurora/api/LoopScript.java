package net.aurora.api;

import net.aurora.util.Logger;

/**
 * @author Rick van Biljouw
 *         Date: 4-12-11
 *         Time: 4:52
 */
public abstract class LoopScript implements ScriptModel, Runnable {
    private boolean active = true;
    private Thread t_script;

    public void start() {
        this.t_script = new Thread(this);
        this.t_script.start();
    }

    public void pause() {
        try {
            this.t_script.wait();
        } catch (InterruptedException e) {
            this.t_script.interrupt();
        }
    }

    public void resume() {
        this.t_script.notify();
    }

    public void stop() {
        this.active = false;
    }

    public void run() {
        while (this.active) {
            try {
                Thread.sleep(loop());
            } catch (Exception e) {
                Logger.log(this, "Exception thrown by script!");
                Logger.log(this, e.toString());

                Thread.currentThread().interrupt();
            }
        }
    }

    public abstract int loop();
}
