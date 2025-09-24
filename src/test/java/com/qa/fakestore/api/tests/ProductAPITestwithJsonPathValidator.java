package com.qa.fakestore.api.tests;

import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.utils.JsonPathValidatiorUtil;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ProductAPITestwithJsonPathValidator  extends BaseTest{
	
	@Test
	public void getProductTest() {
		Response response =restClient.get(BASE_URL_FAKESTOREAPI,"/products", null, null, AuthType.NO_AUTH,ContentType.JSON);
		Assert.assertEquals(response.statusCode(), 200);
		List<Number> prices =JsonPathValidatiorUtil.readList(response, "$[?(@.price >100)].price");
		System.out.println(prices);
		List<Map<String,Object>> prods=JsonPathValidatiorUtil.readListOfMaps(response, "$[?((@.category == 'jewelery') && (@.price <20))].['title','id']");
		System.out.println(prods);
		
		for(Map<String, Object> p : prods) {
			String heads = (String)p.get("title");
			System.out.println(heads);
			Number IDs = (Number)p.get("id");
			System.out.println(IDs);
			Number prc = (Number)p.get("price");
			System.out.println(prc);
			System.out.println("****************");
		}
	System.out.println("------------------------");
	Double minPrice = JsonPathValidatiorUtil.read(response, "min($[*].price)");
	System.out.println(minPrice);
	
	}

}
