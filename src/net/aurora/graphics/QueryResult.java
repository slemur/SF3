package net.aurora.graphics;

import java.awt.*;

/**
 * @author Rick van Biljouw
 *         Date: 21-11-11
 *         Time: 1:36
 */
public class QueryResult {
    private GraphicalObject queried;
    private Rectangle[] bounds;

    public QueryResult(GraphicalObject queried, Rectangle[] bounds) {
        this.queried = queried;
        this.bounds = bounds;
    }

    public GraphicalObject getQueried() {
        return this.queried;
    }

    public Rectangle[] getBounds() {
        return this.bounds;
    }
}
