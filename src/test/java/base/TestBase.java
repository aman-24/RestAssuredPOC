package base;

import java.io.IOException;
import java.util.Properties;

import org.testng.Assert;
import org.testng.annotations.BeforeSuite;

import httprequests.EndPoints;
import httprequests.Request;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.module.jsv.JsonSchemaValidatorSettings;
import io.restassured.response.Response;
import utils.FileUtils;

public class TestBase {

	private FileUtils fileUtils = new FileUtils();
	protected Request request = new Request();

	/***
	 * This method will execute before the test suite starts and calls the
	 * configureEndpoints method
	 * 
	 * @throws IOException
	 */
	@BeforeSuite
	public void setup() throws IOException {
		configureEndpoints();
	}

	/***
	 * This method reads the endpoints.properties files and load them into a
	 * hash-map which is finally being used by the Endpoints class to set various
	 * endpoints
	 * 
	 * @throws IOException
	 */
	private void configureEndpoints() throws IOException {
		Properties properties = fileUtils.readPropertiesFile("endpoints.properties");
		EndPoints.endPoints = fileUtils.loadPropertiesFileInHashMap(properties);
	}

	/***
	 * validates the api response status code with expected status code attach the
	 * api response in the allure report
	 * 
	 * @param expectedStatusCode
	 * @param attachmentName
	 * @param response
	 */
	@Step
	public void validateAndAttachResponse(int expectedStatus, String attachmentName, Response response) {
		Allure.addAttachment(attachmentName + " - Request", request.requestWriter.toString());
		Allure.addAttachment(attachmentName + " - Response", "Status code: " + response.statusCode()
				+ System.lineSeparator() + "Response:" + System.lineSeparator() + response.asString());
		Assert.assertEquals(response.getStatusCode(), expectedStatus, "Status code not matched");
	}

	/***
	 * validates the api response scehma that if al required feilds are present and
	 * are of same type as expectedd.
	 * 
	 * @param response
	 * @param serviceName
	 */
	@Step
	public void validateJsonSchema(Response response, String serviceName) {
		response.then().assertThat()
				.body(JsonSchemaValidator
						.matchesJsonSchemaInClasspath("jsons/response_schema/" + serviceName + "_Response_Schema.json")
						.using(JsonSchemaValidatorSettings.settings().with().checkedValidation(false)));

	}
}
