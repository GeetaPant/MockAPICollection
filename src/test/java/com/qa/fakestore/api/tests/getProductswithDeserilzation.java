package com.qa.fakestore.api.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.pojo.Product;
import com.qa.api.utils.JsonUtils;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class getProductswithDeserilzation extends BaseTest{
	
	@Test
	public void getProductTest() {
		Response response =restClient.get(BASE_URL_FAKESTOREAPI,"/products", null, null, AuthType.NO_AUTH,ContentType.JSON);
		Assert.assertEquals(response.statusCode(), 200);
		Product[] product = JsonUtils.deserialize(response, Product[].class);
		
		for(Product p: product) {
			System.out.println("ID:  " +p.getId());
			System.out.println("Rate is :"+ p.getRating().getRate());
			System.out.println("-----------------------------------");
		}
		

}
}