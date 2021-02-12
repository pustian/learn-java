package tian.pusen.jdk._object_init;

public class Test {
	public static void main(String[] args) {
		InitialOrderSub initialOrderSub;
		System.out.println("using defalt contructor");
		initialOrderSub = new InitialOrderSub();
		initialOrderSub.printObject();

		System.out.println("using undefalt contructor");
		initialOrderSub = new InitialOrderSub(true);
		initialOrderSub.printObject();
		
	}
}
