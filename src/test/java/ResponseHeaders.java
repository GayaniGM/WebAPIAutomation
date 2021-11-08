import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


//There are 3 main different terms. MIME-Type, Media Type and Content-Type. Here we consider the Content-Type

public class ResponseHeaders extends BaseClass{
	

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
	public void contentTypeIsJson() throws IOException {
		
		//Get request ------ make api url constant
			HttpGet get = new HttpGet(BASE_ENDPOINT);
		//Execute the get request
			 response = client.execute(get);
		
			 Header contentType = response.getEntity().getContentType();
			
			// assertEquals(contentType.getValue(), "application/json; charset=uft-8");
			 ContentType ct = ContentType.getOrDefault(response.getEntity());
			 assertEquals(ct.getMimeType(), "application/json");
	}
	
	@Test
	public void serverIsGithub() throws IOException {
		
		//Get request ------ make api url constant
			HttpGet get = new HttpGet(BASE_ENDPOINT);
		//Execute the get request
			 response = client.execute(get);
		
			 String headerValue = getHeader(response, "Server");
			 
			 Assert.assertEquals(headerValue, "Github.com");
	}


	private String getHeader(CloseableHttpResponse response2, String headerName) {

		//Get all the headers
		Header[] headers = response.getAllHeaders();
		List<Header> httpHeaders = Arrays.asList(headers);
		String returnHeader = "";
		
		//Loop over the headers list
		for(Header header : httpHeaders) {
			if (headerName.equalsIgnoreCase(header.getName())) {
				returnHeader = header.getValue();
			}
		}
		
		//If no header found throw exception
		if (returnHeader.isEmpty()) {
			throw new RuntimeException("Didn't find the header: " + headerName);
			
		}
		
		//Return the header
		return returnHeader;
	}
	
	
}