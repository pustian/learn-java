Frame 中 组件添加方式发生了变化
v5以前
```java
        Container contentPane = getContentPane();
        Component component = new NotHelloWorldComponent();
        contentPane.add(component);
```
Q:
JComponent 中 getPreferredSize 方法大小
size需要重新设置

v5以后
```java
        add(new NotHelloWorldComponent());
        pack();
```

JPanel/ JComponent
> JPanel为 JComponent 子类
> JPanel 可以包含其他组件的容器，同样可以在其上面绘制
> JPanel 不透明，意味着需要在画板的边界内绘制所有的像素。
>   一般实现实在paintComponent 方法调用 super.paintComponent 绘制背景画板

窗口
框架 JFrame
    --> 跟窗格
        ---> 层级窗格
            ---> 菜单栏(可选)
                ---> 内容窗格
                    ---> 玻璃窗格