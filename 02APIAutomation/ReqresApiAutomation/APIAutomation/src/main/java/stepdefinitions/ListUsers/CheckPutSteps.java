package stepdefinitions.ListUsers;

import java.net.http.HttpResponse;
import java.util.Map;

import com.sun.tools.javac.util.List;

import common.ApiUtils;
import common.JsonUtils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import junit.framework.Assert;

public class CheckPutSteps {
	String requestBody, url, method ="";
	HttpResponse<String> response = null;
	ApiUtils apiUtils = new ApiUtils();
	JsonUtils jsonUtils = new JsonUtils();
	@Given("I have URL and method and Request body file name")
	public void i_have_url_and_method_and_request_body_file_name(List<Map<String, String>> givenTable) {
		Map<String, String> row1 = givenTable.get(0);
		url = row1.get("URL");
		method = row1.get("Method");
		String requestBodyName = row1.get("RequestBodyname");
		if(requestBodyName != "") {
			requestBodyName = jsonUtils.getRequestBodyByName(requestBodyName);	
		}	
	}
	@When("I send request")
	public void i_send_request_with_valid_url() {
		response = apiUtils.sendRequest(url, requestBody, method);	
	}	
	@Then("I check status code after put")
	public void i_check_status_code() {
	    int actualStatusCode = response.statusCode();
	    System.out.println(actualStatusCode);
	    Assert.assertEquals(actualStatusCode, 200);
	}

}
