package com.jPushDemo.jPushDemo;


import cn.jiguang.common.ClientConfig;
import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;

import java.util.HashMap;
import java.util.Map;

/**
* Utils - 移动端推送
*/
public final class JPushUtils {
   
   private static String MASTER_SECRET = "4fad27b7d11e0f9df4d048e6";
   private static String APP_KEY = "eb6e3947002f1c2f29a195e2";

   /**
    * 不可实例化
    */
   private JPushUtils() {
   }

   /**
    * 向所有设备发送广播消息
    * @param title   标题
    * @param content 内容
    * @return 结果
    */
   public static boolean sendBroadcast(String title, String content) {
       Map<String, String> map = new HashMap<>(4);
       PushPayload pushPayload = buildPushObject_android_ios(title, content, map);
       try {
           JPushClient jPushClient = new JPushClient(MASTER_SECRET, APP_KEY, null, ClientConfig.getInstance());
           PushResult pushResult = jPushClient.sendPush(pushPayload);
           System.out.println("---------------------------pushResult--------------------------------");
           System.out.println(pushResult);
           jPushClient.close();
           return true;
       } catch (Exception e) {
           e.printStackTrace();
       }
       return false;
   }
   
   
   /**
    * 向某个用户发送消息
    * @param userId   用户ID
    * @param title   标题
    * @param content 内容
    * @return 结果
    */
   public static boolean send(Long userId, String title, String content) {
       Map<String, String> map = new HashMap<>(4);
   		String alias = "user_" + userId;
       PushPayload pushPayload = buildPushObject_android_ios_byAlias(alias, title, content, map);
       try {
           JPushClient jPushClient = new JPushClient(MASTER_SECRET, APP_KEY, null, ClientConfig.getInstance());
           PushResult pushResult = jPushClient.sendPush(pushPayload);
           System.out.println("---------------------------pushResult--------------------------------");
           System.out.println(pushResult);
           jPushClient.close();
           return true;
       } catch (Exception e) {
           e.printStackTrace();
       }
       return false;
   }

   /**
    * 根据别名向设备推送信息
    * @param alias 设备名
    * @param title 标题
    * @param content 内容
    * @param map 附加内容
    * @return
    */
   private static PushPayload buildPushObject_android_ios_byAlias(String alias, String title, String content, Map<String, String> map) {
       return PushPayload.newBuilder().setPlatform(Platform.android_ios()).setAudience(Audience.alias(alias)).setNotification(Notification.newBuilder().setAlert(content).addPlatformNotification(AndroidNotification.newBuilder().setTitle(title).build()).addPlatformNotification(IosNotification.newBuilder().incrBadge(1).addExtras(map).build()).build()).setOptions(Options.newBuilder().setApnsProduction(true).build()).build();
   }

   /**
    * 向所有设备推送信息
    * @param title 标题
    * @param content 内容
    * @param map 附加内容
    * @return 结果
    */
   private static PushPayload buildPushObject_android_ios(String title, String content, Map<String, String> map) {
       return PushPayload.newBuilder().setPlatform(Platform.android_ios()).setAudience(Audience.all()).setNotification(Notification.newBuilder().setAlert(content).addPlatformNotification(AndroidNotification.newBuilder().setTitle(title).build()).addPlatformNotification(AndroidNotification.newBuilder().addExtras(map).build()).build()).setOptions(Options.newBuilder().setApnsProduction(false).build()).build();
   }
   
   
   
 //极光推送>>Android
 	//Map<String, String> parm是我自己传过来的参数,同学们可以自定义参数
 	public static void jpushAndroid(Map<String, String> parm) {
  
 		//创建JPushClient(极光推送的实例)
 		JPushClient jpushClient = new JPushClient(MASTER_SECRET, APP_KEY);
 		//推送的关键,构造一个payload 
 		PushPayload payload = PushPayload.newBuilder()
 				.setPlatform(Platform.android())//指定android平台的用户
 				.setAudience(Audience.all())//你项目中的所有用户
 				//.setAudience(Audience.registrationId(parm.get("id")))//registrationId指定用户
 				.setNotification(Notification.android(parm.get("msg"), "这是title", parm))
 				//发送内容
 				.setOptions(Options.newBuilder().setApnsProduction(false).build())
 				//这里是指定开发环境,不用设置也没关系
 				.setMessage(Message.content(parm.get("msg")))//自定义信息
 				.build();
  
 		try {
 			PushResult pu = jpushClient.sendPush(payload);  
 		} catch (APIConnectionException e) {
 			e.printStackTrace();
 		} catch (APIRequestException e) {
 			e.printStackTrace();
 		}    
 	}
 	
 	//极光推送>>ios
 	//Map<String, String> parm是我自己传过来的参数,同学们可以自定义参数
 	public static  void jpushIOS(Map<String, String> parm) {
  
 		//创建JPushClient
 		JPushClient jpushClient = new JPushClient(MASTER_SECRET, APP_KEY);
 		PushPayload payload = PushPayload.newBuilder()
 				.setPlatform(Platform.ios())//ios平台的用户
 				.setAudience(Audience.all())//所有用户
 				//.setAudience(Audience.registrationId(parm.get("id")))//registrationId指定用户
 				.setNotification(Notification.newBuilder()
 						.addPlatformNotification(IosNotification.newBuilder()
 								.setAlert(parm.get("msg"))
 								.setBadge(+1)
 								.setSound("happy")//这里是设置提示音(更多可以去官网看看)
 								.addExtras(parm)
 								.build())
 						.build())
 				.setOptions(Options.newBuilder().setApnsProduction(false).build())
 				.setMessage(Message.newBuilder().setMsgContent(parm.get("msg")).addExtras(parm).build())//自定义信息
 				.build();
  
 		try {
 			PushResult pu = jpushClient.sendPush(payload);
  
 		} catch (APIConnectionException e) {
 			e.printStackTrace();
 		} catch (APIRequestException e) {
 			e.printStackTrace();
 		}    
 	}
 	
 	//极光推送>>All所有平台
 	public static void jpushAll(Map<String, String> parm) {
  
 		//创建JPushClient
 		JPushClient jpushClient = new JPushClient(MASTER_SECRET, APP_KEY);
 		//创建option
 		PushPayload payload = PushPayload.newBuilder()
 				.setPlatform(Platform.all())  //所有平台的用户
 				.setAudience(Audience.registrationId(parm.get("id")))//registrationId指定用户
 				.setNotification(Notification.newBuilder()
 						.addPlatformNotification(IosNotification.newBuilder() //发送ios
 								.setAlert(parm.get("msg")) //消息体
 								.setBadge(+1)
 								.setSound("happy") //ios提示音
 								.addExtras(parm) //附加参数
 								.build())
 						.addPlatformNotification(AndroidNotification.newBuilder() //发送android
 								.addExtras(parm) //附加参数
 								.setAlert(parm.get("msg")) //消息体
 								.build())
 						.build())
 				.setOptions(Options.newBuilder().setApnsProduction(true).build())//指定开发环境 true为生产模式 false 为测试模式 (android不区分模式,ios区分模式)
 				.setMessage(Message.newBuilder().setMsgContent(parm.get("msg")).addExtras(parm).build())//自定义信息
 				.build();
 		try {
 			PushResult pu = jpushClient.sendPush(payload);
 			System.out.println(pu.toString());
 		} catch (APIConnectionException e) {
 			e.printStackTrace();
 		} catch (APIRequestException e) {
 			e.printStackTrace();
 		}    
 	}
}
