package tian.pusen.juc.core_java_v9._04_call_future.deadlock;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

// 死锁
public class FutureDeadLock {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Entry base directory(eg. d:/):");
        String directory = in.nextLine(); // ../../../src/main
        System.out.println("Entry keyword(eg.volatile):");
        String keyword = in.nextLine();  // public class

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
                    if (search(file))
                        count++;
                }
            }
            for (Future<Integer> result : results) {
                try {
                    count += result.get();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        } catch (InterruptedException e) {
        }
        return count;
    }

    public boolean search(File file) {
        try (Scanner in = new Scanner(System.in)) {
            boolean found = false;
            while (!found && in.hasNextLine()) {
                String line = in.nextLine();
                if (line.contains(keyword))
                    found = true;
            }
            return found;
        }
    }
}
