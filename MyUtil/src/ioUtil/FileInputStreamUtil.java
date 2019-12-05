package ioUtil;
import java.io.FileInputStream;
import java.io.IOException;
 
/**
 	字节流,读取 a.txt 文件内容,并打印出来
 */
public class FileInputStreamUtil {
	public static void main(String[] args) {
 
		try {
			FileInputStream input = new FileInputStream("C:/Users/Administrator/Desktop/webTest/fy.js");
			// read() 从此输入流中读取一个数据字节。
			// read(byte[] b) 从此输入流中将最多 b.length 个字节的数据读入一个 byte 数组中。
			byte[] b = new byte[input.available()];
			input.read(b);
			// 打印
			String str=new String(b);
			System.out.println(str);
			input.close();
 
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}