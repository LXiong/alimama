package dataoke;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import jodd.http.HttpBrowser;
import jodd.http.HttpRequest;
import jodd.http.HttpResponse;
import ruokuai.RuoKuai;
import ruokuai.RuoKuaiTest;
import ruokuai.RuoKuaiUnit;

public class Zhuce {
	
	static HttpBrowser browser = new HttpBrowser();
	
	static{
	}
	
	static String tnum = "";
	
	
	public static void execute()throws Exception{
		System.out.println("获取手机号码");
		 String num = Ma60.getnum();
		 tnum = num;
		 
		 if(verify(num,4)){
			 String code = "";
			 System.out.println("获取短信内容");
			 for(int i=0;i<20;i++){
				 String str = Ma60.getmsg();
				 if(StringUtils.isBlank(str)){
					 Thread.sleep(4000);
					 continue;
				 }else{
					 if(str.contains("大淘客")){
						 code = str.replace("【大淘客】您的验证码是", "").replace("，请于5分钟内正确输入", "");
						 break;
					 }
				 }
			 }
			 System.out.println("验证码为："+code);
			 
			 if(StringUtils.isNotBlank(code)){
				 boolean flag= reg(num, "1qaz2wsx", code);
				 if(flag){
					 FileUtils.write(out, num+"----"+"1qaz2wsx"+"\r\n",true);
					 System.out.println("释放手机号码");
				 }else{
					 flag= reg(num, "1qaz2wsx", code);
					 if(flag){
						 FileUtils.write(out, num+"----"+"1qaz2wsx"+"\r\n",true);
						 System.out.println("释放手机号码");
					 }
				 }
				 
			 }else{
				 System.out.println("验证码为null>>>>>>>>>>>>>>>>>>>>>");
			 }
			 Ma60.resleNum();
		 }
	}
	
	public static void main(String[] args)throws Exception {
		
		 Ma60.login();
		 for(int i=0;i<3;i++){
			 execute();
		 }
		 
		
		
	}
	
	static File out = new File("d:\\dataokeuser.txt");
	
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
	
	
	public static boolean verify(String num,int count)throws Exception{
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
		 
		 vc = new RuoKuaiUnit().getImgStr(bs);
		 
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
			 count  = count-1;
			 if(count==0){
				 return false;
			 }
			 return verify(num,count);
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
