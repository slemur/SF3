package net.aurora.input;

import net.aurora.bot.Bot;
import net.aurora.input.model.Keyboard;
import net.aurora.input.model.Mouse;

/**
 * @author Rick van Biljouw
 *         Date: 4-12-11
 *         Time: 3:57
 */
public class InputManager {
    private Bot bot;
    private Mouse mouse;
    private Keyboard keyboard;

    public InputManager(Bot bot) {
        this.bot = bot;
    }

    /**
     * Get the mouse attached to the bot
     * @return mouse
     */
    public Mouse getMouse() {
        return mouse;
    }

    /**
     * Set the mouse attached to the bot
     * @param mouse the mouse to use
     */
    public void setMouse(Mouse mouse) {
        this.mouse = mouse;
    }

    /**
     * Get the keyboard attached to the bot
     * @return keyboard
     */
    public Keyboard getKeyboard() {
        return keyboard;
    }

    /**
     * Set the keyboard attached to this bot
     * @param keyboard keyboard to use
     */
    public void setKeyboard(Keyboard keyboard) {
        this.keyboard = keyboard;
    }

    /**
     * Get the bot that uses this input manager
     * @return bot
     */
    public Bot getBot() {
        return bot;
    }

    /**
     * Create a new input manager
     *
     * @param bot the bot
     * @param attachDefaults add default devices?
     * @return new input manager
     */
    public static InputManager create(Bot bot, boolean attachDefaults) {
        InputManager newManager = new InputManager(bot);
        if(attachDefaults) {
            newManager.setMouse(new DefaultMouse(bot));
        }
        return newManager;
    }

}
