package dataoke;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import jodd.http.HttpBrowser;
import jodd.http.HttpRequest;
import jodd.http.HttpResponse;
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
		 }else if(num.contains("余额不足")){
			 System.out.println("余额不足退出程序>>>>>>>>>>>>>>>>>>>>>>>>>.");
			 System.exit(0);
			 
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
				 int size = new Random().nextInt(4);
				 String pwd =  new PassWordCreate().createPassWord(6+size);
				 boolean flag= reg(num, pwd, code);
				 if(flag){
					 okSize +=1;
					 FileUtils.write(out, num+"----"+pwd+"\r\n",true);
					 tuijian(num, pwd);
					 System.out.println("释放手机号码");
				 }else{
					 flag= reg(num, pwd, code);
					 if(flag){
						 okSize +=1;
						 FileUtils.write(out, num+"----"+pwd+"\r\n",true);
						 tuijian(num, pwd);
						 System.out.println("释放手机号码");
					 }
				 }
				 
			 }else{
				 System.out.println("验证码为null>>>>>>>>>>>>>>>>>>>>>");
				 Ma60.jiaheiNum();
			 }
			
		 }
	}
	
	static List<String> pids = new ArrayList<String>();
	
	static List<String> pidsRandom = new ArrayList<String>();
	
	
	static{
		pids.add("2391539");
		pids.add("2378064");
		pids.add("2391466");
		pids.add("2394091");
		
		
		
		pidsRandom.add("2373837");
		pidsRandom.add("2375835");
		pidsRandom.add("2375825");
		pidsRandom.add("2374825");
		pidsRandom.add("2374821");
		pidsRandom.add("2372621");
		pidsRandom.add("2372225");
		pidsRandom.add("2372126");
		pidsRandom.add("2374126");
		pidsRandom.add("2374139");
		pidsRandom.add("2374831");
		pidsRandom.add("2374431");
		pidsRandom.add("2374739");
		pidsRandom.add("2374439");
		pidsRandom.add("2374712");
		pidsRandom.add("2374634");
		pidsRandom.add("2374714");
		pidsRandom.add("2374775");
		pidsRandom.add("2374315");
		pidsRandom.add("2373575");

		
		
	}
	
	
	public static String getRandomPid(){
		//String ua = "Mozilla/5.0 (Linux; Android 6.0; ALE-TL00 Build/HuaweiALE-TL00) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/37.0.0.0 Mobile MQQBrowser/6.8 TBS/036872 Safari/537.36 MicroMessenger/6.3.31.940 NetType/WIFI Language/zh_CN";
		String ua = pidsRandom.get(new Random().nextInt(pidsRandom.size())).trim();
		return ua;
	}
	
	static int okSize = 0;
	
	
	public static void tuijian(String num,String pwd){
		try{
		Thread.sleep(Cmd.getSleepTime());	
		boolean flag =  Test.login(num, pwd);
		System.out.println("u = "+num + "登陆>>>>>>>>>>>>>"+flag);
		if(flag){
			for(String pid:pids){
				Test.readExecute(pid, num);
				boolean flagt = Test.tuijian(pid, num);
				if(flagt){
					System.out.println("推广成功》》》》》》》》》》》》》》》》》》》pid="+pid+" uname="+num);
				}else{
					System.out.println("推广失败》》》》》》》》》》》》》》》》》》     pid="+pid+"   uname="+num);
				}
				Thread.sleep(Cmd.getSleepTime());
				
				pid = getRandomPid();
				
				Test.readExecute(pid, num);
				flagt = Test.tuijian(pid, num);
				if(flagt){
					System.out.println("推广成功》》》》》》》》》》》》》》》》》》》pid="+pid+" uname="+num);
				}else{
					System.out.println("推广失败》》》》》》》》》》》》》》》》》》     pid="+pid+"   uname="+num);
				}
				Thread.sleep(Cmd.getSleepTime());
			}
		}	
		}catch(Exception e){
			e.printStackTrace();
		}
		 
	}
	
	public static void main(String[] args)throws Exception {
		
		 Ma60.login();
		 for(int i=0;i<10000;i++){
			 try{
				 if(okSize > 50){
					 System.out.println("超过 okSize == "+okSize + "停止程序");
					 System.exit(0);
				 }
				 
				 execute();
			 }catch(Exception e){
				 e.printStackTrace();
			 }finally{
				 try{
					 Ma60.resleNum(tnum);
				 }catch(Exception e){
					 e.printStackTrace();
					 Thread.sleep(2000);
				 }
				 browser.close();
				 browser =  new HttpBrowser();
			 }
			 Thread.sleep(2000);
		 }
		 
		
		
	}
	
	static File out = new File("d:\\dataokeuser1.txt");
	
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
