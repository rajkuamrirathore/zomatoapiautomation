package Zomato.Zomatoapiautomation;

import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.Test;

import Data.CommonData;
import io.restassured.path.json.JsonPath;

public class GetCuisines {
	JsonPath  cus;
	int size,cusid;
	String cusname;
@Test(priority =0)
public void Cuisines()
{
String response = 	given().spec(CommonData.req).queryParams("city_id","39").when().get("api/v2.1/cuisines").
			then().statusCode(200).extract().response().asString();
	System.out.println(response);
	  cus = new JsonPath(response);
	   size = cus.getInt("cuisines.size()");
}

@Test(priority =1)
public void getcuisineid()
{
	for(int i=0;i<size;i++)
	{
		
		cusname = cus.get("cuisines["+i+"].cuisine.cuisine_name");
		if(cusname.equalsIgnoreCase("Asian"))
		{
			cusid = cus.get("cuisines["+i+"].cuisine.cuisine_id");
		}
			}
	
	Assert.assertEquals(cusid, 3);
}


@Test(priority =3)
public void totalsizeodcuisine()
{
	Assert.assertEquals(size, 59);
}

}