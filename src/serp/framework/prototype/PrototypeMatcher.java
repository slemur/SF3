package serp.framework.prototype;

import serp.bytecode.*;
import serp.framework.algorithm.LevenshteinDistance;

import java.lang.reflect.Field;
import java.util.HashMap;

/**
 * @author rvbiljouw
 *         Date: 17-1-12
 *         Time: 14:01
 */
public class PrototypeMatcher {
    private final Project project = new Project("prototypes");
    private PrototypeClass prototype;

    public PrototypeMatcher(PrototypeClass prototype) {
        this.prototype = prototype;
    }

    public boolean match(BCClass clazz) {

        BCClass prototypeRepresentation = project.loadClass(prototype.getClass());

        if (!matchFields(prototypeRepresentation, clazz) || !matchMethods(prototypeRepresentation, clazz)) {
            return false;


        }

        this.prototype.onValidated(clazz);
        return true;


    }

    private boolean matchFields(BCClass prototype, BCClass subject) {
        HashMap<String, Integer> prototypeFieldMap = new HashMap<String, Integer>();
        HashMap<String, Integer> subjectFieldMap = new HashMap<String, Integer>();

        for (BCField field : prototype.getFields()) {

            String myLocalKey = this.prototype.getExpectations() == PrototypeMatch.EXACT ?
                    field.getName() + "." + field.getTypeName() : field.getTypeName();

            if (prototypeFieldMap.containsKey(myLocalKey)) {

                int currentValue = prototypeFieldMap.get(myLocalKey);
                prototypeFieldMap.remove(myLocalKey);
                prototypeFieldMap.put(myLocalKey, currentValue + 1);
            } else {
                prototypeFieldMap.put(myLocalKey, 1);
            }
        }

        for (BCField field : subject.getFields()) {
            String myLocalKey = this.prototype.getExpectations() == PrototypeMatch.EXACT ?
                    field.getName() + "." + field.getTypeName() : field.getTypeName();

            if (subjectFieldMap.containsKey(myLocalKey)) {

                int currentValue = subjectFieldMap.get(myLocalKey);
                subjectFieldMap.remove(myLocalKey);
                subjectFieldMap.put(myLocalKey, currentValue + 1);
            } else {
                subjectFieldMap.put(myLocalKey, 1);
            }
        }

        for (String key : prototypeFieldMap.keySet()) {
            int amountInPrototype = prototypeFieldMap.get(key);

            if (!(subjectFieldMap.containsKey(key) && amountInPrototype <= subjectFieldMap.get(key))) {
                return false;
            }
        }
        return true;
    }

    private boolean matchMethods(BCClass prototype, BCClass subject) {
        HashMap<MethodAttributes, Integer> prototypeMethodMap = new HashMap<MethodAttributes, Integer>();
        HashMap<MethodAttributes, Integer> subjectMethodMap = new HashMap<MethodAttributes, Integer>();

        for (BCMethod method : prototype.getMethods()) {
            if (!method.getName().contains("onValidated") && !method.getName().contains("getExpectations") && !method.getName().contains("getAccessFlags")
                    && !method.getName().contains("<init>") && !method.getName().contains("<clinit>")) {
                int accessFlags = method.getAccessFlags();
                if (method.getDeclaredAnnotations(false) != null && method.getDeclaredAnnotations(false).getAnnotation(PrototypeSpecialAccess.class) != null) {
                    accessFlags = method.getDeclaredAnnotations(false).getAnnotation(PrototypeSpecialAccess.class).getProperty("access").getIntValue();
                }

                MethodAttributes myLocalKey = new MethodAttributes(
                        method.getName(), method.getParamTypesAsString(), method.getReturnName(), accessFlags, method.getCode(false) != null ? method.getCode(false).toString() : ""
                );

                if (prototypeMethodMap.containsKey(myLocalKey)) {

                    int currentValue = prototypeMethodMap.get(myLocalKey);
                    prototypeMethodMap.remove(myLocalKey);
                    prototypeMethodMap.put(myLocalKey, currentValue + 1);
                } else {
                    prototypeMethodMap.put(myLocalKey, 1);
                }

            }
        }

        for (BCMethod method : subject.getMethods()) {
            MethodAttributes myLocalKey = new MethodAttributes(
                    method.getName(), method.getParamTypesAsString(), method.getReturnName(), method.getAccessFlags(), method.getCode(false) != null ? method.getCode(false).toString() : ""
            );

            if (subjectMethodMap.containsKey(myLocalKey)) {

                int currentValue = subjectMethodMap.get(myLocalKey);
                subjectMethodMap.remove(myLocalKey);
                subjectMethodMap.put(myLocalKey, currentValue + 1);
            } else {
                subjectMethodMap.put(myLocalKey, 1);
            }
        }

        for (MethodAttributes key : prototypeMethodMap.keySet()) {
            MethodAttributes subjectKey = null;
            int amountInPrototype = prototypeMethodMap.get(key);

            for (MethodAttributes aSubjectKey : subjectMethodMap.keySet()) {
                if (aSubjectKey.equals(key)) subjectKey = key;
            }

            if (subjectKey == null
                    || subjectMethodMap.get(key) < amountInPrototype
                    || key.getCodeDistance(subjectKey.getCode()) > (key.getCode().length() / 2)
                    || !subjectKey.getArgumentTypeNameString().contains(key.argumentTypeNameString)) {
                return false;
            }
        }
        return true;
    }

    private UnknownType fetchUnknownType(BCField field) {
        try {
            Field refField = prototype.getClass().getDeclaredField(field.getName());
            refField.setAccessible(true);

            Object instanceOfUnknownType = refField.get(prototype);
            if (instanceOfUnknownType != null) {
                return (UnknownType) instanceOfUnknownType;
            }
        } catch (Exception e) {

        }
        return null;
    }

    private String fetchTypeName(BCField field) {
        String typeName = field.getTypeName();
        if (field.getTypeName().contains("UnknownType")) {
            UnknownType type = fetchUnknownType(field);
            if (type != null) {
                typeName = type.selfReference ? "self" : type.signature;
            }
        }
        return typeName;
    }

    private class MethodAttributes {
        private int accessModifier;
        private String returnTypeName;
        private String argumentTypeNameString;
        private String methodName;
        private String code;

        private MethodAttributes(String methodName, String argumentTypeNameString, String returnTypeName, int accessModifier, String code) {
            this.methodName = methodName;
            this.argumentTypeNameString = argumentTypeNameString;
            this.returnTypeName = returnTypeName;
            this.accessModifier = accessModifier;
            this.code = code;
        }

        public int getCodeDistance(String aCode) {
            return LevenshteinDistance.editDistance(aCode, this.code);
        }

        public String getCode() {
            return this.code;
        }

        public String getArgumentTypeNameString() {
            return this.argumentTypeNameString;
        }

        @Override
        public String toString() {
            return accessModifier + " " + returnTypeName + " " + argumentTypeNameString + " " + methodName;
        }

        @Override
        public int hashCode() {
            return prototype.getExpectations() == PrototypeMatch.EXACT ?
                    accessModifier + returnTypeName.hashCode() + methodName.hashCode()
                    : accessModifier + returnTypeName.hashCode();
        }

        @Override
        public boolean equals(Object object) {
            return hashCode() == object.hashCode();
        }
    }

}
