package JsonUtil;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class GsonText2 {
	public static void main(String[] args) {
		String json="{\"status\":0,\"result\":{\"location\":{\"lng\":107.68209999999995,\"lat\":36.64239991228289},"
				+ "\"formatted_address\":\"甘肃省庆阳市华池县\",\"business\":\"\",\"addressComponent\":{\"country\":\"中国\",\"country_code\":0,"
				+ "\"country_code_iso\":\"CHN\",\"country_code_iso2\":\"CN\",\"province\":\"甘肃省\",\"city\":\"庆阳市\",\"city_level\":2,"
				+ "\"district\":\"华池县\",\"town\":\"\",\"town_code\":\"\",\"adcode\":\"621023\",\"street\":\"\",\"street_number\":\"\","
				+ "\"direction\":\"\",\"distance\":\"\"},\"pois\":[{\"addr\":\"庆阳市华池县\",\"cp\":\" \",\"direction\":\"东北\",\"distance\":\"523\","
				+ "\"name\":\"上渠\",\"poiType\":\"行政地标\",\"point\":{\"x\":107.67815671465832,\"y\":36.64033884190094},\"tag\":\"行政地标;村庄\",\"tel\":\"\","
				+ "\"uid\":\"cc788fde0c4d268a88fc0663\",\"zip\":\"\",\"parent_poi\":{\"name\":\"\",\"tag\":\"\",\"addr\":\"\",\"point\":{\"x\":0.0,\"y\":0.0},"
				+ "\"direction\":\"\",\"distance\":\"\",\"uid\":\"\"}}],\"roads\":[],\"poiRegions\":[],\"sematic_description\":\"上渠东北523米\",\"cityCode\":135}}";
		//多层json解析成jsonObject(Gson)对象
		JsonParser parser = new JsonParser();
        JsonObject object = parser.parse(json).getAsJsonObject();
        //每解析一层就把json解析为多组键值对 ,通过get获取对应的键值对,再将值中的json解析成下一个jsonObject
        String addr = (object.get("result").getAsJsonObject()).get("formatted_address").getAsString();
        System.out.println(addr);
	}

}
