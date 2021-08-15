package com.feederapi.interview.test.get;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.feederapi.interview.test.util.BaseTest;
import com.feederapi.interview.test.util.ExcelUtil;
import com.feederapi.interview.test.util.ValidateResponse;
import com.feederapi.interview.utility.Constants;
import com.feederapi.interview.utility.RestAssuredUtil;

import io.restassured.response.Response;

/**
 * Test Class to test per&page query params for get call.
 * 
 * @author PUNIT GARG
 *
 */
public class TweetCountPageTest extends BaseTest {
	static Logger log = Logger.getLogger(TweetCountTest.class.getName());

	@BeforeClass
	@Override
	protected void setup() throws IOException, JSONException, URISyntaxException {
		super.setup();
		test = extent.createTest("tweetCountAndPageTest");
	}
	
	/**
	 * This will test the number of tweets to be shown per page as well as the
	 * particular page of the response
	 * 
	 * @author PUNIT GARG
	 * @param dataInputMap
	 * @throws JSONException
	 */
	@Test(dataProvider = "test-data", description = "Test the number of twits (max 100) per page and current page.")
	public void tweetCountPerPageTest(Map<Object, Object> dataInputMap) throws JSONException {
		log.info("Executing tweetCountAndPageTest for following test input: [{" + dataInputMap.keySet().toString() + ":" + dataInputMap.values().toString() + "}]");
		Response response = RestAssuredUtil.getResponse(RestAssuredUtil.setQueryParamsHashMap(request, dataInputMap));
		ValidateResponse.validateResponseNew(response, dataInputMap);
	}

	// To execute the test case for different set of data
	@DataProvider(name = "test-data")
	private Object[][] dataProvFunc() throws IOException, URISyntaxException {
		// Read the input data from InputData.xlsx workbook
		return ExcelUtil.dataSupplier(Constants.GET_PAGE_QUERY_PARAM);
	}

}
