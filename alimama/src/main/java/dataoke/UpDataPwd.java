package dataoke;

import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpHost;

import util.IpPoolUtil;

public class UpDataPwd {
	    static File base = new File("D:\\dataoke\\updateusers");
	    static File baseOut = new File("D:\\dataoke\\updateusersok");
	    
		public static int getSleepTime(int min,int max){
			Random random = new Random();
	        int s = random.nextInt(max)%(max-min+1) + min;
	        System.out.println("获取随机时间为："+s);
	        return s;
		}
		
		static int count=0;
		static int tuiguangOk=0;
		static File fiar = new File(base,"fail.txt");
		@SuppressWarnings("deprecation")
		public static void main(String[] args)throws Exception {
			for(final File file:base.listFiles()){
				final File newFile = new File(baseOut, file.getName());
				System.out.println("开始读取文件>>>>>>>>>>"+file.getAbsolutePath());
				List<String> lists=FileUtils.readLines(file);
				Set<String> sets = new HashSet<String>(lists);
				System.out.println("文件去重大小》》》》》》》》》》》"+sets.size());
				for(String s:sets){
					if(StringUtils.isBlank(s)){
						continue;
					}
					count++;
					
					final String uname = s.split("\\----")[0].trim();
					final String pwd = s.split("\\----")[1].trim();
					System.out.println("u = "+uname + "p = "+pwd +" 开始登陆  当前已刷>>>>>>>>>>>>>>>"+count+" 当前 文件名称："+file.getName());
					try{
						//final HttpHost proxy = IpPoolUtil.getHttpHost();
						final HttpHost proxy = null;
						Test.proxy = proxy;
						FutureTask<Boolean> futureTask = new FutureTask<Boolean>(new Callable<Boolean>() {
							public Boolean call() throws Exception {
								try{
									boolean flag = Test.loginHttpClient(uname, pwd);
									System.out.println("u = "+uname + "登陆>>>>>>>>>>>>>"+flag);
									Thread.sleep(1000);
									if(flag){
										int size = new Random().nextInt(4);
										String newPwd =  new PassWordCreate().createPassWord(6+size);
										flag = Test.updatePwdHttpClient(uname, pwd, newPwd);
										if(flag){
											tuiguangOk+=1;
											System.out.println("修改密码成功》》》》》》》》》》》》》》》》》》》 uname="+uname +" 当前修改成功推广："+tuiguangOk+" 当前ip=="+(proxy==null?"无":proxy.getHostName()));
											FileUtils.write(newFile, uname+"----"+newPwd+"\r\n",true);
										}else{
											FileUtils.write(fiar, uname+"----"+pwd+"\r\n",true);
											System.out.println("修改密码失败》》》》》》》》》》》》》》》》》》》 uname="+uname);
										}
										
									}else{
										FileUtils.write(fiar, uname+"----"+pwd+"\r\n",true);
										System.out.println("加pid失败》》》》》》》》》》》》》》》》》》》 uname="+uname);
									}
								}catch(Exception e){
									FileUtils.write(fiar, uname+"----"+pwd+"\r\n",true);
									System.out.println("加pid失败》》》》》》》》》》》》》》》》》》》 uname="+uname);
								}
								
								return true;
							}
						});
						Thread thread = new Thread(futureTask);
						thread.start();
						boolean flag = futureTask.get(30, TimeUnit.SECONDS);
						System.out.println("线程回调结果>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+flag);
					}catch(Exception e){
						e.printStackTrace();
						FileUtils.write(fiar, uname+"----"+pwd+"\r\n",true);
					}finally{
						Test.proxy = null;
						Thread.sleep(Cmd.getSleepTime(1000, 2000));
					}
					
				}
			}
			
		}
		
		

}
