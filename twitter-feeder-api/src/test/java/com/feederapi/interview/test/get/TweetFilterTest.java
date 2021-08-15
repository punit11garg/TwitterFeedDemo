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
	 * @param queryParam = "per"
	 * @param value      = any integer
	 * @throws JSONException
	 */
	@Test(dataProvider = "test-data", description = "Test the filtering of response on account basis.")
	public void tweetFilterTest(String queryParamPer, String value1, String queryParamPage, String value2,
			String queryParamFilter, String value3) throws JSONException {
		log.info("Executing tweetFilterTest for following test input: [{" + queryParamPer + ":" + value1 + ","
				+ queryParamPage + ":" + value2 + "," + queryParamFilter + ":" + value3 + "}]");
		value1 = value1.equalsIgnoreCase("") ? "0" : value1;
		value2 = value2.equalsIgnoreCase("") ? "1" : value2;
		request.queryParam(queryParamPer, value1);
		request.queryParam(queryParamPage, value2);
		request.queryParam(queryParamFilter, value3);
		ValidateResponse.validateFilteredData(RestAssuredUtil.getResponse(request), Integer.parseInt(value1),
				Integer.parseInt(value2), value3);
	}

	// To execute the test case for different set of data
	@DataProvider(name = "test-data")
	private Object[][] dataProvFunc() throws IOException {
		// Read the input data from InputData.xlsx workbook
		return ExcelUtil.ReadVariant(Constants.GET_FILTER_QUERY_PARAM);
	}
}
