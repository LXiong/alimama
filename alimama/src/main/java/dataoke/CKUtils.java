package dataoke;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.io.FileUtils;

public class CKUtils {
	
	static Map<String,String> map = new LinkedHashMap<String, String>();

	
	public static void main(String[] args) throws Exception {
		
		File file = new File("D:\\dataoke\\cks\\1.txt");
		for(Entry<String, String> m :getCks(file).entrySet()){
			System.out.println(m.getKey());
		}
	}
	
	public static synchronized Map<String,String> getAll()throws Exception {
		if(MapUtils.isNotEmpty(map)){
			return map;
		}
		for(File f:new File("D:\\dataoke\\cks").listFiles()){
			map.putAll(getCks(f));
		}
		return map;
	}
	
	
	public static synchronized Map<String,String> getAll(File[] files)throws Exception {
		 Map<String,String> map = new LinkedHashMap<String, String>();
		for(File f:files){
			map.putAll(getCks(f));
		}
		return map;
	}
	
	public static Map<String,String> getCks(File file) throws IOException{
		Map<String,String> map = new LinkedHashMap<String, String>();
		List<String> lists = FileUtils.readLines(file);
	
		for(int i=0;i<lists.size();i+=3){
			String s = lists.get(i).replace("[", "]");
			String ss = lists.get(i+1);
		    map.put(s, ss);
		}
		return map;
	}
}
