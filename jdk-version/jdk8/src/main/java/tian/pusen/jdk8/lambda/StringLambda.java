package tian.pusen.jdk8.lambda;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

//******************************************************************************
// () -> 5  		// 1. 不需要参数,返回值为 5  
// x -> 2 * x		// 2. 接收一个参数(数字类型),返回其2倍的值  
// (x, y) -> x – y  // 3. 接受2个参数(数字),并返回他们的差值  
// (int x, int y) -> x + y  // 4. 接收2个int型整数,返回他们的和  
//(String s) -> System.out.print(s) // 5. 接受一个 string 对象,并在控制台打印,不返回任何值(看起来像是返回void)  
//******************************************************************************
//	// 1.1使用匿名内部类  
//	new Thread(new Runnable() {  
//	    @Override  
//	    public void run() {  
//	        System.out.println("Hello world !");  
//	    }  
//	}).start();  
//	// 1.2使用 lambda expression  
//	new Thread(() -> System.out.println("Hello world !")).start();  
//******************************************************************************  
//	// 2.1使用匿名内部类  
//	Runnable race1 = new Runnable() {  
//	    @Override  
//	    public void run() {  
//	        System.out.println("Hello world !");  
//	    }  
//	};  
//// 2.2使用 lambda expression  
//Runnable race2 = () -> System.out.println("Hello world !");  
//	// 直接调用 run 方法(没开新线程哦!)  
//	race1.run();  
//	race2.run();  

public final class StringLambda {

    public static void main(String[] args) {
        String[] atp = {"Rafael Nadal", "Novak Djokovic",
                "Stanislas Wawrinka",
                "David Ferrer", "Roger Federer",
                "Andy Murray", "Tomas Berdych",
                "Juan Martin Del Potro"};
        List<String> players = Arrays.asList(atp);

        players.forEach(x -> System.out.println(x));

        // 以前的循环方式
        System.out.println("\nCollections traditional sort");
        // 1.1 使用匿名内部类根据 name 排序 players
        Collections.sort(players, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return (s1.compareTo(s2));
            }
        });

        // 1.2 使用 lambda expression 排序 players
        System.out.println("\nlambda expression 排序 players lamba interface");
        Comparator<String> sortByName = (String s1, String s2) -> (s1.compareTo(s2));
        Collections.sort(players, sortByName);

        // 1.3 也可以采用如下形式:
        System.out.println("\nlambda expression 排序 players lamba interface");
        Collections.sort(players, (String s1, String s2) -> (s1.compareTo(s2)));

        for (String player : players) {
            System.out.println(player + "; ");
        }
    }
}
