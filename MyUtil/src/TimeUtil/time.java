package TimeUtil;

import java.text.SimpleDateFormat;

public class time {
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
