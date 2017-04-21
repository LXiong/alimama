package dataoke;

import java.io.File;

import jodd.http.HttpBrowser;
import jodd.http.HttpRequest;
import jodd.http.HttpResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

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
		 
		 if(StringUtils.isBlank(num)){
			 System.out.println("获取手机号码有问题》》》》》》》》》》》》》》");
			 return;
		 }
		 
		 if(verify(num,4)){
			 String code = "";
			 System.out.println("获取短信内容");
			 for(int i=0;i<10;i++){
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
				 String pwd =  new PassWordCreate().createPassWord(8);
				 boolean flag= reg(num, pwd, code);
				 if(flag){
					 FileUtils.write(out, num+"----"+pwd+"\r\n",true);
					 System.out.println("释放手机号码");
				 }else{
					 flag= reg(num, pwd, code);
					 if(flag){
						 FileUtils.write(out, num+"----"+pwd+"\r\n",true);
						 System.out.println("释放手机号码");
					 }
				 }
				 
			 }else{
				 System.out.println("验证码为null>>>>>>>>>>>>>>>>>>>>>");
				 Ma60.jiaheiNum();
			 }
			
		 }
	}
	
	public static void main(String[] args)throws Exception {
		
		 Ma60.login();
		 for(int i=0;i<10000;i++){
			 try{
				 execute();
			 }catch(Exception e){
				 e.printStackTrace();
			 }finally{
				 Ma60.resleNum(tnum);
				 browser.close();
				 browser =  new HttpBrowser();
			 }
			 Thread.sleep(2000);
		 }
		 
		
		
	}
	
	static File out = new File("d:\\dataokeuser.txt");
	
	public static boolean reg(String username,String password,String code)throws Exception{
		 String url = "http://www.dataoke.com/login?user=reg";
		 HttpRequest httpRequest =HttpRequest.post(url).timeout(10000);
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
		 HttpRequest httpRequest =HttpRequest.get(url).timeout(10000);
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
		 httpRequest = HttpRequest.get(url2).timeout(10000);
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
			 Thread.sleep(2000);
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
