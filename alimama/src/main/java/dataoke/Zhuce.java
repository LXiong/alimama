package dataoke;

import jodd.http.HttpBrowser;
import jodd.http.HttpRequest;
import jodd.http.HttpResponse;

public class Zhuce {
	
	static HttpBrowser browser = new HttpBrowser();
	
	static String tnum = "";
	
	public static void main(String[] args)throws Exception {
		
		 Ma60.login();
		 
		 System.out.println("获取手机号码");
		 String num = Ma60.getnum();
		 tnum = num;
		 
		 if(verify(num)){
			 System.out.println("获取短信内容");
			 String str = Ma60.getmsg();
			 String code = "";
			 boolean flag= reg(num, "1qaz2wsx", code);
			 if(flag){
				 System.out.println("释放手机号码");
				 Ma60.resleNum();
			 }
		 }
		
		
	}
	
	public static boolean reg(String username,String password,String code)throws Exception{
		 String url = "http://www.dataoke.com/login?user=reg";
		 HttpRequest httpRequest =HttpRequest.post(url);
		 httpRequest.header("Host", "www.dataoke.com");
		 httpRequest.header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:52.0) Gecko/20100101 Firefox/52.0");
		 httpRequest.header("Referer", "http://www.dataoke.com/login?user=reg");
		 httpRequest.header("Connection", "keep-alive");
		 httpRequest.header("X-Requested-With", "XMLHttpRequest");
		 
		 httpRequest.form("username", username);
		 httpRequest.form("password", password);
		 httpRequest.form("code", code);
		 
		 HttpResponse response = browser.sendRequest(httpRequest);
		 
		 response = browser.sendRequest(httpRequest);
		 String rc = response.body();
		 System.out.println(rc);
		 
		 if(rc.contains("1")){
			System.out.println("注册成功>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>.");
			return true;
		 }else{
			 System.out.println("注册失败>>>>>>>>>>>>>>>>>>>>>>>>");
			 return false;
		 }
	}
	
	
	public static boolean verify(String num)throws Exception{
		 String url = "http://www.dataoke.com/verify";
		 HttpRequest httpRequest =HttpRequest.get(url);
		 httpRequest.header("Host", "www.dataoke.com");
		 httpRequest.header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:52.0) Gecko/20100101 Firefox/52.0");
		 httpRequest.header("Referer", "http://www.dataoke.com/login?user=reg");
		 httpRequest.header("Connection", "keep-alive");
		 httpRequest.header("X-Requested-With", "XMLHttpRequest");
		 
		HttpResponse response = browser.sendRequest(httpRequest);
		//HttpResponse response = httpRequest.send();
		
		
		byte[] bs = response.bodyBytes();
		TestImage.buff2Image(bs,"d:\\test.jpg");
		
		 String vc = "";
		 String url2 = "http://www.dataoke.com/message?username="+num+"&vc="+vc;
		 httpRequest = HttpRequest.get(url2);
		 httpRequest.header("Host", "www.dataoke.com");
		 httpRequest.header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:52.0) Gecko/20100101 Firefox/52.0");
		 httpRequest.header("Referer", "http://www.dataoke.com/login?user=reg");
		 httpRequest.header("Connection", "keep-alive");
		 httpRequest.header("X-Requested-With", "XMLHttpRequest");
		 
		 response = browser.sendRequest(httpRequest);
		 String rc = response.body();
		 System.out.println(rc);
		 
		 if(rc.contains("1")){
			System.out.println("驗證碼輸入正確>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>.");
			return true;
		 }else{
			 System.out.println("驗證碼輸入錯誤>>>>>>>>>>>>>>>>>>>>>>>>");
			 Thread.sleep(4000);
			 return verify(num);
		 }
	}
	
	public static void test()throws Exception{
		 HttpBrowser browser = new HttpBrowser();
		 String url = "http://www.dataoke.com/verify";
		 HttpRequest httpRequest =HttpRequest.get(url);
		 httpRequest.header("Host", "www.dataoke.com");
		 httpRequest.header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:52.0) Gecko/20100101 Firefox/52.0");
		 httpRequest.header("Referer", "http://www.dataoke.com/login?user=reg");
		 httpRequest.header("Connection", "keep-alive");
		 httpRequest.header("X-Requested-With", "XMLHttpRequest");
		 
		HttpResponse response = browser.sendRequest(httpRequest);
		//HttpResponse response = httpRequest.send();
		
		
		byte[] bs = response.bodyBytes();
		TestImage.buff2Image(bs,"d:\\test.jpg");
		
		String vc = "";
		
		 String url2 = "http://www.dataoke.com/message?username=15201733862&vc="+vc;
		 httpRequest = HttpRequest.get(url2);
		 httpRequest.header("Host", "www.dataoke.com");
		 httpRequest.header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:52.0) Gecko/20100101 Firefox/52.0");
		 httpRequest.header("Referer", "http://www.dataoke.com/login?user=reg");
		 httpRequest.header("Connection", "keep-alive");
		 httpRequest.header("X-Requested-With", "XMLHttpRequest");
		 
		 response = browser.sendRequest(httpRequest);
		 String rc = response.body();
		 System.out.println(rc);
		 
		 if(rc.contains("1")){
			System.out.println("驗證碼輸入正確>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>.");
		 }else{
			 System.out.println("驗證碼輸入錯誤>>>>>>>>>>>>>>>>>>>>>>>>");
		 }
	}

}
