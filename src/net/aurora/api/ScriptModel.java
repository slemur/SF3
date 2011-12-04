package net.aurora.api;

import net.aurora.bot.Bot;

/**
 * @author Rick van Biljouw
 *         Date: 4-12-11
 *         Time: 4:51
 */
public interface ScriptModel {

    /**
     * Set the bot for this script
     * @param bot bot
     */
    public void setBot(Bot bot);

    /**
     * Start the script
     */
    public void start();

    /**
     * Pause the script
     */
    public void pause();

    /**
     * Resume the script
     */
    public void resume();

    /**
     * Stop the script
     */
    public void stop();

}
