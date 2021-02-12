# 线程状态

## new 新建

> 创建线程 new Thread()， 但是没有运行
>
> 此时系统需要做一些基础工作

## runnable

> 调用start方法，线程处于runnable状态。
>
> 线程实际上可能在运行，也可能没有运行。
>
> 此处线程调度方法依赖操作系统。

## blocked

> 当线程获取内部对象锁，但是锁被其他线程持有，此线程进入阻塞状态。
>
> 当所有其他线程释放锁，线程调度器允许本线程持有它的时候，该线程变为非阻塞状态。

## waiting

> 线程等待另外一个线程通知调度器一个条件时，他自己进入等待状态。
>
> concorrent库中的Lock，Condition, Object.wait 或是Thread.join

## timed waiting

> 有几个带超时参数方法，调用他们导致线程进入计时等待状态。
>
> 保持到超时期满或是收到适当的通知。 
>
> 带超时参数的方法 Thread.sleep， Object.wait, Thread.join, Lock.tryLock, Condition.await的计时版

## terminal

> run方法正常退出，自然死亡
>
> 未捕获的异常导致的run方法意外死亡。  stop方法杀死一个线程。

线程状态。

new --->                      runnable                                     ----> terminal

​                    |锁|           |通知|          |超时或通知|

​                  被阻塞          等待               计时等待

# 线程属性

## 优先级priority

> 默认继承父线程的优先级
>
> setPriority  [MIN_PRIORITY, MAX_PRIORITY] [1,10]  默认NORM_PRIORITY=5
>
> 调度器优先选较高优先级的线程，但是线程优先级`高度依赖系统`

## 守护线程 daemon

> setDaemon(true) 这样的线程唯一用途是为其他线程提供服务。 例如计时器线程
>
> 当只剩下守护进程时，虚拟机就退出

## **未捕获异常处理器**

+ 关注

> Thread类中run方法不会抛出任何未检测的异常，但不被检测的异常会导致线程终止。
>
> UncaughtExceptionHandler

# 同步

> ```
> 存在成员变量的类用于多线程时是不安全的，不安全体现在这个成员变量可能发生非原子性的操作，而变量定义在方法内也就是局部变量是线程安全的。
> ```

## 竞争

## 原子操作

## 锁对象

### 可重入锁

防止代码块受并发干扰 Back/transfer

ReentrantLock() 

+ lock()
+ unlock()

> + 可重入锁
> + 公平锁

### 条件对象/条件变量

进入临界区，但是需要满足一定条件才能执行。使用条件对象/变量管理获得一个锁但是缺不能做有用工作的线程。

Condition ReentrantLock().newCondition() 一个与锁相关的条件对象

> + await 该线程到等待集合中
> + singal 该条件的等待集合中随机选择一个线程，解除阻塞状态
> + singalAll 该条件的集合中所有的线程的阻塞状态

## Synchronized

wait 添加一个线程到等待集合中    类似condition中await

notify/notifyAll 解除等待线程的阻塞状态 类似condition中signal/singalAll

+ notify()
+ notifyAll()
+ wait()
+ wait(long millis)

## 监视器概念

不加锁，保证监视器安全。最成功的方法是`监视器`

+ 只包含私有域的类
+ 每个监视器类对象有一个相关锁
+ 该锁对所有的方法进行加锁
+ 该锁可以有任意多个相关条件

Java每一个对象有一个内部锁和内部条件，如果一个方法用 `synchronized` 关键字声明,他表现得就像是一个监视器方法。 通过wait/notify/notifyAll 来访问条件变量。

## volatile域

声明为 `volatile` 的域，编译器和虚拟机就知道该域可能被另外一个线程并发更新。

> volatile不具备互斥性(当一个线程持有锁时，其他线程进不来，这就是互斥性)。
> volatile不具备原子性。

## final域

构造函数完成后其他线程看到这个域值。



## 原子性

java.util.concurrent.atomic



## 死锁

java对死锁没有解决办法，如果出现死锁，则程序会一直挂起。

+ 所以使用锁时，最好锁的时候，增加以下时间参数。



## 局部变量/ThreadLocal

> get
>
> set
>
> remove

## 锁测试和超时 --- 避免死锁

Lock

> + tryLock（）
> + lockInterruptibly()

Codition

> await
>
> awaitUninterruptibly

## 读写锁 ReentrantReadWriteLock

readLock

> 共享锁  允许读线程共享访问。

writeLock

> 排他锁 写线程是互斥访问。

## 阻塞队列

使用队列可以安全的从一个线程向另外一个线程传递数据。

两组队列配合使用 put/take

`BlockingQueueTest.java`

```java
1, 如果只保留 emuerator 线程。 进程阻塞
2, emuerator 线程，放在search线程后启动，也能正常工作。
```

> 线程安全的队列实现者考虑 锁和条件。

BlockingQueue

LinkedBlockingQueue 

LinkedBlockingDeque

ArrayBlockingQueue

PriorityBlockingQueue

DelayQueue

TransferQueue/LinkedTransferQueue  `1.7版本` 生产者等待

## 线程安全的集合

### Map, Set, Queue

ConcurrentHashMap

ConcurrentSkipListMap

ConcurrentSkipListSet

ConcurrentLinkedQueue

### 写数组的复制

CopyOnWriteArrayList

CopyOnWriteArraySet

### 早期线程安全集合

List<E> synchArrayList = Collections.synchronizedList(new ArrayList<E>() )

Map<K, V> synchArrayList = Collections.synchronizedMap(new HashMap<K, V>() )

在经常修改的数组列表， Collections.synchronizedList(new ArrayList<E>() ) 效率会高过 CopyOnWriteArrayList，

+ Collections.synchronizedCollection

+ Collections.synchronizedList
+ Collections.synchronizedSet
+ Collections.synchronizedSortedSet
+ Collections.synchronizedMap
+ Collections.synchronizedSortedMap

## Callable-Future

Callable与Runnable类似但是有返回值

Future 保存异步计算结果

## 执行器

构建线程牵扯到操作系统开销，频繁创建结束线程，应该使用线程池

线程池中包含许多准备运行的空闲线程。

将Runnbale对象交给线程池，就会有一个线程调用run方法。run方法退出时，线程池中准备为下一个请求提供服务。

#### ExecutorService

| Executors. 方法                  | 描述                                                | 适用场景                                                     |
| -------------------------------- | --------------------------------------------------- | ------------------------------------------------------------ |
| newCachedThreadPool              | 必要时创建新线程，空闲线程会被保留60s               | 快速处理大量耗时较短的任务，如Netty的NIO接受请求时，可使用CachedThreadPool |
| newFixedThreadPool               | 固定数量的线程，空闲时一直被保留                    | 可用于Web服务瞬时削峰，但需注意长时间持续高峰情况造成的队列阻塞。    提交任务多余空闲线程，把任务放到队列中。 |
| newSingleThreadPool              | 只有一个线程的`池`，顺序执行每个提交任务            | 退化为线程数为1的线程池                                      |
| newScheduledThreadPool           | 用于预定执行而构建的固定线程池，代替java.util.Timer |                                                              |
| newSingleThreadScheduledExecutor | 用于预定执行而构建的单线程`池`                      |                                                              |

+ 其实都是调用 ThreadPoolExecutor 来实现的



## 线程池

ExcutorService.ThreadPoolExecutor 

#### 提交Runnable/Callable对象给ExecutorService

> Future<?> submit(Runnable task)
>
> Future<T> submit(Runnable task, T result)
>
> Future<T> submit(Callable<T> task)

#### 关闭线程池

shutdown启动线程池的关闭序列。 被关闭的线程池不再接收新任务。所有任务结束，线程池中线程死亡。

shutdownNow 线程池 取消尚未开始的所有任务，并实施图中断正在运行的线程

##### 线程池使用顺序

1. 调用静态方法 newCachedThreadPool newSingleThreadPool 或是newCachedThreadPool
2. 调用submit 提交Runnable或是Callable对象
3. 取消任务或是提交Callable对象，保存好返回的Future对象
4. 不在提交任何任务时，调用shutdown

### 预定执行

> newScheduledThreadPool
>
> newSingleThreadScheduledExecutor

### 控制任务组

ExecutorService

> invokeAll
>
> invokeAny

ExecutorCompletionService

> submit
>
> take
>
> poll

### Fork-join



# 同步器

# Q:

## ConcurrentHashMap 源码

## 线程池调度原理

