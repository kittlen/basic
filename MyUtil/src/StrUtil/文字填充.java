package StrUtil;

public class 文字填充 {

	public static void main(String[] args) {
		
		String s=" s  ss sd sd ss   d   1 ";
		String s2="";
		int in=1;
		 s2=String.format("%04d",123);
		try {
			 s2=String.format("%05d","sds");
			in=Integer.parseInt(s);
			
		}catch (Exception e) {
			// TODO: handle exception
			in=Integer.parseInt(s2);
		}
		System.out.println(s2);
		System.out.println(s.replace(" ", ""));
		
	}

}
