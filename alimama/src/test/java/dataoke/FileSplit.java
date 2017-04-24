package dataoke;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;

public class FileSplit {
	
static Map<String,File> map = new LinkedHashMap<String, File>();
	
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
	
	
	static List<String> users = new ArrayList<String>();
	
	public static void main(String[] args)throws Exception {
		File base = new File("D:\\dataoke\\users\\");
		for(File f:map.values()){
			List<String> list = FileUtils.readLines(f);
			users.addAll(list);
		}
		System.out.println("users =="+users.size());
		File out = null;
		int fileSize = 1;
		for(int i=1;i<=users.size();i++){
			System.out.println(users.get(i-1));
			out = new File(base,fileSize+".txt");
			FileUtils.write(out, users.get(i)+"\r\n", true);
			if(i % 100 == 0){
				fileSize = fileSize+1;
				out = new File(base,(fileSize)+".txt");
				System.out.println("====================");
			}
			
		}
		
	}

}
