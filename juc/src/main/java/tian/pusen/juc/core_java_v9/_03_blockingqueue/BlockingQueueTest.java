package tian.pusen.juc.core_java_v9._03_blockingqueue;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

//  emurator  queue [] [] [] [] [] [] ... []
//  search    queue --
//  search    queue    --
//  search    queue       --
public class BlockingQueueTest {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Entry base directory(eg. d:/):");
        String directory = in.nextLine();
        System.out.println("Entry keyword(eg.volatile):");
        String keyword = in.nextLine();

        final int FILE_QUEUE_SIZE = 10;
        final int SEARCH_THREAD = 100;

        BlockingQueue<File> queue = new ArrayBlockingQueue<>(FILE_QUEUE_SIZE);

        FileEnumerationTask emuerator = new FileEnumerationTask(queue, new File(directory));
        new Thread(emuerator).start();

        for (int i = 1; i < SEARCH_THREAD; ++i) {
            new Thread(new SearchTask(queue, keyword)).start();
        }
    }
}

// 列出所有的文件，包含子目录中文件
class FileEnumerationTask implements Runnable {
    public static File DUMMY = new File("");
    private BlockingQueue<File> queue;
    private File startingDirectory;

    public FileEnumerationTask(BlockingQueue<File> queue, File startingDirectory) {
        this.queue = queue;
        this.startingDirectory = startingDirectory;
    }

    @Override
    public void run() {
        try {
            enumerate(startingDirectory);
            queue.put(DUMMY);
            System.out.println("Enumeration Thread id" + Thread.currentThread().getId() + " Queue size:" + queue.size());
        } catch (InterruptedException e) {
        }
    }

    public void enumerate(File directory) throws InterruptedException {
        File[] files = directory.listFiles();
        for (File file : files) {
            if (file.isDirectory())
                enumerate(file);
            else
                queue.put(file);
        }
    }
}

class SearchTask implements Runnable {
    private BlockingQueue<File> queue;
    private String keyword;

    public SearchTask(BlockingQueue<File> queue, String keyword) {
        this.queue = queue;
        this.keyword = keyword;
    }

    @Override
    public void run() {
        try {
            boolean done = false;
            while (!done) {
                File file = queue.take();
                if (file == FileEnumerationTask.DUMMY) {
                    queue.put(file);
                    done = true;
                } else {
                    search(file);
                }
            }
            System.out.println("Search Thread id" + Thread.currentThread().getId() + " Queue size:" + queue.size());
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (InterruptedException ex) {

        }
    }

    public void search(File file) throws IOException {
        try (Scanner in = new Scanner(file)) {
            int lineNum = 0;
            while (in.hasNextLine()) {
                lineNum++;
                String line = in.nextLine();
                if (line.contains(keyword)) {
                    System.out.printf("%s:%d:%s\n", file.getPath(), lineNum, line);
                }
            }
        }
    }
}