package serp.framework.prototype;

import serp.bytecode.BCClass;

/**
 * @author rvbiljouw
 *         Date: 17-1-12
 *         Time: 13:58
 */
public interface PrototypeClass {

    public void onValidated(BCClass clazz);

    public int getAccessFlags();

    public PrototypeMatch getExpectations();

}
