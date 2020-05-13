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