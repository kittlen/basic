package 排序;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListSortCustom {

	public static void main(String[] args) {
		Map<String, Object> map = null;
		List<Map<String, Object>> accountList = new ArrayList<>();
		String[] regulation = {"诸葛亮","鲁班","貂蝉","吕布"};
		final List<String> regulationOrder = Arrays.asList(regulation);
		String[] ordered = {"貂蝉","诸葛亮","吕布","貂蝉","鲁班","诸葛亮","貂蝉","鲁班","诸葛亮"};
		for(int i=0;i<9;i++) {
			map = new HashMap<>();
			map.put("name",ordered[i]);
			accountList.add(map);
		}
		sort(accountList, "name", regulation);
//		Collections.sort(accountList, new Comparator<Object>()
//		{
//			public int compare(Object obj0, Object obj1) {
//				Map<String, String> map0 = (Map) obj0;
//				Map<String, String> map1 = (Map) obj1;
//				int io0=regulationOrder.indexOf(map0.get("name"));
//				int io1=regulationOrder.indexOf(map1.get("name"));
//				return io0-io1;
//			}
//		});
		for (int i = 0; i < accountList.size(); i++) {
			Map mapInfo = accountList.get(i);
			System.out.println(mapInfo.get("name"));
		}
	}
	static void sort(List<Map<String, Object>> data,final String key,final String[] Basis) {
		List<String> regulationOrder = Arrays.asList(Basis);
		Collections.sort(data, new Comparator<Object>()
		{
			public int compare(Object obj0, Object obj1) {
				Map<String, String> map0 = (Map) obj0;
				Map<String, String> map1 = (Map) obj1;
				int io0=regulationOrder.indexOf(map0.get(key));
				int io1=regulationOrder.indexOf(map1.get(key));
				return io0-io1;
			}
		});
	}
}
