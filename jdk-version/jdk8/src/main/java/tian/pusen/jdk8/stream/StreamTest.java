package tian.pusen.jdk8.stream;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class StreamTest {
    static String[] strings = {"Zhang san", "Li si", "Wang wu", "Zhao", "Qian", "Sun", "Li", "Wang", "Wang Chao"};
    static List<String> stringCollection = Arrays.asList(strings);

    public static void main(String[] args) {
        System.out.println("\nstream");
        stream();
        System.out.println("\nfilter");
        filter();
        System.out.println("\nsort");
        sort();
        System.out.println("\nmap");
        map();
        System.out.println("\nmatch");
        match();
        System.out.println("\ncount");
        count();
        System.out.println("\nreduce");
        reduce();
    }

    private static void stream() {
        System.out.println("\nparallelStream  顺序不一致");
        stringCollection
                .parallelStream()
                .forEach(x -> System.out.print(x + "  |  "));
        System.out.println("\neach ordered 原始顺序");
        stringCollection
                .parallelStream()
                .forEachOrdered(x -> System.out.print(x + "  |  "));
        System.out.println("\nstream");
        stringCollection
                .stream()
                .forEach(x -> System.out.print(x + "  |  "));
    }
    private static void filter() {
        stringCollection.forEach(x -> System.out.print(x + "  |  "));
        System.out.println("\nStream filter");
        stringCollection
                .parallelStream()
//                .stream()
                .filter((s) -> s.startsWith("Z"))
                .forEach(x -> System.out.print(x + "  |  "));
    }

    private static void sort() {
        stringCollection.forEach(x -> System.out.print(x + "  |  "));
        System.out.println("\nStream sort");
        stringCollection
                .parallelStream()
//                .stream()
                .sorted()
                .forEach(x -> System.out.print(x + "  |  "));
//                .forEachOrdered(x -> System.out.print(x + "  |  "));
//
//        System.out.println();
//        stringCollection.stream()
//                .forEachOrdered(x -> System.out.print(x + "  |  "));
    }

    private static void map() {
        stringCollection.forEach(x -> System.out.print(x + "  |  "));
        System.out.println("\nStream map");
        stringCollection
                .parallelStream()
//                .stream()
                .map(String::toUpperCase)
                .forEach(x -> System.out.print(x + "  |  "));
    }

    private static void match() {
        stringCollection.forEach(x -> System.out.print(x + "  |  "));
        System.out.println("\nStream allMatch");
        boolean allMatched = stringCollection
                .parallelStream()
//                .stream()
                .allMatch(x -> 'A' <= x.charAt(0) && x.charAt(0) <= 'Z');
        System.out.println("allMatched start with [A, Z]" + allMatched);

        System.out.println("\nStream anyMatch");
        boolean anyMatched = stringCollection
                .parallelStream()
//                .stream()
                .anyMatch(x -> x.startsWith("Zh"));
        System.out.println("anyMatched start with Zh" + anyMatched);

        System.out.println("\nStream noneMatch");
        boolean noneMatched = stringCollection
                .parallelStream()
//                .stream()
                .noneMatch(x -> 'a' <= x.charAt(0) && x.charAt(0) <= 'z');
        System.out.println("noneMatched start with [a,z]" + noneMatched);
    }

    private static void count() {
        System.out.println("\nStream count");
        long count = stringCollection
                .parallelStream()
//                .stream()
                .count();
        System.out.println("Stream size =" + count);
        System.out.println("list size = " + stringCollection.size());
    }

    // 可以将流中元素反复结合起来，得到一个值
    private static void reduce() {
        System.out.println("\nStream reduce");
        Optional<String> reduced = stringCollection
                .parallelStream()
//                .stream()
                .filter((s) -> s.startsWith("Wang"))
                .reduce((s0, s1)-> s0+"|"+s1);

        reduced.ifPresent(System.out::println);
    }
}
