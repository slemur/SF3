/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.aurora.sorcery.reflection.exception;

/**
 *
 * @author Devel
 */
public class InvalidQueryException extends RuntimeException {
    
    public InvalidQueryException(String queryPart) {
        super("Invalid FieldQuery... syntax error near " + queryPart);
    }
    
}
