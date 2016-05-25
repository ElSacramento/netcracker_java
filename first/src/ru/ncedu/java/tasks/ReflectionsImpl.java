package ru.ncedu.java.tasks;

import java.lang.reflect.*;
import java.util.*;

/**
 * Created by Sony on 15.10.2015.
 */
public class ReflectionsImpl implements Reflections {
    private List<Class> result = new ArrayList<>();
    private Set<Method> result_set = new HashSet<>();

    @Override
    public Object getFieldValueByName(Object object, String fieldName) throws NoSuchFieldException, NullPointerException {
        try {
            Class example = object.getClass();
            Field temp = example.getDeclaredField(fieldName);
            temp.setAccessible(true);
            return temp.get(object);
        }
        catch (NullPointerException eo) {
            throw new NullPointerException();
        }
        catch (NoSuchFieldException em) {
            throw new NoSuchFieldException();
        }
        catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Set<String> getProtectedMethodNames(Class clazz) throws NullPointerException{
        try {
            Set<String> result = new HashSet<>();
            Method[] methods = clazz.getDeclaredMethods();
            for (Method ex : methods) {
                if (Modifier.isProtected(ex.getModifiers())) result.add(ex.getName());
            }
            return result;
        }
        catch (NullPointerException eo){
            throw new NullPointerException();
        }
    }

    @Override
    public Set<Method> getAllImplementedMethodsWithSupers(Class clazz) throws NullPointerException{
        try {
            Method[] methods = clazz.getDeclaredMethods();
            for (Method me : methods)
                result_set.add(me);
            if (clazz.getSuperclass() != null)
                getAllImplementedMethodsWithSupers(clazz.getSuperclass());
            return result_set;
        }
        catch (NullPointerException eo) {
            throw new NullPointerException();
        }
    }

    @Override
    public List<Class> getExtendsHierarchy(Class clazz) throws NullPointerException{
        Class superClass = clazz.getSuperclass();
        try {
            if (superClass != null)
            result.add(superClass);
        }
        catch (NullPointerException eo) {
            throw new NullPointerException();
        }
        if (superClass != null)
            getExtendsHierarchy(superClass);
        return result;
    }

    @Override
    public Set<Class> getImplementedInterfaces(Class clazz) throws NullPointerException{
        try {
            Set<Class> result = new HashSet<>();
            Class[] interfaces = clazz.getInterfaces();
            for (Class cl : interfaces) {
                result.add(cl);
            }
            return result;
        }
        catch (NullPointerException eo) {
            throw new NullPointerException();
        }
    }

    @Override
    public List<Class> getThrownExceptions(Method method) throws NullPointerException{
        try {
            Class[] result = method.getExceptionTypes();
            List<Class> res = new ArrayList<>();
            for (Class cl : result) {
                res.add(cl);
            }
            return res;
        }
        catch (NullPointerException eo) {
            throw new NullPointerException();
        }
    }

    @Override
    public String getFooFunctionResultForDefaultConstructedClass() {
        try {
            //Class temp = (new SecretClass()).getClass();
            ReflectionsImpl outerObj = new ReflectionsImpl();
            ReflectionsImpl.SecretClass innerObj = outerObj.new SecretClass();
            Method method = (innerObj.getClass()).getDeclaredMethod("foo");
            method.setAccessible(true);
            String s = (String) method.invoke(innerObj);
            return s;
        }
        catch (NoSuchMethodException eo){
            eo.printStackTrace();
        }
        catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public String getFooFunctionResultForClass(String constructorParameter, String string, Integer... integers) {
        try{
            Class temp = (new SecretClass(constructorParameter)).getClass();
            Class[] paramTypes = new Class[] {String.class, Integer[].class };
            ReflectionsImpl outerObj = new ReflectionsImpl();
            ReflectionsImpl.SecretClass innerObj = outerObj.new SecretClass(constructorParameter);
            Method method = innerObj.getClass().getDeclaredMethod("foo", paramTypes);
            method.setAccessible(true);
            Object[] args = new Object[] {string, integers};
            String s = (String) method.invoke(innerObj, args);
            return s;
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public class SecretClass {

        private String text = null;

        private String secret = "secret";

        private SecretClass() {
        }

        public SecretClass(String text) {
            this.text = text;
        }

        public void setSecret (String secret) {
            this.secret = secret;
        }

        private String foo(String string, Integer ... integers){
            String s = "";
			/* Some text hidden : start */
            int in = 0;
            if(integers != null)
                for(Integer i : integers)
                    in += i;
            s += string + " - > " + in;
			/* Some text hidden : end */
            return s;
        }

        private String foo(){
            String s = "";
			/* Some text hidden : start */
            s += "abraKadabra";
			/* Some text hidden : end */
            return s;
        }

    }
}
