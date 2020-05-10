## jdk7



## jdk8

### default关键字

> 在java里面，我们通常都是认为接口里面是只能有抽象方法，不能有任何方法的实现的。
>
> 在jdk1.8里面打破了这个规定，引入了新的关键字default，通过使用default修饰方法，可以让我们在接口里面定义具体的方法实现。
>
> default方法是所有的实现类都不需要去实现的就可以直接调用

### FQA:

#### A:

```bash
Idea Lambda expressions are not supported at language level '5' 
```

#### Q:

出现该错误的原因是我的maven没有指定jdk版本，在pom.xml中增加以下内容

```xml
    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <configuration>
                    <source>8</source>
                    <target>8</target>
                </configuration>
            </plugin>
        </plugins>
    </build>

```

### lambda表达式

> Lambda表达式是jdk1.8里面的一个重要的更新，这意味着java也开始承认了函数式编程，并且尝试引入其中。
>
> + 简单的来说就是，函数也是一等公民了，在java里面一等公民有变量，对象，那么函数式编程语言里面函数也可以跟变量，对象一样使用了，也就是说函数既可以作为参数，也可以作为返回值了

lambda表达式的一个最直观的作用就是大大的简化了代码的开发

+ 替代匿名内部类

#### Lmabda表达式的语法总结： () -> ();

| 前置                                       | 语法                                                 |
| ------------------------------------------ | ---------------------------------------------------- |
| 无参数无返回值                             | () -> System.out.println(“Hello WOrld”)              |
| 有一个参数无返回值                         | (x) -> System.out.println(x)                         |
| 有且只有一个参数无返回值                   | x -> System.out.println(x)                           |
| 有多个参数，有返回值，有多条lambda体语句   | (x，y) -> {System.out.println(“xxx”);return xxxx;}； |
| 有多个参数，有返回值，只有一条lambda体语句 | (x，y) -> xxxx                                       |
|                                            |                                                      |

口诀：左右遇一省括号，左侧推断类型省

> 注：当一个接口中存在多个抽象方法时，如果使用lambda表达式，并不能智能匹配对应的抽象方法，因此引入了函数式接口的概念

### 双冒号运算符

> 双冒号运算就是Java中的[方法引用],[方法引用]的格式是
> 类名::方法名
> 注意是方法名哦，后面没有括号“()”哒。
>
> + 因为这样的是式子并不代表一定会调用这个方法。
> + 这种式子一般是用作Lambda表达式，Lambda有所谓懒加载嘛，不要括号就是说，看情况调用方法

### 函数式接口
>  函数式接口的提出是为了给Lambda表达式的使用提供更好的支持。

> “函数式接口”是指仅仅只包含一个抽象方法的接口，每一个该类型的lambda表达式都会被匹配到这个抽象方法。
>
> jdk1.8提供了一个@FunctionalInterface注解来定义函数式接口，如果我们定义的接口不符合函数式的规范便会报错。

#### 常见的四大函数式接口:
> Consumer <T>	消费型接口，有参无返回值
> Supplier <T>		供给型接口，无参有返回值
> Function <T, R>	函数式接口，有参有返回值
> Predicate<T>		断言型接口，有参有返回值，返回值是boolean类型

### 局部变量限制
> Lambda表达式也允许使用自由变量（不是参数，而是在外层作用域中定义的变量），就像匿名类一样。 它们被称作捕获Lambda。
>
> Lambda可以没有限制地捕获（也就是在其主体中引用）实例变量和静态变量。但局部变量必须显式声明为final，或事实上是final。
>
> + 实例变量和局部变量背后的实现有一个关键不同。实例变量都存储在堆中，而局部变量则保存在栈上。如果Lambda可以直接访问局部变量，而且Lambda是在一个线程中使用的，则使用Lambda的线程，可能会在分配该变量的线程将这个变量收回之后，去访问该变量。因此， Java在访问自由局部变量时，实际上是在访问它的副本，而不是访问原始变量。
> + 这一限制不鼓励你使用改变外部变量的典型命令式编程模式。

### Date Api更新
> jdk启动时时区的设置
> | 方法                  | 说明                                                         |
> | --------------------- | ------------------------------------------------------------ |
> | TimeZone.setDefault   | 通过 java.utils.TimeZone 进行动态绑定                        |
> | user.timezone传递方法 | 运行时通过启动参数设置 java -Duser.timezone=Asia/Shanghai XXXMain |
> | TZ环境变量方式        | export TZ设置 export TZ=Asia/Shanghai                        |

> 1.8之前JDK自带的日期处理类非常不方便，我们处理的时候经常是使用的第三方工具包，比如commons-lang包等

java.time包下
#### LocalDate/LocalTime/LocalDateTime
> 新的日期API都是不可变的，更使用于多线程的使用环境中

+ LocalDate为日期处理类
+ LocalTime为时间处理类
+ LocalDateTime为日期时间处理类

> Clock 时钟 
>
> + Clock类提供了访问当前日期和时间的方法，Clock是时区敏感的，可以用来取代 System.currentTimeMillis() 来获取当前的微秒数。
>
> Timezones 时区
>
> + 在Instant时间点对象到本地日期对象之间转换的时候是极其重要的
>
> LocalTime 本地时间
>
> + LocalTime 定义了一个没有时区信息的时间
>
> LocalDate 本地日期
>
> + LocalDate 表示了一个确切的日期。用起来和LocalTime基本一致。
>
> LocalDateTime 本地日期时间
>
> + LocalDateTime 同时表示了时间和日期，相当于前两节内容合并到一个对象上了
>
> DateTimeFormatter
>
> + 新版的DateTimeFormatter是不可变的，所以它是线程安全的。

#### TemporalAdjusters 
日期调整时非常有用，比如得到当月的第一天、最后一天，当年的第一天、最后一天，下一周或前一周的某天等
#### DateTimeFormatter

以前日期格式化一般用SimpleDateFormat类，但是不怎么好用，现在1.8引入了DateTimeFormatter类，默认定义了很多常量格式（ISO打头的）

### Stream 流

> 流是Java API的新成员，它允许我们以声明性方式处理数据集合（通过查询语句来表达，而不是临时编写一个实现）。
>
> 就现在来说，我们可以把它们看成遍历数据集的高级迭代器。此外，流还可以透明地并行处理，也就是说我们不用写多线程代码了

Stream 不是集合元素，它不是数据结构并不保存数据，它是有关算法和计算的，它更像一个高级版本的 Iterator。 Stream 的创建需要指定一个数据源，比如 java.util.Collection的子类，List或者Set， Map不支持。

> Stream 可以并行化操作，迭代器只能命令式地、串行化操作。
>
> 使用并行去遍历时，数据会被分成多个段，其中每一个都在不同的线程中处理，然后将结果一起输出。Stream 的并行操作依赖于 Java7 中引入的 Fork/Join 框架（JSR166y）来拆分任务和加速处理过程。
>
> Stream api中声明可以通过parallel()与sequential()方法在并行流和串行流之间进行切换。

+ 流的操作类型分为两种：

#### Intermediate

​	一个流可以后面跟随零个或多个 intermediate 操作。

​	其目的主要是打开流，做出某种程度的数据映射/过滤，然后返回一个新的流，交给下一个操作使用。这类操作都是惰性化的（lazy），就是说，仅仅调用到这类方法，并没有真正开始流的遍历。

#### Terminal

​	一个流只能有一个 terminal 操作，当这个操作执行后，流就被使用“光”了，无法再被操作。所以这必定是流的最后一个操作。Terminal 操作的执行，才会真正开始流的遍历，并且会生成一个结果，或者一个 side effect。

> + 时间复杂度
>   + 对于一个 Stream 进行多次转换操作 (Intermediate 操作)，每次都对 Stream 的每个元素进行转换，而且是执行多次，这样时间复杂度就是 N（转换次数）个 for 循环里把所有操作都做掉的总和吗？其实不是这样的，转换操作都是 lazy 的，多个转换操作只会在 Terminal 操作的时候融合起来，一次循环完成。
>   + 我们可以这样简单的理解，Stream 里有个操作函数的集合，每次转换操作就是把转换函数放入这个集合中，在 Terminal 操作的时候循环 Stream 对应的集合，然后对每个元素执行所有的函数。

### Optional 接口

> 用来防止NullPointerException异常的辅助类型
>

JVM的 元数据