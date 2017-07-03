package dataoke;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.io.FileUtils;

public class UsersFile {

	public static void main(String[] args)throws Exception {
		// TODO Auto-generated method stub
		Set<String> users = new HashSet<String>();
         File file = new  File("D:\\dataoke\\5.30之前");
         for(File f:file.listFiles()){
        	 users.addAll(FileUtils.readLines(f));
         }
         FileUtils.writeLines(new File(file,"5.30All.txt"), users);
         
	}

}
