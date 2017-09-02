package file;

import java.io.File;
import java.util.List;

import org.apache.commons.io.FileUtils;

public class MegerFileDataokeMail {
	
	static File base = new File("D:\\dataoke\\邮箱账号\\20170902");
	
	public static void main(String[] args)throws Exception {
		File dataoke = new File(base,"dataoke.txt");
		
		File mail = new File(base,"mail.txt");
		
		List<String> mails = FileUtils.readLines(mail);
		
		int i = 0;
		for(String m:FileUtils.readLines(dataoke)){
			 System.out.println("index==="+i);
			 
		    FileUtils.write(new File(base,"all.txt"), m+"----"+mails.get(i).replace(":", "----")+"\r\n",true);
            i +=1;
           
		}
	}

}
