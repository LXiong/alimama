package dataoke;

import java.io.File;
import java.util.List;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

public class CreatePidHttpClient {
	
    static File base = new File("D:\\dataoke\\createpid\\");
	
	public static int getSleepTime(int min,int max){
		Random random = new Random();
        int s = random.nextInt(max)%(max-min+1) + min;
        System.out.println("获取随机时间为："+s);
        return s;
	}
	
	static int count=0;
	public static void main(String[] args)throws Exception {
		for(File file:base.listFiles()){
			System.out.println("开始读取文件>>>>>>>>>>"+file.getAbsolutePath());
			List<String> lists=FileUtils.readLines(file);
			for(String s:lists){
				if(StringUtils.isBlank(s)){
					continue;
				}
				count++;
				
				String uname = s.split("\\----")[0].trim();
				String pwd = s.split("\\----")[1].trim();
				System.out.println("u = "+uname + "p = "+pwd +" 开始登陆  当前已刷>>>>>>>>>>>>>>>"+count+" 当前 文件名称："+file.getName());
				try{
					boolean flag = Test.loginHttpClient(uname, pwd);
					System.out.println("u = "+uname + "登陆>>>>>>>>>>>>>"+flag);
					Thread.sleep(200);
					if(flag){
						flag = Test.createPidAllHttpClient(uname);
						System.out.println("创建pi===="+flag);
					}
				}catch(Exception e){
					e.printStackTrace();
				}finally{
					Thread.sleep(1000);
				}
				
			}
		}
		
	}
	
	

}
