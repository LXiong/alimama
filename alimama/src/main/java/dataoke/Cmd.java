package dataoke;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

public class Cmd {
	
static Map<String,File> map = new HashMap<String, File>();
	
	static{
		map.put("1", new File("d:\\dataoke\\大淘客帐号\\A批第1组500.txt"));
		map.put("2", new File("d:\\dataoke\\大淘客帐号\\A批第2组500.txt"));
		map.put("3", new File("d:\\dataoke\\大淘客帐号\\A批第3组500.txt"));
		map.put("4", new File("d:\\dataoke\\大淘客帐号\\A批第4组390.txt"));
		map.put("5", new File("d:\\dataoke\\大淘客帐号\\B批第1组500.txt"));
		map.put("6", new File("d:\\dataoke\\大淘客帐号\\B批第2组500.txt"));
		map.put("7", new File("d:\\dataoke\\大淘客帐号\\B批第3组500.txt"));
		map.put("8", new File("d:\\dataoke\\大淘客帐号\\B批第4组410.txt"));
	}
	
	public static int min = 1;
	
	public static int max = 3;
	
	public static int getSleepTime(){
	   return getSleepTime(min, max);
	}
	
	public static int getSleepTime(int min,int max){
		Random random = new Random();
        int s = random.nextInt(max)%(max-min+1) + min;
        System.out.println("获取随机时间为："+s);
        return s;
	}
	
	public static void main(String[] args) {
		String pids = args[0];
		String fileIds = args[1];
		
		if(args.length > 2){
			System.out.println("时间参数为>>>>>>>>>>>>>>>>>"+args[2]);
			min = Integer.parseInt(args[2].split(",")[0]);
			max = Integer.parseInt(args[2].split(",")[1]);
		}
		
		if(args.length > 3){
			Integer sleep = Integer.parseInt(args[3]);
			System.out.println("睡眠 ："+sleep+" 在实行程序");
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
