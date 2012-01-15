/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.aurora.sorcery.bytecode.viewer;

import serp.bytecode.BCField;

/**
 *
 * @author Devel
 */
public abstract class FieldFilter {
  
    public abstract boolean accept(BCField field);
    
}
