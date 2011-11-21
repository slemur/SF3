package net.aurora.graphics;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author Rick van Biljouw
 *         Date: 20-11-11
 *         Time: 23:38
 *         Convenience class for running a chain of filters over the buffer.
 */
public class FilterChain {
    private ArrayList<Filter> filters = new ArrayList<Filter>();

    /**
     * Construct filter chain with no filters
     */
    public FilterChain() {

    }

    /**
     * Construct filter chain with initial filter(s)
     * @param filters initial filter(s)
     */
    public FilterChain(Filter... filters) {
        this.filters.addAll(Arrays.asList(filters));
    }

    /**
     * Apply all of the filters in this chain to the buffer
     * @param buffer buffer to apply to
     */
    public BufferedImage apply(Buffer buffer) {
        BufferedImage temp = buffer.getGameBuffer();
        for(Filter filter : this.filters) {
            temp = filter.apply(temp);
        }
        return temp;
    }

    /**
     * Add filter(s) to the chain.
     * @param filters The filter(s) to add.
     */
    public void add(Filter... filters) {
        this.filters.addAll(Arrays.asList(filters));
    }

    /**
     * Remove filter(s) from the chain.
     * @param filters The filter(s) to remove.
     */
    public void remove(Filter... filters) {
        this.filters.removeAll(Arrays.asList(filters));
    }
}
