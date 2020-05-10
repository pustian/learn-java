package tian.pusen.jdk8.functionInterface;

public class FunctionInterfaceTest {
    public static void main(String[] args) {
        LambdaInterface lambdaInterface = string -> System.out.println(string);

        lambdaInterface.test("hello");
    }
}

@FunctionalInterface
interface LambdaInterface {
//    default void tests() {
//        System.out.println("test");
//    }
    void test(String string);
}
