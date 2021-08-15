package com.feederapi.interview.utility;

/**
 * Constant class to store input file path and Get query param.
 * @author PUNIT GARG
 *
 */
public class Constants {
	
	public static final String INPUT_EXCEL_FILE_NAME = "ExcelInputData.xlsx";
	public static final String CONFIG_PROPERTIES_FILE_NAME="config.properties";
	public static final String EXTENT_REPORT_FILE = System.getProperty("user.dir") + "/test-output/ExtentReport.html";
	public static final String GET_PER_QUERY_PARAM = "per";
	public static final String GET_PAGE_QUERY_PARAM = "page";
	public static final String GET_FILTER_QUERY_PARAM = "filter";
	public static final String GET_STARTING_AT_QUERY_PARAM = "starting_at";
	
	//Source for this test will alway be Twitter since only that feed is available
	public static final String FEED_SOURCE = "Twitter";

}
