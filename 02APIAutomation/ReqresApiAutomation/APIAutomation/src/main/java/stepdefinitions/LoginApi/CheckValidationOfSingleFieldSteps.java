package stepdefinitions.LoginApi;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.util.Map;

import org.testng.Assert;

import com.sun.tools.javac.util.List;

import common.ApiUtils;
import common.JsonUtils;
import io.cucumber.cienvironment.internal.com.eclipsesource.json.Json;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


public class CheckValidationOfSingleFieldSteps {
	String requestBodyName, url, method ="";
	HttpResponse<String> response = null;
	ApiUtils apiUtils = new ApiUtils();
	
	JsonUtils jsonUtils = new JsonUtils();
	@Given("I have login url and method and Request body file name")
	public void i_have_login_url_and_method_and_request_body_file_name(List<Map<String, String>> givenTable) {
		Map<String, String> row1 = givenTable.get(0);
		url = row1.get("URL");
		method = row1.get("Method");
		requestBodyName = row1.get("RequestBodyname");
		if(requestBodyName != "") {
			requestBodyName = jsonUtils.getRequestBodyByName(requestBodyName);	
		} 
	}

	@When("I send valid login request with {string} and {string}")
	public void i_send_valid_login_request_with_and(String givenFieldName, String givenValue) {
		File sourceFile = new File(jsonUtils.getFilePath(requestBodyName));
		File destinationFile = new File(jsonUtils.getFilePath("Copy" + requestBodyName));
		jsonUtils.copyJsonFile(sourceFile, destinationFile);
		String requestBody = jsonUtils.changeValueByFieldName(destinationFile, givenFieldName, givenValue);
		response = apiUtils.sendRequest(url, requestBody, method);
	}

	@Then("Response return {string} and {string}")
	public void response_return_and(String expectedStatusCode, String expectedErrorMessage) {
	   int actualStatusCode = response.statusCode();
	   String body = response.body();
	   String actualErrorMessage = jsonUtils.getValueByKey(body, "error");
	  Assert.assertEquals(actualStatusCode, expectedErrorMessage);
	  Assert.assertEquals(actualErrorMessage, expectedErrorMessage);
	}


}
