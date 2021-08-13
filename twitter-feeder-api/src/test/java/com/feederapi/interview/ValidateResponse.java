package com.feederapi.interview;

import java.util.HashMap;
import java.util.List;

import org.testng.Assert;

import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import utility.RestAssuredUtil;

public class ValidateResponse {

	public static int getPostCount(Response response) {
		HashMap<String, Object> postsMap = RestAssuredUtil.getJsonPath(response).get("posts");
		List<Object> itemList = (List<Object>) postsMap.get("items");
		return itemList.size();
	}

	public static int getCurrentPage(Response response) {
		HashMap<String, Object> postsMap = RestAssuredUtil.getJsonPath(response).get("posts");
		int currentPage = (Integer) postsMap.get("current_page");
		return currentPage;
	}

	public static void isPostCountExpected(Response response, int expectedCount, int totalPostCount) {
		ValidatableResponse validResponse = response.then();
		validResponse.statusCode(200);
		if (totalPostCount >= Math.abs(expectedCount))
			Assert.assertEquals(getPostCount(response), Math.abs(expectedCount), "Actual Post Count is: "
					+ getPostCount(response) + " , Expected Post Count is: " + Math.abs(expectedCount));
		else {
			Assert.assertEquals(getPostCount(response), totalPostCount, "Actual Post Count is: "
					+ getPostCount(response) + " , Expected Post Count is Total Post: " + totalPostCount);
		}
	}

	public static void isCurrentPageExpected(Response response, int pageNumber, int countPerPage, int totalPostCount) {
		int actualPageCount = getCurrentPage(response);
		if (countPerPage == 0) {
			pageNumber = (totalPostCount/100 == 0) ? 1: totalPostCount/100;
		}
		Assert.assertEquals(actualPageCount, Math.abs(pageNumber), "");
		boolean flag = (getPostCount(response) <= Math.abs(countPerPage));
		Assert.assertEquals(flag, true, "");


	}

}
