package Data;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class CommonData {
	
public static 	RequestSpecification req= new RequestSpecBuilder().setBaseUri("https://developers.zomato.com").
			addParam("apikey","187ed0a553da7fc1661807b40132e704").build();

}
