package dataoke;

import java.io.File;

public class DeleteAll {
	
	public static void main(String[] args) throws Exception{
		File[] files = new File[]{new File("d:\\taoke\\大淘客帐号\\B批第1组500.txt"),new File("d:\\taoke\\大淘客帐号\\B批第2组500.txt")
		,new File("d:\\taoke\\大淘客帐号\\B批第3组500.txt"),new File("d:\\taoke\\大淘客帐号\\B批第4组410.txt")};
		Test.execteDeleteAll(files);
	}

}
