package TimeUtil;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeUtil {
	/**
	 * 获取系统当前日期时间 年月日
	 * 
	 * @return 系统当前日期时间
	 */
	public static String getNowTime() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		return df.format(System.currentTimeMillis());
	}

	/**
	 * 获取系统当前日期时间 年月日时分秒
	 * 
	 * @return 系统当前日期时间
	 */
	public static String getNowDetailedTime() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return df.format(System.currentTimeMillis());
	}

	/**
	 * 获取当前时间前size dataType的日子
	 * @param dataType 类型 0 日 ,1 月 2 年,3 星期
	 * @param size 相差
	 * @return
	 */
	public static String getNowDetailedTime(int dataType,int size){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar c = Calendar.getInstance();
		String dataTime="";
		switch (dataType){
			case 0:
				c.setTime(new Date());
				c.add(Calendar.DATE, - size);
				Date d = c.getTime();
				dataTime = format.format(d);
				break;
			case 1:
				c.setTime(new Date());
				c.add(Calendar.MONTH, -size);
				Date m = c.getTime();
				dataTime = format.format(m);
				break;
			case 2:
				c.setTime(new Date());
				c.add(Calendar.YEAR, -size);
				Date y = c.getTime();
				dataTime = format.format(y);
				break;
			case 3:
				c.setTime(new Date());
				c.add(Calendar.DAY_OF_MONTH, -size);
				Date month = c.getTime();
				dataTime = format.format(month);
				break;
			default:
				c.setTime(new Date());
				dataTime = format.format(c.getTime());
				break;
		}
		return dataTime;
	}

	/**
	 * 获取当前日期所对应的学期
	 * 
	 * @return 当前学期
	 */
	public static String getNewSemester() {
		String date = getNowTime();
		int y = Integer.parseInt(date.split("-")[0]);
		int M = Integer.parseInt(date.split("-")[1]);
		if (M > 8) {
			return y + "-" + (y + 1) + "学年上学期";
		} else {
			return (y - 1) + "-" + (y) + "学年下学期";
		}

	}

	/**
	 * 获取对应日期所对应的学期 0当前日期 1-n前n日期对应的学期(1上一个学期 2上两个学期)
	 * 
	 * @param index
	 * @return
	 */
	public static String getSemester(int index) {
		String date = getNowTime();
		int y = Integer.parseInt(date.split("-")[0]);
		int M = Integer.parseInt(date.split("-")[1]);
		if (index % 2 == 0) {
			index /= 2;
			if (M > 8) {
				return (y - index) + "-" + (y + 1 - index) + "学年上学期";
			} else {
				return (y - 1 - index) + "-" + (y - index) + "学年下学期";
			}
		} else {
			index /= 2;
			if (M > 8) {
				return (y - 1 - index) + "-" + (y - 1 - index) + "学年下学期";
			} else {
				return (y - 1 - index) + "-" + (y - index) + "学年上学期";
			}

		}

	}

}
