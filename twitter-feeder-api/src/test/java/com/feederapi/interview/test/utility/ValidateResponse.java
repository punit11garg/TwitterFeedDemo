
package com.feederapi.interview.test.utility;

import java.util.Map;
import java.util.Set;

import org.json.JSONException;
import org.json.JSONObject;
import org.testng.Assert;

import com.feederapi.interview.common.utility.Constants;
import com.feederapi.interview.common.utility.RestAssuredUtil;

import io.restassured.response.Response;

/**
 * A public class which contains functions to validate response
 * @author PUNIT GARG
 *
 */
public class ValidateResponse {

	static int responseParamValue = 0;
	static String inputParamValue = "";
	static int expectedParamValue = 0;

	/**
	 * A common function should be created for validation of any param. However,
	 * since limited access was granted, so could only make for these 3 calls. This function
	 * will validate response on the basis of per, page and Filter params
	 * @author PUNIT GARG
	 * @param response
	 * @param dataInputMap
	 * @throws JSONException
	 */
	public static void validateResponse(Response response, Map<Object, Object> dataInputMap) throws JSONException {
		
		//Validate response code to 200
		validateResponseStatusCode(response);
		JSONObject JSONResponseBody = RestAssuredUtil.getJsonResponseBody(response);
		
		Set<Object> inputQueryParams = dataInputMap.keySet();
		
		//Validate response for each query param passed in call
		for (Object inputQueryParam : inputQueryParams) {
			inputParamValue = dataInputMap.get(inputQueryParam).toString();

			if (inputQueryParam.toString().equals(Constants.GET_PAGE_QUERY_PARAM)) {
				expectedParamValue = (inputParamValue.equals("")) ? 1 : Integer.parseInt(inputParamValue);
				ValidateResponse.validateCurrentPage(RestAssuredUtil.getCurrentPage(JSONResponseBody),
						expectedParamValue);
			}
			if (inputQueryParam.toString().equals(Constants.GET_PER_QUERY_PARAM)) {
				expectedParamValue = (inputParamValue.equals("")) ? 0 : Integer.parseInt(inputParamValue);
				ValidateResponse.validateCountPerPage(RestAssuredUtil.getCountLimitPage(JSONResponseBody),
						expectedParamValue);
			}
			if (inputQueryParam.toString().equals(Constants.GET_FILTER_QUERY_PARAM)) {
				ValidateResponse.validateFilteredCount(response,
						inputQueryParam.toString());
			}

		}

	}

	/**
	 * Verify current page in response
	 * @author PUNIT GARG
	 * @param actualPageCount
	 * @param expectedPageCount
	 * @throws JSONException
	 */
	public static void validateCurrentPage(int actualPageCount, int expectedPageCount) throws JSONException {
		// Expected Page can't be 0. So, if 0 is passed then set to 1
		expectedPageCount = (expectedPageCount == 0) ? 1 : Math.abs(expectedPageCount);

		Assert.assertEquals(actualPageCount, expectedPageCount,
				"Expected page:" + expectedPageCount + ", actual page: " + actualPageCount);
	}

	/**
	 * Verify count limit per page in response
	 * @author PUNIT GARG
	 * @param actualCountPerPage
	 * @param expectedCountPerPage
	 * @throws JSONException
	 */
	public static void validateCountPerPage(int actualCountPerPage, int expectedCountPerPage) throws JSONException {
		// Max limit per page is 100. so, limit the expected value to 100.
		expectedCountPerPage = (expectedCountPerPage > 100) ? 100 : Math.abs(expectedCountPerPage);
		
		Assert.assertEquals(actualCountPerPage, expectedCountPerPage,
				"Expected limit:" + expectedCountPerPage + ", actual limit:" + actualCountPerPage);
	}

	/**
	 * Verify that post count for any feed other than twitter must be 0.
	 * @author PUNIT GARG
	 * @param response
	 * @param filterName
	 * @throws JSONException
	 */
	public static void validateFilteredCount(Response response, String filterName) throws JSONException {
		JSONObject JSONResponseBody = RestAssuredUtil.getJsonResponseBody(response);

		//Verify the source. In this case, it will always be Twitter but should be checked dynamically
		Assert.assertEquals(RestAssuredUtil.getSource(JSONResponseBody), Constants.FEED_SOURCE, "Feed Source is Twitter");
		
		//Posts count for any feed other than Twitter must be 0
		if (filterName.toLowerCase().contains("twitter") || filterName.equalsIgnoreCase(""))
			Assert.assertEquals(RestAssuredUtil.getItemsLength(JSONResponseBody) == 0, true,
					"Number of posts for any feed other than twitter is 0");
		else
			Assert.assertEquals(RestAssuredUtil.getItemsLength(JSONResponseBody) >= 0, true,
					"Number of posts for twitter feed should be >= 0");
		
	}

	/**
	 * Verify response status code should be 200
	 * @param response
	 */
	public static void validateResponseStatusCode(Response response) {
		response.then().statusCode(200);

	}
}
