package net.aurora.rs;

import serp.bytecode.BCClass;
import serp.bytecode.BCField;
import serp.bytecode.Constants;
import serp.bytecode.UnknownType;
import serp.framework.prototype.PrototypeClass;
import serp.framework.prototype.PrototypeMatch;

/**
 * @author Rick van Biljouw
 *         Date: 17-1-12
 *         Time: 18:30
 */
public class NodePrototype implements PrototypeClass {
    protected long id;

    final boolean hasMoreElements() {
        Object dummy = null;

        if(dummy == null) { // node.next
            return false;
        }
        return true;
    }

    public void onValidated(BCClass clazz) {
        int selfReferences = 0;

        if(clazz.getSuperclassName().contains("Object")) {
            for(BCField field : clazz.getFields()) {
                if(field.getTypeName().equals(clazz.getClassName())) {
                    selfReferences++;
                }
            }
        }

        if(selfReferences == 2) {
            System.out.println("Identified Node -> " + clazz.getClassName());
        }
    }

    public int getAccessFlags() {
        return Constants.ACCESS_PUBLIC;
    }

    public PrototypeMatch getExpectations() {
        return PrototypeMatch.LIKE;
    }

}
