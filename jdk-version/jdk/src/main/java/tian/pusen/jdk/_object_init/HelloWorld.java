package tian.pusen.jdk._object_init;
/**
 * 
 * @Author pustian@msn.com
 * 
 */
public class HelloWorld {
  public static void main(String[] args) {
    System.out.println("Hello world");
    System.out.println("The command args length:"+ args.length);
    for(int i = 0; i< args.length; i++)
      System.out.println("args["+i+"]:"+args[i]);
  }
}
