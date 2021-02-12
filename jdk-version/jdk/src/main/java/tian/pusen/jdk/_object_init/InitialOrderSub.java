package tian.pusen.jdk._object_init;

/**
 * 类成员初始化顺序： 
父类--静态变量
父类--静态初始化块
子类--静态变量
子类--静态初始化块
子类main方法
父类--变量
父类--初始化块
父类--构造器
子类--变量
子类--初始化块
子类--构造器

 * @author pustian
 *
 */
public class InitialOrderSub extends InitialOrder {
	int iSub = 5;
	static int staticISub  = 9;
	static String staticStrSub = "static string";
	String varStrSub = "var string";
	
	static {
		System.out.println("sub static block");
	}
	
	{
		System.out.println("sub block");
	}
	
	public InitialOrderSub() {
		System.out.println("InitialOrderSub contructor");
	}
	
	public InitialOrderSub(boolean flag) {
		System.out.println("InitialOrderSub contructor");
		iSub = -55;
		staticISub = -99;
		staticStrSub = "sub static string constructor";
		varStrSub = "sub var string constructor";
	}
	

	public void printObject(){
		System.out.println("printObject");
		System.out.println(toString());
	}
	
	public String toString() {
		
		String str = "[staticISub:"+staticISub+"]\t[staticStrSub:"+staticStrSub+"]\n"
					+"[iSub:"+iSub+"]\t[varStrSub:"+varStrSub +"]\n";
		return super.toString()+str;
	}
	
	 public static void main(String[] args) {
		  System.out.println("子类main方法");
		  new InitialOrderSub();
		  InitialOrder initialOrderSub = new InitialOrderSub();
		  System.out.println("=-----------------------------------------=");
		  initialOrderSub.printObject();
	 }
}
