package com.feederapi.interview.test.get;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.feederapi.interview.test.util.BaseTest;
import com.feederapi.interview.test.util.ExcelUtil;
import com.feederapi.interview.test.util.ValidateResponse;
import com.feederapi.interview.utility.Constants;
import com.feederapi.interview.utility.RestAssuredUtil;

import io.restassured.response.Response;

/**
 * Test Class To test three query params (per, page & filter) in 1 get call.
 * Could not test because max limit was reached
 * 
 * @author PUNIT GARG
 *
 */
public class TweetFilterTest extends BaseTest {

	static Logger log = Logger.getLogger(TweetFilterTest.class.getName());

	/**
	 * This is to test the no. of tweets to be shown per page. JSON response will
	 * show page 1 and limit will be set to the value. Maximum 100 tweets can be
	 * displayed per page
	 * 
	 * @author PUNIT GARG
	 * @param dataInputMap
	 * @throws JSONException
	 */
	@Test(dataProvider = "test-data", description = "Test the filtering of response on social account feed basis.")
	public void tweetFilterTest(Map<Object, Object> dataInputMap) throws JSONException {
		log.info("Executing tweetCountAndPageTest for following test input: [{" + dataInputMap.keySet().toString() + ":" + dataInputMap.values().toString() + "}]");
		Response response = RestAssuredUtil.getResponse(RestAssuredUtil.setQueryParamsHashMap(request, dataInputMap));
		ValidateResponse.validateResponseNew(response, dataInputMap);
	}

	// To execute the test case for different set of data
	@DataProvider(name = "test-data")
	private Object[][] dataProvFunc() throws IOException, URISyntaxException {
		// Read the input data from InputData.xlsx workbook
		return ExcelUtil.dataSupplier(Constants.GET_FILTER_QUERY_PARAM);
	}
}
