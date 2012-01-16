/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.aurora.sorcery.reflection;

import java.lang.reflect.Field;

/**
 *
 * @author Devel
 */
public class FieldValue {

    private Object instance;
    private String value;
    private boolean inArray;
    private String indexesAsStr;

    public FieldValue(String value, boolean inArray, String indexesAsStr, Object instance) {
        this.value = value;
        this.inArray = inArray;
        this.indexesAsStr = indexesAsStr;
        this.instance = instance;
    }
    
    public Object getInstance() {
        return this.instance;
    }

    public String getValue() {
        return this.value;
    }

    public boolean isInArray() {
        return this.inArray;
    }

    public String getIndexesAsStr() {
        return this.indexesAsStr;
    }
}
