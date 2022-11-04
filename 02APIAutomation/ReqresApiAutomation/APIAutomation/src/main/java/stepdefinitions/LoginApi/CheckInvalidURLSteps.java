package stepdefinitions.LoginApi;

import java.io.File;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import common.ApiUtils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import junit.framework.Assert;

public class CheckInvalidURLSteps {
	String requestBody, method, url = "";
	HttpResponse<String> response = null;
	@Given("I have URL and Method and Request body file name")
	public void i_have_url_and_method_and_request_body_file_name(List<Map<String, String>> givenTable) {
	   Map<String, String> row1 = givenTable.get(0);
	   url = row1.get("URL");
	   method = row1.get("Method");
	   String requestBodyName = row1.get("RequestBodyName");
	   String filePath = System.getProperty("user.dir") 
				 + "/src/main/resources/LoginApi/" + requestBodyName;
		 try {
			 File file = new File(filePath);
			 if(file.exists()) {
				 requestBody = new String(Files.readAllBytes(Paths.get(filePath)));
			 }
		 }
			 catch(Exception e) {
				 System.out.println("file path: " + filePath);
				 System.out.println("request body: " + requestBody);
			 }
		 }

	@When("I send request with invalid url")
	public void i_send_request_with_invalid_url() {
	    ApiUtils apiUtils = new ApiUtils();
	    response = apiUtils.sendRequest(url, requestBody, method);
	}

	@Then("I validate status code and message")
	public void i_validate_status_code_anf_message() {
		int actualStatusCode = response.statusCode();
		System.out.println(actualStatusCode);
		Assert.assertEquals(actualStatusCode, 404);
	    
	}

}
