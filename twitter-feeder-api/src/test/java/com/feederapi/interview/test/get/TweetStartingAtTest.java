package com.feederapi.interview.test.get;

import java.io.IOException;

import org.json.JSONException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.feederapi.interview.test.util.BaseTest;
import com.feederapi.interview.test.util.ExcelUtil;
import com.feederapi.interview.test.util.ValidateResponse;
import com.feederapi.interview.utility.Constants;
import com.feederapi.interview.utility.RestAssuredUtil;
import com.feederapi.interview.utility.TestUtil;

/**
 * To test three query params (per, page & Starting At date) in 1 get call. 
 * @author PUNIT GARG
 *
 */
public class TweetStartingAtTest extends BaseTest {

	/**
	 * This is to test the no. of tweets to be shown per page on/after a starting date. JSON response will show maximum 100 tweets 
	 * 
	 * @author PUNIT GARG
	 * @param queryParam = "per"
	 * @param value      = any integer
	 * @throws JSONException
	 */
	@Test(dataProvider = "test-data", description = "Test the filtering of response on account basis.")
	public void tweetStartingAtTest(String queryParamPer, String value1, String queryParamPage, String value2,
			String queryParamStartingAt, String value3) throws JSONException {
		value1  = value1.equalsIgnoreCase("") ? "0" : value1;
		value2  = value2.equalsIgnoreCase("") ? "1" : value2;
		request.queryParam(queryParamPer, value1);
		request.queryParam(queryParamPage, value2);
		request.queryParam(queryParamStartingAt, value3);
		ValidateResponse.validateFilteredData(RestAssuredUtil.getResponse(request), Integer.parseInt(value1), Integer.parseInt(value2), value3);
	}

	// To execute the test case for different set of data
	@DataProvider(name = "test-data")
	private Object[][] dataProvFunc() throws Exception {
		// Read the input data from InputData.xlsx workbook
		return new Object[][] {
			{Constants.GET_PER_QUERY_PARAM, 1, Constants.GET_PAGE_QUERY_PARAM, 1, Constants.GET_STARTING_AT_QUERY_PARAM, TestUtil.getPreviousDate()}, 
			{Constants.GET_PER_QUERY_PARAM, 0, Constants.GET_PAGE_QUERY_PARAM, 0, Constants.GET_STARTING_AT_QUERY_PARAM, TestUtil.getNextDate()},
				{Constants.GET_PER_QUERY_PARAM, 1, Constants.GET_PAGE_QUERY_PARAM, 0, Constants.GET_STARTING_AT_QUERY_PARAM, "2021-08-13"}
		};
		//return ExcelUtil.ReadVariant(Constants.GET_FILTER_QUERY_PARAM);
	}
}
