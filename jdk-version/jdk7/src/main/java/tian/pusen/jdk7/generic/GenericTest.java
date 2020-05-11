package tian.pusen.jdk7.generic;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class GenericTest {
    public static void main(String[] args) throws Exception {
        List<String> stringList = new ArrayList<>();
        stringList.add("tian");
        stringList.add("pusen");
//        stringList.add(123);

        Class clazz = stringList.getClass();
//        Method[] method = clazz.getMethods();
        Method method = clazz.getDeclaredMethod("add", Object.class);
        method.setAccessible(true);
        method.invoke(stringList, 123);
        for(int i =0; i< stringList.size(); ++i) {
            Object obj = stringList.get(i);
            System.out.println(obj.toString());
        }
    }
}
