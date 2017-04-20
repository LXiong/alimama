package dataoke;

import jodd.http.HttpRequest;
import jodd.http.HttpResponse;

import org.apache.commons.codec.digest.DigestUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class Ma60 {

	public static void main(String[] args) {
		login();
		
		
	}
	
	public static void execute(){
		login();
		
		for(;;){
			try{
				getnum();
				getmsg();
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				resleNum();
			}
		}
		
	
	}

	static String userId = "";

	static String userKey = "";

	static String telnum = "";

	public static void login() {
		String pwd = DigestUtils.md5Hex("qq77662857");
		String url = "http://sms.60ma.net/loginuser?cmd=login&encode=utf-8&encode=utf-8&dtype=json&newclient=new&usertype=0&username=qq77662857&password="
				+ pwd;
		HttpRequest httpRequest = HttpRequest.get(url);
		HttpResponse response = httpRequest.send();
		// response.accept("utf-8");
		// response.acceptEncoding("utf-8");
		response.charset("utf-8");
		String rc = response.bodyText();
		System.out.println(rc);

		JSONObject jsonObject = JSON.parseObject(rc);
		jsonObject = jsonObject.getJSONObject("Return");

		String staus = jsonObject.getString("Staus");
		if ("0".equals(staus)) {
			System.out.println("登陆成功");
			userId = jsonObject.getString("UserID");
			userKey = jsonObject.getString("UserKey");
		}

	}

	/**
	 * 获取手机号码
	 * 
	 * @return
	 */
	public static String getnum() {
		String url = "http://sms.60ma.net/newsmssrv?cmd=gettelnum&encode=utf-8&dtype=json&docks=10692408025D0DB&userid="
				+ userId
				+ "&userkey="
				+ userKey
				+ "&province=0&city=0&operator=0";
		HttpRequest httpRequest = HttpRequest.get(url);
		HttpResponse response = httpRequest.send();
		// response.accept("utf-8");
		// response.acceptEncoding("utf-8");
		response.charset("utf-8");
		String rc = response.bodyText();
		System.out.println(rc);

		JSONObject jsonObject = JSON.parseObject(rc);
		jsonObject = jsonObject.getJSONObject("Return");

		String staus = jsonObject.getString("Staus");
		if ("0".equals(staus)) {
			System.out.println("获取手机号码成功");
			telnum = jsonObject.getString("Telnum");
		}
		return telnum;
	}

	/**
	 * 获取短信
	 * 
	 * @return
	 */
	public static String getmsg() {
		String url = "http://sms.60ma.net/newsmssrv?cmd=getsms&dtype=json&encode=utf-8&userid="
				+ userId
				+ "&userkey="
				+ userKey
				+ "&dockcode=10692408025D0DB&telnum="
				+ telnum;
		HttpRequest httpRequest = HttpRequest.get(url);
		HttpResponse response = httpRequest.send();
		// response.accept("utf-8");
		// response.acceptEncoding("utf-8");
		response.charset("utf-8");
		String rc = response.bodyText();
		System.out.println(rc);
		JSONObject jsonObject = JSON.parseObject(rc);
		jsonObject = jsonObject.getJSONObject("Return");

		String staus = jsonObject.getString("Staus");
		if ("0".equals(staus)) {
			System.out.println("获取短信成功");
			String SmsContent = jsonObject.getString("SmsContent");
			System.out.println(SmsContent);
			return SmsContent;
		}

		return "";
	}

	// 释放所有号码
	public static void resleNum() {
         System.out.println("释放所有号码");
		String url = "http://sms.60ma.net/newsmssrv?cmd=freetelnumall&dtype=json&encode=utf-8&userid="
				+ userId + "&userkey=" + userKey;
		HttpRequest httpRequest = HttpRequest.get(url);
		HttpResponse response = httpRequest.send();
		response.charset("utf-8");
		String rc = response.bodyText();
		System.out.println(rc);

	}
	
	// 释放所有号码
		public static void jiaheiNum() {
	         System.out.println("加入黑号");
			String url = "http://sms.60ma.net/newsmssrv?cmd=addblacktelnum&encode=utf-8&docks=10692408025D0DB&userid="+userId+"&userkey="+userKey+"&telnum="+telnum;
			HttpRequest httpRequest = HttpRequest.get(url);
			HttpResponse response = httpRequest.send();
			response.charset("utf-8");
			String rc = response.bodyText();
			System.out.println(rc);

		}

}
