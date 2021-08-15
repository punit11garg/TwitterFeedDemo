package com.feederapi.interview.test.get;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.feederapi.interview.test.util.*;
import com.feederapi.interview.utility.Constants;
import com.feederapi.interview.utility.RestAssuredUtil;

/**
 * Test Class to test per&page query params for get call.
 * 
 * @author PUNIT GARG
 *
 */
public class TweetCountPageTest extends BaseTest {
	static Logger log = Logger.getLogger(TweetCountTest.class.getName());

	/**
	 * This will test the number of tweets to be shown per page as well as the
	 * particular page of the response
	 * 
	 * @author PUNIT GARG
	 * @param queryParamPer  = "per"
	 * @param value1         = int
	 * @param queryParamPage = "page"
	 * @param value2         = int
	 * @throws JSONException
	 * @throws NumberFormatException
	 */
	@Test(dataProvider = "test-data", description = "Test the number of twits (max 100) to be displayed per page and the current page")
	public void tweetCountAndPageTest(String queryParamPer, String value1, String queryParamPage, String value2)
			throws NumberFormatException, JSONException {
		log.info("Executing tweetCountAndPageTest for following test input: [{" + queryParamPer + ":" + value1 + ","
				+ queryParamPage + ":" + value2 + "}]");
		value1 = value1.equalsIgnoreCase("") ? "0" : value1;
		value2 = value2.equalsIgnoreCase("") ? "1" : value2;
		request.queryParam(queryParamPer, value1);
		request.queryParam(queryParamPage, value2);
		ValidateResponse.validateResponse(RestAssuredUtil.getResponse(request), Integer.parseInt(value1),
				Integer.parseInt(value2));

	}

	// To execute the test case for different set of data
	@DataProvider(name = "test-data")
	private Object[][] dataProvFunc() throws IOException {
		return ExcelUtil.ReadVariant(Constants.GET_PAGE_QUERY_PARAM);
	}

}
