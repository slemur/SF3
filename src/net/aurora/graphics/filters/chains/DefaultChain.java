package net.aurora.graphics.filters.chains;

import net.aurora.graphics.FilterChain;
import net.aurora.graphics.filters.GrayscaleFilter;

/**
 * @author Rick van Biljouw
 *         Date: 21-11-11
 *         Time: 0:00
 */
public class DefaultChain extends FilterChain {

    public DefaultChain() {
        super(new GrayscaleFilter());
    }

}
