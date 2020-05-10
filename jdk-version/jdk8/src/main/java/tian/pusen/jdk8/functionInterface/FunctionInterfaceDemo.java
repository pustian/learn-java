package tian.pusen.jdk8.functionInterface;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class FunctionInterfaceDemo {
    public static void main(String[] args) {
        System.out.println("Consumer<T> no return with one paramter");
        testConsumer();

        System.out.println("Function<T, R> returned with  paramter");
        testFunction();

        System.out.println("Supplier<T> return value of the type T without paramter");
        testSupplier();

        System.out.println("Predicate<T> return boolean with one paramter");
        testPredicate();
    }

    /**
     * 演示Consumer<T>的使用
     * Consumer<T> : 消费型接口
     * 方法：void accept(T t),消耗参数，但是没有返回
     * Consumer可以使用andThen来对参数进行组合处理
     */
    public static void testConsumer(){
        Consumer<String> consumer = (s) -> System.out.println(s);
        consumer.accept("hello world");
    }
    /**
     * 演示Function<T>的使用
     * Function<T>:函数型接口
     * 方法：R apply(T t)，需要传入参数T，返回R,因为只有一个抽象方法，所以可以使用lambda表达式实现
     * Function可以利用compose和andThen来对参数进行组合处理
     *
     */
    public static void testFunction(){
        Function<String,String> function = (s) -> s.toUpperCase();
        System.out.println(function.apply("hello world"));
        Function<String,Integer> convert2Int = (s) ->Integer.parseInt(s.trim()) ;
        System.out.println(convert2Int.apply("100 "));
    }

    /**
     * 演示Supplier<T>的使用
     * Supplier<T>：供给型接口
     * 方法：T get()，直接返回一个结果
     */
    public static void testSupplier(){
        Supplier<String> supplier = () -> "hello";
        System.out.println(supplier.get());
    }
    /**
     * 演示Predicate<T>的使用
     *
     * Predicate<T>：断言型接口
     * 方法：boolean test(T t)，需要传入参数T，返回boolean，因为只有一个抽象方法，所以可以使用lambda表达式实现
     * Predicate可以利用and、or和negate组成与、或和非等复杂的逻辑。
     */
    public static void testPredicate(){
        Predicate<String> predicateOne = (s) -> null != s && s.length() > 0 ;
        Predicate<String> predicateTwo = (s) ->  s.length() > 10;
        System.out.println("test param null, result=" + predicateOne.and(predicateTwo).test(null));
        System.out.println("test param ''  , result=" + predicateOne.and(predicateTwo).test(""));
        System.out.println("test param 'hello world', result=" + predicateOne.and(predicateTwo).test("hello world"));
    }

}