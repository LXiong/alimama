package dataoke;

import java.io.File;

public class DeleteAll {
	
	public static void main(String[] args) throws Exception{
		File[] files = new File[]{new File("G:\\taoke\\第1组500.txt"),new File("G:\\taoke\\第2组500.txt")
		,new File("G:\\taoke\\第3组500.txt"),new File("G:\\taoke\\第4组.txt")};
		Test.execteDeleteAll(files);
	}

}
