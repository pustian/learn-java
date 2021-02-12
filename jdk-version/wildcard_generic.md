java 泛型中 T 和 问号（通配符）的区别
类型本来有：简单类型和复杂类型，引入泛型后把复杂类型分的更细了；
    现在List, List 是 两种不同的类型;且 无继承关系；
    泛型的好处如：
    开始版本
	    public void write(Integer i, Integer[] ia);
    	public void write(Double  d, Double[] da);
    泛型版本
	    public <T> void write(T t, T[] ta);
    简便了代码

定义泛型
	1.定义在类后面,紧跟类名后面
        public class TestClassDefine<T, S extends T>{}
        定义泛型 T, S, 且S 继承 T
    2.定义在方法装饰符后面,紧跟修饰符后面（public）
        public <T, S extends T> T testGenericMethodDefine(T t, S s){}
        定义泛型 T, S, 且S 继承 T

实例化泛型
	1.实例化定义在类上的泛型
	    第一声明类变量或者实例化时。例如
        List<String> list = new ArrayList<String>;
		第二继承类或者实现接口时。例如
    	public class MyList<E> extends ArrayList<E> implements List<E> {...} 
    2.实例化定义方法上的泛型
    	当调用范型方法时，编译器自动对类型参数(泛型)进行赋值，当不能成功赋值时报编译错误

通配符(?)
    上面有泛型的定义和赋值；当在赋值的时候，上面一节说赋值的都是为具体类型，当赋值的类型不确定的时候，我们用通配符(?)代替了：
    如
     List<?> unknownList;
     List<? extends Number> unknownNumberList;
     List<? super Integer> unknownBaseLineIntgerList; 



泛型实在编译节点编辑器匹配类型使用。再运行阶段，可用通过反射存储类型不一致的数据。