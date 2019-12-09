package TimeUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Time比较 {

	public static void main(String[] args) throws ParseException {
		// TODO Auto-generated method stub
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		Date date1=df.parse("2050-12-31 23:59:59.0");
		Date date2=new Date();
		System.out.println(df.format(new Date()));
		if(date1.after(date2)) {
			System.out.println("yes");
		}
		if(date2.before(date1)) {
			System.out.println("no");
		}
	}

}
