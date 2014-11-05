package org.readbook.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	
	/**
     * 获取当前日期是星期几<br>
     * 
     * @param dt
     * @return 当前日期是星期几
     */
    public static String getWeekOfDate(Date dt) {
        String[] weekDays = {"Sun", "Mon", "Tus", "Wen", "Sth", "Fri", "星期六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);

        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;

        return weekDays[w];
    }
    
    /**
     * 获取当前日期是星期几<br>
     * 
     * @param dt
     * @return 当前日期是星期几
     */
    public static String getMonthOfDate(Date dt) {
        String[] weekDays = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);

        int w = cal.get(Calendar.MONTH);
        if (w < 0)
            w = 0;

        return weekDays[w];
    }

    public static String[] formatTime(String fromTime){
    	try {
			String format = "yyyy-MM-dd HH:mm";
			SimpleDateFormat df = new SimpleDateFormat(format);
			Date d = new Date(Long.parseLong(fromTime) * 1000);
			String result = df.format(d);
			return result.split(" ");
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	return null;
    }
    
    public static String formatTime2(String fromTime){
    	try {
			String format = "yyyy-MM-dd HH:mm";
			SimpleDateFormat df = new SimpleDateFormat(format);
			Date d = new Date(Long.parseLong(fromTime) * 1000);
			String result = df.format(d);
			return result;
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	return fromTime;
    }
    
    public static String formatTime4(String fromTime){
    	try {
			String format = "MM-dd";
			SimpleDateFormat df = new SimpleDateFormat(format);
			Date d = new Date(Long.parseLong(fromTime) * 1000);
			String result = df.format(d);
			return result;
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	return fromTime;
    }
    
    public static String formatTime3(String fromTime){
    	try {
			String format = "yyyy-MM-dd HH:mm:ss";
			SimpleDateFormat df = new SimpleDateFormat(format);
			Date d = new Date(Long.parseLong(fromTime) * 1000);
			String result = df.format(d);
			return result;
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	return fromTime;
    }
    

	public static String getTimeString(String fromTime){
		try {
			String format = "yyyy-MM-dd HH:mm:ss";
			SimpleDateFormat df = new SimpleDateFormat(format);
			Date d1 = df.parse(dateFormat(format, new Date()));
		    Date d2 = df.parse(fromTime);
		    long diff = d1.getTime() - d2.getTime();
		    long distance = diff / (60 * 24);
//			
//			Date now = df.parse(dateFormat(format, new Date()));
//			Date date= df.parse(fromTime);
//			long l=now.getTime()-date.getTime();
//			long day=l/(24*60*60*1000);
//			long hour=(l/(60*60*1000)-day*24);
//			long min=((l/(60*1000))-day*24*60-hour*60);
//			long distance=(l/1000-day*24*60*60-hour*60*60-min*60);
			String timestamp;
		    if (distance < 0) distance = 0;
		    
		    if (distance < 60) {
		    	timestamp = distance + "秒前";
		    }
		    else if (distance < 60 * 60) {  
		        distance = distance / 60;
		        timestamp =distance + "分钟前";
		    }  
		    else if (distance < 60 * 60 * 24) {
		        distance = distance / 60 / 60;
		        timestamp = distance + "小时前";
		    }
		    else if (distance < 60 * 60 * 24 * 7) {
		        distance = distance / 60 / 60 / 24;
		        timestamp = distance + "天前";
		    }
		    else if (distance < 60 * 60 * 24 * 7 * 4) {
		        distance = distance / 60 / 60 / 24 / 7;
		        timestamp = distance + "周前";
		    }
		    else {
		    	timestamp = getTime(fromTime);
		    }
		    return timestamp;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return getTime(fromTime);
	}
	
	public static String getTimeString3(String fromTime){
		try {
			String format = "yyyy-MM-dd HH:mm:ss";
			SimpleDateFormat df = new SimpleDateFormat(format);
			Date d1 = df.parse(dateFormat(format, new Date()));
		    Date d2 = df.parse(fromTime);
		    long distance = Math.abs(d2.getTime() - d1.getTime()) / 1000;// 秒
			String timestamp;
			if (distance < 60 * 60 * 24) {
				timestamp = getTime3(fromTime);
		    } else if (distance < 60 * 60 * 24 * 2) {
		    	timestamp = "昨天";
		    }else{
		    	timestamp = getTime2(fromTime);
		    }
		    return timestamp;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return getTime2(fromTime);
	}
	
	public static boolean checkTime(String fromTime1, String fromTime2){
		try {
			String format = "yyyy-MM-dd HH:mm:ss";
			SimpleDateFormat df = new SimpleDateFormat(format);
			Date d1 = df.parse(fromTime1);
		    Date d2 = df.parse(fromTime2);
		    long diff = Math.abs(d2.getTime() - d1.getTime()) / 1000;// 秒
		    return diff > 60 * 5;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}
	
	//
	//String format = "yyyy-MM-dd HH:mm:ss";
	public static String getTimeString2(String fromTime){
		try {
			String timestamp;
			Calendar cal = Calendar.getInstance();
			int birthMonth = cal.get(Calendar.MONTH) + 1;
			int birthDay = cal.get(Calendar.DAY_OF_MONTH);
		    fromTime = fromTime.replace("-", "");
			int month = Integer.parseInt(fromTime.substring(4, 6));
			int day = Integer.parseInt(fromTime.substring(6, 8));
			String h = fromTime.substring(9, 14);
			if(month != birthMonth){
				timestamp = month+"月"+day+"日  "+ h;
			}else{
				int d = Math.abs(birthDay - day);
				if(d <= 1){
				    timestamp = "今天  "+ h;
				}else if(d == 2){
				    	timestamp = "昨天  " + h;
				   }else{
					   timestamp = month+"月"+day+"日  "+ h;
				   }
			}
		    return timestamp;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return getTime(fromTime);
	}
	
	public static String dateFormat(String format, Date date) {
		SimpleDateFormat time = new SimpleDateFormat(format);
		return time.format(date);
	}
	
	public static String getDay(long time){
		//s / 60 = m / 60 = h / 24 = d
		if(time <= 0){
			return "已下架";
		}
		if(time <= 60){
			return time + "秒";
		}
		long m = time / 60;
		if(m < 60){
			return m + "分" + (time % 60) + "秒";
		}
		long h = m / 60;
		if(h < 24){
			return h + "小时" + (m % 60) + "分";
		}
		long d = h / 24;
		return d + "天以上";
	}
	
	public static String getDay2(long time){
		//s / 60 = m / 60 = h / 24 = d
		if(time <= 60){
			return time + "秒";
		}
		long m = time / 60;
		if(m < 60){
			return m + "分" + (time % 60) + "秒";
		}
		long h = m / 60;
		if(h < 24){
			return h + "小时" + (m % 60) + "分";
		}
		long d = h / 24;
		return d + "天";
	}
	
	private static String getTime(String time){
		if (null == time || "".equals(time))
			return null;
		time = time.replace("-", "");
		String result = "";
		String year = time.substring(0, 4);
		String month = time.substring(4, 6);
		String day = time.substring(6, 8);
		String h = time.substring(9, 14);
		result = year + "年" + month + "月" + day + "日  "+h;
		return result;
	}
	
	private static String getTime2(String time){
		if (null == time || "".equals(time))
			return null;
		time = time.replace("-", "");
		String result = "";
		String year = time.substring(2, 4);
		String month = time.substring(4, 6);
		String day = time.substring(6, 8);
		String h = time.substring(9, 14);
		result = year + "-" + month + "-" + day;
		return result;
	}
	
	private static String getTime3(String time){
		if (null == time || "".equals(time))
			return null;
		time = time.replace("-", "");
		String result = "";
//		String year = time.substring(2, 4);
//		String month = time.substring(4, 6);
//		String day = time.substring(6, 8);
		String h = time.substring(9, 14);
		result = h;
		return result;
	}
	
	public static String getWeek(String time){
		String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
//		String[] weekDays = { "星期四", "星期五", "星期六", "星期日", "星期一", "星期二", "星期三",};
		if (null == time || "".equals(time))
			return null;
		time = time.replace(".", "");
		String year = time.substring(0, 4);
		String month = time.substring(4, 6);
		String day = time.substring(6, 8);
        Calendar cal = Calendar.getInstance();
        cal.set(Integer.parseInt(year), Integer.parseInt(month)-1, Integer.parseInt(day));
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        return weekDays[w];
	}
	//2014-09-11 11:03:33
	public static String getCurrentTime(){
		Calendar cal = Calendar.getInstance();
		int mYear = cal.get(Calendar.YEAR);    
		int mMonth = cal.get(Calendar.MONTH) + 1;  
		int mDay = cal.get(Calendar.DAY_OF_MONTH);  
		String month = ""+mMonth;
		if(mMonth < 10){
			month = "0"+mMonth;
		}
		String day = ""+mDay;
		if(mDay < 10){
			day = "0"+mDay;
		}
		int h = cal.get(Calendar.HOUR_OF_DAY);
		int m = cal.get(Calendar.MINUTE);
		int s = cal.get(Calendar.SECOND);
		String hour = ""+h;
		if(h < 10){
			hour = "0"+h;
		}
		String minute = ""+m;
		if(m < 10){
			minute = "0"+m;
		}
		String second = ""+s;
		if(s < 10){
			second = "0"+s;
		}
		String result = mYear+"-"+month+"-"+day+" "+hour+":"+minute+":"+second;
		return result;
	}
	
	/**
	 * 
	 * @param year
	 * @param month
	 * @param day
	 * @return
	 */
	public static int isUntilnow(int year, int month, int day){
		Calendar cal = Calendar.getInstance();
		int mYear = cal.get(Calendar.YEAR);    
		int mMonth = cal.get(Calendar.MONTH) + 1;  
		int mDay = cal.get(Calendar.DAY_OF_MONTH);
		if(year > mYear){
			return 1;
		}
		if(year == mYear && month > mMonth){
			return 1;
		}
		if(year == mYear && month == mMonth && day >= mDay){
			return 1;
		}
		return 0;
	}
	
	public static boolean isTimeAvilable(int year, int month){
		Calendar cal = Calendar.getInstance();
		int mYear = cal.get(Calendar.YEAR);    
		int mMonth = cal.get(Calendar.MONTH) + 1; 
		if(year > mYear){
			return false;
		}
		if(year == mYear && month > mMonth){
			return false;
		}
		return true;
	}
	
	public static boolean isTimeAvilable(int syear, int smonth, int eyear, int emonth){
		if(syear > eyear){
			return false;
		}
		if(syear == eyear && smonth > emonth){
			return false;
		}
		return true;
	}
	
	/**NSDate *curtime=[NSDate dateWithDateTimeString:msg.time];
    //self.timeLabel.text=[curtime formattedExactRelativeTimestamp];
    //仿微信显示日期时间
    NSTimeInterval time = [curtime timeIntervalSince1970];
	NSTimeInterval now = [[NSDate date] timeIntervalSince1970];
	int distance = now - time;
    if (distance < 60 * 60 * 24) {
        self.timeLabel.text = [curtime formattedDateWithFormatString:@"HH:mm"];
    }
    else if (distance < 60 * 60 * 24 * 2) {
        self.timeLabel.text = @"昨天";
    }
    else if (distance < 60 * 60 * 24 * 7) {
        switch ([curtime weekday]) {
            case 1:
                self.timeLabel.text=@"星期日";
                break;
            case 2:
                self.timeLabel.text=@"星期一";
                break;
            case 3:
                self.timeLabel.text=@"星期二";
                break;
            case 4:
                self.timeLabel.text=@"星期三";
                break;
            case 5:
                self.timeLabel.text=@"星期四";
                break;
            case 6:
                self.timeLabel.text=@"星期五";
                break;
            case 7:
                self.timeLabel.text=@"星期六";
                break;
            case 0:
                self.timeLabel.text=@"星期日";
                break;
        }
    }
    else{
        self.timeLabel.text = [curtime formattedDateWithFormatString:@"yy-MM-dd"];
    }*/
}
