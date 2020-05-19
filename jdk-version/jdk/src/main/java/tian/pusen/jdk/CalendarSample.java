/**
 * 
 */
package tian.pusen.jdk;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.concurrent.Callable;

/**
 * <p> Title:CalendarSample </p>
 * <p> Description:       </p>
 * <p> Company: Masapay   </p>
 * @author: tianpusen
 * @Date  : 2016年10月20日 下午5:14:41
 */
public final class CalendarSample {

	public static void main(String[] args) {
		lastDateAtPrecedingMonth();
	}
	// 上月最后一天
	public static Date lastDateAtPrecedingMonth() {
		System.out.println("设置时间限制有问题， 需要限制天数");
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		calendar.add(Calendar.MONTH, month-1); // 上月
		//
		System.out.println("上月的最后一个日有问题。 可以直接写清楚2月根据闰月28/29 1，3，5，7，8，10，12--31 天。 4，6，9，11--30天");
		int days = 0;
		switch (month) {
			case 2:
				if( (year % 4 == 0 && year % 100 != 0 ) || (year % 400 == 0 ) )
					days = 29;
				else
					days =28;
				break;
			case 1:
			case 3:
			case 5:
			case 7:
			case 8:
			case 10:
			case 12:
				days = 31;
				break;
			case 4:
			case 6:
			case 9:
			case 11:
				days = 31;
				break;
		}

//		int days = calendar.getActualMaximum(Calendar.MONTH);
		calendar.set(Calendar.DAY_OF_MONTH,  days-1) ;
		Date date = calendar.getTime();
		System.out.println(new Date(calendar.getTimeInMillis()));
		System.out.println(date);
		return date;
	}
	
//	public static Date getDateAtYYYYMMDDHHMMSS(int year, int month, int day, int hh, int mm, int ss){
//		Calendar calendar = Calendar.getInstance();
//		Calendar gregorianCalendar = new GregorianCalendar();
//		Calendar gregorianCalendarAtSpecificDay = new GregorianCalendar(2016, Calendar.JANUARY, 1);
//		Calendar gregorianCalendarAtSpecificDayAndTime = new GregorianCalendar(year, month-1, day, hh, mm, ss);
//		return gregorianCalendarAtSpecificDayAndTime.getTime();
//	}
}
