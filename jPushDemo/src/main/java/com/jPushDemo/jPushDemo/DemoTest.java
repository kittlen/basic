package com.jPushDemo.jPushDemo;

import java.util.HashMap;
import java.util.Map;

public class DemoTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
/*		//设置推送参数
				//这里同学们就可以自定义推送参数了
				Map<String, String> parm = new HashMap<String, String>();
				parm.put("title", "这是标题");
				//这里的id是,移动端集成极光并登陆后,极光用户的rid
//				parm.put("id", "XXXXXXXXXXXXXXXX");
				//设置提示信息,内容是文章标题
				parm.put("msg","测试测试,收到请联系发送人");
				JPushUtils.jpushAndroid(parm);
*/		
		
		System.out.println(JPushUtils.sendBroadcast("这是一个标题","这是内容"));//此方法标题无法传递

	}

}
