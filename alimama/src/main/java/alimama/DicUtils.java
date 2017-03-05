package alimama;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Random;

import org.apache.commons.io.IOUtils;

public class DicUtils {
	
	static List<String> list =  null;
	
	static{
		System.out.println("start ciku>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		//InputStream inputStream = DicUtils.class.getClassLoader().getResourceAsStream("taobaociku.txt");
		InputStream inputStream = DicUtils.class.getClassLoader().getResourceAsStream("main.dic");
		try {
			list = IOUtils.readLines(inputStream,Charset.forName("utf-8"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	    System.out.println("end ciku >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
	}
	
	/**
	 * �����ȡ����
	 * @return
	 */
	public static String getDic(){
		try{
			String str = list.get(new Random().nextInt(list.size()-1));
			return str;
		}catch(Exception e){
			return list.get(new Random().nextInt(list.size()-1));
		}
	}

}
