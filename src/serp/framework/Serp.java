package serp.framework;

import serp.framework.optimize.Optimizer;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author rvbiljouw
 *         Date: 17-1-12
 *         Time: 13:25
 */
public class Serp {
    /**
     * Singleton
     */
    private static Serp _instance;

    /**
     * Members
     */
    private CopyOnWriteArrayList<Optimizer> optimizers = new CopyOnWriteArrayList<Optimizer>();
    private boolean runOptimizersOnLoad = false;

    /**
     * Add an optimizer to serp
     * @param optimizer optimizer
     */
    public void addOptimizer(Optimizer optimizer) {
        this.optimizers.add(optimizer);
    }

    /**
     * Remove an optimizer from serp
     * @param optimizer optimizer
     */
    public void removeOptimizer(Optimizer optimizer) {
        this.optimizers.remove(optimizer);
    }

    /**
     * Get an instance of serp.
     * Only one instance of this class may exist.
     * This is ensured by the singleton pattern.
     * @return instance
     */
    public static Serp getSerp() {
        if(_instance == null) _instance = new Serp();
        return _instance;
    }
}
