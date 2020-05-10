package tian.pusen.jdk8.optianl;

import java.util.Arrays;
import java.util.Optional;

/**
 *      Optional.of(T t); // 创建一个Optional实例
 *      Optional.empty(); // 创建一个空的Optional实例
 *      Optional.ofNullable(T t); // 若T不为null，创建一个Optional实例，否则创建一个空实例
 *      isPresent();    // 判断是够包含值
 *      orElse(T t);   //如果调用对象包含值，返回该值，否则返回T
 *      orElseGet(Supplier s);  // 如果调用对象包含值，返回该值，否则返回s中获取的值
 *      map(Function f): // 如果有值对其处理，并返回处理后的Optional，否则返回Optional.empty();
 *      flatMap(Function mapper);// 与map类似。返回值是Optional
 *
 *      总结：Optional.of(null)  会直接报NPE
 */
public class OptionalTest {
    public static void main(String[] args) {
        Optional<String> string1 = Optional.of("hello");
        Optional<String> string2 = Optional.ofNullable("hello world");
        Optional<String> string3 = Optional.empty();

        Optional<String>[] optianlString = // new Optional[]{ string1, string2, };
                new Optional[]{string1, string2, string3};

        Arrays.asList(optianlString).forEach(x -> {if(x.isPresent() )System.out.println(x.get() ); } );
    }
}
