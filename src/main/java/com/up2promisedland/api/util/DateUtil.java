package com.up2promisedland.api.util;

import java.util.Calendar;
import java.util.Date;

public class DateUtil {

	public static Date addMonths(Date date, int monthsToAdd) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.MONTH, monthsToAdd);
		return c.getTime();
	}

}
