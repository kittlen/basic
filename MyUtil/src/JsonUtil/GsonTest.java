package JsonUtil;

import com.google.gson.Gson;

import mode.User;

public class GsonTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		User user=new User();
		user.setName("张三");
		user.setPwd("123");
		
		Gson sGson=new Gson();
		String json=sGson.toJson(user);//将对象转换为json数据
		System.out.println(json);
		User user2=sGson.fromJson(json, User.class);//将json数据转换为对象
		System.out.println(user2.getName());
	}

}
