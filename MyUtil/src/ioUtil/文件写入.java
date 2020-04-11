package ioUtil;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

/**
 * 以上代码就是把字符串"itobin"写入到d:\test.txt里的过程。
 * 
 * @author Administrator
 *
 */
public class 文件写入 {
	public static void main(String[] args) {
		write2file("itobinssddsss", "G:\\test.txt", true, true);
	}

	/**
	 * 
	 * @param cbuf //数据源
	 * @param fileStr //文件地址
	 * @param append //是否在原文件末端追加
	 * @param create //文件不存在时,是否创建文件
	 */
	public static void write2file(String cbuf, String fileStr, boolean append, boolean create) {
		File file = null;// 首先定义文件类
		OutputStream os = null;// 定义字节流
		OutputStreamWriter osw = null;// OutputStreamWriter是字节流通向字符流的桥梁。
		BufferedWriter bw = null;// 定义缓冲区

		try {
			file = new File(fileStr); // 新建文件对象
			if (create) {
				checkFileExists(file);
			}
			// 从文件系统中的某个文件中获取字节
			os = new FileOutputStream(file, append); // true是append设为允许，即可以在原文件末端追加。
			// 将字节流转换成字符流
			osw = new OutputStreamWriter(os);
			// 把接收到的字符流放入缓冲区，提高读写速度。
			bw = new BufferedWriter(osw);

			// 将字符串以流的形式写入文件
			bw.write(cbuf);
		} catch (FileNotFoundException e) {
			System.out.println("找不到指定文件");
		} catch (IOException e) {
			System.out.println("写入文件错误");
		} finally {
			try {
				// 关闭文件放到finally里，无论读取是否成功，都要把流关闭。
				// 关闭的顺序：最后开的先关闭，栈的先进后出原理。
				bw.close();
				osw.close();
				os.close();
			} catch (IOException e) {
				System.out.println("文件流无法关闭");
			}
		}
	}

	/**
	 * 判断文件是否存在
	 * 
	 * @param file
	 */
	public static void checkFileExists(File file) {
		if (!file.exists())
			System.out.println("待写入文件不存在, 创建文件成功");
		try {
			file.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
