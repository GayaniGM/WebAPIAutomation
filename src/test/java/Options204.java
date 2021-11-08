import java.io.IOException;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpOptions;
import org.apache.http.impl.client.CloseableHttpClient;
import org.testng.annotations.Test;

import com.sun.tools.javac.util.Assert;

public class Options204 extends BaseClass {
	
	CloseableHttpClient client;
	static CloseableHttpResponse response;
	
	@Test
	public void optionsReturnsCorrectMethodsList() throws IOException{
		
		String header = "Access-Control-Allow-Methods";
		String expectedReply = "GET, POST, PATCH, PUT, DELETE";
		
		HttpOptions request = new HttpOptions(BASE_ENDPOINT);
		response = client.execute(request);
		
		String actualValue = ResponseUtils.getHeader(response, header);
		org.testng.Assert.assertEquals(actualValue, expectedReply);
		
	}
}