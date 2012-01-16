/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.aurora.sorcery.reflection;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.aurora.sorcery.Sorcery;
import serp.bytecode.BCClass;

/**
 *
 * @author Devel
 */
public class FieldQuery {

    private HashMap<Field, FieldValue> resultSet = new HashMap<Field, FieldValue>();
    private String value;
    private Class type;

    public FieldQuery(String value) {
        this.value = value;
    }

    public void execute(boolean includeInstances) {
        BCClass[] availableClasses = Sorcery.getSorcery().getClassContainer().getClasses();
        for (BCClass bcClazz : availableClasses) {
            if (bcClazz.getClassName().length() <= 3) {
                try {
                    Class<?> clazz = Sorcery.getSorcery().getClassLoader().loadClass(bcClazz.getClassName());
                    this.type = clazz;
                    Object[] instances = findInstances();
                    //System.out.println("Looking through " + clazz.getSimpleName() + "[" + clazz.getDeclaredFields().length + " fields]");
                    for (Field field : clazz.getDeclaredFields()) {
                        if ((field.getModifiers() & Modifier.STATIC) != 0) {
                            try {
                                field.setAccessible(true);
                                expandFieldValue(field, field.get(null), 0, "");
                            } catch (Exception e) {
                            }
                        } else {
                            try {
                                field.setAccessible(true);

                                for (Object instance : instances) {
                                    expandFieldValue(field, field.get(instance), 0, "");
                                }


                            } catch (Exception e) {
                            }
                        }
                    }

                } catch (ClassNotFoundException ex) {
                    //Logger.getLogger(FieldQuery.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    static int maxDepth = 2000;

    private void expandFieldValue(Field field, Object object, int depth, String indexAsStr) {
        if (depth > maxDepth) {
            //System.out.println("hit max depth on " + object.getClass().getSimpleName() + " : " + object.toString());
            return;
        }
        if (object.getClass().isArray()) {
            int arrayLength = Array.getLength(object);
            for (int i = 0; i < arrayLength; i++) {
                Object arrayValue = Array.get(object, i);
                if (arrayValue.getClass().isArray()) {

                    expandFieldValue(field, arrayValue, depth + 1, indexAsStr + "[" + i + "]");

                } else if (String.valueOf(arrayValue).equalsIgnoreCase(this.value)) {
                    resultSet.put(field, new FieldValue(this.value, false, indexAsStr, null));
                }
            }

        } else if (String.valueOf(object).equalsIgnoreCase(this.value)) {
            resultSet.put(field, new FieldValue(this.value, false, "", null));
        }
    }

    private Object[] findInstances() {
        BCClass[] availableClasses = Sorcery.getSorcery().getClassContainer().getClasses();
        ArrayList<Object> objectz = new ArrayList<Object>();
        for (BCClass bcClazz : availableClasses) {
            if (bcClazz.getClassName().length() <= 3) {
                try {
                    Class<?> clazz = Sorcery.getSorcery().getClassLoader().loadClass(bcClazz.getClassName());
                    //System.out.println("Looking through " + clazz.getSimpleName() + "[" + clazz.getDeclaredFields().length + " fields]");
                    for (Field field : clazz.getDeclaredFields()) {
                        if ((field.getModifiers() & Modifier.STATIC) != 0) {
                            try {
                                field.setAccessible(true);
                                objectz.addAll(Arrays.asList(getInstancesForClassBalls(field, field.get(null), 0, "")));
                            } catch (Exception e) {
                            }
                        }
                    }
                } catch (Exception e) {
                }
            }
        }
        return objectz.toArray(new Object[objectz.size()]);
    }

    private Object[] getInstancesForClassBalls(Field field, Object object, int depth, String indexAsStr) {
        ArrayList<Object> objectz = new ArrayList<Object>();
        if (depth > maxDepth) {
            //System.out.println("hit max depth on " + object.getClass().getSimpleName() + " : " + object.toString());
            return null;
        }
        if (object.getClass().isArray()) {
            int arrayLength = Array.getLength(object);
            for (int i = 0; i < arrayLength; i++) {
                Object arrayValue = Array.get(object, i);
                if (arrayValue.getClass().isArray()) {

                    objectz.addAll(Arrays.asList(getInstancesForClassBalls(field, arrayValue, depth + 1, indexAsStr + "[" + i + "]")));

                } else if (arrayValue.getClass().getSimpleName().equals(this.type.getSimpleName())) {
                    objectz.add(arrayValue);
                }
            }

        } else if (object.getClass().getSimpleName().equals(this.type.getSimpleName())) {
            objectz.add(object);
        }
        return objectz.toArray(new Object[objectz.size()]);
    }

    public HashMap<Field, FieldValue> getResultSet() {
        return resultSet;
    }
}
