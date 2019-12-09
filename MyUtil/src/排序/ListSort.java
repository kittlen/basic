package 排序;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListSort {

	public static void main(String[] args) {
		List<Map<String, Object>> accountList = new ArrayList<>();
		Map<String, Object> map = null;
		for (int i = 0; i < 5; i++) {
			map = new HashMap<>();
			map.put("accountNum", "王" + i);
			map.put("createTime", "2018-01-1" + i+" 12:21:12");
			accountList.add(map);
		}
		map.put("accountNum", "利");
		map.put("createTime", "2018-02-01"+" 12:21:12");
		accountList.add(map);
		map = new HashMap<>();
		map.put("accountNum", "张");
		map.put("createTime", "2018-02-11"+" 12:21:12");
		accountList.add(map);
		
		SortClass sort = new SortClass();
		SortChineseName scn=new SortChineseName();
		Collections.sort(accountList, sort);
		/*Collections.reverse(accountList);*//*使list 倒排序*/
		for (int i = 0; i < accountList.size(); i++) {
			Map mapInfo = accountList.get(i);
			System.out.println("账号:" + mapInfo.get("accountNum") + ", 创建日期:" + mapInfo.get("createTime"));
		}

	}

	/**
	 * 按时间排序
	 * @author Administrator
	 *
	 */
	static class SortClass implements Comparator {
		@Override
		public int compare(Object obj0, Object obj1) {
			Map<String, String> map0 = (Map) obj0;
			Map<String, String> map1 = (Map) obj1;
			int flag = map0.get("createTime").toString().compareTo(map1.get("createTime").toString());
			return -flag; // 不取反，则按正序排列
		}
	}
	
	/**
	 * 按中文排序
	 * @author Administrator
	 *
	 */
	static class SortChineseName implements Comparator {
		Collator cmp=Collator.getInstance(java.util.Locale.CHINA);
		@Override
		public int compare(Object obj0, Object obj1) {
			Map<String, String> map0 = (Map) obj0;
			Map<String, String> map1 = (Map) obj1;
			if(cmp.compare(map0.get("accountNum"), map1.get("accountNum"))>0) {
				return 1;
			}else if(cmp.compare(map0.get("accountNum"), map1.get("accountNum"))<0) {
				return -1;
			}
			return 0;
		}
	}
	
	/**
	 * 按字符长度排序
	 * @author Administrator
	 *
	 */
	
	static class SortStringLength implements Comparator {
		@Override
		public int compare(Object obj0, Object obj1) {
			Map<String, String> map0 = (Map) obj0;
			Map<String, String> map1 = (Map) obj1;
			String string0=(String)map0.get("accountNum");
			String string1=(String)map1.get("accountNum");
			if (string0.length() > string1.length()) {
				return 1;
			} else if (string0.length() == string1.length()) {
				return 0;
			} else {
				return -1;
			}
		}
	}

}
