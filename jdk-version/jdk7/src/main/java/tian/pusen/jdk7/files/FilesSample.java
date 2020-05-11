/**
 * 
 */
package tian.pusen.jdk7.files;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

/**
 * <p> Title:FilesUtils </p>
 * <p> Description:       </p>
 * <p> Company: Masapay   </p>
 * @author: tianpusen
 * @Date  : 2016年11月10日 下午1:19:23
 */
public final class FilesSample {
	public void manipulateFiles() throws IOException {
        List<String> content = new ArrayList<String>();
        content.add("Hello");
        content.add("World");

	    Path newFile = Files.createFile(Paths.get("testFilesUtils.txt").toAbsolutePath());
        Path newFile2 = Files.write(newFile, content, Charset.forName("UTF-8"));
        System.out.println("newFile == newFile2 " + (newFile == newFile2 ) );

        long size = Files.size(newFile2);
        System.out.println("size = " +  size);

        byte[] bytes = Files.readAllBytes(newFile);
        System.out.println(new String(bytes));

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        Files.copy(newFile, output);
        Files.delete(newFile);
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        FilesSample fu = new FilesSample();
        fu.manipulateFiles();
    }
}
