package tian.pusen.juc.core_java_v9._04_call_future;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

// 死锁
public class FutureTest {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Entry base directory(eg. d:/):");
        String directory = in.nextLine();
        System.out.println("Entry keyword(eg.volatile):");
        String keyword = in.nextLine();

//        String directory = "D:\\workshop\\personal\\github.com\\pustian\\learn-java\\jdk-version\\jdk7\\src\\main";
//        String keyword = "public class";

        MatchCounter counter = new MatchCounter(new File(directory), keyword);
        FutureTask<Integer> task =  new FutureTask<>(counter);
        Thread thread = new Thread(task);
        thread.start();
        try {
            System.out.println(task.get() + " matching files.");
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
        }
    }
}

class MatchCounter implements Callable<Integer> {
    private File directory;
    private String keyword;
    private int count;

    public MatchCounter(File directory, String keyword) {
        this.directory = directory;
        this.keyword = keyword;
    }

    @Override
    public Integer call() {
        count = 0;
        try {
            File[] files = directory.listFiles();
            List<Future<Integer>> results = new ArrayList<>();
            for (File file : files) {
                if (file.isDirectory()) {
                    MatchCounter counter = new MatchCounter(file, keyword);
                    FutureTask<Integer> task = new FutureTask<>(counter);
                    results.add(task);
                    Thread thread = new Thread(task);
                    thread.start();
                } else {
//                    count++;
//                    System.out.println("Thread id" + Thread.currentThread().getId() +" count=" + count + " path:" + file.getPath());
                    if (search(file)) {
                        count++;
                        System.out.println("Thread id" + Thread.currentThread().getId() +" count=" + count + " path:" + file.getPath());
                    }
                }
            }
            for (Future<Integer> result : results) {
                try {
                    count += result.get();
                    System.out.println("Thread id" + Thread.currentThread().getId() +" result=" + result.get() + " count="+count);
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        } catch (InterruptedException e) {
        }
        return count;
    }

    public boolean search(File file) {
        try {
            try (Scanner in = new Scanner(file)) {
                boolean found = false;
                while (!found && in.hasNextLine()) {
                    String line = in.nextLine();
                    if (line.contains(keyword))
                        found = true;
                }
                return found;
            }
        } catch (IOException e) {
            return false;
        }
    }
}
