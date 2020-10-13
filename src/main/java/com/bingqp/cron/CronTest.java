package com.bingqp.cron;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
 
import org.quartz.CronExpression;
 
public class CronTest {

	public static void main(String[] args) {
		cronTest();
	}

	private static void cronTest() {
		try {
			CronExpression exp = new CronExpression("0 0 9 ? * TUE-FRI");
			SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
			Date d = new Date();
			int i = 0;
			// 循环得到接下来n此的触发时间点，供验证
			while (i < 10) {
				d = exp.getNextValidTimeAfter(d);
				System.out.println(df.format(d));
				++i;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}
 
