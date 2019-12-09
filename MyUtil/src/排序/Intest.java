package 排序;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Intest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		fileOutString(new File("G:\\test.txt"));
		fileInString(new File("G:\\test.txt"));
		int q = 0;
		int w = 0;
		for (int i = 2; i < 5; i++) {
			boolean f = true;
			for (int t = 2; t < i - 1; t++) {
				if (i % t == 0) {
					f = false;
					break;
				}
			}
			if (f) {
				System.out.print(i + "  ");
				q += i / 10;
				w += i % 10;
			}
		}
		System.out.println();
		System.out.println(q);
		System.out.println(w);
		
	}
	public static void fileOutString(File file) {
		try {
			BufferedReader bReader=new BufferedReader(new FileReader(file));
			String line;
			while ((line=bReader.readLine())!=null) {
				System.out.println(line);
			}
			bReader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void fileInString(File file) {
		try {
			//true 代表添加 空或false代表覆盖
			FileWriter fileWriter=new FileWriter(file,true);
			String line="������ӵ����";
			fileWriter.write((line));
			fileWriter.flush();
			fileWriter.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
