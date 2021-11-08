import static org.testng.Assert.assertEquals;

import java.io.IOException;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Get401 extends BaseClass{
	
	
	CloseableHttpClient client;
	CloseableHttpResponse response;
	
	
	//Methods to create new connection and close existing connection before execute the tests
	@BeforeMethod
	public void setup() {
		
		//Create new client
		client = HttpClientBuilder.create().build();
	}
	
	@AfterMethod
	public void closeResource() throws IOException {
		//Close existing client and connection
		client.close();
		response.close();
		
	}
	
	//sINCE HE ENDPOINTS ARE SAME WE INTRODUCE A DATAPROVIDER TO RUN THE FOLLOWING 3 SCENARIOS
	//Therfore1 test is enough
	@DataProvider
	private Object[][] endpoints(){
		return new Object[][] {
			
			{"/user"},
			{"/user/followers"},
			{"/notifications"}
		};
	}
	
	
	@Test(dataProvider = "endpoints")
	public void userReturns401(String endpoints) throws IOException {
		
		//Get request ------ make api url constant
			HttpGet get = new HttpGet(BASE_ENDPOINT + endpoints);
		//Execute the get request
			 response = client.execute(get);
		//Response with status
			int actualStatus = response.getStatusLine().getStatusCode();
			assertEquals(actualStatus, 401);
	}
	
}