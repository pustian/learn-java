package tian.pusen.jdk8.deafult;

public class DefaultKeyTest {
    public static void main(String[] args) {
        Person husband = new Husband();
        System.out.println("husband:" + husband.isMale());
        Person wife = new Wife();
        System.out.println("wife:" + wife.isMale());

        // The default method must be implicate defined.
        Person child =  new Person() {
            @Override
            public boolean isMale() {
                return false;
            }
        };
        System.out.println("child:" + child.isMale());
    }
}

interface Person {
    default boolean isMale() {
        return true;
    }
}

class Husband implements Person {
}

class Wife implements Person {
    public boolean isMale() {
        return false;
    }
}