package NumberUtil;

/**
 * 求一组数的最小公约数
 */
import java.util.Scanner;

public class 最小公约数 {
	public static void main(String[] args) {
		Scanner in =new Scanner(System.in);
		int len=in.nextInt();
		int[] cont=new int[len];
		for(int i=0;i<len;i++) {
			cont[i]=in.nextInt();
		}
		int s=cont[0],a,b;
		for (int i = 0; i < cont.length-1; i++) {
			a=s;
			b=cont[i+1];
			s=gys(a,b);
		}
		System.out.println(s);
		in.close();
	}

	private static int gys(int a, int b) {
		// TODO Auto-generated method stub
		if (b==0) {
			return a;
		}
		return gys(b, a%b);
	}

}