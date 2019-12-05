package NumberUtil;


import java.util.Scanner;

public class 等差数列 {
	public static void main(String[] args) {
		Scanner in =new Scanner(System.in);
		int len=in.nextInt();
		int[] cont=new int[len];
		for(int i=0;i<len;i++) {
			cont[i]=in.nextInt();
		}
		int s=cont[0],a = 0,b;
		for (int i = 0; i < cont.length-1; i++) {
			a=s;
			b=cont[i+1];
			s=gys(a,b);
		}
		for(int i=0;i<len;i++) {
			for(int j=0;j<len-1-i;j++) {
				if(cont[j]>cont[j+1]) {
					int max=cont[j];
					cont[j]=cont[j+1];
					cont[j+1]=max;
				}
			}
		}
		int max=cont[len-1];
		int min=cont[0];
		int n=((max-min)/s)+1;
		for(int i=0;i<n;i++) {
			System.out.print(min+i*s+" ");
		}
		System.out.println();
		System.out.println(n);
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

