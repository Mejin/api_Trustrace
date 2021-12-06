package com.api.qa.test;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class apiValidationTest extends apiValidationMain {

	public apiValidationTest() {
		super();
	}

	
	@DataProvider
	public Object[][] getApiData()
	{
		Object data[][] = apiValidationMain.testDataInitialization();
		return data;
		
	}

	
	@Test(priority =1,dataProvider ="getApiData")
	public void validatePostApiResponseCode(String apiEndpoint,String postPayload,String apiResponsecode, String Method) {
		
	String payLoad=	apiValidationMain.readPayload(postPayload);
	
	Response Response=	given().when().body(payLoad).log().all().post(apiEndpoint);
	
	Assert.assertEquals(Response.statusCode(), 201);
		
	}
}
