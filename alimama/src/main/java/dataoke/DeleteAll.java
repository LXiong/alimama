package dataoke;

import java.io.File;

public class DeleteAll {
	
	public static void main(String[] args) throws Exception{
		//Thread.sleep(1000 * 60 * 60);
		File[] files = new File[]{new File("d:\\dataoke\\大淘客帐号\\dataokeuse20170510.txt")};
		Test.execteDeleteAll(files);
	}

}
