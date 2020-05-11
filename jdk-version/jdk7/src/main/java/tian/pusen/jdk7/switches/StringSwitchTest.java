package tian.pusen.jdk7.switches;

public class StringSwitchTest {
	//	①case只有一种情况，直接转成if； 
	//	②如果只有一个case和default，则直接转换为if...else...； 
	//	③有多个case，先将String转换为hashCode，然后对应的进行处理，JavaCode在底层兼容Java7以前版本。
	public static void main(String[] args) {
		String profile = "dev";
		switch (profile) {
			case "dev":
				System.out.println("develop env");
				break;
			case "test":
				System.out.println("test env");
				break;
			case "uat":
				System.out.println("uat env");
				break;
			case "prod":
				System.out.println("prod env");
				break;
			default:
				System.out.println("default env");
				break;
		}
	}
}
