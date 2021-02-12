### 数学函数

#### Math

#### StrictMath

#### strictfp 关键字  

> 完全可预测的结果比运行速度更重要的话，StrictMath 库，strictfp关键字

switch 方法 在编译时可以考虑

```bash
javac -Xlint:fallthrough XXXX.java
在分支缺少break时，编译器会发出警告消息。

XXXX.java 代码中通过增加
@SuppressWarnings("fallthrough") //消除 -Xlint:fallthrough 产生的警告 
```

类似的 `-Xlint:uncheck` 在泛型中的检查

### clone方法

### 初始化顺序 ???

1. 静态域
2. 静态块
3. 域
4. 初始化
5. 构造器

### 阻止继承和动态绑定方法

final

反向代理？？？

equal hashcode一块重写的原因？？？



包装类做参数时，方法不会改变参数的值？？？ 

+ 包含在包装器内的内容不会改变。

* org.omg.CORBA 包中的IntHolder，BooleanHolder等可以修改值

  ```java
  public static void triple(Integer x) {
      x = x*3;
  } 
  // 运行完后 x值不变
  ```

  ```java
  public static void triple(IntHolder x) {
      x.value = x.value*3;
  } 
  // 运行完后 x.value 值改变
  ```

  