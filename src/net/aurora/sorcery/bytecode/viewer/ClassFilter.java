/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.aurora.sorcery.bytecode.viewer;

import serp.bytecode.BCClass;

/**
 *
 * @author Devel
 */
public abstract class ClassFilter {
    
    public abstract boolean accept(BCClass clazz);
    
}
