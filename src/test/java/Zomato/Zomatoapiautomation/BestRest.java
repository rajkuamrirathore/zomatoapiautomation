package Zomato.Zomatoapiautomation;
	import io.restassured.RestAssured;
	import static io.restassured.RestAssured.*;

import org.testng.Assert;
import org.testng.annotations.Test;

import Data.CommonData;
import io.restassured.path.json.JsonPath;

	public class BestRest {
		String response;
		JsonPath res;
		@Test(priority =0)
		public void Restaurent()
		{		
		 response = given().spec(CommonData.req).queryParams("entity_id", "4")
				 .queryParams("entity_type", "city")
				 .queryParams("count", "10")
				 .queryParams("establishment_type", "31")
				. queryParams("sort", "cost")
				.queryParams("order", "asc").when().
				get("api/v2.1/search").then().statusCode(200).extract().response().asString();
		 System.out.println(response);
			res = new JsonPath(response);
		}
		
		@Test(priority =1)
		public void Resultfound()
		{
			int actualresult = res.getInt("results_found");
			int expectedresult = 857;
			Assert.assertEquals(actualresult, expectedresult);
		}
		
		@Test(priority =2)
		public void restarentsize()
		{
			int actualresult = res.getInt("restaurants.size()");
			int expectedresult = 87;
			if(actualresult == expectedresult )
			
		System.out.println("the value of the restauent compared is equal");
			
			else
			
				System.out.println("the value of the restauent compared is not  equal");
		}
	}

