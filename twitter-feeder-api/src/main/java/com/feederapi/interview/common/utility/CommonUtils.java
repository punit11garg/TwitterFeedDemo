package com.feederapi.interview.common.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * Common utility class which will contain common supplementary functions
 * @author PUNIT GARG
 *
 */
public class CommonUtils {

	static Logger log = Logger.getLogger(CommonUtils.class.getName());

	private static Properties testProperties = null;
	private static final String DATE_FORMAT = "yyyy-MM-dd";
	static ClassLoader classLoader = CommonUtils.class.getClassLoader();
		

	/**
	 * Get the value of property from config.properties file.
	 * @author PUNIT GARG
	 * @param propertyName
	 * @return
	 * @throws IOException
	 * @throws URISyntaxException 
	 */
	public static String getProperty(String propertyName) throws IOException, URISyntaxException {
		log.info("Getting property: " + propertyName);
		URL resource = classLoader.getResource(Constants.CONFIG_PROPERTIES_FILE_NAME);
		if (testProperties == null) {
			testProperties = new Properties();
			InputStream inputStream = new FileInputStream(new File(resource.toURI()));
			testProperties.load(inputStream);
			inputStream.close();
		}

		// get the property value and print it out
		return testProperties.getProperty(propertyName);
	}

	/**
	 * Get today's date in "yyyy-mm-dd" format
	 * @return
	 * @throws Exception
	 */
	public static String getCurrentDate() throws Exception {
		SimpleDateFormat df = new SimpleDateFormat(DATE_FORMAT);
		return df.format(new Date());
	}

}
