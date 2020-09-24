package Zomato.Zomatoapiautomation;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import Data.CommonData;

public class GetCategories {
	int size, id;
	String name;
	JsonPath js;
	@Test(priority =1)
	public void categories()
	{
	String response = 	given().spec(CommonData.req).
	when().get("api/v2.1/categories").then().log().all().statusCode(200).extract().response().asString();


	 js = new JsonPath(response);
	 size = js.getInt("categories.size()");
	
	HashMap<Integer,String> idname= new HashMap<Integer,String>();
	// putting the values in hashmap
	for(int i=0;i<size;i++)
	{
	     id = js.get("categories["+i+"].categories.id");
		 name = js.get("categories["+i+"].categories.name");
		idname.put(id, name);  
	}
	
	// printing the key n value
	for(Map.Entry hm :idname.entrySet())
	{
		System.out.println(hm.getKey()+ "   "+hm.getValue());
	}
}
	
	@Test(priority =2)
	public void validatesize()
	{
		int expectedsize = 13;
		Assert.assertEquals(size, expectedsize);
	}
	
	@Test(priority =3)
	public void catname()
	{
		for(int i=0;i<size;i++)
		{
			name = js.get("categories["+i+"].categories.name");
			if(name.equalsIgnoreCase("Pocket Friendly Delivery"))
			{
				id = js.get("categories["+i+"].categories.id");
			}
			
		}
		Assert.assertEquals(id, 13);
	}
	
}
