import static org.testng.Assert.assertEquals;

import java.io.IOException;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Get404 extends BaseClass{
	

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
	
	@Test
	public void nonExistingUrlReturns404() throws IOException {
		
		//Get request ------ make api url constant
			HttpGet get = new HttpGet(BASE_ENDPOINT + "/nonexistingurl");
		//Execute the get request
			 response = client.execute(get);
		//Response with status
			int actualStatus = response.getStatusLine().getStatusCode();
			assertEquals(actualStatus, 404);
	}
	
	
}