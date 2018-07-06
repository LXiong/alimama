package file;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;

import com.google.common.collect.Sets;

public class DICUtils {
	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws IOException {
		File file = new File("E:\\eclipse-workspace\\alimama\\alimama\\src\\main\\resources\\main.dic");
		File fileOut = new File("E:\\main.dic");
		List<String> readLines = FileUtils.readLines(file);
		Set<String> readLinesOut =Sets.newHashSet();
		for(String s :readLines) {
			if(StringUtils.isNotBlank(s)) {
				if(s.length() >= 2) {
					/*s = s.substring(0, 2);
					FileUtils.write(fileOut, s, true);*/
					readLinesOut.add( s.substring(0, 1));
				}
		}
	}
		FileUtils.writeLines(fileOut, readLinesOut);
		
		
	}

}
