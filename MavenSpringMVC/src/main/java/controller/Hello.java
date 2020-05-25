package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import model.User;

@Controller
@RequestMapping("/aaa")
public class Hello {
	
	@RequestMapping("/he")
	public String toHello() {
		return "hello";
	}
	@RequestMapping("/user")
	@ResponseBody
	public  User toU(){
		User user=new User();
		user.setName("张三");
		user.setPwd("123");
		return user;
	}
	@RequestMapping("/heStr")
	@ResponseBody
	public String toHelloStr() {
		User user=new User();
		user.setName("张三");
		user.setPwd("123");
		Gson gson=new Gson();
		String userStr=gson.toJson(user);
		return userStr;
	}
	

}
