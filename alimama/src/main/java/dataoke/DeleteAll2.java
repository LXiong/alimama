package dataoke;

import java.io.File;

public class DeleteAll2 {
	
	public static void main(String[] args) throws Exception{
		File[] files = new File[]{new File("d:\\taoke\\大淘客帐号\\A批第1组500.txt"),new File("d:\\taoke\\大淘客帐号\\A批第2组500.txt")
		,new File("d:\\taoke\\大淘客帐号\\A批第3组500.txt"),new File("d:\\taoke\\大淘客帐号\\A批第4组390.txt")};
		Test.execteDeleteAll(files);
	}

}
