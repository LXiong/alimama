package util;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

public class LOG {
	
	public static void printLog(Object content){
		try {
			System.out.println(content);
			FileUtils.write(new File("d:\\log.log"), content+"\r\n",true);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
