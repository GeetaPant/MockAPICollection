package com.qa.fakestore.api.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class getProductsTest extends BaseTest{
@Test
public void getProductTest() {
	Response response =restClient.get(BASE_URL_FAKESTOREAPI,"/products", null, null, AuthType.NO_AUTH,ContentType.JSON);
	Assert.assertEquals(response.statusCode(), 200);
} 
@Test
public void getProductLimitTest() {
	Map<String, String> queryParams = Map.of("limit","5");
	Response response =restClient.get(BASE_URL_FAKESTOREAPI,"/products", queryParams, null, AuthType.NO_AUTH,ContentType.JSON);
	Assert.assertEquals(response.statusCode(), 200);
}
}
