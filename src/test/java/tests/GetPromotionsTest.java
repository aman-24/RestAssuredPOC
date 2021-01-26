package tests;

import java.util.ArrayList;
import java.util.HashMap;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.testng.annotations.Test;

import base.TestBase;
import httprequests.EndPoints;
import io.qameta.allure.Feature;
import io.restassured.response.Response;

@Feature("Get promotions test")
public class GetPromotionsTest extends TestBase {

	/********************  Test 1  **************************
	 * Test with all positive value.
	 * 1. Validate the JSON response as per following and add assertions respectively.
	 * 	Response HTTP Status code should be 200
	 * 	Assert following json properties present in the response for each object
	 * 2. Perform following assertions
	 * 	Example:
	 * 	promotionId: any string value,
	 * 	programType: EPISODE or MOVIE or SERIES or SEASON
	 */
	@Test(testName = "Get all promotions available")
	public void getAllPromotion() {

		// Values required for further assertion.
		ArrayList<String> progType = new ArrayList<String>();
		progType.add("episode");
		progType.add("movie");
		progType.add("series");
		progType.add("season");

		//get api key for prams from property file.
		HashMap<String, String> prams = new HashMap<String, String>();
		prams.put("apikey", EndPoints.getAPIKey());

		//make a get request on the URL from property file.
		Response response = this.request.getRequest(EndPoints.getPromotionsEndpoint(), prams);

		//Validate response and print details omn report.
		this.validateAndAttachResponse(HttpStatus.SC_OK, "getAllPromtionTest", response);
		//Validate schema of response which solve the assignment question 1 and 2.
		this.validateJsonSchema(response, "getPromotion");
		//get all program type from response.
		ArrayList<ArrayList<String>> progTypeResponse = response.path("promotions.properties.programType");
		//iterate through all the retrieved program type and assert through each of them.
		for (ArrayList<String> arr : progTypeResponse) {
			Assert.assertTrue(progType.contains(arr.get(0)));
		}
	}

	/********************  Test 2  **************************
	 * Test with wrong api key value.
	 * 3. Validate the response for a request with invalid value passed for “apikey” query parameter.
	 * 	HTTP Status code 403
	 * 	“requestId” should not be null
	 * 	“code” should be “8001” and "message" should be "invalid api key"
	 *
	 */
	@Test(testName = "get promotions negative - with wrong token")
	public void getPromotion_wrongToken_Negative(){
		//get api key for prams from property file.
		HashMap<String, String> prams = new HashMap<String, String>();
		prams.put("apikey", "random string");

		//make a get request on the URL from property file.
		Response response = this.request.getRequest(EndPoints.getPromotionsEndpoint(), prams);

		//Validate response and print details omn report.
		this.validateAndAttachResponse(HttpStatus.SC_FORBIDDEN, "getAllPromtionTest", response);
		//Validate schema of response which solve the assignment question 1 and 2.
		this.validateJsonSchema(response, "getPromotion-Negative");

		//validate all response fields
		response.then().body("error.message", Matchers.equalTo("invalid api key"))
		.body("error.code", Matchers.equalTo("8001"))
		.body("error.requestId", Matchers.notNullValue());
	}

}
