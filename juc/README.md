# JUC介绍

JUC就是java.util .concurrent工具包的简称。这是一个处理线程的工具包，JDK 1.5开始出现的。

## 内存可见性

> 内存可见性问题：当多个线程操作共享数据时，彼此不可见。

### valatile

> 复制到工作区的值是当前的值（可能已修改），有其他线程修改了堆中的值不会反馈到线程中。
>
> valatile确保修改的值及时同步到多个线程中。

示例见 `Memvisiable.java`

```bash
//     public boolean flag = false;
主存： 存放共享数据
线程1： 从主存中读取数据，然后修改数据
线程2： 在线程1修改数据前，从主存中读取数据
```

```bash
//     public volatile boolean flag = false;
主存： 存放共享数据
线程1： 从主存中读取数据，然后修改数据
线程2： 在线程1修改数据前，从主存中读取数据    --- volatile 会将修改的flag同步到其他线程中
```

### volatile和synchronized的区别：

> volatile不具备互斥性(当一个线程持有锁时，其他线程进不来，这就是互斥性)。
> volatile不具备原子性。

## 多线程jvm调用模型

tian.pusen.juc._01

Customer0 --- 多线程

```bash
|Customer i    countIncrease       | 堆区  --> 线程共享区
|------|------|------|------|------| 虚拟机多线程栈
```

Customer1--- 多线程

```bash
|Customer  i    countIncrease      | 方法区 --> 线程共享区
|Customer                          | 堆区   --> 线程共享区
|------|------|------|------|------| 虚拟机多线程栈
```

Customer01---被多线程调用

```
|线程1---| 线程2---|------|------|------| 虚拟机多线程栈
线程1 |Customer i    countIncrease       | 堆区1  --> 线程共享区
线程2 |Customer i    countIncrease       | 堆区2  --> 线程共享区
```

Customer11---被多线程调用

```bash
|线程1---| 线程2---|------|------|------| 多线程
线程1 |Customer  i    countIncrease      | 方法区 --> 线程共享区
线程2 |                                  | 方法区 --> 线程共享区
```

## java.util.concurrent.atomic

### 原子变量具备如下特点：

+ 有volatile保证内存可见性。

+ 用CAS算法保证原子性。

  > CAS算法是计算机硬件对并发操作共享数据的支持，CAS包含3个操作数：
  >
  > + 内存值V
  > + 预估值A
  > + 更新值B
  >
  > 当且仅当V==B时，才会把B的值赋给V，即V = B，否则不做任何操作。
  >
  > 上面的i++问题，CAS算法是这样处理的：
  >
  > ​	首先V是主存中的值0，然后预估值A也是0，因为此时还没有任何操作，这时V=B，所以进行自增，同时把主存中的值变为1。如果第二个线程读取到主存中的还是0也没关系，因为此时预估值已经变成1，V不等于B，所以不进行任何操作。

### 锁分段机制

###### ConcurrentHashMap

###### 对比HashTable



## synchronized

> jdk 1.5 以前版本
>
> - 同步代码块
> - 同步方法

## 创建线程的方式 --- 实现Callable接口

## 闭锁

## Lock同步锁