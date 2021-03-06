package com.csy.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <p>Title: 时间格式转化</p>
 * <p>Description: </p>
 * <p>Copyright: </p>
 * <p>Company: INESA</p>
 * @author Wang Qiang
 * @version 1.0
 * <pre>Histroy:
 *       2014-11-20  Wang Qiang  Create
 * </pre>
*/
public class TimeFormatUtil {
	/**
	 * 日期转化为字符串函数
	 * @param date 要转化的日期
	 * @return 转化后的时间字符串
	 */
	public static String dateToString(Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		String time = formatter.format(date); 
		return time; 
	}
	
	/**
	 * 获取一个日期的格式
	 * @param date 要转化的日期
	 * @param dateFormat 格式化字符串
	 * @return 转化后的时间字
	 */
	public static String dateToString(Date date,String dateFormat) {
		SimpleDateFormat formatter = new SimpleDateFormat(dateFormat); 
		String time = formatter.format(date); 
		return time; 
	}
	
	/**
	 * 字符串转换到时间格式
	 * @param dateStr 需要转换的字符串
	 * @param formatStr 需要格式的目标字符串  举例 yyyy-MM-dd
	 * @return Date 返回转换后的时间
	 * @throws ParseException 转换异常
	 */
	public static Date StringToDate(String dateStr,String formatStr){
		DateFormat sdf=new SimpleDateFormat(formatStr);
		Date date=null;
		try {
			date = sdf.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
}
