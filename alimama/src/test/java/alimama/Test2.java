package alimama;

import java.io.InputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.time.DateUtils;

public class Test2 {
	
	public static void main(String[] args)throws Exception {
		  Date date =DateUtils.addDays(new Date(), 6);
			
			long endDay = DateUtils.getFragmentInDays(date, Calendar.MONTH);
			System.out.println(endDay);
			
			/*InputStream inputStream = PropertiesUtil.class.getClassLoader().getResourceAsStream("/main.dic");
			List<String> list = IOUtils.readLines(inputStream);
		
			for(String s : list){
				System.out.println(s);
			}
			System.out.println(list.size());*/
	}

}
