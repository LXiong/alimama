package dataoke;

import java.io.File;

public class DeleteAll {
	
	public static void main(String[] args) throws Exception{
		File[] files = new File[]{new File("d:\\taoke\\第1组500.txt"),new File("d:\\taoke\\第2组500.txt")
		,new File("d:\\taoke\\第3组500.txt"),new File("d:\\taoke\\第4组.txt")};
		Test.execteDeleteAll(files);
	}

}
