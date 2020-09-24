package Zomato.Zomatoapiautomation;

import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.Test;

import Data.CommonData;
import io.restassured.path.json.JsonPath;

public class DiailyMenu {
	int size;
	JsonPath daily;
	String response;
	@Test(priority =0)
	public void dailymenu()
	{		
	 response = given().spec(CommonData.req).queryParams("res_id", "16507624").when().
			get("api/v2.1/dailymenu").then().statusCode(200).extract().response().asString();
	 System.out.println(response);
	 
	daily = new JsonPath(response);
	 size = daily.getInt("daily_menus.size()"); }
	//System.out.println(size); }
	@Test(priority =1)
	public void dishes()
	{ int dishessize = 0;
		for(int i =0;i<size;i++)
		{
			int dailyid = daily.getInt("daily_menus["+i+"].daily_menu.daily_menu_id");
			if(dailyid == 17178059)
			{
				 dishessize = daily.getInt("daily_menus["+i+"].daily_menu.dishes.size()");
			  }
		}
		
		int expectedsize = 10;
		Assert.assertEquals(dishessize, expectedsize);
	}

	@Test(priority =2)// get the dishes name under the dish id 634206981
	public void dishesid()
	{ int dishessize;
	String dishname = null ;
		for(int i =0;i<size;i++)
		{
			int dailyid = daily.getInt("daily_menus["+i+"].daily_menu.daily_menu_id");
			if(dailyid == 17178059)
			{
				dishessize = daily.getInt("daily_menus["+i+"].daily_menu.dishes.size()");
				for(int j=0;j<dishessize;j++)
				{
					String dishid = daily.getString("daily_menus["+i+"].daily_menu.dishes["+j+"].dish.dish_id");
					
					if(dishid.equalsIgnoreCase("634206981")) {
						dishname = daily.getString("daily_menus["+i+"].daily_menu.dishes["+j+"].dish.name");
						System.out.println(dishname);}
				}
			  }
		}
		
		String expected = "Kachní paštika s brusinkami, mandlemi a cibulovou marmeládou";
		Assert.assertEquals(dishname, expected);
	}
	
	
	
@Test(priority = 3)
public void negativecase() {
	int dishessize =0;

	
		for(int i =0;i<size;i++)
		{
			int dailyid = daily.getInt("daily_menus["+i+"].daily_menu.daily_menu_id");
			if(dailyid == 17178059)
			{
				dishessize = daily.getInt("daily_menus["+i+"].daily_menu.dishes.size()");
				
	}
} 
		int expextisize = 22;
	Assert.assertEquals(dishessize, expextisize);	
}
}

