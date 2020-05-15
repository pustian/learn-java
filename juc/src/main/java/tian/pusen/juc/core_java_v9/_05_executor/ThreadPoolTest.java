package tian.pusen.juc.core_java_v9._05_executor;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.*;

public class ThreadPoolTest {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Entry base directory(eg. d:/):");
        String directory = in.nextLine();
        System.out.println("Entry keyword(eg.volatile):");
        String keyword = in.nextLine();

//        ExecutorService pool = Executors.newCachedThreadPool();
        ExecutorService pool = Executors.newFixedThreadPool(20);

//        MatchCounter counter = new MatchCounter(new File(directory), keyword, pool);
//        Future<Integer> result = pool.submit(counter);
//
//        MatchCounter2 counter = new MatchCounter2(new File(directory), keyword, pool);
//        Future<Integer> result = pool.submit(counter);

        MatchCounter3 counter = new MatchCounter3(new File(directory), keyword, pool);
        Future<Integer> result = pool.submit(counter);

        try {
            System.out.println(result.get() + " matching files.");
        } catch (InterruptedException e) {
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        pool.shutdown();

        int largestPoolSize = ((ThreadPoolExecutor)pool).getLargestPoolSize() ;
        System.out.println("largestPoolSize = " + largestPoolSize);
    }
}
//class MatchCounter implements Callable<Integer> {
//    private File directory;
//    private String keyword;
//    private ExecutorService pool;
//    private int count;
//
//    public MatchCounter(File directory, String keyword, ExecutorService pool) {
//        this.directory = directory;
//        this.keyword = keyword;
//        this.pool = pool;
//    }
//
//    @Override
//    public Integer call() {
//        count = 0;
//        try {
//            File[] files = directory.listFiles();
//            List<Future<Integer>> results = new ArrayList<>();
//
//            for (File file : files) {
//                if (file.isDirectory()) {
//                    MatchCounter counter = new MatchCounter(file, keyword, pool);
//                    Future<Integer> result = pool.submit(counter);
//                    results.add(result);
//                } else {
//                    if (search(file)) {
//                        count++;
//                    }
//                }
//            }
//            for (Future<Integer> result : results) {
//                try {
//                    count += result.get();
//                } catch (ExecutionException e) {
//                    e.printStackTrace();
//                }
//            }
//        } catch (InterruptedException e) {
//        }
//        return count;
//    }
//
//    public boolean search(File file) {
//        try {
//            try (Scanner in = new Scanner(file)) {
//                boolean found = false;
//                while (!found && in.hasNextLine()) {
//                    String line = in.nextLine();
//                    if (line.contains(keyword))
//                        found = true;
//                }
//                return found;
//            }
//        } catch (IOException e) {
//            return false;
//        }
//    }
//}
