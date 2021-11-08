import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.testng.annotations.Test;

public class ResponseUtils{
	

	protected static final String BASE_ENDPOINT = "https://api.github.com";
	CloseableHttpClient client;
	static CloseableHttpResponse response;
	
	@Test
	public static String getHeader(CloseableHttpResponse response2, String headerName) {

		
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
	
	
	@Test
	public void serverIsGithub() throws IOException {
		
		//Get request ------ make api url constant
			HttpGet get = new HttpGet(BASE_ENDPOINT);
		
			//Execute the get request
			response = client.execute(get);
		
			 String headerValue = ResponseUtils.getHeader(response, "Server");
			 
			 assertEquals(headerValue, "Github.com");
	}
}