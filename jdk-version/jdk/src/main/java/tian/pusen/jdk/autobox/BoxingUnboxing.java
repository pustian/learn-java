package tian.pusen.jdk.autobox;

//Note:
//    Integer、Short、Byte、Character、Long这几个包装类的valueOf方法的实现是类似的；
//    Double、Float的valueOf方法的实现是类似的。
//    Boolean的valueOf方法的实现是个三目运算，形如`  return (b ? TRUE : FALSE); 
public class BoxingUnboxing {
	//从源码中可以看出，Integer对象自动缓存int值范围在low~high（-128~127），
	//  如果超出这个范围则会自动装箱为包装类。
	public static void main(String[] args) {
		Integer i0 = 64;
		Integer i1 = 64;
		int     i2 = 64;
		
		Integer i00 = 164;
		Integer i01 = 164;
		int     i02 = 164;
		
		System.out.println("--------Boxing--------");
		System.out.println(i0 == i1); 
		// Integer.ValueOf
		System.out.println(i00 == i01);    // 当包装器类型进行“==”比较时，i00会调用Integer.valueOf自动装箱基本数据类型为包装器类型。
		System.out.println(i0.equals(i2)); // 在Integer包装类实现的equals方法中，只要比较的当前对象是Integer实例，那么就会自动拆箱为基本数据类型
		
		System.out.println("--------UnBoxing--------");
		System.out.println(i0 == i2);   
		// Integer.intValue
		System.out.println(i00 == i02);    // 当包装器类型和基本数据类型进行“==”比较时，包装器类型会自动拆箱为基本数据类型。
		System.out.println(i00.equals(i02)); // 在Integer包装类实现的equals方法中，只要比较的当前对象是Integer实例，那么就会自动拆箱为基本数据类型
		
		System.out.println("The parameter type of Integer's constructor  is string--");
		Integer si0= new Integer("17");
		Integer si00= new Integer("164");
		System.out.println(si0.equals(i0));
		System.out.println(si00.equals(i00));
		System.out.println(si0 == i0);
		System.out.println(si00 == i00);
		
//		// mathmatic
//		Long g = 3L;
//		Long h = 2L;
	}
}
