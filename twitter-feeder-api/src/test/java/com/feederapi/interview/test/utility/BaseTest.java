package com.feederapi.interview.test.utility;

import java.io.IOException;
import java.net.URISyntaxException;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.feederapi.interview.common.utility.CommonUtils;
import com.feederapi.interview.common.utility.Constants;
import com.feederapi.interview.common.utility.RestAssuredUtil;

import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.SpecificationQuerier;

/**
 * Base class which will do set up before executing every test class
 * 
 * @author PUNIT GARG
 *
 */
public class BaseTest {

	static Logger log = Logger.getLogger(BaseTest.class.getName());
	protected static ExtentHtmlReporter htmlReporter;
	protected static ExtentReports extent;
	protected static ExtentTest test;
	protected RequestSpecification request;

	/**
	 * Set the extent report parameters
	 * @throws IOException 
	 * 
	 * @throws URISyntaxException
	 */
	@BeforeSuite
	public void suitelevelsetup() throws IOException, URISyntaxException {
		htmlReporter = new ExtentHtmlReporter(Constants.EXTENT_REPORT_FILE);
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("Test API Base Path", CommonUtils.getProperty("baseUri") + CommonUtils.getProperty("basePath"));

		htmlReporter.config().setChartVisibilityOnOpen(true);
		htmlReporter.config().setDocumentTitle("Automation Test Report");
		htmlReporter.config().setReportName("Test Report for Twitter Feed API");
		htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
		htmlReporter.config().setTheme(Theme.DARK);
	}

	/**
	 * Initialize base uri (including path) before every class
	 * 
	 * @throws IOException
	 * @throws JSONException
	 * @throws URISyntaxException
	 */
	@BeforeClass
	protected void setup() throws IOException, JSONException, URISyntaxException {
		// Test Setup
		RestAssuredUtil.setBaseURI(); // Setup Base URI
		RestAssuredUtil.setBasePath(); // Setup Base Path
		RestAssuredUtil.setContentType(ContentType.JSON); // Setup Content Type
		request = RestAssuredUtil.getRequestSpec();
		log.info("Request Base Path: " + SpecificationQuerier.query(request).getURI());
		ValidateResponse.validateResponseStatusCode(RestAssuredUtil.getResponse(request));

	}

	/**
	 * Writes the result in the extent report after every test method execution
	 * 
	 * @param result
	 */
	@AfterMethod
	public void getResult(ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE) {
			test.log(Status.FAIL,
					MarkupHelper.createLabel(result.getName() + " Test case Failed for input query params:"
							+ result.getParameters()[0].toString(), ExtentColor.RED));
			test.fail(result.getThrowable());
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			test.log(Status.PASS,
					MarkupHelper.createLabel(result.getName() + " Test Case Passed for input query params: "
							+ result.getParameters()[0].toString(), ExtentColor.GREEN));
		} else {
			test.log(Status.SKIP,
					MarkupHelper.createLabel(
							result.getName() + " Test Case Skipped and not executed for input query params: "
									+ result.getParameters()[0].toString(),
							ExtentColor.ORANGE));
			test.skip(result.getThrowable());
		}
	}

	/**
	 * Flush extent report after suite finishes
	 */
	@AfterSuite
	public void tearDown() {
		extent.flush();
	}
}
