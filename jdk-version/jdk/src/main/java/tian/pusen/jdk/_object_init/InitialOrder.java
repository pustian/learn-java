package tian.pusen.jdk._object_init;

/**
 * 类成员初始化顺序： 
1. 静态变量
2. 静态初始化块
3. 变量
4. 初始化块
5. 构造器

 * @author pustian
 *
 */
public class InitialOrder {
	int i = 5;
	static int staticI  = 9;
	static String staticStr = "static string";
	String varStr = "var string";
	
	static {
		System.out.println("static block");
	}
	
	{
		System.out.println("block");
	}
	
	public InitialOrder() {
		System.out.println("InitialOrder contructor");
	}
	
	public InitialOrder(boolean flag) {
		System.out.println("InitialOrder contructor");
		i = 55;
		staticI = 99;
		staticStr = "static string constructor";
		varStr = "var string constructor";
	}
	
	public static void staticPrintObject(){
		System.out.println("staticPrintObject");
		System.out.println("staticI:"+staticI+" staticStr:"+staticStr);
	}

	public void printObject(){
		System.out.println("printObject");
		System.out.println(toString());
	}
	
	public String toString() {
		String str = "[staticI:"+staticI+"]\t[staticStr:"+staticStr+"]\n"
					+"[i:"+i+"]\t[varStr:"+varStr +"]\n";
		return str;
	}
	
}
