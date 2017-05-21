package dataoke;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import jodd.http.HttpBrowser;
import jodd.http.HttpRequest;
import jodd.http.HttpResponse;
import ruokuai.RuoKuaiUnit;

public class Zhuce {
	
	static HttpBrowser browser = null;
	
	
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
	
	static Map<String,HashSet<Integer>> pidsMap = new HashMap<String, HashSet<Integer>>();
	
	static{
		//pids.add("2388149");
		//pids.add("2397311");
		//pids.add("2402473");
		
		
	/*	HashSet<Integer> hashSet = new HashSet<Integer>();
		hashSet = randomCommon(0, 180, 180);
		
		pidsMap.put("2463308", hashSet);
	*/
        HashSet<Integer> hashSet1 = new HashSet<Integer>();
    	hashSet1 = randomCommon(0, 180, 180);
    	
		
		pidsMap.put("2472720", hashSet1);
		
		
		HashSet<Integer> hashSet2 = new HashSet<Integer>();
			
	    hashSet2 = randomCommon(0, 180, 180);
			
			pidsMap.put("2468377", hashSet2);
		
		for(String key:pidsMap.keySet()){
			System.out.println("key ==="+key +" 隨機數大小===="+pidsMap.get(key).size()+" 數組>>>"+pidsMap.get(key));
		}
		
		
		
		
		
		pidsRandom.add("2373537");
		pidsRandom.add("2372335");
		pidsRandom.add("2371815");
		pidsRandom.add("2375125");
		pidsRandom.add("2371861");
		pidsRandom.add("2373131");
		pidsRandom.add("2372425");
		pidsRandom.add("2372126");
		pidsRandom.add("2374616");
		pidsRandom.add("2374289");
		pidsRandom.add("2377831");
		pidsRandom.add("2372131");
		pidsRandom.add("2371439");
		pidsRandom.add("2379139");
		pidsRandom.add("2373162");
		pidsRandom.add("2371614");
		pidsRandom.add("2374214");
		pidsRandom.add("2373475");
		pidsRandom.add("2373815");
		pidsRandom.add("2376175");

		
		
	}
	
	
  
    /** 
     * 随机指定范围内N个不重复的数 
     * 利用HashSet的特征，只能存放不同的值 
     * @param min 指定范围最小值 
     * @param max 指定范围最大值 
     * @param n 随机数个数 
     * @param HashSet<Integer> set 随机数结果集 
     */  
       public static void randomSet(int min, int max, int n, HashSet<Integer> set) {  
           if (n > (max - min + 1) || max < min) {  
               return;  
           }  
           for (int i = 0; i < n; i++) {  
               // 调用Math.random()方法  
               int num = (int) (Math.random() * (max - min)) + min;  
               set.add(num);// 将不同的数存入HashSet中  
           }  
           int setSize = set.size();  
           // 如果存入的数小于指定生成的个数，则调用递归再生成剩余个数的随机数，如此循环，直到达到指定大小  
           if (setSize < n) {  
            randomSet(min, max, n - setSize, set);// 递归  
           }  
       }
       
       /** 
        * 随机指定范围内N个不重复的数 
        * 最简单最基本的方法 
        * @param min 指定范围最小值 
        * @param max 指定范围最大值 
        * @param n 随机数个数 
        */  
       public static HashSet<Integer> randomCommon(int min, int max, int n){  
    	   HashSet<Integer> integers = new HashSet<Integer>();
    	   if(max==n){
    		   for(int i=0;i<n;i++){
    			   integers.add(i);
    		   }
    		   return integers;
    	   }
    	   
           if (n > (max - min + 1) || max < min) {  
                  return null;  
              }  
           int[] result = new int[n];  
           int count = 0;  
           while(count < n) {  
               int num = (int) (Math.random() * (max - min)) + min;  
               boolean flag = true;  
               for (int j = 0; j < n; j++) {  
                   if(num == result[j]){  
                       flag = false;  
                       break;  
                   }  
               }  
               if(flag){  
                   result[count] = num;  
                   count++;  
               }  
           }  
           
           
           for(int i:result){
        	   integers.add(i);
           }
           
           return integers;  
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
			for(String pid:pidsMap.keySet()){
				
				/*if(!pidsMap.get(pid).contains(okSize)){
					continue;
				}*/
				
				Test.readExecute(pid, num);
				boolean flagt = Test.tuijian(pid, num);
				if(flagt){
					System.out.println("推广成功》》》》》》》》》》》》》》》》》》》pid="+pid+" uname="+num);
				}else{
					System.out.println("推广失败》》》》》》》》》》》》》》》》》》     pid="+pid+"   uname="+num);
				}
				Thread.sleep(Cmd.getSleepTime());
				
				/*pid = getRandomPid();
				
				Test.readExecute(pid, num);
				flagt = Test.tuijian(pid, num);
				if(flagt){
					System.out.println("推广成功》》》》》》》》》》》》》》》》》》》pid="+pid+" uname="+num);
				}else{
					System.out.println("推广失败》》》》》》》》》》》》》》》》》》     pid="+pid+"   uname="+num);
				}
				Thread.sleep(Cmd.getSleepTime());*/
			}
		}	
		}catch(Exception e){
			e.printStackTrace();
		}
		 
	}
	
	public static void main(String[] args)throws Exception {
		
		
		Thread.sleep(1000 * 60 * 60);
		browser = new HttpBrowser();
		
		executeAll();
		 
		 
		// HashSet<Integer> hashSet = new HashSet<Integer>();
		 
		// randomSet(0, 400, 50, hashSet);
		
		 //System.out.println(hashSet);
		
		
		//testRandom();
	}
	
	
	public static void testRandom(){
		for(int i=0;i<500;i++){
			for(String pid:pidsMap.keySet()){
				if(!pidsMap.get(pid).contains(okSize)){
					continue;
				}
				//System.out.println("okSize====="+okSize);
				okSize+=1;
				
				
				
			}
		}
	}
	
	
	public static void executeAll()throws Exception{

		 Ma60.login();
		 for(int i=0;i<10000;i++){
			 try{
				 if(okSize > 180){
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
