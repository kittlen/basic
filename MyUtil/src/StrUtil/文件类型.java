package StrUtil;

public class 文件类型 {
	public static void main(String[] args) {
		String string="123.123.txt";
		System.out.println(string.substring(string.lastIndexOf(".")+1));
	}

}
