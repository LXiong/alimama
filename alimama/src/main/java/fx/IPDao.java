package fx;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.io.FileUtils;


public class IPDao {
	
	public File dbFile = null;
	Map<String,String> ipMap = new ConcurrentHashMap<String, String>();
	
	public IPDao(File dbFile){
		this.dbFile = dbFile;
		try {
			if(!dbFile.exists()){
				return ;
			}
			List<String> list = FileUtils.readLines(dbFile);
			for(String ip:list){
				ipMap.put(ip, ip);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	public void addIp(String ip){
			ipMap.put(ip, ip);
			try {
				FileUtils.write(dbFile, ip+"\r\n",true);
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	
	public boolean containsKey(String ip){
		return ipMap.containsKey(ip);
	}

}
