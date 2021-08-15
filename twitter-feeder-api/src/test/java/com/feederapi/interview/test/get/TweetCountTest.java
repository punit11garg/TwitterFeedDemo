package com.feederapi.interview.test.get;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.feederapi.interview.test.util.BaseTest;
import com.feederapi.interview.test.util.ExcelUtil;
import com.feederapi.interview.test.util.ValidateResponse;
import com.feederapi.interview.utility.Constants;
import com.feederapi.interview.utility.RestAssuredUtil;

/**
 * Test Class to test per query params for get call.
 * 
 * @author PUNIT GARG
 *
 */
public class TweetCountTest extends BaseTest {

	static Logger log = Logger.getLogger(TweetCountTest.class.getName());

	/**
	 * This is to test the no. of tweets to be shown per page. JSON response will
	 * show page 1 and limit will be set to the value. Maximum 100 tweets can be
	 * displayed per page
	 * 
	 * @author PUNIT GARG
	 * @param queryParam = "per"
	 * @param value      = any integer
	 * @throws JSONException
	 */
	@Test(dataProvider = "test-data", description = "Test the number of twits (max 100) to be displayed per page")
	public void tweetCountPerPageTest(String queryParam, String value) throws JSONException {
		request.queryParam(queryParam, value);
		log.info("Executing tweetCountPerPageTest for following test input: {" + queryParam + ":" + value + "}");

		// if per is blank then limit is set to 0 in response.
		value = (value.equals("")) ? "0" : value;

		// Page param is 1 if no value is provided. This value is passed from here to
		// make the validation function more generic
		ValidateResponse.validateResponse(RestAssuredUtil.getResponse(request), Integer.parseInt(value), 1);
	}

	// To execute the test case for different set of data
	@DataProvider(name = "test-data")
	private Object[][] dataProvFunc() throws IOException {

		// Read the input data from InputData.xlsx workbook
		return ExcelUtil.ReadVariant(Constants.GET_PER_QUERY_PARAM);
	}

}
