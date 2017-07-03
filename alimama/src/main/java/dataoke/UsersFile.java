package dataoke;

import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;

import util.ForkJoinPool;

public class UsersFile {
	
	 static File file = new  File("D:\\dataoke\\5.30之前");

	public static void main(String[] args)throws Exception {
		test2();
	}
	
	public static void test2()throws Exception{
		File fileAll = new File(file,"5.30All.txt");
		List<String> lists = FileUtils.readLines(fileAll);
		List<List<String>> listSplit =ForkJoinPool.splitTask(lists, 70);
		File fileOutBase =new File("D:\\dataoke\\5.30之前\\20170703");
		for(int i=0;i<listSplit.size();i++){
			FileUtils.writeLines(new File(fileOutBase, (i+1)+".txt"), listSplit.get(i));
		}
	}
	
	public static void test()throws Exception {
		// TODO Auto-generated method stub
				Set<String> users = new HashSet<String>();
		         for(File f:file.listFiles()){
		        	 users.addAll(FileUtils.readLines(f));
		         }
		         FileUtils.writeLines(new File(file,"5.30All.txt"), users);
	}

}
