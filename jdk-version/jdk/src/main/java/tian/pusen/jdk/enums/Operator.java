package tian.pusen.jdk.enums;

public class Operator {
	public static void main(String[] args) {
		Operator0 op = Operator0.PLUS;
		System.out.println(op.getOperator());
		System.out.println(Operation.PLUS.eval(1, 2));
	}
}

enum Operator0 {
	PLUS("+", "加法"), MINUS("-", "减法"), TIMES("*", "乘法"), DIVIDE("/", "除法");

	private final String operator;
	private final String desc;

	private Operator0(String operator, String desc) {
		this.operator = operator;
		this.desc = desc;
	}

	public String getOperator() {
		return operator;
	}

	public String getDesc() {
		return desc;
	}
}

enum Operation {

	PLUS {
		public double eval(double x, double y) {
			return x + y;
		}
	},
	MINUS {
		public double eval(double x, double y) {
			return x - y;
		}
	},
	TIMES {
		public double eval(double x, double y) {
			return x * y;
		}
	},
	DIVIDE {
		public double eval(double x, double y) {
			return x / y;
		}
	};
	public abstract double eval(double x, double y);
}