package stepdefinitions.CreateUserApi;

import java.net.http.HttpResponse;

import org.testng.Assert;

import common.JsonUtils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CheckCreateSuccessfullySteps {
	HttpResponse<String> response = null;

	@Then("I validate status code and name and job")
	public void i_validate_status_code_and_name_and_job() {
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
