package stepdefinitions.LoginApi;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.testng.Assert;

import bsh.ParseException;
import common.ApiUtils;
import common.JsonUtils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CheckResponseWhenSendRequestSuccessfullySteps {
	String requestBody, url, method = "";
	HttpResponse<String> response = null;
	ApiUtils apiUtils = new ApiUtils();
	JsonUtils jsonUtils = new JsonUtils();
	//@Given("I have URL and method and Request body file name")
	public void i_have_url_and_method_and_request_body_file_name(List<Map<String, String>> givenTable) {
		// Lay dong du lieu dau tien (tuong ung dong t2 trong bang)
		Map<String, String> row1 = givenTable.get(0);
		url = row1.get("URL");
		method = row1.get("Method");
		String requestBodyName = row1.get("RequestBodyName");
		if(requestBodyName!="") {
			requestBody = jsonUtils.getRequestBodyByName(requestBodyName);
		}
	}

	//@When("I send request")
	public void i_send_request() {
		response = apiUtils.sendRequest(url, requestBody, method);
	}

	@Then("I validate status code and token")
	public void i_validate_status_code_and_token() {
		int actualStatusCode = response.statusCode();
		Assert.assertEquals(actualStatusCode, 200);
		String responseBody = response.body();
		if (responseBody != "") {
			JsonUtils jsonUils = new JsonUtils();
			String actualToken = jsonUils.getValueByKey(responseBody, "token");
			Assert.assertEquals(actualToken, "QpwL5tke4Pnpja7X4");
		} else {
			Assert.assertFalse(false);
		}
	}

}
