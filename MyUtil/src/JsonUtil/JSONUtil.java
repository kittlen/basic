package JsonUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;

public class JSONUtil {
	/***
     * 将List对象序列化为JSON文本
     */
    public static <T> String toJSONString(List<T> list) {
        JSONArray jsonArray = JSONArray.fromObject(list);
 
        return jsonArray.toString();
    }
 
    /***
     * 将对象序列化为JSON文本
     * 
     * @param object
     * @return
     */
    public static String toJSONString(Object object) {
        JSONArray jsonArray = JSONArray.fromObject(object);
 
        return jsonArray.toString();
    }
 
    /***
     * 将JSON对象数组序列化为JSON文本
     * 
     * @param jsonArray
     * @return
     */
    public static String toJSONString(JSONArray jsonArray) {
        return jsonArray.toString();
    }
 
    /***
     * 将JSON对象序列化为JSON文本
     * 
     * @param jsonObject
     * @return
     */
    public static String toJSONString(JSONObject jsonObject) {
        return jsonObject.toString();
    }
 
    /***
     * 将对象转换为List对象
     * 
     * @param object
     * @return
     */
    public static List toArrayList(Object object) {
        List arrayList = new ArrayList();
 
        JSONArray jsonArray = JSONArray.fromObject(object);
 
        Iterator it = jsonArray.iterator();
        while (it.hasNext()) {
            JSONObject jsonObject = (JSONObject) it.next();
 
            Iterator keys = jsonObject.keys();
            while (keys.hasNext()) {
                Object key = keys.next();
                Object value = jsonObject.get(key);
                arrayList.add(value);
            }
        }
 
        return arrayList;
    }
 
    /***
     * 将对象转换为Collection对象
     * 
     * @param object
     * @return
     */
    public static Collection toCollection(Object object) {
        JSONArray jsonArray = JSONArray.fromObject(object);
 
        return JSONArray.toCollection(jsonArray);
    }
 
    /***
     * 将对象转换为JSON对象数组
     * 
     * @param object
     * @return
     */
    public static JSONArray toJSONArray(Object object) {
        return JSONArray.fromObject(object);
    }
 
    /***
     * 将对象转换为JSON对象
     * 
     * @param object
     * @return
     */
    public static JSONObject toJSONObject(Object object) {
        return JSONObject.fromObject(object);
    }
 
    /***
     * 将对象转换为HashMap
     * 
     * @param object
     * @return
     */
    public static HashMap toHashMap(Object object) {
        HashMap<String, Object> data = new HashMap<String, Object>();
        JSONObject jsonObject = JSONUtil.toJSONObject(object);
        Iterator it = jsonObject.keys();
        while (it.hasNext()) {
            String key = String.valueOf(it.next());
            Object value = jsonObject.get(key);
            data.put(key, value);
        }
 
        return data;
    }
 
    /***
     * 将对象转换为List>
     * 
     * @param object
     * @return
     */
    // 返回非实体类型(Map)的List
    public static List<Map<String, Object>> toList(Object object) {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        JSONArray jsonArray = JSONArray.fromObject(object);
        for (Object obj : jsonArray) {
            JSONObject jsonObject = (JSONObject) obj;
            Map<String, Object> map = new HashMap<String, Object>();
            Iterator it = jsonObject.keys();
            while (it.hasNext()) {
                String key = (String) it.next();
                Object value = jsonObject.get(key);
                map.put((String) key, value);
            }
            list.add(map);
        }
        return list;
    }
 
    /***
     * 将JSON对象数组转换为传入类型的List
     * 
     * @param
     * @param jsonArray
     * @param objectClass
     * @return
     */
    public static <T> List<T> toList(JSONArray jsonArray, Class<T> objectClass) {
        return JSONArray.toList(jsonArray, objectClass);
    }
 
    /***
     * 将对象转换为传入类型的List
     * 
     * @param
     * @param jsonArray
     * @param objectClass
     * @return
     */
    public static <T> List<T> toList(Object object, Class<T> objectClass) {
        JSONArray jsonArray = JSONArray.fromObject(object);
 
        return JSONArray.toList(jsonArray, objectClass);
    }
 
    /***
     * 将JSON对象转换为传入类型的对象
     * 
     * @param
     * @param jsonObject
     * @param beanClass
     * @return
     */
    public static <T> T toBean(JSONObject jsonObject, Class<T> beanClass) {
        return (T) JSONObject.toBean(jsonObject, beanClass);
    }
 
    /***
     * 将将对象转换为传入类型的对象
     * 
     * @param
     * @param object
     * @param beanClass
     * @return
     */
    public static <T> T toBean(Object object, Class<T> beanClass) {
        JSONObject jsonObject = JSONObject.fromObject(object);
 
        return (T) JSONObject.toBean(jsonObject, beanClass);
    }
    /**
     * jsonStr转List
     * 
     * @param jsonStr
     * @return
     */
    public static List<Map<String, Object>> parseJSON2List(String jsonStr) {
        JSONArray jsonArr = JSONArray.fromObject(jsonStr);
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        Iterator<JSONObject> it = jsonArr.iterator();
        while (it.hasNext()) {
            JSONObject json2 = it.next();
            list.add(parseJSON2Map(json2.toString()));
        }
        return list;
    }
 
    /**
     * jsonStr转Map
     * 
     * @param jsonStr
     * @return
     */
    public static Map<String, Object> parseJSON2Map(String jsonStr) {
        Map<String, Object> map = new HashMap<String, Object>();
        // 最外层解析
        JSONObject json = JSONObject.fromObject(jsonStr);
        for (Object k : json.keySet()) {
            Object v = json.get(k);
            // 如果内层还是数组的话，继续解析
            if (v instanceof JSONArray) {
                List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
                Iterator<JSONObject> it = ((JSONArray) v).iterator();
                while (it.hasNext()) {
                    JSONObject json2 = it.next();
                    list.add(parseJSON2Map(json2.toString()));
                }
                map.put(k.toString(), list);
            } else {
                map.put(k.toString(), v);
            }
        }
        return map;
    }
    public static JSONArray listMapString2JsonArray(String listMapToString) {
		JSONArray arr = new JSONArray();
		if (StringUtils.isNotBlank(listMapToString)) {
			if (listMapToString.startsWith("[")) {
				listMapToString = StringUtils.strip(listMapToString, "[]");
			}
			if (StringUtils.isNotBlank(listMapToString)) {
				listMapToString = listMapToString.replaceAll("=", "\":\"")//把所有的【=】替换为【":"】
						.replaceAll(", (?!\\{)","\",\"")//把所有的不包含【{】的【, 】替换为【","】
						.replaceAll("\\{","{\"")//把所有的【{】替换为【{"】
						.replaceAll("\\}, ","\"},")//把所有的【}, 】替换为【"},】
						.replaceAll("}$","\"}]")//把最后的【}】替换为【"}]】
						.replaceFirst("^", "[");//最前面加上【[】
				arr = JSONArray.fromObject(listMapToString);
			}
		}
		return arr;
	}
}
