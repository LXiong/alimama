package dataoke;

import java.io.File;

public class DeleteAll {
	
	public static void main(String[] args) throws Exception{
		//Thread.sleep(1000 * 60 * 60);
		File[] files = new File[]{new File("d:\\dataoke\\users\\dataokeuser20170509.txt"),new File("d:\\dataoke\\users\\dataokeuser20170510.txt")};
		Test.execteDeleteAll(files);
	}

}
