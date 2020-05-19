package tian.pusen.jdk.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;

public class ReflectionTest {
    public static void main(String[] args) {
        // read class name
        System.out.println("\nConstructors");
        printConstructor(java.lang.Double.class);
        System.out.println("\nMethods");
        printMethod(java.lang.Double.class);
        System.out.println("\nFields");
        printField(java.lang.Double.class);
    }

    public static void printConstructor(Class clazz) {
        System.out.println("\n=============Public==================");
        Constructor[] constructors = clazz.getConstructors();
        for (Constructor constructor : constructors) {
            String name = constructor.getName();
            String modifier = Modifier.toString(constructor.getModifiers());
            Class[] paramTypes = constructor.getParameterTypes();
            print(name, modifier, paramTypes);
        }

        System.out.println("\n=============Not Public==================");
        Constructor[] allConstructors = clazz.getDeclaredConstructors();
        for (Constructor constructor : allConstructors) {
            if (!Arrays.asList(constructors).contains(constructor)) {
                String name = constructor.getName();
                String modifier = Modifier.toString(constructor.getModifiers());
                Class[] paramTypes = constructor.getParameterTypes();
                print(name, modifier, paramTypes);
            }
        }
    }

    public static void printMethod(Class clazz) {
        System.out.println("\n=============Public==================");
        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            String name = method.getName();
            String modifier = Modifier.toString(method.getModifiers());
            Class returnType = method.getReturnType();
            Class[] paramTypes = method.getParameterTypes();
            print(returnType, name, modifier, paramTypes);
        }

        System.out.println("\n=============Not Public==================");
        Method[] allMethods = clazz.getDeclaredMethods();
        for (Method method : allMethods) {
            if (!Arrays.asList(methods).contains(method)) {
                String name = method.getName();
                String modifier = Modifier.toString(method.getModifiers());
                Class returnType = method.getReturnType();
                Class[] paramTypes = method.getParameterTypes();
                print(returnType, name, modifier, paramTypes);
            }
        }
    }

    public static void printField(Class clazz) {
        System.out.println("\n=============Public==================");
        Field[] fields = clazz.getFields();
        for (Field field : fields) {
            Class type = field.getType();
            String name = field.getName();
            String modifier = Modifier.toString(field.getModifiers());
            print(type, name, modifier, null);
        }

        System.out.println("\n=============Not Public==================");
        Field[] allfields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (!Arrays.asList(fields).contains(field)) {
                Class type = field.getType();
                String name = field.getName();
                String modifier = Modifier.toString(field.getModifiers());
                print(type, name, modifier, null);
            }
        }
    }

    private static void print(String name, String modifier, final Class[] paramTypes) {
        print(null, name, modifier, paramTypes);
    }

    private static void print(Class returnType, String name, String modifier, final Class[] paramTypes) {
        if (returnType != null)
            System.out.println("return type = " + returnType.getName());
        System.out.println("name=" + name);
        System.out.println("modifier =" + modifier);
        if (paramTypes != null) {
            System.out.print("paramTypes =");
            for (int i = 0; i < paramTypes.length; ++i) {
                System.out.print(paramTypes[i].getName() + ", ");
            }
            System.out.println();
        }
    }
}
