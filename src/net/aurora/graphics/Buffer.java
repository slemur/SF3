package net.aurora.graphics;

import java.awt.image.BufferedImage;

/**
 * @author Rick van Biljouw
 *         Date: 20-11-11
 *         Time: 22:45
 */
public class Buffer {
    private BufferedImage output = new BufferedImage(762, 503, BufferedImage.TYPE_INT_ARGB);
    private BufferedImage input = new BufferedImage(762, 503, BufferedImage.TYPE_INT_RGB);
    private int width = 762, height = 503;

    /**
     * Returns a copy of the game buffer
     * The reason we cant export the real game buffer is that it will fuck around with the screen.
     */
    public BufferedImage getGameBuffer() {
        BufferedImage copy = new BufferedImage(this.input.getWidth(), this.input.getHeight(), this.input.getType());
        copy.getGraphics().drawImage(this.input, 0, 0, null);
        return copy;
    }

    /**
     * Fetches the bot-side buffer which is painted to the canvas on repaint.
     * This hackup allows us to paint our image processing results without holding up the games painting cycle (no screen lag).
     *
     * @return
     */
    public BufferedImage getBotBuffer() {
        this.output.getGraphics().drawString("Canvas rendered using bot buffer", 10, 30);
        return this.output;
    }

    /**
     * This method is called by the canvas whenever the canvas changes its size.
     * The reason this is necessary is that the backbuffer needs to be of the same size to provide good screen results.
     *
     * @param width
     * @param height
     */
    public void onDimensionChanged(int width, int height) {
        this.width = width;
        this.height = height;
        this.output = new BufferedImage(this.width, this.height, BufferedImage.TYPE_INT_ARGB);
        this.input = new BufferedImage(this.width, this.height, BufferedImage.TYPE_INT_RGB);
    }

    /**
     * Called whenever the client repaints
     */
    public void onRepaint(BufferedImage input) {
        this.input = input; // Draw the image on our input so we don't modify the actual gamebuffer.
    }

    int update = 0;
}
