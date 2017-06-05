package util;

import java.io.File;

import org.apache.commons.io.FileUtils;

public class MerageFileUtile {
	
	public static void main(String[] args)throws Exception {
		File base = new File("D:\\大淘客账号注册");
		for(File f : base.listFiles()){
			if(f.isDirectory()){
				File out = new File(f, f.getName()+".txt");
				merageFile(out, f.listFiles());
			}
		}
		
	}
	
	public static void merageFile(File out,File... src)throws Exception{
		if(!out.exists()){
			out.createNewFile();
		}
		for(File s :src){
			FileUtils.writeLines(out, FileUtils.readLines(s),true);
		}
	}

}
