package tian.pusen.jdk.enums;

public class Gender {
	public static void main(String[] args) {
		Gender0 g0 = Enum.valueOf(Gender0.class, "FEMALE");
		g0.name = "女";

		Gender1 g1 = Gender1.valueOf("FEMALE");
		g1.setName("女");

		Gender2 g2 = Gender2.FEMALE;

		Gender3 g3 = Gender3.FEMALE;

		System.out.println("" + g0 + "" + g1 + "" + g2);
	}
}

enum Gender0 {
	MALE, FEMALE;
	public String name;
}

enum Gender1 {
	MALE, FEMALE;
	private String name;

	public void setName(String name) {
		switch (this) {
		case MALE:
			// "男".equals(name)?this.name=name:System.out.println("参数错误");
			if ("男".equals(name))
				this.name = name;
			else
				System.out.println("参数错误");
			break;
		case FEMALE: // ？： 为什么不可以用
			if ("女".equals(name))
				this.name = name;
			else
				System.out.println("参数错误");
			break;
		}
	}

	public String getName() {
		return name;
	}
}

enum Gender2 {
	MALE("男"), FEMALE("女");
	private final String name;

	// 注意修饰符，必须为private或是集成使用绝不可以是public
	private Gender2(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}

interface Gender3Desc {
	void desc();
}

enum Gender3 implements Gender3Desc {
	MALE("男"), FEMALE("女");
	private final String name;

	private Gender3(String name) {
		this.name = name;
	}

	@Override
	public void desc() {
		// TODO Auto-generated method stub
		System.out.println("表示性别是" + name);
	}

}