package tian.pusen.jdk7.thread;

import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ForkJoinSample extends RecursiveTask<Integer> {
//    private static final int THRESHOLD = 500_000;
    private static final int THRESHOLD = 50_000_000; // 123_456_000
    private long[] array;
    private int low;
    private int high;

    public ForkJoinSample(long[] array, int low, int high) {
        this.array = array;
        this.low = low;
        this.high = high;
    }

    @Override
    protected Integer compute() {
        int sum = 0;
        if (high - low < THRESHOLD) {
            // 小于阈值则直接计算
            for (int i = low; i <= high; i++) {
                sum += array[i];
            }
//            System.out.println("Direct(" + low + ", " + high + ") result="+ sum);
        } else {
            // 1. 一个大任务分割成两个子任务
            int mid = (low + high) >>> 1;
            ForkJoinSample left = new ForkJoinSample(array, low, mid);
            ForkJoinSample right = new ForkJoinSample(array, mid + 1, high);

            // 2. 分别计算
            left.fork();
//            System.out.println("left-->(" + low + ", " + mid + ")");
            right.fork();
//            System.out.println("right-->(" + (mid+1) + ", " + high + ")");

            // 3. 合并结果
            sum = left.join() + right.join();
        }
        return sum;
    }

    public static void main(String[] args) throws ExecutionException,
            InterruptedException {
        long[] array = genArray(123_456_000);
//        for (int i = 0; i < array.length; ++i) {
//            System.out.println("index=" + i + " value=" + array[i]);
//        }
        // 顺序运算用于对比
        long begin = System.currentTimeMillis();
        long sum = 0L;
        for (int i = 0; i < array.length; ++i) {
            sum += array[i];
        }
        long end = System.currentTimeMillis();
        System.out.println(String.format("结果 %s 耗时 %sms", sum, end - begin));

        // Fork-join 运算用于对比
        begin = System.currentTimeMillis();
        // 1. 创建任务
        ForkJoinSample sumTask = new ForkJoinSample(array, 0, array.length - 1);

        // 2. 创建线程池
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        // 3. 提交任务到线程池
        forkJoinPool.submit(sumTask);
        // 4. 获取结果
        Integer result = sumTask.get();

        end = System.currentTimeMillis();
        System.out.println(String.format("结果 %s 耗时 %sms", result, end - begin));
    }

    private static long[] genArray(int size) {
        long[] array = new long[size];
        for (int i = 0; i < size; i++) {
            array[i] = new Random().nextInt() / 100000L;
//            array[i] = i;
        }
        return array;
    }
}
