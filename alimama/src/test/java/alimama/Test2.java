package alimama;

import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;

public class Test2 {
	
	public static void main(String[] args) {
		  Date date =DateUtils.addDays(new Date(), 6);
			
			long endDay = DateUtils.getFragmentInDays(date, Calendar.MONTH);
			System.out.println(endDay);
	}

}
