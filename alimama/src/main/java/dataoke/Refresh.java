package dataoke;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import jodd.http.Cookie;

public class Refresh {
	
	static Map<String,Cookie[]> map = new HashMap<String, Cookie[]>();
	
	public static void main(String[] args) throws Exception{
		//boolean flag = tuijian("2231931");
		//System.out.println("推广成功》》》》》》》》》》》》》》》》》》》"+flag);
	
		//executeTest();
	  // testck();
		File[] files = new File[]{new File("d:\\taoke\\第1组500.txt"),new File("d:\\taoke\\第2组500.txt")
		,new File("d:\\taoke\\第3组500.txt"),new File("d:\\taoke\\第4组.txt")};
		//execteAll("2255030",new File("G:\\taoke\\第4组.txt"));
		Test.execteAll("2263225",files);
		
		
	}
	
	
}
