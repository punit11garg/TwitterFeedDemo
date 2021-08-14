package com.feederapi.interview.utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.log4j.Logger;

public class TestUtil {

	static Logger log = Logger.getLogger(TestUtil.class.getName());

	private static Properties testProperties = null;
	private static final String DATE_FORMAT = "yyyy-MM-dd";
	private static final long ONE_DAY_MILLI_SECONDS = 24 * 60 * 60 * 1000;

	public static String getProperty(String propertyName) throws IOException {
		log.info("Getting property: " + propertyName);
		if (testProperties == null) {
			testProperties = new Properties();
			InputStream inputStream = new FileInputStream(
					System.getProperty("user.dir") + "/src/main/resources/config.properties");
			testProperties.load(inputStream);
			inputStream.close();
		}

		// get the property value and print it out
		return testProperties.getProperty(propertyName);
	}

	public static String getCurrentDate() throws Exception {
		SimpleDateFormat df = new SimpleDateFormat(DATE_FORMAT);
		return df.format(new Date());
	}

	public static String getNextDate() throws Exception {
		SimpleDateFormat df = new SimpleDateFormat(DATE_FORMAT);
		Date date = df.parse(getCurrentDate());
		long nextDayMilliSeconds = date.getTime() + ONE_DAY_MILLI_SECONDS;
		Date nextDate = new Date(nextDayMilliSeconds);
		return df.format(nextDate);
	}

	public static String getPreviousDate() throws Exception {
		SimpleDateFormat df = new SimpleDateFormat(DATE_FORMAT);
		Date date = df.parse(getCurrentDate());
		long preDayMilliSeconds = date.getTime() - ONE_DAY_MILLI_SECONDS;
		Date preDate = new Date(preDayMilliSeconds);
		return df.format(preDate);

	}

}
