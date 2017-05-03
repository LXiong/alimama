package dataoke;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

public class CreatePid {
	
     static Map<String,File> map = new LinkedHashMap<String, File>();
    static File base = new File("D:\\dataoke\\users\\");
	static{
		for(File f:base.listFiles()){
			map.put(f.getName().replace(".txt", ""), f);
		}
	}
	
	public static int min = 1000;
	
	public static int max = 2000;
	
	public static int getSleepTime(){
	   return getSleepTime(min, max);
	}
	
	public static int getSleepTime(int min,int max){
		Random random = new Random();
        int s = random.nextInt(max)%(max-min+1) + min;
        System.out.println("获取随机时间为："+s);
        return s;
	}
	
	static int count=0;
	public static void main(String[] args)throws Exception {
		Collection<File> files=new ArrayList<File>();
		files.add(map.get("1"));
		files.add(map.get("2"));
		
		for(File file:files){
			System.out.println("开始读取文件>>>>>>>>>>"+file.getAbsolutePath());
			List<String> lists=FileUtils.readLines(file);
			for(String s:lists){
				if(StringUtils.isBlank(s)){
					continue;
				}
				count++;
				if(count<13){
					continue;
				}
				String uname = s.split("\\----")[0].trim();
				String pwd = s.split("\\----")[1].trim();
				System.out.println("u = "+uname + "p = "+pwd +" 开始登陆  当前已刷>>>>>>>>>>>>>>>"+count+" 当前 文件名称："+file.getName());
				try{
					Test.createPidAll(uname);
				}catch(Exception e){
					e.printStackTrace();
				}finally{
					Thread.sleep(2000);
				}
				
			}
		}
		
	}
	
	
	public static void execute(String pids,String fileIds){
		System.out.println("输入的商品id==="+pids);
		
		System.out.println("文件id==="+fileIds);
		
		System.out.println("开始验证>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		if(Test.check()){
			try {
				Test.execteAll(pids.split(","),getFiles(fileIds));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static File[]  getFiles(String fileIds){
		//fileIds.split(",").length
		File[] files = new File[]{};
		if(StringUtils.isNotBlank(fileIds)){
			for(String id:fileIds.split(",")){
				if(map.containsKey(id)){
					File f = map.get(id);
					if(f!=null){
						files=(File[])ArrayUtils.add(files, f);
					}
				}
			}
		}
		for(File f:files){
			if(f!=null){
				System.out.println("1111输入的文件名称>>>>>>>>>>>>>>"+f.getAbsolutePath());
			}
		}
		return files;
	}
	
	

}
