package tian.pusen.juc.core_java_v9._05_executor;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.*;

public class MatchCounter2 implements Callable<Integer> {
    private File directory;
    private String keyword;
    private ExecutorService pool;
    private int count;

    public MatchCounter2(File directory, String keyword, ExecutorService pool) {
        this.directory = directory;
        this.keyword = keyword;
        this.pool = pool;
    }

    @Override
    public Integer call() {
        count = 0;
        try {
            File[] files = directory.listFiles();
//            ExecutorCompletionService service = new ExecutorCompletionService(pool);
            List<Callable<Integer> > tasks = new ArrayList<>();

            for (File file : files) {
                if (file.isDirectory()) {
                    MatchCounter2 counter = new MatchCounter2(file, keyword, pool);
                    tasks.add(counter);
                } else {
                    if (search(file)) {
                        count++;
                    }
                }
            }
            List<Future<Integer> > results = pool.invokeAll(tasks);

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
