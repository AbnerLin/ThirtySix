package com.thirtySix.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

public class TimeFormatter {

	private Logger logger = Logger.getLogger(this.getClass());

	private final SimpleDateFormat spf = new SimpleDateFormat(
			"yyyy/MM/dd HH:mm:ss");

	private static TimeFormatter instance = null;

	public static TimeFormatter getInstance() {
		if (instance == null)
			instance = new TimeFormatter();
		return instance;
	}

	public String getTime() {
		String result = "";
		try {
			Date now = new Date();
			result = spf.format(now);
		} catch (Exception e) {
			logger.error("解析時間發生錯誤。", e);
		}
		return result;
	}

	public String getTime(Date date) {
		String result = "";
		try {
			result = spf.format(date);
		} catch (Exception e) {
			logger.error("解析時間發生錯誤。", e);
		}
		return result;
	}

}
