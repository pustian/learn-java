package tian.pusen.jdk._object_init;

// 
public class CreateInnerInstance {
	public static void main(String[] args) {
		// Out.In 是Out对象下的一个类
		Out.In in = new Out("new Out").new In("new in");
		System.out.println("==================================");
		SubOutIn subOutIn = new SubOutIn(new Out("new out as Para"), "new in as para");
		
		System.out.println("==================================");
		// StaticOut.StaticIn 之相当于一个namespace下的类
		StaticOut.StaticIn staticIn = new StaticOut.StaticIn("new staticIn");
		
	}
}

///////////////////////////////////////////////////////
class Out{
	String outMsg;
	public Out(String outMsg){
		System.out.println(outMsg);
		System.out.println("---new In(非静态内部类).inMsg:"+new In("非静态内部类").inMsg+"---");
	}
	class In{
		String inMsg;
		public In(String inMsg) {
			System.out.println(inMsg);
		}
	}
}

class SubOutIn extends Out.In {
	public SubOutIn(Out out, String inMsg) {
		out.super(inMsg);
		System.out.println("sub Out.In field:OutIn.inMsg"+super.inMsg);
		System.out.println("SubOutIn itself constructor");
	}
}

//////////////////////////////////////////////////////////////
class StaticOut{
	public StaticOut(String outMsg){
		System.out.println(outMsg);
	}
	static class StaticIn{
		public StaticIn(String inMsg) {
			System.out.println(inMsg);
		}
	}
}