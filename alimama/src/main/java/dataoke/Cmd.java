package dataoke;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

public class Cmd {
	
static Map<String,File> map = new HashMap<String, File>();
	
	static{
		map.put("1", new File("d:\\dataoke\\大淘客帐号\\A批第1组500.txt"));
		map.put("2", new File("d:\\dataoke\\大淘客帐号\\A批第2组500.txt"));
		map.put("3", new File("d:\\dataoke\\大淘客帐号\\A批第3组500.txt"));
		map.put("4", new File("d:\\dataoke\\大淘客帐号\\A批第4组500.txt"));
		map.put("5", new File("d:\\dataoke\\大淘客帐号\\B批第1组390.txt"));
		map.put("6", new File("d:\\dataoke\\大淘客帐号\\B批第2组500.txt"));
		map.put("7", new File("d:\\dataoke\\大淘客帐号\\B批第3组500.txt"));
		map.put("8", new File("d:\\dataoke\\大淘客帐号\\B批第4组410.txt"));
	}
	
	public static void main(String[] args) {
		String pids = args[0];
		String fileIds = args[1];
        execute(pids, fileIds);
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
		
		File[] files = new File[fileIds.split(",").length];
		if(StringUtils.isNotBlank(fileIds)){
			for(String id:fileIds.split(",")){
				if(map.containsKey(id)){
					File f = map.get(id);
					System.out.println("输入的文件名称>>>>>>>>>>>>>>"+f.getAbsolutePath());
					ArrayUtils.add(files, f);
				}
			}
		}
		return files;
	}
	
	

}