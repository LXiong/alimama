package file;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;

public class DICUtils {
	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws IOException {
		File file = new File("E:\\eclipse-workspace\\alimama\\alimama\\src\\main\\resources\\main.dic");
		File fileOut = new File("E:\\main.dic");
		List<String> readLines = FileUtils.readLines(file);
		List<String> readLinesOut =new ArrayList<>();
		for(String s :readLines) {
			if(StringUtils.isNotBlank(s)) {
				if(s.length() >= 2) {
					/*s = s.substring(0, 2);
					FileUtils.write(fileOut, s, true);*/
					readLinesOut.add( s.substring(0, 2));
				}
		}
	}
		FileUtils.writeLines(fileOut, readLinesOut);
		
		
	}

}
