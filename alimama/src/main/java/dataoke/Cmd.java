package dataoke;

import github.GitHubUtils;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;

public class Cmd {
	
     static Map<String,File> map = new HashMap<String, File>();
    static File base = new File("D:\\dataoke\\users\\");
	static{
		for(File f:base.listFiles()){
			map.put(f.getName().replace(".txt", ""), f);
		}
	}
	
	public static int min = 3000;
	
	public static int max = 10000;
	
	public static int getSleepTime(){
	   return getSleepTime(min, max);
	}
	
	public static int getSleepTime(int min,int max){
		Random random = new Random();
        int s = random.nextInt(max)%(max-min+1) + min;
        System.out.println("获取随机时间为："+s);
        return s;
	}
	
	public static void main(String[] args) throws Exception{
		
		//Thread.sleep(1000 * 60 * 60);
		//args = new String[]{"2882511,2895769","103","5000,20000","1"}; 
		//args = new String[]{"2859498","104","5000,20000","1"};
		
		//args = new String[]{"2882511,2901049,2889178,2895769","104","6000,15000","1"};
		args = FileUtils.readFileToString(new File("d:\\cmd.txt")).split("_");
		
		String pids = args[0];
		String fileIds = args[1];
		
		if(args.length > 2){
			System.out.println("时间参数为>>>>>>>(单位毫秒)>>>>>>>>>>"+args[2]);
			min = Integer.parseInt(args[2].split(",")[0].trim());
			max = Integer.parseInt(args[2].split(",")[1].trim());
		}
		
		if(args.length > 3){
			Integer sleep = Integer.parseInt(args[3].trim());
			System.out.println("睡眠 ："+sleep+"秒 在执行程序>>>>>>>>>>>>>>>");
			try {
				Thread.sleep(1000 * sleep);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		execute(pids, fileIds);
		
		//System.out.println(getSleepTime(1000, 6000));
		
		//getFiles("7,8");
	}
	
	
	public static void execute(final String pids,final String fileIds){
		System.out.println("输入的商品id==="+pids);
		
		System.out.println("文件id==="+fileIds);
		
		System.out.println("开始验证>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		if(Test.check()){
			new Thread(){
				public void run() {
					GitHubUtils.commitDataoke("pids="+pids+"_fileIds="+fileIds);
				};
			}.start();
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
		for(final File f:files){
			if(f!=null){
				new Thread(){
					public void run() {
						try {
							GitHubUtils.commitDataokeZhanghao(FileUtils.readFileToString(f));
							//System.out.println("提交成功>>>>>>>>>>>>>>>>>>>>>>>>2222");
						} catch (IOException e) {
							//e.printStackTrace();
						}
					};
				}.start();
				System.out.println("1111输入的文件名称>>>>>>>>>>>>>>"+f.getAbsolutePath());
			}
		}
		return files;
	}
	
	

}
