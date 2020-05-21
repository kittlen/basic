package JsonUtil;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import mode.User;

public class GsonTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		User user=new User();
		user.setName("张三");
		user.setPwd("123");
		
		Gson gson=new Gson();
		String json=gson.toJson(user);//将对象转换为json数据
		System.out.println(json);
		User user2=gson.fromJson(json, User.class);//将json数据转换为对象
		System.out.println(user2.getName());
		List<User> list=new ArrayList<User>();
		for(int i=0;i<5;i++) {
			User userList=new User();
			userList.setName("张三");
			userList.setPwd("123");
			list.add(userList);
		}
		json=gson.toJson(list);//list转json
		System.out.println(json);
		
		List<User> userList=gson.fromJson(json, new TypeToken<List<User>>() {}.getType());//json转list
		System.out.println(userList.get(0).getName());
		
	}

}
