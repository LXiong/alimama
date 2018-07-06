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
				char[] c = s.toCharArray();
				for(int i= 0;i<c.length;i++)
					readLinesOut.add( String.valueOf(c[i]));
				}
		}
		FileUtils.writeLines(fileOut, readLinesOut);
		
	}

}
