package 排序;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;


public class MapTest {

	public static void main(String[] args) {
		List<String> list  =   new  ArrayList<String>(); 
        list.add("aaa");
        list.add("bbb");
        list.add("aaa");
        list.add("aba");
        list.add("aaa");

        List newList = new ArrayList(new HashSet(list)); 

        System.out.println( "ȥ�غ�ļ��ϣ� " + newList); 

	}
}
