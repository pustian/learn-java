package tian.pusen.jdk._annotation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class AnnotationDemo {
	private JFrame mainWin = new JFrame("使用注释绑定事件监听器");
	// 使用注释 通过注释反射实现的不符合注释的初衷不影响代码执行
	@ActionListenerFor(listener = OKListener.class)
	private JButton ok = new JButton("确认");
	// 未使用注释
	private JButton cancel = new JButton("取消");

	public void init() {
		JPanel jp = new JPanel();
		jp.add(ok);
		// 为使用的监听使用
		cancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "单击取消按钮 once");
			}
		});
		jp.add(cancel);
		mainWin.add(jp);
		ActionListenrerInstaller.processAnnotations(this);
		mainWin.pack();
		mainWin.setVisible(true);
		mainWin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		new AnnotationDemo().init();
	}
}

class OKListener implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent e) {
		JOptionPane.showMessageDialog(null, "click  确认按钮 once");
	}
}

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@interface ActionListenerFor {
	// 定义一个成员变量，用于设置元数据 listener用于保存监听器实现类
	Class<? extends ActionListener> listener();
}
class ActionListenrerInstaller {
	public static void processAnnotations(Object obj) {
		try {
			// 获取obj对象的类
			Class cl = obj.getClass();
			// 获取obj对象的成员变量
			for (Field f : cl.getDeclaredFields()) {
				f.setAccessible(true);
				ActionListenerFor a = f.getAnnotation(ActionListenerFor.class);
				Object fobj = f.get(obj); // 获取成员变量f的值
				if (a != null && fobj != null && fobj instanceof AbstractButton) {
					Class<? extends ActionListener> listenerClazz = a
							.listener();
					// 使用反射创建listenre类的对象
					ActionListener al = listenerClazz.newInstance();
					AbstractButton ab = (AbstractButton) fobj;
					// 为 ab 按钮 添加事件监听器
					ab.addActionListener(al);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}