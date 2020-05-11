package tian.pusen.jdk7.exception;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileExceptionTest {
    public static void main(String[] args) throws Exception {
        readFile("pom.xml");
    }

    public static void readFile(String filename) throws Exception {
        FileInputStream input = null;
        FileInputStream fis2 = null;
        Exception exception = null;
        try {
            input = new FileInputStream(filename);
            fis2 = new FileInputStream("helloworld");
        } catch (IOException ex) {
            exception = ex;
        } finally {
            if (input != null) {
                try {
                    input.close();
                    int i = 10 / 0;    // runtime 在Jdk7以前会被抑制掉
                } catch (IOException | RuntimeException ex) {   // JDK7写法
                    if (exception != null) {    //此处的区别 。// runtime 在Jdk7以前会被抑制掉
                        exception.addSuppressed(ex);
                    } else {
                        exception = ex;
                    }
                }
            }
            if (exception != null) {
                System.out.println(exception);
                throw exception;
            }
            if(fis2 != null) {
                fis2.close();
            }
        }
    }
}
