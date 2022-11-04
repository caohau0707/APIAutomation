package stepdefinitions.ListUsers;

import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;

import common.ApiUtils;
import common.JsonUtils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import junit.framework.Assert;
import objects.users;



public class CheckGetListUsersSuccessfullySteps {
	String requestBody, url, method = "";
	HttpResponse<String> response = null;
	ApiUtils apiUtils = new ApiUtils();
	JsonUtils jsonUtils = new JsonUtils();
	//@Given("I have URL and method and Request body file name")
	public void i_have_url_and_method_and_request_body_file_name(List<Map<String, String>> givenTable) {
	Map<String, String> row1 = givenTable.get(0);	
	url = row1.get("URL");
	method = row1.get("Method");
	String requestBodyName = row1.get("RequestBodyName");
	if(requestBodyName !="") {
		requestBodyName = jsonUtils.getRequestBodyByName(requestBodyName);
	}
	}
	//@When("I send request")
	public void i_send_request_with_valid_url() {
		response = apiUtils.sendRequest(url, requestBody, method);
	}
	

	//@Then("I check status code and data in response")
	public void i_check_status_code_and_data_in_response() {
	  int actualStatusCode = response.statusCode();
	  System.out.println(actualStatusCode);
	  Assert.assertEquals(actualStatusCode, 200);
	  String responseBody = response.body();
	  //System.out.println(responseBody);
	  if (responseBody != "") {
			JsonUtils jsonUils = new JsonUtils();
			String actualTotalPage = jsonUils.getValueByKey(responseBody, "total_pages");
			String actualTotal = jsonUils.getValueByKey(responseBody, "total");
			String data = jsonUils.getValueByKey(responseBody, "data");
			for(users user : jsonUils.getValuesAsArray(data)) {
				System.out.println(user.getFirstName());
				//System.out.println(user.getEmail());
			}
			Assert.assertEquals(actualTotalPage, "2");
			Assert.assertEquals(actualTotal, "12");
		} else {
			Assert.assertFalse(false);
		}
	}
}

