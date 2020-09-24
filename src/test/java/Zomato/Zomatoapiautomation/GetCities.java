package Zomato.Zomatoapiautomation;
import static io.restassured.RestAssured.*;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import Data.CommonData;
import io.restassured.path.json.JsonPath;
public class GetCities {
	JsonPath cities;
	String response;
	@Test(priority =0)
	public void cities()
	{		
	 response = given().spec(CommonData.req).queryParams("q", "bantwal").when().
			get("api/v2.1/cities").then().statusCode(200).extract().response().asString();
	//System.out.println(response);
		
	 cities = new JsonPath(response);
	int size = cities.getInt("location_suggestions.size()");
	int id = cities.getInt("location_suggestions[0].id");
	//System.out.println(id);
	
/*	HashMap<Integer,String> idname= new HashMap<Integer,String>();
	for(int i=0;i<size;i++)
	{
	    int id = cities.get("location_suggestions["+i+"].id");
		String name = cities.get("location_suggestions["+i+"].name");
		idname.put(id, name);  
	}

	for(Map.Entry hm :idname.entrySet())
	{
		System.out.println(hm.getKey()+ "   "+hm.getValue());
	
	}*/
	}
	
	@Test(priority = 1) //validate country name of the city Bantwal
	public void countryname()
	{
		String cntryname = cities.getString("location_suggestions[0].country_name");
		String expectedcountry = "India";
		Assert.assertEquals(cntryname, expectedcountry);
	}
	@Test(priority =2)
	public void Statuscode()
	{
		String expectedcode = "success";
		String status = cities.getString("location_suggestions.status");
		System.out.println(status);
		Assert.assertEquals(status, expectedcode);
	}
	
}
